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
 * IfThenBlock — runs an action only when a condition is true.
 *
 * <p>Mirrors Scratch's "if &lt;condition&gt; then" block. The condition is
 * a plain Java boolean expression; when it's true the block logs the
 * action it would take. {@link Math#abs(double)} is used to build the
 * condition from a numeric input, so it demonstrates Math usage while
 * staying "just Java".</p>
 */
public class IfThenBlock {

    /** A value to test against. */
    private static final double VALUE = -12.5;

    /** Threshold the absolute value must exceed. */
    private static final double LIMIT = 10.0;

    public static void main(String[] args) {
        // 📏 Math.abs gives the magnitude, independent of sign
        double magnitude = Math.abs(VALUE);

        // 🔀 the condition: magnitude greater than the limit
        boolean condition = magnitude > LIMIT;

        System.out.println("🔀 IfThenBlock: checking condition");
        System.out.println("📏 |" + VALUE + "| = " + magnitude);
        System.out.println("⚖️ limit = " + LIMIT);
        System.out.println("❓ condition = " + condition);

        // 🎯 the "then" branch — only runs when true
        if (condition) {
            System.out.println("✅ Then: magnitude exceeds the limit!");
        } else {
            System.out.println("⏭️ Else: condition was false, skipping then.");
        }
    }
}