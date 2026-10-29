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

/**
 * GoToSpriteBlock — jumps this sprite to another sprite's position.
 *
 * <p>Reads the target sprite's stored x/y, takes their magnitudes with
 * {@link Math#abs(double)} (auto-imported from {@code java.lang}), and
 * places this sprite there. Starting position is the origin for now,
 * until the sprite/backdrop system exists.</p>
 */
public class GoToSpriteBlock {

    /** Name of the sprite to jump to. */
    private static final String TARGET_SPRITE = "Sprite1";

    /** Stored position of the target sprite. */
    private static final double TARGET_X = 120.0;
    private static final double TARGET_Y = 80.0;

    public static void main(String[] args) {
        // 📏 treat stored coordinates as magnitudes
        double x = Math.abs(TARGET_X);
        double y = Math.abs(TARGET_Y);

        System.out.println("🎯 GoToSpriteBlock: going to " + TARGET_SPRITE);
        System.out.println("📍 target x = " + x);
        System.out.println("📍 target y = " + y);
        System.out.println("🧱 sprite position: (" + x + ", " + y + ")");
    }
}