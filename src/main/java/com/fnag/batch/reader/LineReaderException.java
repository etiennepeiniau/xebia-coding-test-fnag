package com.fnag.batch.reader;

/**
 * {@code LineReaderException} is thrown by a {@link com.fnag.batch.reader.LineReader} when a line can not be read.
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
public class LineReaderException extends RuntimeException {

    public LineReaderException(String message) {
        super(message);
    }

}
