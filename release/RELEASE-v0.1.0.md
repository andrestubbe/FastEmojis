# FastEmojis v0.1.0 — Standalone Release 🚀

## 🎉 Version 0.1.0: Zero-Allocation Unicode Width Engine
**Release Date:** 2026-05-18  
**Tag:** `v0.1.0`

---

## ✨ Features

### ⚡ Zero-Allocation, Zero-GC Engine
- Operates using highly optimized procedural ranges and bitwise comparison checks to calculate East Asian Width and Emoji widths.
- Guarantees **exactly zero heap allocations** during lookup, ensuring 0% Garbage Collection overhead for demanding graphics rendering loops.

### 📏 Precise Grid Alignment
- Supports complete East Asian Width (EAW) specification rules.
- Accurately measures wide CJK characters and double-column emojis to eliminate text shifts and display corruption in TUI viewports.

### 🎨 Curated Symbol Palette
- Out-of-the-box support for premium TUI symbols, rounded single/double-line borders, diagnostic circles, and custom progress bar blocks.

---

## 📦 Installation (JitPack)

### Maven
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
        <version>v0.1.0</version>
    </dependency>
</dependencies>
```

---

## 🔧 Technical Details
- **Architecture:** Pure Java 17, 100% Platform-Independent.
- **Dependencies:** Zero external dependencies.
- **Test Coverage:** Full JUnit suite covering CJK, Emojis, Zero-width joiners, and standard ASCII.

---

## 🙏 Credits
- Part of the **FastJava Ecosystem** — *Making the JVM faster.*
