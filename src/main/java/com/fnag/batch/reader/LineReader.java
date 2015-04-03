package com.fnag.batch.reader;

import java.util.List;

/**
 * A {@code LineReader} reads a line or a group of lines of a specific format
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
public interface LineReader {

    static final String LINE_SEPARATOR = "|";
    static final String REGEXP_LINE_SEPARATOR = "\\" + LINE_SEPARATOR;

    /**
     * Reads several lines with the same format
     *
     * @param lines a {@link java.util.List} of lines
     */
    void readLines(List<String> lines);

    /**
     * Reads a line
     *
     * @param line a line
     */
    void readLine(String line);

}
