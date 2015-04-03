package com.fnag.batch.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@code DefaultResultReader} reads the result lines and delegate each group of lines to a {@link com.fnag.batch.reader.LineReader}
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
public class DefaultResultReader implements ResultReader {

    private List<LineReader> lineReaders = new ArrayList<>();

    public DefaultResultReader(LineReader... lineReaders) {
        this.lineReaders = Arrays.asList(lineReaders);
    }

    @Override
    public void read(List<String> results) {
        if (results == null || results.isEmpty()) {
            throw new ResultReaderException("No results provided");
        } else {
            List<String> lines = results;
            for (LineReader linesReader : lineReaders) {
                if (lines.isEmpty()) {
                    throw new ResultReaderException("No more lines to read for " + linesReader.getClass().getCanonicalName());
                } else {
                    lines = read(linesReader, lines);
                }
            }
        }
    }

    private List<String> read(LineReader linesReader, List<String> lines) {
        try {
            int lineCount = Integer.valueOf(lines.get(0));
            if (lines.size() < lineCount + 1) {
                throw new ResultReaderException("Not enough lines to read for " + linesReader.getClass().getCanonicalName());
            } else {
                linesReader.readLines(lines.subList(1, lineCount + 1));
                return lines.subList(lineCount + 1, lines.size());
            }
        } catch (NumberFormatException nfe) {
            throw new ResultReaderException("Invalid line count for " + linesReader.getClass().getCanonicalName());
        }
    }

}
