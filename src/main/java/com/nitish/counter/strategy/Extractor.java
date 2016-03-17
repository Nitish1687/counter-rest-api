package com.nitish.counter.strategy;

import java.util.Map;

@FunctionalInterface
public interface Extractor {
    Map<String, Long> extract(String filePath);
}
