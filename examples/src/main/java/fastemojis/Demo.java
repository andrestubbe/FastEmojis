package fastemojis;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import fastterminal.FastTerminal;

public class Demo {

    public static void main(String[] args) {
        runEmojiWallDemo();
    }

    private static void runAlignmentDemo() {
        System.out.println("  Constant Name       | Symbol | length() | FastEmojis | Alignment Preview");
        System.out.println(" " + FastEmojis.BOX_HORIZONTAL.repeat(68));
        
        // Pick a few diverse emojis to show
        String[] samples = {
            "SMILEY", FastEmojis.SMILEY,
            "ROCKET", FastEmojis.ROCKET,
            "HEART", FastEmojis.HEART,
            "ZAP", "⚡", // ZAP is actually ⚡
            "BLOCK_FULL", FastEmojis.BLOCK_FULL,
            "BOX_DOUBLE_TOP", FastEmojis.BOX_DOUBLE_TOP_LEFT
        };
        
        for (int i = 0; i < samples.length; i += 2) {
            String name = samples[i];
            String symbol = samples[i+1];
            
            int length = symbol.length();
            int width = getVisualWidth(symbol);
            
            // Format columns
            String col1 = padRight(name, 19);
            String col2 = padRightVisual(symbol, 6);
            String col3 = padRight(String.valueOf(length), 8);
            String col4 = padRight(String.valueOf(width), 10);
            
            // Alignment preview: padding a string to exactly 10 columns
            String previewStr = symbol + " Text";
            String previewPadBad = padRight(previewStr, 12); // Broken padding
            String previewPadGood = padRightVisual(previewStr, 12); // Correct padding
            
            System.out.printf("  %s | %s | %s | %s | [ %s ] vs [ %s ]%n", 
                col1, col2, col3, col4, previewPadBad, previewPadGood);
        }
    }

    private static void runBenchmark() {
        // Create a massive string with 100,000 emojis and text
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20000; i++) {
            sb.append("Hello ").append(FastEmojis.ROCKET).append(" World ").append(FastEmojis.SMILE);
        }
        String testString = sb.toString();
        
        System.out.println("  String length: " + testString.length() + " chars.");
        
        // Warmup
        for (int i = 0; i < 100; i++) {
            getVisualWidth(testString);
        }
        
        // Benchmark
        long start = System.nanoTime();
        int totalWidth = getVisualWidth(testString);
        long end = System.nanoTime();
        
        long nanos = end - start;
        double millis = nanos / 1_000_000.0;
        
        System.out.println("  Visual Width calculated: " + totalWidth + " columns.");
        System.out.printf("  Time taken: %.3f ms%n", millis);
        System.out.printf("  Throughput: %.2f GB/s%n", (testString.length() * 2.0) / nanos);
    }
    
    private static void runEmojiWallDemo() {
        // Pre-compute core emojis into a massive chain.
        // We use extremely strictly curated blocks of pure graphical emojis 
        // (Faces, Animals, Food) that are guaranteed to be rendered as 
        // 2 columns wide by the Windows Terminal Font Engine.
        List<String> emojiChain = new ArrayList<>();
        int[][] blocks = {
            {0x1F600, 0x1F637}, // Classic round faces
            {0x1F641, 0x1F644}, // Slight frown, smile, upside-down, rolling eyes
            {0x1F910, 0x1F917}, // Zipper mouth, Nerd, Thinking, Hugging
            {0x1F920, 0x1F925}, // Cowboy, Clown, ROFL, Drooling, Lying
            {0x1F927, 0x1F92F}, // Sneezing to Exploding head (SKIPS 1F926 Facepalm!)
            {0x1F970, 0x1F976}, // Hearts, Yawning to Cold face (SKIPS 1F977 Ninja!)
            {0x1F978, 0x1F97A}  // Disguised face, Pleading
        };
        
        for (int[] block : blocks) {
            for (int cp = block[0]; cp <= block[1]; cp++) {
                if (FastEmojis.getWidth(cp) == 2) { 
                    emojiChain.add(new String(Character.toChars(cp)));
                }
            }
        }
        
        FastTerminal.setAnsiRawMode(true);
        // Alt buffer, hide cursor, disable auto-wrap
        System.out.print("\033[?1049h\033[?25l\033[?7l");
        
        // Listen for exit in background
        Thread inputThread = new Thread(() -> {
            try {
                System.in.read();
                // Restore terminal
                System.out.print("\033[?1049l\033[?25h\033[?7h");
                FastTerminal.setAnsiRawMode(false);
                System.exit(0);
            } catch (Exception e) {}
        });
        inputThread.setDaemon(true);
        inputThread.start();
        
        int[] currentSize = new int[]{-1, -1};
        Random random = new Random();
        
        // Render loop
        while (true) {
            int[] size = FastTerminal.getWindowSize(120, 30);
            boolean resized = (size[0] != currentSize[0] || size[1] != currentSize[1]);
            
            if (resized) {
                currentSize = size;
                // Full repaint of the audience on resize
                repaintAudience(currentSize[0], currentSize[1], emojiChain, random);
            } else {
                // Just twinkle a few random faces in the audience
                // 10 faces change state every 50ms
                twinkleAudience(currentSize[0], currentSize[1], emojiChain, random, 10);
            }
            
            try {
                Thread.sleep(50); // 20 FPS Update rate
            } catch (InterruptedException e) {}
        }
    }
    
    private static void repaintAudience(int cols, int rows, List<String> palette, Random random) {
        StringBuilder frame = new StringBuilder();
        frame.append("\033[H"); // Cursor to 1,1
        
        int emojisPerRow = cols / 2;
        
        for (int row = 1; row <= rows; row++) {
            frame.append("\033[").append(row).append(";1H");
            
            // Da wir jetzt eine reine Liste aus verifizierten 2-Spalten-Emojis haben,
            // können wir die exakte Anzahl mathematisch perfekt berechnen!
            // Das behebt den Bug des Windows-Terminals am rechten Rand komplett,
            // da wir nicht mehr versehentlich "überschreiben".
            for (int i = 0; i < emojisPerRow; i++) {
                String emoji = palette.get(random.nextInt(palette.size()));
                frame.append(emoji);
            }
        }
        System.out.print(frame.toString());
    }
    
    private static void twinkleAudience(int cols, int rows, List<String> palette, Random random, int count) {
        StringBuilder frame = new StringBuilder();
        int maxCellX = cols / 2;
        if (maxCellX <= 0 || rows <= 0) return;
        
        for (int i = 0; i < count; i++) {
            int cellX = random.nextInt(maxCellX);
            int row = random.nextInt(rows) + 1; // 1-indexed row
            int col = cellX * 2 + 1;            // 1-indexed column, step by 2
            
            String emoji = palette.get(random.nextInt(palette.size()));
            
            frame.append("\033[").append(row).append(";").append(col).append("H");
            frame.append(emoji);
        }
        System.out.print(frame.toString());
    }
    
    // Calculate true visual width using FastEmojis
    private static int getVisualWidth(String str) {
        int width = 0;
        for (int i = 0; i < str.length(); ) {
            int cp = str.codePointAt(i);
            width += FastEmojis.getWidth(cp);
            i += Character.charCount(cp);
        }
        return width;
    }
    
    // Broken padding (using java string length)
    private static String padRight(String s, int n) {
        int pad = n - s.length();
        if (pad <= 0) return s;
        return s + " ".repeat(pad);
    }
    
    // Perfect padding (using FastEmojis width)
    private static String padRightVisual(String s, int n) {
        int visualWidth = getVisualWidth(s);
        int pad = n - visualWidth;
        if (pad <= 0) return s;
        return s + " ".repeat(pad);
    }
}
