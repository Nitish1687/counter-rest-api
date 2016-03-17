package com.nitish.counter.service;

import com.nitish.counter.dto.CountDto;
import com.nitish.counter.strategy.FileExtractor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CountServiceTest {

    @Mock
    private FileExtractor extractor;
    @Mock
    private HttpServletResponse response;
    @InjectMocks
    private CountService countService;

    @Test
    public void shouldReturnCountOfEachSearchTextIfPresentOnServerFile() throws Exception {
        HashMap<String, List<String>> searchTexts = inputDataForSearchText();
        HashMap<String, Long> extractedData = extractedDataFromFilePresentOnServer();
        when(extractor.extract(anyString())).thenReturn(extractedData);

        Collection<CountDto> countDtos = countService.countOccuranceFor(searchTexts);

        assertEquals(2, countDtos.size());
    }

    @Test
    public void shouldReturnTheTopCountWordAsPerUserInput() throws Exception {
        HashMap<String, Long> extractedData = extractedDataFromFilePresentOnServer();
        when(extractor.extract(anyString())).thenReturn(extractedData);

        Collection<CountDto> higherCountTextUpTo = countService.getHigherCountTextUpTo(1);
        assertEquals(1, higherCountTextUpTo.size());


    }

    private HashMap<String, Long> extractedDataFromFilePresentOnServer() {
        HashMap<String, Long> extractedData = new HashMap<>();
        extractedData.put("HI", 2L);
        extractedData.put("HELLO", 1L);
        return extractedData;
    }

    private HashMap<String, List<String>> inputDataForSearchText() {
        HashMap<String, List<String>> searchTexts = new HashMap<>();
        searchTexts.put("search", asList("Hi", "HELLO"));
        return searchTexts;
    }
}