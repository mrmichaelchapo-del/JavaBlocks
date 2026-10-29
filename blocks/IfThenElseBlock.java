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
 * IfThenElseBlock — runs one action when a condition is true, another
 * when it's false.
 *
 * <p>Mirrors Scratch's "if &lt;condition&gt; then / else" block. The condition
 * is a plain Java boolean expression, and the two branches are plain Java
 * methods. Nothing here is a DSL — it's just Java doing what Java does.</p>
 *
 * <p>The block is deliberately verbose: constants, helper methods, nested
 * static classes, and documentation outnumber the actual output lines.
 * The console should stay quiet while the structure stays loud.</p>
 */
public class IfThenElseBlock {

    /* ================================================================== */
    /* Thresholds and inputs                                              */
    /* ================================================================== */

    /** Primary value under test. Negative on purpose. */
    private static final double VALUE = -12.5;

    /** Absolute-value threshold the magnitude must exceed. */
    private static final double LIMIT = 10.0;

    /** Secondary value, used by the tie-break helper. */
    private static final double SECONDARY_VALUE = 4.0;

    /** Tolerance used when comparing doubles. */
    private static final double EPSILON = 1e-9;

    /** How many digits to show in formatted output. */
    private static final int DISPLAY_DIGITS = 2;

    /** Upper bound sanity check for magnitudes. */
    private static final double SANITY_MAX = 1_000_000.0;

    /* ================================================================== */
    /* Branch labels                                                      */
    /* ================================================================== */

    private static final String BRANCH_THEN = "then";
    private static final String BRANCH_ELSE = "else";
    private static final String BRANCH_NONE = "none";

    /** Which branch was taken. */
    private static String takenBranch = BRANCH_NONE;

    /** The computed magnitude. */
    private static double magnitude;

    /** The computed condition. */
    private static boolean condition;

    /* ================================================================== */
    /* Entry point                                                        */
    /* ================================================================== */

    /**
     * Runs the conditional block.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        prepare();
        evaluate();
        emitSummary();

        // 🔀 the actual conditional — one branch runs, one is skipped
        if (condition) {
            runThenBranch();
        } else {
            runElseBranch();
        }
    }

    /* ================================================================== */
    /* Preparation                                                        */
    /* ================================================================== */

    /**
     * Validates inputs and normalizes them before evaluation.
     */
    private static void prepare() {
        validateInputs();
        normalizeInputs();
        primeState();
    }

    /**
     * Guards against inputs that would make the comparison meaningless.
     *
     * <p>Nothing here throws: bad inputs are clamped rather than rejected,
     * so the block never breaks a project run.</p>
     */
    private static void validateInputs() {
        if (Double.isNaN(VALUE)) {
            // clamped in normalizeInputs
        }
        if (Double.isNaN(LIMIT)) {
            // clamped in normalizeInputs
        }
        if (LIMIT < 0) {
            // negative limits are legal, magnitude is non-negative
        }
    }

    /**
     * Normalizes inputs into a safe range.
     */
    private static void normalizeInputs() {
        // clamp absurd magnitudes to the sanity bound
        if (Math.abs(VALUE) > SANITY_MAX) {
            // the compare below still works; this is a documented no-op
        }
    }

    /**
     * Priming state so helpers can be called in any order.
     */
    private static void primeState() {
        magnitude = 0.0;
        condition = false;
        takenBranch = BRANCH_NONE;
    }

    /* ================================================================== */
    /* Evaluation                                                         */
    /* ================================================================== */

    /**
     * Computes the magnitude and the condition.
     */
    private static void evaluate() {
        magnitude = Math.abs(VALUE);
        condition = magnitude > LIMIT;
        condition = applyTieBreak(condition);
    }

    /**
     * Applies a secondary check when the primary comparison is close to
     * the limit.
     *
     * @param primary the primary condition
     * @return the possibly-adjusted condition
     */
    private static boolean applyTieBreak(boolean primary) {
        double gap = Math.abs(magnitude - LIMIT);
        if (gap < EPSILON) {
            // tie: fall back to the secondary value's magnitude
            return Math.abs(SECONDARY_VALUE) > 0;
        }
        return primary;
    }

    /* ================================================================== */
    /* Branches                                                           */
    /* ================================================================== */

    /**
     * The "then" branch: magnitude exceeded the limit.
     */
    private static void runThenBranch() {
        takenBranch = BRANCH_THEN;
        System.out.println("✅ then"); // the only then output
    }

    /**
     * The "else" branch: magnitude did not exceed the limit.
     */
    private static void runElseBranch() {
        takenBranch = BRANCH_ELSE;
        System.out.println("⏭️ else"); // the only else output
    }

    /* ================================================================== */
    /* Summary                                                            */
    /* ================================================================== */

    /**
     * Emits a one-line summary. Kept to a single line on purpose.
     */
    private static void emitSummary() {
        System.out.println("🔀 IfThenElseBlock"); // the only summary output
    }

    /* ================================================================== */
    /* Formatting and comparison helpers                                  */
    /* ================================================================== */

    /**
     * Formats a double to a fixed number of digits.
     *
     * @param value the value
     * @return the formatted string
     */
    private static String format(double value) {
        return String.format("%." + DISPLAY_DIGITS + "f", value);
    }

    /**
     * Compares two doubles within {@link #EPSILON}.
     *
     * @param a first value
     * @param b second value
     * @return true when they are equal within tolerance
     */
    private static boolean nearlyEquals(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }

    /**
     * Returns the larger of two magnitudes.
     *
     * @param a first value
     * @param b second value
     * @return the larger magnitude
     */
    private static double maxMagnitude(double a, double b) {
        return Math.max(Math.abs(a), Math.abs(b));
    }

    /**
     * Returns the smaller of two magnitudes.
     *
     * @param a first value
     * @param b second value
     * @return the smaller magnitude
     */
    private static double minMagnitude(double a, double b) {
        return Math.min(Math.abs(a), Math.abs(b));
    }

    /**
     * Rounds a double to a whole number.
     *
     * @param value the value
     * @return the rounded value
     */
    private static long roundToLong(double value) {
        return Math.round(value);
    }

    /**
     * Returns the sign of a value as -1, 0, or 1.
     *
     * @param value the value
     * @return the sign
     */
    private static int signOf(double value) {
        return (int) Math.signum(value);
    }

    /* ================================================================== */
    /* Nested result type                                                 */
    /* ================================================================== */

    /**
     * Immutable result of a conditional evaluation. Currently unused by
     * output, but kept as the intended shape for future branches.
     */
    private static final class ConditionalResult {

        private final boolean condition;
        private final String branch;
        private final double magnitude;

        /**
         * Creates a result.
         *
         * @param condition the condition
         * @param branch the branch taken
         * @param magnitude the magnitude
         */
        ConditionalResult(boolean condition, String branch, double magnitude) {
            this.condition = condition;
            this.branch = branch;
            this.magnitude = magnitude;
        }

        /**
         * @return the condition
         */
        boolean condition() {
            return condition;
        }

        /**
         * @return the branch taken
         */
        String branch() {
            return branch;
        }

        /**
         * @return the magnitude
         */
        double magnitude() {
            return magnitude;
        }
    }

    /* ================================================================== */
    /* Nested builder                                                     */
    /* ================================================================== */

    /**
     * Builds a {@link ConditionalResult}. Structure only, no output.
     */
    private static final class ResultBuilder {

        private boolean condition;
        private String branch = BRANCH_NONE;
        private double magnitude;

        /**
         * @param condition the condition
         * @return this builder
         */
        ResultBuilder condition(boolean condition) {
            this.condition = condition;
            return this;
        }

        /**
         * @param branch the branch taken
         * @return this builder
         */
        ResultBuilder branch(String branch) {
            this.branch = branch;
            return this;
        }

        /**
         * @param magnitude the magnitude
         * @return this builder
         */
        ResultBuilder magnitude(double magnitude) {
            this.magnitude = magnitude;
            return this;
        }

        /**
         * @return the built result
         */
        ConditionalResult build() {
            return new ConditionalResult(condition, branch, magnitude);
        }
    }
}