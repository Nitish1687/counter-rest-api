package com.nitish.counter.strategy;

import com.nitish.counter.bean.WordDocument;
import com.nitish.counter.exception.InvalidFileReadException;
import com.nitish.counter.exception.InvalidWordDocumentRead;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;

import static com.nitish.counter.service.WordDocumentFactory.getWordDocument;
import static java.util.stream.Collectors.*;

@Component
public class FileExtractor implements Extractor {

    private static final String regex = "[\\s,.]+";

    @Override
    public Map<String, Long> extract(String filePath) {
        Path pathOfInputFile = new File(filePath).toPath();
        List<WordDocument> wordDocumentList = extractWordDocumentFromFile(pathOfInputFile).stream().map(wordDocumentSupplier()).collect(toList());
        Map<String, Long> textWithFrequency = wordDocumentList.stream().findFirst().get().getWords().stream().flatMap(Pattern.compile(regex)::splitAsStream)
                .map(mapToUpperCase()).collect(groupingBy(text -> text, counting()));
        return textWithFrequency;
    }

    private List<Optional<WordDocument>> extractWordDocumentFromFile(Path pathOfInputFile) throws InvalidWordDocumentRead {
        List<Optional<WordDocument>> collect = null;
        try {
            collect = Files.list(pathOfInputFile).map(path -> {
                        WordDocument wordDocument = null;
                        try {
                            wordDocument = getWordDocument(path.getFileName().toString(), getCapitalizeWord(path));
                        } catch (IOException e) {
                            throw new InvalidWordDocumentRead(" problem occurred at word document read", e);
                        }
                        return Optional.of(wordDocument);
                    }
            ).collect(toList());
        } catch (IOException e) {
            throw new InvalidFileReadException("problem occurred while reading file path", e);
        }
        return collect;
    }

    private Function<Optional<WordDocument>, WordDocument> wordDocumentSupplier() {
        return wordOptional -> wordOptional.orElseGet(() -> new WordDocument("", null));
    }

    private List<String> getCapitalizeWord(Path path) throws IOException {
        return Files.readAllLines(path).stream().flatMap(Pattern.compile(regex)::splitAsStream).map(mapToUpperCase()).collect(toList());
    }

    private Function<String, String> mapToUpperCase() {
        return searchText -> searchText.toUpperCase();
    }


}
