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
 * MoveStepsBlock — moves the sprite 10 steps.
 *
 * <p>Steps are converted into an x/y delta using {@link Math} (cos/sin),
 * which is auto-imported from {@code java.lang}. {@link Math#abs(int)}
 * guarantees the step count is non-negative, so direction is controlled
 * only by {@code DIRECTION}. Direction convention: 0° = right, 90° = up.</p>
 */
public class MoveStepsBlock {

    /** How many steps this block moves per run. */
    private static final int STEPS = 10;

    /** Current facing direction in degrees. 0° = right, 90° = up. */
    private static final double DIRECTION = 90.0;

    public static void main(String[] args) {
        // 🧭 degrees → radians (Math is auto-imported from java.lang)
        double radians = Math.toRadians(DIRECTION);

        // 📏 Math.abs guarantees a non-negative distance, no matter what
        int distance = Math.abs(STEPS);

        // 🎯 split the distance into x and y
        double dx = distance * Math.cos(radians);
        double dy = distance * Math.sin(radians);

        // 🧱 where the sprite would end up (starting at origin for now)
        double x = 0.0 + dx;
        double y = 0.0 + dy;

        System.out.println("🚶 MoveStepsBlock: moving " + distance + " steps");
        System.out.println("🧭 direction: " + DIRECTION + "°");
        System.out.println("➡️ dx = " + dx);
        System.out.println("⬆️ dy = " + dy);
        System.out.println("📍 new position: (" + x + ", " + y + ")");
    }
}