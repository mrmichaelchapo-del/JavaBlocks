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
```
## Why plain Java?

Because the whole point is that anyone who knows Java can drop a file
into blocks/ and have a working block. No DSL to learn, no build step
to configure, no separate runtime. The block is the program.

## Compiled output

By default the compiled .class files land in the project root, because
the runner uses the in-process javac tool without a -d output
directory. Redirecting them to blocks/out/ is a planned improvement.

---

## 📚 Block catalog

### 🧱 Block Files: What it does
- 🚩 Start program (UI button) — Scans, compiles, and runs every *Block.java*.
- 🚶 Move steps MoveStepsBlock.java Moves 10 steps using Math.cos/Math.sin and Math.abs.
- 🔄 Turn degree TurnDegreeBlock.java Turns by 45° and wraps to 0–360 with Math.floorMod + Math.abs.
- 🎯 Go to sprite GoToSpriteBlock.java Jumps to another sprite's position using Math.abs on stored coords
- ✏️ Your block YourBlock.java The placeholder — write anything

<small>All math comes from java.lang.Math, which is auto-imported. No imports
needed, ever, for Math.</small>

---

# 🚩 Running blocks

Press the start 🚩 program block on the workspace. The runner:

1. Scans blocks/ for every *Block.java file.
2. Sorts them by file name (so execution order is predictable).
3. Compiles them together with the in-process javac tool.
4. Runs each class in order with the in-process java tool.
5. Streams all output into the console.

Compilation happens once, before any block runs. If compilation fails,
nothing runs and the console tells you why. Running happens on a
background thread so the UI never freezes.

Console output

Every run logs its phases:

```output
🚩 Starting all blocks...
📦 Found 4 block file(s).
⚙️ Compiling blocks...
✅ Compilation succeeded.
▶️ Running GoToSpriteBlock ...
🏁 GoToSpriteBlock finished (exit 0).
▶️ Running MoveStepsBlock ...
🏁 MoveStepsBlock finished (exit 0).
All blocks executed — done ✅ 
```