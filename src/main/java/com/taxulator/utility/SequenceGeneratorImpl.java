package com.taxulator.utility;

import org.springframework.stereotype.Component;

@Component
public class SequenceGeneratorImpl implements SequenceGenerator {
    private long value = 1L;

    public synchronized long getNext() {
        return value++;
    }
}