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

import app.javablocks.model.Block;
import app.javablocks.ui.BlockView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * JavaBlocks — Scratch-like visual programming IDE, 100% Java.
 *
 * <p>Entry point. Builds the main {@link Stage}, a free-positioning
 * {@link Pane} workspace, and drops a single starter block so we can
 * test the drag feel before adding snapping.</p>
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // 🎯 workspace: plain Pane = free positioning, absolute layout
        Pane workspace = new Pane();
        workspace.setPrefSize(1000, 700);

        // 🧱 one starter block, so milestone 1 has something to drag
        Block startBlock = new Block("start", 120, 120);
        BlockView startView = new BlockView(startBlock);
        workspace.getChildren().add(startView);

        Scene scene = new Scene(workspace, 1000, 700);

        // 🎨 stylesheet — we'll fill in blocks.css later
        // scene.getStylesheets().add(getClass().getResource("/css/blocks.css").toExternalForm());

        stage.setTitle("JavaBlocks");
        stage.setScene(scene);
        stage.setMinWidth(640);
        stage.setMinHeight(480);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}