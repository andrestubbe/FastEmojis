# ⚡ FastEmojis

**High-Performance, Zero-Allocation UTF-8 Unicode East Asian Width (EAW) & Emoji Width Engine for Java**

FastEmojis is a zero-dependency, ultra-lightweight library designed to solve the single biggest headache in terminal rendering, text rendering, and text area components: **accurate column-width measurement of Unicode symbols, East Asian CJK characters, and Emojis**.

---

## 🎯 Core Features

* **⚡ Ultra-Fast, Zero-Allocation Engine**: Operates procedurally using highly optimized range checks and bitwise comparisons. Perfect for high-frequency 120+ FPS renders.
* **📏 Precise Column-Width Resolution**: Implements complete **East Asian Width (EAW)** rules (Halfwidth vs. Fullwidth, Wide characters, Zero-width modifiers).
* **😀 Premium TUI Symbol Palette**: Contains a massive database of curated static constant symbols (Smileys, Status badges, File types, Security, Weather, and Box Drawing blocks) to avoid typos and standardize CLI design.
* **🌐 100% Standalone & Lightweight**: Zero external dependencies, pure Java 17, and fully compatible with both Terminal/Console and Swing/AWT graphics pipelines.

---

## 🚀 Quick Start

### 1. Dynamic Width Calculations
Pass any UTF-32 codepoint to `FastEmojis.getWidth` to obtain its visual grid width (0, 1, or 2 columns):

```java
import fastemojis.FastEmojis;

int cp1 = "A".codePointAt(0);
int width1 = FastEmojis.getWidth(cp1); // Returns 1

int cp2 = "⚡".codePointAt(0);
int width2 = FastEmojis.getWidth(cp2); // Returns 2

int cp3 = "汉".codePointAt(0);
int width3 = FastEmojis.getWidth(cp3); // Returns 2

int vs16 = 0xFE0F; // Variation Selector 16
int width4 = FastEmojis.getWidth(vs16); // Returns 0 (zero-width modifier)
```

### 2. Standardized UI Symbols
Use the curated constants directly in your UI code to design stunning dashboards:

```java
import fastemojis.FastEmojis;

String title = FastEmojis.LIGHTNING + " SYSTEM MONITOR " + FastEmojis.LIGHTNING;
System.out.println(title); // Prints: ⚡ SYSTEM MONITOR ⚡
```

---

## 🎨 Curated Symbol Groups

FastEmojis organizes premium double-width symbols into type-safe constants:
* **Diagnostics**: `LIGHTNING` (⚡), `TARGET` (🎯), `GEAR` (⚙️), `BUG` (🐛), `FIRE` (🔥), `ROCKET` (🚀)
* **Smileys**: `SMILE` (😊), `WINK` (😉), `COOL` (😎), `CELEBRATE` (🥳), `THINKING` (🤔), `ROBOT` (🤖)
* **Status**: `SUCCESS_GREEN` (🟢), `ERROR_RED` (🔴), `WARN_YELLOW` (🟡), `CRITICAL` (🚨), `CHECK` (✅)
* **Box-Drawing Controls**: Comprehensive rounded (`╭──╮`), double (`╔══╗`), single (`┌──┐`), and block building elements.

---

## 📦 Maven Integration (via JitPack)

To add `FastEmojis` to your own project, add the JitPack repository and the dependency inside your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.andrestubbe</groupId>
        <artifactId>FastEmojis</artifactId>
        <version>0.1.0</version>
    </dependency>
</dependencies>
```

---

## ⚖️ License
This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.
