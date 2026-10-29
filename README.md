# JavaBlocks 🧱

Scratch-like visual programming IDE, 100% Java.

JavaBlocks is a block-based programming environment built with Java 21 and
JavaFX 21. Blocks are plain Java files — each one gets compiled and run by
the Programming Station's start block, with output streamed straight into
an in-app console.

---

## 📖 Table of contents

- [Status](#-status)
- [Milestones](#-milestones)
- [How blocks work](#-how-blocks-work)
- [Block catalog](#-block-catalog)
- [Running blocks](#-running-blocks)
- [The Programming Station](#-the-programming-station)
- [Requirements](#️-requirements)
- [Build & run](#️-build--run)
- [Project layout](#-project-layout)
- [Styling](#-styling)
- [Design principles](#-design-principles)
- [Roadmap](#-roadmap)
- [Known issues](#-known-issues)
- [Contributing](#-contributing)
- [Screenshots](#-screenshots)
- [License](#-license)

---

## ✨ Status

**Early alpha.** Expect rough edges, missing pieces, and occasional
downtime — the server is under maintenance from time to time, so outages
are expected. The local IDE keeps working while the server is down.

What works today:

- ✅ Free-positioning workspace with a draggable start block
- ✅ In-app console with streamed block output
- ✅ Automatic block discovery, compilation, and execution
- ✅ `Math`-powered move and turn blocks
- ✅ JavaFX CSS styling (no HTML)

What doesn't work yet:

- ❌ Block snapping and connection logic
- ❌ A real sprite/backdrop system
- ❌ Project save/load
- ❌ Block palette (blocks are files, not a sidebar)
- ❌ Server-backed features (currently under maintenance)

---

## 🚀 Milestones

- **Milestone 1** — workspace, start block, console, block discovery ✅
- **Milestone 2** — drag feel, snapping, more blocks 🚧
- **Milestone 3** — sprite system, backdrops, project saving 📋

Each milestone ships something usable on its own. Nothing gets merged
that breaks the previous milestone's demo.

---

## 🧱 How blocks work

Every block is a Java file in `blocks/`, named `<BlockName>Block.java`:


## Rules:

- The file name **must** end in `Block.java`.
- The class name must match the file name
  (`MoveStepsBlock.java` → `public class MoveStepsBlock`).
- It needs a `main(String[] args)` method — that's what the runner executes.
- It's just Java. Write whatever you want.

The placeholder block says it best:

```java
/*
 *
 * Write something, it's just Java.
*/

## Why plain Java?

Because the whole point is that anyone who knows Java can drop a file
into blocks/ and have a working block. No DSL to learn, no build step
to configure, no separate runtime. The block is the program.

## Compiled output

By default the compiled .class files land in the project root, because
the runner uses the in-process javac tool without a -d output
directory. Redirecting them to blocks/out/ is a planned improvement.