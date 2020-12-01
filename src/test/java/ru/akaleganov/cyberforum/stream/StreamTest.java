package ru.akaleganov.cyberforum.stream;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("тест stream")
public class StreamTest {

    @DisplayName("Stream: найти самоедлинное слово")
    @Test
    public void searchNameSLength() {
        Stream<String> names = Stream.of("John Lenon", "Paul Fast", "Georg Harrison", "Rino Starr", "Patte Best");
        assertEquals(names.reduce("", (a, b) -> a.length() > b.length() ? a : b), "Georg Harrison");
    }
}
