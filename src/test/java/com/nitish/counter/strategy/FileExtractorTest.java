package com.nitish.counter.strategy;

import com.nitish.counter.exception.InvalidFileReadException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class FileExtractorTest {

    @InjectMocks
    private FileExtractor extractor;

    @Test
    public void shouldExtractTheWordWithFrequencyCount() throws Exception {
        Map<String, Long> extract = extractor.extract(getClass().getClassLoader().getResource("input.txt").getPath().split("input.txt")[0]);
        assertNotNull(extract);
        assertEquals(3, extract.keySet().size());
        assertEquals(3, extract.values().size());
    }

    @Test(expected = InvalidFileReadException.class)
    public void shouldThrowInvalidWordDocumentReadExceptionWhenSomethingWentIncorrectToServerFile() throws Exception {
        extractor.extract(getClass().getClassLoader().getResource("input.txt").getPath());
    }
}