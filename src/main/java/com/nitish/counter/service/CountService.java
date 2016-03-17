package com.nitish.counter.service;

import com.nitish.counter.dto.CountDto;
import com.nitish.counter.strategy.FileExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
@ConfigurationProperties(prefix = "file")
public class CountService {

    @Autowired
    private FileExtractor extractor;
    private String path;

    public Collection<CountDto> countOccuranceFor(Map<String, List<String>> searchTexts) throws RuntimeException {
        Map<String, Long> wordExtractedFromFile = extractor.extract(getPath());
        return searchTexts.values().stream().flatMap(Collection::stream).map(createCountDtoCollectionForSearchText(wordExtractedFromFile))
                .collect(toList());
    }

    public Collection<CountDto> getHigherCountTextUpTo(int count) {
        Map<String, Long> wordExtractedFromFile = extractor.extract(getPath());
        List<String> keyFromSortedExtractedMap = wordExtractedFromFile.entrySet().stream().
                sorted(sortByValueInReverseOrder()).map(Map.Entry::getKey).limit(count).collect(toList());
       return keyFromSortedExtractedMap.stream().map(createCountDtoCollectionForGivenInputCount(wordExtractedFromFile)).collect(toList());
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private List<CountDto> getCountDtoAsPerUserInput(int count, Map<String, Long> wordExtractedFromFile) {

        List<String> keyFromSortedExtractedMap = wordExtractedFromFile.entrySet().stream().
                sorted(sortByValueInReverseOrder()).map(Map.Entry::getKey).limit(count).collect(toList());

       return  keyFromSortedExtractedMap.stream().map(createCountDtoCollectionForGivenInputCount(wordExtractedFromFile)).collect(toList());
    }

    private Function<String, CountDto> createCountDtoCollectionForSearchText(Map<String, Long> wordExtractedFromFile) {
        return text -> CountDto.builder().withText(text).withFrequency(wordExtractedFromFile.get(text.toUpperCase())).build();
    }

    private Function<String, CountDto> createCountDtoCollectionForGivenInputCount(Map<String, Long> wordExtractedFromFile) {
        return key -> CountDto.builder().withText(key).withFrequency(wordExtractedFromFile.get(key)).build();
    }

    private Comparator<Map.Entry<String, Long>> sortByValueInReverseOrder() {
        return Map.Entry.comparingByValue(Comparator.reverseOrder());
    }

}
