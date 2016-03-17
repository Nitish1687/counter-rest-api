package com.nitish.counter.bean;

import java.util.List;

public class WordDocument {

    private String fileName;
    private List<String> words;

    public WordDocument(String fileName, List<String> words) {
        this.fileName = fileName;
        this.words = words;
    }

    public WordDocument() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
