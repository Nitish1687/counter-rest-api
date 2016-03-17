package com.nitish.counter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.io.Serializable;

@JsonDeserialize()
public class CountDto implements Serializable {
    private String word;
    private Long count;

    public String getWord() {
        return word;
    }

    public Long getCount() {
        return count;
    }

    private CountDto() {
    }

    private CountDto(String word, Long count) {
        this.word = word;
        this.count = count;
    }

    public static CountDtoBuilder builder() {
        return new CountDtoBuilder();
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CountDtoBuilder {
        private String text;
        private Long frequency;

        public CountDtoBuilder withText(String word) {
            text = word;
            return this;
        }

        public CountDtoBuilder withFrequency(Long count) {
            frequency = count;
            return this;
        }

        public CountDto build() {
           return new CountDto(text, frequency);
        }

    }
}