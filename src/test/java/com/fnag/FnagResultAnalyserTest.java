package com.fnag;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.OutputCapture;

import static org.assertj.core.api.Assertions.assertThat;

public class FnagResultAnalyserTest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void should_print_results() throws Exception {
        // given
        String path = this.getClass().getResource("/results.txt").getFile();
        // when
        FnagResultAnalyser.main(new String[]{"--resultPath=" + path});
        // then
        String output = outputCapture.toString();
        assertThat(output).contains("TOPSALE|T127|T-shirt 'no place like 127.0.0.1'|4");
        assertThat(output).contains("TOPSELLER|Lyon|Alice|229.98");
    }

}
