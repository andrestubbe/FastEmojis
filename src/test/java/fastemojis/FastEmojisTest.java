package fastemojis;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FastEmojisTest {

    @Test
    public void testStandardCharacterWidths() {
        // Standard single-width printable characters
        assertEquals(1, FastEmojis.getWidth('A'));
        assertEquals(1, FastEmojis.getWidth('1'));
        assertEquals(1, FastEmojis.getWidth(' '));
    }

    @Test
    public void testEmojiWidths() {
        // Explicit double-width BMP emojis
        assertEquals(2, FastEmojis.getWidth("⚡".codePointAt(0)));
        assertEquals(2, FastEmojis.getWidth("⚙".codePointAt(0)));
        assertEquals(2, FastEmojis.getWidth("⭐".codePointAt(0)));

        // Standard plane emojis (Smileys, etc.)
        assertEquals(2, FastEmojis.getWidth("😊".codePointAt(0)));
        assertEquals(2, FastEmojis.getWidth("😀".codePointAt(0)));
        assertEquals(2, FastEmojis.getWidth("🤖".codePointAt(0)));
        assertEquals(2, FastEmojis.getWidth("🚀".codePointAt(0)));
    }

    @Test
    public void testEastAsianCharacterWidths() {
        // CJK characters
        assertEquals(2, FastEmojis.getWidth("汉".codePointAt(0)));
        assertEquals(2, FastEmojis.getWidth("字".codePointAt(0)));
        
        // Hiragana / Katakana
        assertEquals(2, FastEmojis.getWidth("あ".codePointAt(0)));
        assertEquals(2, FastEmojis.getWidth("コ".codePointAt(0)));
    }

    @Test
    public void testZeroWidthModifiers() {
        // Combining Marks and Variation Selectors
        assertEquals(0, FastEmojis.getWidth(0xFE0F)); // VS16
        assertEquals(0, FastEmojis.getWidth(0x200D)); // ZWJ
    }
}
