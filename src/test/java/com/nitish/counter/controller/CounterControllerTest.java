package com.nitish.counter.controller;

import com.nitish.counter.dto.CountDto;
import com.nitish.counter.service.CountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.valueOf;

@RunWith(MockitoJUnitRunner.class)
public class CounterControllerTest {

    @Mock
    private CountService countService;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletOutputStream servletOutputStream;
    @InjectMocks
    private CounterController counterController;

    @Test
    public void shouldReturnResponseEntityWithCountDtos() throws Exception {
        HashMap<String, List<String>> searchTexts = new HashMap<>();
        searchTexts.put("search", asList("Hi", "HELLO"));
        when(countService.countOccuranceFor(searchTexts))
                .thenReturn(asList(createCountDto("HI", 1L), createCountDto("HELLO", 1L)));

        ResponseEntity<Collection<CountDto>> search = counterController.search(searchTexts);

        assertNotNull(search);
        assertEquals(valueOf(200), search.getStatusCode());
    }

    @Test
    public void shouldWriteDataToCSVForTopCountResult() throws Exception {
        when(countService.getHigherCountTextUpTo(2))
                .thenReturn(asList(createCountDto("HI", 4), createCountDto("HELLO", 2)));
        when(response.getOutputStream()).thenReturn(servletOutputStream);
        when(response.getHeader("Content-Disposition")).thenReturn("attachment; filename=topText.csv");

        counterController.topText(2, response);

        assertEquals("attachment; filename=topText.csv", response.getHeader("Content-Disposition"));
    }

    private CountDto createCountDto(String text, long frequency) {
        return CountDto.builder().withText(text).withFrequency(frequency).build();
    }

}