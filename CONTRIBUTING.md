# Contributing to JavaBlocks 🤝

Thanks for wanting to contribute to JavaBlocks! This guide covers how we
work at the Programming Station and what we expect from a pull request.

---

## 📖 Table of contents

- [The two rules](#-the-two-rules)
- [Getting started](#-getting-started)
- [Ways to contribute](#-ways-to-contribute)
- [New block checklist](#-new-block-checklist)
- [Code style](#-code-style)
- [Commit messages](#-commit-messages)
- [Pull requests](#-pull-requests)
- [Reporting bugs](#-reporting-bugs)
- [Suggesting features](#-suggesting-features)
- [Questions](#-questions)

---

## 🎯 The two rules

### 1. AI for examples. HI for production code.

AI is great for sketching ideas, exploring an API, or drafting an example.
It is **not** the author of production code. Every line that ships is
written and reviewed by a human.

Why this rule exists: an overconfident AI once jumped ahead and referenced
a `BlockView` class that didn't exist yet, breaking Milestone 1. The rule
prevents exactly that kind of mistake.

Practical version:

- ✅ Use AI to explore an idea or generate a snippet you then rewrite.
- ✅ Use AI to explain unfamiliar code.
- ❌ Don't paste AI output into a PR without understanding every line.
- ❌ Don't let AI make design decisions for you.

### 2. If it's not "just Java", it's not a block.

Blocks are plain Java files with a `main` method. The moment blocks need
a custom DSL, a second runtime, or a build step, the idea falls apart.

- ✅ `Math`, `System.out`, plain classes.
- ❌ External parsers, custom languages, code generation layers.

---

## 🚀 Getting started

Requirements:

- JDK 21+
- Maven 3.8+
- Git

Clone and run:

```bash
git clone https://github.com/mrmichaelchapo-del/JavaBlocks.git
cd JavaBlocks
mvn javafx:run
```

The app should start with the workspace and the **start 🚩 program**
block. Press it and confirm the console streams block output.

---

## 🛠️ Ways to contribute

- 🧱 **Add a block.** New `*Block.java` files in `blocks/`.
- 🐛 **Fix a bug.** Check the known issues in the README first.
- 📄 **Improve docs.** README, this guide, Javadoc.
- 🎨 **Improve the styling.** `src/main/resources/css/blocks.css`.
- ⚙️ **Improve the runner.** Block discovery, compilation, execution.
- 🧪 **Test things.** Try edge cases and report what breaks.

---

## ✅ New block checklist

Before opening a PR that adds a block:

- [ ] File name ends in `Block.java`
- [ ] Class name matches the file name exactly
      (`MoveStepsBlock.java` → `public class MoveStepsBlock`)
- [ ] Has a `main(String[] args)` method
- [ ] Uses `java.lang.Math` where math is needed (no import required —
      `java.lang` is auto-imported)
- [ ] `Math.abs` is used to guarantee non-negative magnitudes where it
      makes sense
- [ ] Prints useful, readable console output with emojis
- [ ] Includes the Apache 2.0 license header
- [ ] Listed in the block catalog table in `README.md`
- [ ] Compiles and runs cleanly with `mvn javafx:run`

Example skeleton:

```java
/*
 * JavaBlocks App
 * Copyright 2026 JavaBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * ...
 */
public class MyBlock {

    public static void main(String[] args) {
        System.out.println("Running block...");
    }
}
```

---

## 🎨 Code style

- **Java 21.** Use modern features where they help readability.
- **4 spaces**, no tabs.
- **Braces on the same line** for methods and control flow.
- **One class per file**, public class matches file name.
- **Comments are welcome** — especially emoji phase logs (🚩, ⚙️, ✅, 🏁).
- **Javadoc on public classes and methods.** Explain the *why*.
- **No wildcard imports.** Import what you use.
- **Prefer `Math` over hand-rolled arithmetic** for cos/sin/abs/floorMod.
- **Keep methods short.** If it's over ~40 lines, consider splitting.

---

## ✍️ Commit messages

Short, imperative, and scoped:

```
blocks: add MoveStepsBlock with Math.cos/sin
ui: move start block styling into blocks.css
runner: redirect compile output to blocks/out
docs: add CONTRIBUTING.md
fix: guard against missing blocks directory
```

Prefixes: `blocks:`, `ui:`, `runner:`, `docs:`, `fix:`, `chore:`.

Avoid "update", "changes", "stuff", or messages that don't say what changed.

---

## 🔀 Pull requests

Before opening a PR:

1. **Rebase on latest main** and resolve conflicts yourself.
2. **Run the app** with `mvn javafx:run` and press the start block.
3. **Update the docs** — README block catalog, known issues, etc.
4. **Keep PRs small.** One feature or fix per PR.
5. **Explain the why** in the description, not just the what.

A PR description should include:

- What changed and why
- Screenshots for UI changes
- Any known limitations

PRs that violate the two rules will be asked to change, not merged as-is.

---

## 🐛 Reporting bugs

Open an issue with:

- What you expected to happen
- What actually happened
- Steps to reproduce
- Console output (copy from the in-app console)
- Your OS, JDK version, and Maven version

If the bug involves a block, paste the block's source.

---

## 💡 Suggesting features

Open an issue with:

- The problem you're trying to solve
- Your proposed solution
- Alternatives you considered
- Whether it fits the "just Java" rule

Features that require a custom DSL or a second runtime are out of scope.

---

## 💬 Questions

Open a discussion or an issue tagged `question:`. There are no **STUPID!!!*** questions — the project is early alpha and things are still being figured out.

---

## 📜 License

By contributing, you agree that your contributions are licensed under the
Apache License 2.0, the same license as the project.

---

Thanks for contributing to JavaBlocks! 🧱🚀