package com.nitish.counter.service;

import com.nitish.counter.bean.WordDocument;

import java.util.List;


public class WordDocumentFactory {

    private WordDocumentFactory (){

    }

    public static WordDocument getWordDocument(String documentName, List<String> words) {
        return new WordDocument(documentName, words);
    }
}
