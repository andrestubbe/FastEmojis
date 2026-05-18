# ⚡ FastEmojis API Reference

## 🛠️ Core API

### `FastEmojis.getWidth`
```java
public static int getWidth(int codepoint)
```
Resolves the logical display column-width for a given Unicode codepoint.
* **Arguments**: `codepoint` (the UTF-32 integer codepoint of the character).
* **Returns**:
  * `0`: Zero-width spacing modifiers, ZWJs, and variation selectors (VS15/VS16).
  * `1`: Standard ASCII and single-width characters.
  * `2`: Standard Emojis, Smileys, Pictographs, and CJK characters (East Asian Width Wide/Fullwidth).

---

## 🎨 Layout & UI Elements Reference

FastEmojis defines standard, cross-platform Unicode elements for high-performance terminal border and graph drawings:

### 1. Box-Drawing Characters
| Constant Name | Glyph | Codepoint | Description |
| :--- | :---: | :---: | :--- |
| `BOX_HORIZONTAL` | `─` | `U+2500` | Single horizontal line |
| `BOX_VERTICAL` | `│` | `U+2502` | Single vertical line |
| `BOX_TOP_LEFT` | `┌` | `U+250C` | Single top-left corner |
| `BOX_ROUND_TOP_LEFT` | `╭` | `U+256D` | Rounded top-left corner |
| `BOX_DOUBLE_HORIZONTAL` | `═` | `U+2550` | Double horizontal line |
| `BOX_DOUBLE_VERTICAL` | `║` | `U+2551` | Double vertical line |

### 2. Block Building Elements (Progress Bars, Graphs)
| Constant Name | Glyph | Codepoint | Description |
| :--- | :---: | :---: | :--- |
| `BLOCK_FULL` | `█` | `U+2588` | Full solid character block |
| `BLOCK_DARK` | `▓` | `U+2593` | 75% shaded block |
| `BLOCK_MEDIUM` | `▒` | `U+2592` | 50% shaded block |
| `BLOCK_LIGHT` | `░` | `U+2591` | 25% shaded block |
| `BLOCK_LEFT_1_2` | `▌` | `U+258C` | Left half-vertical block |
| `BLOCK_BOTTOM_1_2` | `▄` | `U+2584` | Bottom half-horizontal block |
