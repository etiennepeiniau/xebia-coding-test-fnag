package com.fnag.batch.reader;

import java.util.List;

/**
 * A {@code ResultReader} is responsible of reading the result lines.
 * <p>
 * The result must have the following format :
 * <pre>
 * number of line ot type X
 * line X
 * line X
 * ...
 * number of line of type Y
 * line Y
 * line Y
 * ...
 * </pre>
 * <p>
 * If the format is not valid, the ResultReader will throw a {@link com.fnag.batch.reader.ResultReaderException}
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
public interface ResultReader {

    /**
     * Read the result lines
     *
     * @param results a {@link java.util.List} containing the result lines
     */
    void read(List<String> results);

}
