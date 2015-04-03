package com.fnag;

import com.fnag.batch.reader.ResultReader;
import com.fnag.service.GlobalResultService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FnagCommandLineRunnerTest {

    @InjectMocks
    private FnagCommandLineRunner fnagCommandLineRunner;

    @Mock
    private GlobalResultService globalResultService;

    @Mock
    private ResultReader resultReader;

    @Test
    public void should_run() throws Exception {
        // given
        String path = this.getClass().getResource("/results.txt").getFile();
        Field pathField = ReflectionUtils.findField(FnagCommandLineRunner.class, "path");
        pathField.setAccessible(true);
        pathField.set(fnagCommandLineRunner, path);
        // when
        fnagCommandLineRunner.run();
        // then
        verify(resultReader).read(anyList());
        verify(globalResultService).findTopSales();
        verify(globalResultService).findTopSellers();
    }

}
