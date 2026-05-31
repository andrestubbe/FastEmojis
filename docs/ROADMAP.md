# ⚡ FastEmojis Roadmap

## 🎯 Completed Milestones (v0.1.0)
* [x] **Zero-Allocation EAW Engine**: Highly optimized range-based column measurement.
* [x] **Curated TUI constants**: Extensive constants database including rounded/double borders and status indicators.
* [x] **Universal Porting**: Extracted core engine into a standalone package.
* [x] **Unit Testing**: Robust JUnit coverage for wide and halfwidth unicode codepoints.

---

## 🚀 Active Milestones (v0.2.0)
* [ ] **Ecosystem Integration**:
  * [ ] Integrate `FastEmojis` into `FastTerminal` scene layout calculations.
  * [ ] Port `FastEmojis` to `FastGraphics` text metrics mapping.
  * [ ] Support `FastEmojis` in custom `FastUI` rich TextAreas.
* [ ] **Performance Benchmarks**:
  * [ ] Implement JMH micro-benchmarks comparing `FastEmojis.getWidth` to regex or character classification lookup methods.

---

## 🔮 Future Explorations (v1.0.0)
* [ ] **Extended Grapheme Cluster (EGC) Segmentation**:
  * [ ] Add dynamic support for complex joined emojis (e.g., family symbols, skin-tone modifiers, and country flags) that combine multiple codepoints into a single visual character.
  * [ ] Zero-allocation string segmenter using native grapheme cluster rules.
