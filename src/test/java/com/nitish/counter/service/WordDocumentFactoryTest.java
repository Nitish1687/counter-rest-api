package com.nitish.counter.service;

import com.nitish.counter.bean.WordDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class WordDocumentFactoryTest {

    private static final String EMPTY = "";

    @Test
    public void shouldReturnWordDocumentObject() throws Exception {
        WordDocument wordDocument = WordDocumentFactory.getWordDocument(EMPTY, asList(EMPTY));
        assertEquals("", wordDocument.getFileName());
        assertEquals(1, wordDocument.getWords().size());
    }
}