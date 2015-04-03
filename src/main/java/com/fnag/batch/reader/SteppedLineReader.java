package com.fnag.batch.reader;

import com.fnag.batch.step.LineStep;

import java.util.Arrays;
import java.util.List;

/**
 * {@code SteppedLineReader} reads a line or a group of lines of a specific format.
 * <p>
 * Each line is split into tokens and forwarded to one or several {@link com.fnag.batch.step.LineStep}.
 * <p>
 * The {@code LineStep} validate and handle the tokens of the given line.
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
public class SteppedLineReader implements LineReader {

    private List<LineStep> steps;

    public SteppedLineReader(LineStep... steps) {
        this.steps = Arrays.asList(steps);
    }

    @Override
    public void readLines(List<String> lines) {
        lines.stream().forEach(this::readLine);
    }

    @Override
    public void readLine(String line) {
        String[] tokens = line.split(REGEXP_LINE_SEPARATOR);
        for (LineStep step : steps) {
            if (step.validate(tokens)) {
                step.handle(tokens);
            } else {
                throw new LineReaderException("Validation failed on step " +
                        step.getClass().getCanonicalName() + "for line '" + line + "'");
            }
        }
    }

}
