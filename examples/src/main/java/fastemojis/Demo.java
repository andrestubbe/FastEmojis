package fastemojis;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
        // Pre-compute all emojis into a chain
        List<String> emojiChain = new ArrayList<>();
        int startCp = 0x1F300; 
        int endCp = 0x1FAFF; 
        for (int cp = startCp; cp <= endCp; cp++) {
            if (FastEmojis.getWidth(cp) > 0) {
                emojiChain.add(new String(Character.toChars(cp)));
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
        
        // Render loop
        while (true) {
            int[] size = FastTerminal.getWindowSize(120, 30);
            if (size[0] != currentSize[0] || size[1] != currentSize[1]) {
                currentSize = size;
                repaint(currentSize[0], currentSize[1], emojiChain);
            }
            try {
                Thread.sleep(50); // Poll for resize
            } catch (InterruptedException e) {}
        }
    }
    
    private static void repaint(int cols, int rows, List<String> chain) {
        StringBuilder frame = new StringBuilder();
        frame.append("\033[H"); // Cursor to 1,1
        
        int idx = 0;
        for (int row = 1; row <= rows; row++) {
            frame.append("\033[").append(row).append(";1H");
            
            int currentLineCols = 0;
            while (currentLineCols < cols) {
                String emoji = chain.get(idx % chain.size());
                // Most emojis from this block are width 2, but we check to be safe
                int width = getVisualWidth(emoji);
                
                if (currentLineCols + width <= cols) {
                    frame.append(emoji);
                    currentLineCols += width;
                    idx++;
                } else if (currentLineCols + 1 == cols) {
                    // Fill 1-width gap with a solid block instead of a black space
                    frame.append(FastEmojis.BLOCK_FULL);
                    currentLineCols++;
                }
            }
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
