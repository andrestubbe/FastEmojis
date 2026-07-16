package fastemojis;

/**
 * Universal Emoji, Glyphs, and Unicode Column-Width Measurement Engine.
 * Provides premium console glyph constants and implements robust East Asian Width (EAW)
 * and Emoji width calculation for pixel-perfect TUI layouts under Windows Terminal and other emulators.
 */
public class FastEmojis {

    // ============================================================================
    // Premium TUI Glyph Constants
    // ============================================================================

    // Emojis & Status Icons (Double-Width, 2 Columns)
    
    // Core Dashboard & Diagnostics
    public static String LIGHTNING = "⚡";
    public static String TARGET = "🎯";
    public static String PALETTE = "🎨";
    public static String CHART = "📊";
    public static String PLUG = "🔌";
    public static String SPARKLES = "✨";
    public static String GEAR = "⚙️";
    public static String BUG = "🐛";
    public static String FIRE = "🔥";
    public static String ROCKET = "🚀";

    // Smileys & Emoticons (Double-Width, 2 Columns)
    public static String SMILE = "😊";
    public static String SMILEY = "😀";
    public static String GRIN = "😁";
    public static String WINK = "😉";
    public static String COOL = "😎";
    public static String LAUGH = "😂";
    public static String CELEBRATE = "🥳";
    public static String THINKING = "🤔";
    public static String SHOCKED = "😱";
    public static String SAD = "😢";
    public static String CRY = "😭";
    public static String ANGRY = "😠";
    public static String SWEAT = "😅";
    public static String ROBOT = "🤖";
    public static String ALIEN = "👽";
    public static String GHOST = "👻";
    public static String POOP = "💩";

    // System Components & Storage
    public static String TERMINAL = "💻";
    public static String SERVER = "🖥️";
    public static String FLOPPY = "💾";
    public static String HARD_DISK = "💽";
    public static String KEYBOARD = "⌨️";
    public static String MOUSE = "🖱️";
    public static String PRINTER = "🖨️";

    // File Management
    public static String FOLDER = "📁";
    public static String FILE = "📄";
    public static String ARCHIVE = "📦";
    public static String TRASH = "🗑️";

    // Security & Access
    public static String LOCK = "🔒";
    public static String KEY = "🔑";
    public static String SHIELD = "🛡️";

    // Feedback, Alerts & Status Circles
    public static String SUCCESS_GREEN = "🟢";
    public static String ERROR_RED = "🔴";
    public static String INFO_BLUE = "🔵";
    public static String WARN_YELLOW = "🟡";
    public static String OK_HEART = "💚";
    public static String CHECK = "✅";
    public static String WARN = "⚠️";
    public static String CRITICAL = "🚨";
    public static String SKULL = "☠️";

    // Tools & Engineering
    public static String WRENCH = "🔧";
    public static String HAMMER = "🔨";
    public static String TOOLS = "🛠️";
    public static String CONSTRUCT = "🚧";
    public static String MEASURING_TAPE = "📏";

    // Interaction & Collaboration
    public static String SPEECH_BUBBLE = "💬";
    public static String ENVELOPE = "✉️";
    public static String LINK = "🔗";
    public static String GLOBE = "🌐";
    public static String WIFI = "📶";

    // Time & Metrics
    public static String ALARM_CLOCK = "⏰";
    public static String HOURGLASS = "⌛";
    public static String HOURGLASS_FLOW = "⏳";
    public static String CALENDAR = "📅";
    public static String SEARCH = "🔍";

    // Gamification & Rewards
    public static String TROPHY = "🏆";
    public static String MEDAL = "🏅";
    public static String STAR = "⭐";
    public static String TADA = "🎉";
    public static String HEART = "❤️";
    public static String LIGHTBULB = "💡";

    // Weather & Elements
    public static String SUN = "☀️";
    public static String CLOUD = "☁️";
    public static String RAIN = "🌧️";
    public static String SNOW = "❄️";
    public static String WIND = "🍃";
    public static String TREE = "🌲";

    // Premium UI Symbols (Single-Width, 1 Column)
    public static String INFO = "ℹ";
    public static String CROSS = "✘";
    public static String CHECKMARK = "✔";
    public static String GLYPH_STAR = "✦";
    public static String GLYPH_DOT = "•";
    public static String GLYPH_RIGHT = "›";
    public static String GLYPH_LEFT = "‹";

    // Box Drawing Elements (Single-Width, 1 Column)
    public static String BOX_HORIZONTAL = "─";
    public static String BOX_VERTICAL = "│";
    public static String BOX_TOP_LEFT = "┌";
    public static String BOX_TOP_RIGHT = "┐";
    public static String BOX_BOTTOM_LEFT = "└";
    public static String BOX_BOTTOM_RIGHT = "┘";
    
    // Rounded Corners Box Drawing
    public static String BOX_ROUND_TOP_LEFT = "╭";
    public static String BOX_ROUND_TOP_RIGHT = "╮";
    public static String BOX_ROUND_BOTTOM_LEFT = "╰";
    public static String BOX_ROUND_BOTTOM_RIGHT = "╯";

    // Double Borders Box Drawing
    public static String BOX_DOUBLE_HORIZONTAL = "═";
    public static String BOX_DOUBLE_VERTICAL = "║";
    public static String BOX_DOUBLE_TOP_LEFT = "╔";
    public static String BOX_DOUBLE_TOP_RIGHT = "╗";
    public static String BOX_DOUBLE_BOTTOM_LEFT = "╚";
    public static String BOX_DOUBLE_BOTTOM_RIGHT = "╝";

    // Block Elements (Single-Width, 1 Column)
    public static String BLOCK_FULL = "█";
    public static String BLOCK_DARK = "▓";
    public static String BLOCK_MEDIUM = "▒";
    public static String BLOCK_LIGHT = "░";
    
    // Partial vertical blocks
    public static String BLOCK_LEFT_7_8 = "▉";
    public static String BLOCK_LEFT_3_4 = "▊";
    public static String BLOCK_LEFT_5_8 = "▋";
    public static String BLOCK_LEFT_1_2 = "▌";
    public static String BLOCK_LEFT_3_8 = "▍";
    public static String BLOCK_LEFT_1_4 = "▎";
    public static String BLOCK_LEFT_1_8 = "▏";

    // Partial horizontal blocks
    public static String BLOCK_BOTTOM_1_8 = " ";
    public static String BLOCK_BOTTOM_1_4 = "▂";
    public static String BLOCK_BOTTOM_3_8 = "▃";
    public static String BLOCK_BOTTOM_1_2 = "▄";
    public static String BLOCK_BOTTOM_5_8 = "▅";
    public static String BLOCK_BOTTOM_3_4 = "▆";
    public static String BLOCK_BOTTOM_7_8 = "▇";

    // ============================================================================
    // Fallback Mode (For Swing / Minimal Terminal Support)
    // ============================================================================

    public static void enableFallbackMode() {
        // Double-width icons replaced with [X]
        FLOPPY = "[S]";
        HARD_DISK = "[D]";
        FOLDER = "[O]";
        FILE = "[N]";
        ARCHIVE = "[L]";
        PALETTE = "[C]";
        KEYBOARD = "[K]";
        TARGET = "(@)";
        CHECK = "[+]";
        TRASH = "[-]";
        SEARCH = "(?)";
        TOOLS = "[T]";
        
        // Single width symbols
        CHECKMARK = "v";
        CROSS = "x";
        GLYPH_RIGHT = ">";
        GLYPH_LEFT = "<";
        GLYPH_STAR = "*";
        GLYPH_DOT = ".";
    }

    // ============================================================================
    // Comprehensive Unicode East Asian Width (EAW) and Emoji Width Calculation
    // ============================================================================

    /**
     * Determines whether a Unicode codepoint renders as a double-width (2-column) emoji
     * or East Asian character, a single-width character, or a zero-width modifier.
     */
    public static int getWidth(int codepoint) {
        // 1. Zero-width characters, combining marks, and modifiers
        if (codepoint == 0x200B || // Zero-width space
            codepoint == 0x200D || // Zero-width joiner (ZWJ)
            codepoint == 0xFE0F || // Variation Selector 16 (VS16, forces emoji rendering)
            codepoint == 0xFE0E || // Variation Selector 15 (VS15, forces text rendering)
            (codepoint >= 0x0300 && codepoint <= 0x036F) || // Combining Diacritical Marks
            (codepoint >= 0x1DC0 && codepoint <= 0x1DFF) || // Combining Diacritical Marks Supplement
            (codepoint >= 0x20D0 && codepoint <= 0x20FF) || // Combining Diacritical Marks for Symbols
            (codepoint >= 0xFE20 && codepoint <= 0xFE2F)) { // Combining Half Marks
            return 0;
        }

        // 2. Explicit double-width terminal icons and status emojis
        if (codepoint == 0x26A1 || // ⚡
            codepoint == 0x26A0 || // ⚠️
            codepoint == 0x2699 || // ⚙️
            codepoint == 0x2705 || // ✅
            codepoint == 0x2728 || // ✨
            codepoint == 0x270F || // ✏️
            codepoint == 0x2709 || // ✉️
            codepoint == 0x270C || // ✌️
            codepoint == 0x2714 || // ✔️ (emoji version)
            codepoint == 0x2716 || // ✖️ (emoji version)
            codepoint == 0x274C || // ❌
            codepoint == 0x274E || // ❎
            codepoint == 0x2139 || // ℹ️ (emoji version)
            codepoint == 0x2611 || // ☑️
            codepoint == 0x2615 || // ☕
            codepoint == 0x26FD || // ⛽
            codepoint == 0x26C5 || // ⛅
            codepoint == 0x260E || // ☎️
            codepoint == 0x2764 || // ❤️
            codepoint == 0x2B50 || // ⭐
            codepoint == 0x23F0 || // ⏰
            codepoint == 0x231B || // ⌛
            codepoint == 0x23F3 || // ⏳
            codepoint == 0x2600 || // ☀️
            codepoint == 0x2601 || // ☁️
            codepoint == 0x2744 || // ❄️
            codepoint == 0x2702 || // ✂️
            codepoint == 0x2708 || // ✈️
            codepoint == 0x2620 || // ☠️
            codepoint == 0x2693 || // ⚓
            codepoint == 0x26F2 || // ⛲
            codepoint == 0x26F5 || // ⛵
            codepoint == 0x26BD || // ⚽
            codepoint == 0x26BE || // ⚾
            codepoint == 0x26F3) { // ⛳
            return 2;
        }

        // 3. East Asian Wide (W) and Fullwidth (F) Ranges
        // CJK Unified Ideographs, Hiragana, Katakana, Hangul Syllables, etc.
        if ((codepoint >= 0x1100 && codepoint <= 0x115F) || // Hangul Jamo
            (codepoint >= 0x2E80 && codepoint <= 0x303E) || // CJK Radicals & Symbols
            (codepoint >= 0x3040 && codepoint <= 0x309F) || // Hiragana
            (codepoint >= 0x30A0 && codepoint <= 0x30FF) || // Katakana
            (codepoint >= 0x3100 && codepoint <= 0x312F) || // Bopomofo
            (codepoint >= 0x31A0 && codepoint <= 0x31BF) || // Bopomofo Extended
            (codepoint >= 0x31C0 && codepoint <= 0x31EF) || // CJK Strokes
            (codepoint >= 0x3200 && codepoint <= 0x4DBF) || // Enclosed CJK, CJK Extension A
            (codepoint >= 0x4E00 && codepoint <= 0x9FFF) || // CJK Unified Ideographs
            (codepoint >= 0xAC00 && codepoint <= 0xD7A3) || // Hangul Syllables
            (codepoint >= 0xF900 && codepoint <= 0xFAFF) || // CJK Compatibility Ideographs
            (codepoint >= 0xFE10 && codepoint <= 0xFE19) || // Vertical forms
            (codepoint >= 0xFE30 && codepoint <= 0xFE6F) || // CJK Compatibility Forms
            (codepoint >= 0xFF01 && codepoint <= 0xFF60) || // Fullwidth Forms
            (codepoint >= 0xFFE0 && codepoint <= 0xFFE6) || // Fullwidth Symbol Variants
            (codepoint >= 0x20000 && codepoint <= 0x2FFFD) || // CJK Extension B/C/D/E/F
            (codepoint >= 0x30000 && codepoint <= 0x3FFFD)) {  // CJK Extension G/H
            return 2;
        }

        // 4. Standard Emoji Ranges (Misc Symbols and Pictographs, Emoticons, Transport, etc.)
        if ((codepoint >= 0x1F000 && codepoint <= 0x1F9FF) || // Standard Emojis
            (codepoint >= 0x1FA00 && codepoint <= 0x1FAFF) || // Chess Symbols, Pictographs
            (codepoint >= 0x1FC00 && codepoint <= 0x1FFFD)) {  // Supplementary Emojis
            return 2;
        }

        // 5. Default fallback to standard single column width
        return 1;
    }
}
