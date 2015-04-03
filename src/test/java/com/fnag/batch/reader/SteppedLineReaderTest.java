package com.fnag.batch.reader;

import com.fnag.batch.step.LineStep;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.fnag.batch.reader.LineReader.LINE_SEPARATOR;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SteppedLineReaderTest {

    private static final String line = "ONE" + LINE_SEPARATOR + "TWO" + LINE_SEPARATOR + "TREE" + LINE_SEPARATOR + "FOUR";
    private static final String[] tokens = {"ONE", "TWO", "TREE", "FOUR"};

    private SteppedLineReader steppedLineReader;

    @Mock
    private LineStep lineStep;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private List<String> lines;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        steppedLineReader = new SteppedLineReader(lineStep, lineStep, lineStep);
    }

    @Test
    public void should_read_lines() {
        // when
        steppedLineReader.readLines(lines);
        // then
        verify(lines).stream();
        verify(lines.stream()).forEach(any());
    }

    @Test
    public void should_validate_line() {
        // given
        given(lineStep.validate(tokens)).willReturn(true);
        // when
        steppedLineReader.readLine(line);
        // then
        verify(lineStep, times(3)).validate(tokens);
    }

    @Test(expected = LineReaderException.class)
    public void should_failed_if_validation_failed() {
        // given
        given(lineStep.validate(tokens)).willReturn(false);
        // when
        steppedLineReader.readLine(line);
    }

    @Test
    public void should_handle_line() {
        // given
        given(lineStep.validate(tokens)).willReturn(true);
        // when
        steppedLineReader.readLine(line);
        // then
        verify(lineStep, times(3)).handle(tokens);
    }

}
