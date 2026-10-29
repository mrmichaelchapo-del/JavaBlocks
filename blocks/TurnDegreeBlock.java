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
 * TurnDegreeBlock — turns the sprite by a number of degrees.
 *
 * <p>Adds a non-negative {@code DEGREES} value (guaranteed by
 * {@link Math#abs(double)}) to the current {@code DIRECTION} and wraps
 * the result into 0–360 with {@link Math#floorMod(int, int)}. Both are
 * auto-imported from {@code java.lang}. Unlike {@code %}, floorMod always
 * returns a non-negative result for a positive modulus, so turning
 * -90° from 0° yields 270°, not -90°. Direction convention:
 * 0° = right, 90° = up.</p>
 */
public class TurnDegreeBlock {

    /** Current facing direction in degrees. 0° = right, 90° = up. */
    private static final double DIRECTION = 90.0;

    /** How many degrees to turn. Always treated as a positive magnitude. */
    private static final double DEGREES = 45.0;

    public static void main(String[] args) {
        // 📏 Math.abs guarantees a positive turn magnitude
        double turn = Math.abs(DEGREES);

        // 🧭 new direction = old + turn, wrapped into 0–360
        double newDirection = Math.floorMod(
                (int) Math.round(DIRECTION + turn), 360);

        System.out.println("🔄 TurnDegreeBlock: turning " + turn + "°");
        System.out.println("🧭 old direction: " + DIRECTION + "°");
        System.out.println("🧭 new direction: " + newDirection + "°");
    }
}