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
        // Enable ANSI processing in Windows Console
        FastTerminal.setAnsiRawMode(true);
        
        // Enter Alternate Screen Buffer (no scrollback), hide cursor, move to top-left
        System.out.print("\033[?1049h\033[?25l\033[H");
        
        int[] size = FastTerminal.getWindowSize(120, 30);
        int targetColumns = size[0];
        int targetLines = size[1];
        
        int currentCodepoint = 0x1F300; 
        int endCodepoint = 0x1FAFF; 
        
        for (int row = 1; row <= targetLines; row++) {
            // Move cursor to start of current row
            System.out.print("\033[" + row + ";1H");
            
            int currentLineCols = 0;
            StringBuilder line = new StringBuilder();
            
            while (currentLineCols < targetColumns) {
                int width = 0;
                while (width == 0) {
                    width = FastEmojis.getWidth(currentCodepoint);
                    if (width == 0) {
                        currentCodepoint++;
                        if (currentCodepoint > endCodepoint) currentCodepoint = 0x1F300;
                    }
                }
                
                if (currentLineCols + width <= targetColumns) {
                    line.append(Character.toChars(currentCodepoint));
                    currentLineCols += width;
                    
                    currentCodepoint++;
                    if (currentCodepoint > endCodepoint) currentCodepoint = 0x1F300;
                } else if (currentLineCols + 1 == targetColumns) {
                    line.append(" ");
                    currentLineCols++;
                }
            }
            // Avoid printing the very last character of the bottom-right corner 
            // if auto-wrap is on, to prevent the alternate buffer from scrolling!
            if (row == targetLines) {
                // If the last character is a 2-width emoji, we already replaced it with a space if it didn't fit.
                // We just truncate the last character of the string to avoid the wrap trigger.
                line.setLength(line.length() - 1);
            }
            System.out.print(line.toString());
        }
        
        // Block indefinitely to keep the canvas open
        try {
            System.in.read(); // Wait for user to press Enter instead of thread sleep
        } catch (Exception e) {
        }
        
        // Restore normal terminal state on exit
        System.out.print("\033[?1049l\033[?25h");
        FastTerminal.setAnsiRawMode(false);
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
