/*
 * JavaBlocks App
 * Copyright 2026 JavaBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This product includes software developed by JavaBlocks.
 */
package app.javablocks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaBlocks — Scratch-like visual programming IDE, 100% Java.
 *
 * <p>Milestone 1: a single "start" block on the workspace. Pressing it
 * discovers every {@code *Block.java} file in {@code blocks/}, compiles
 * them with the in-process javac tool, then executes each in order and
 * streams the output into a console.</p>
 */
public class Main extends Application {

    /** Where block sources live, relative to the working directory. */
    private static final Path BLOCKS_DIR = Paths.get("blocks");

    /** Every block source file must end with this suffix. */
    private static final String BLOCK_SUFFIX = "Block.java";

    private final TextArea console = new TextArea();

    @Override
    public void start(Stage stage) {
        // 🎯 workspace: plain Pane = free positioning, absolute layout
        Pane workspace = new Pane();
        workspace.setPrefSize(1000, 700);

        // 🧱 Milestone 1: the start block
        Button startBlock = new Button("start 🚩 program");
        startBlock.setLayoutX(120);
        startBlock.setLayoutY(120);
        startBlock.setStyle(
                "-fx-background-color: #ffd84d;"
                + "-fx-border-color: #b89b1f;"
                + "-fx-border-radius: 8;"
                + "-fx-background-radius: 8;"
                + "-fx-padding: 8 16 8 16;"
                + "-fx-font-size: 14px;"
                + "-fx-font-weight: bold;"
        );
        startBlock.setOnAction(e -> runAllBlocks());
        workspace.getChildren().add(startBlock);

        // 🖥️ console for block output
        console.setEditable(false);
        console.setStyle("-fx-font-family: 'monospace'; -fx-font-size: 12px;");
        console.setPrefRowCount(12);

        BorderPane root = new BorderPane();
        root.setCenter(workspace);
        root.setBottom(new VBox(new Label("Console"), console));

        Scene scene = new Scene(root, 1000, 700);

        stage.setTitle("JavaBlocks");
        stage.setScene(scene);
        stage.setMinWidth(640);
        stage.setMinHeight(480);
        stage.show();
    }

    /** Discover, compile, and run every *Block.java in blocks/. */
    private void runAllBlocks() {
        console.clear();
        log("🚩 Starting all blocks...");

        if (!Files.isDirectory(BLOCKS_DIR)) {
            log("❌ Blocks directory not found: " + BLOCKS_DIR.toAbsolutePath());
            return;
        }

        List<Path> sources;
        try (Stream<Path> files = Files.list(BLOCKS_DIR)) {
            sources = files
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(BLOCK_SUFFIX))
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            log("💥 Could not read blocks directory: " + ex.getMessage());
            return;
        }

        if (sources.isEmpty()) {
            log("❌ No *Block.java files found in " + BLOCKS_DIR.toAbsolutePath());
            return;
        }

        log("📦 Found " + sources.size() + " block file(s).");

        // Run off the FX thread so the UI stays responsive.
        Thread worker = new Thread(() -> {
            try {
                // 1) Compile all blocks at once with the in-process javac tool.
                log("⚙️ Compiling blocks...");
                String[] javacArgs = sources.stream()
                        .map(Path::toString)
                        .toArray(String[]::new);
                int compileResult = javax.tools.ToolProvider.getSystemJavaCompiler()
                        .run(null, null, null, javacArgs);
                if (compileResult != 0) {
                    log("❌ Compilation failed (exit " + compileResult + ").");
                    return;
                }
                log("✅ Compilation succeeded.");

                // 2) Execute each block's class in sorted order.
                java.util.spi.ToolProvider java =
                        java.util.spi.ToolProvider.findFirst("java").orElseThrow();
                for (Path source : sources) {
                    String fileName = source.getFileName().toString();
                    String className = fileName.substring(
                            0, fileName.length() - ".java".length());

                    log("▶️ Running " + className + " ...");
                    int runResult = java.run(
                            new java.io.PrintWriter(new TextAreaWriter(console), true),
                            new java.io.PrintWriter(new TextAreaWriter(console), true),
                            className
                    );
                    log("🏁 " + className + " finished (exit " + runResult + ").");
                }
                log("🎉 All blocks executed.");
            } catch (Exception ex) {
                log("💥 Error: " + ex.getMessage());
            }
        }, "javablocks-runner");
        worker.setDaemon(true);
        worker.start();
    }

    /** Append a line to the console on the FX thread. */
    private void log(String line) {
        Platform.runLater(() -> {
            console.appendText(line + System.lineSeparator());
            console.positionCaret(console.getLength());
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    /** Writer that forwards text to the JavaFX console. */
    private static final class TextAreaWriter extends java.io.Writer {
        private final TextArea target;
        TextAreaWriter(TextArea target) { this.target = target; }

        @Override
        public void write(char[] cbuf, int off, int len) {
            String s = new String(cbuf, off, len);
            Platform.runLater(() -> target.appendText(s));
        }

        @Override
        public void flush() { }

        @Override
        public void close() { }
    }
}