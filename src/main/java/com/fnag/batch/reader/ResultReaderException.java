package com.fnag.batch.reader;

/**
 * {@code ResultReaderException} is thrown by a {@link com.fnag.batch.reader.ResultReader} when a result can not be read.
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
public class ResultReaderException extends RuntimeException {

    public ResultReaderException(String message) {
        super(message);
    }

}
