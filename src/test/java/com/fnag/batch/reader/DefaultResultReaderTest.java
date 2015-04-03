package com.fnag.batch.reader;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DefaultResultReaderTest {

    private DefaultResultReader defaultResultLoader;

    private List<String> results;

    @Mock
    private LineReader lineReader;

    @Captor
    private ArgumentCaptor<List<String>> linesArgumentCaptor;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        defaultResultLoader = new DefaultResultReader(lineReader, lineReader, lineReader);
        results = Arrays.asList(
                "2", "line_1_1", "line_1_2",
                "3", "line_2_1", "line_2_2", "line_2_3",
                "1", "line_3_1");
    }

    @Test
    public void should_failed_when_no_result_is_provided() throws Exception {
        // when
        assertThatThrownBy(() -> defaultResultLoader.read(new ArrayList<>())).hasMessage("No results provided");
    }

    @Test
    public void should_failed_when_result_is_null() throws Exception {
        // when
        assertThatThrownBy(() -> defaultResultLoader.read(null)).hasMessage("No results provided");
    }

    @Test
    public void should_read_the_first_result_group() {
        // when
        defaultResultLoader.read(results);
        // then
        verify(lineReader, atLeast(1)).readLines(linesArgumentCaptor.capture());
        List<List<String>> groupedLines = linesArgumentCaptor.getAllValues();
        assertThat(groupedLines.get(0)).hasSize(2).contains("line_1_1", "line_1_2");
    }

    @Test
    public void should_read_the_second_result_group() {
        // when
        defaultResultLoader.read(results);
        // then
        verify(lineReader, atLeast(2)).readLines(linesArgumentCaptor.capture());
        List<List<String>> groupedLines = linesArgumentCaptor.getAllValues();
        assertThat(groupedLines.get(1)).hasSize(3).contains("line_2_1", "line_2_2", "line_2_3");
    }

    @Test
    public void should_read_the_third_result_group() {
        // when
        defaultResultLoader.read(results);
        // then
        verify(lineReader, times(3)).readLines(linesArgumentCaptor.capture());
        List<List<String>> groupedLines = linesArgumentCaptor.getAllValues();
        assertThat(groupedLines.get(2)).hasSize(1).contains("line_3_1");
    }

    @Test
    public void should_failed_if_result_count_is_not_an_integer() {
        // given
        results = Arrays.asList("FIRST_COUNT", "line_1", "line_2");
        // when
        assertThatThrownBy(() -> defaultResultLoader.read(results)).hasMessageContaining("Invalid line count for ");
    }

    @Test
    public void should_failed_if_result_count_is_too_big() {
        // given
        results = Arrays.asList("10", "line_1", "line_2");
        // when
        assertThatThrownBy(() -> defaultResultLoader.read(results)).hasMessageContaining("Not enough lines to read for ");
    }

    @Test
    public void should_failed_if_not_enough_result_group() {
        // given
        results = Arrays.asList(
                "2", "line_1_1", "line_1_2",
                "3", "line_2_1", "line_2_2", "line_2_3");
        // when
        assertThatThrownBy(() -> defaultResultLoader.read(results)).hasMessageContaining("No more lines to read for ");
    }

}
