package com.fnag.batch.step;

/**
 * A {@code LineStep} performs a step on a line tokens
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
public interface LineStep {

    /**
     * Validate the tokens of a line
     *
     * @param tokens an {@code Array} of {@code String} representing the tokens of a line
     * @return @{code true} if the tokens are valid, {@code false} otherwise
     */
    boolean validate(String[] tokens);

    /**
     * Handle the tokens of a line
     *
     * @param tokens an {@code Array} of {@code String} representing the tokens of a line
     */
    void handle(String[] tokens);

}
