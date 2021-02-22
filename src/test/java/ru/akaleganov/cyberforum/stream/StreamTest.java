package ru.akaleganov.cyberforum.stream;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("тест stream")
@Log4j2
public class StreamTest {

    @DisplayName("Stream: найти самоедлинное слово")
    @Test
    public void searchNameSLength() {
        Stream<String> names = Stream.of("John Lenon", "Paul Fast", "Georg Harrison", "Rino Starr", "Patte Best");
        assertEquals(names.reduce("", (a, b) -> a.length() > b.length() ? a : b), "Georg Harrison");
    }

    @DisplayName("Stream: тест последовательности")
    @Test
    public void streamTest1() {
        IntStream.of(50, 60, 70, 80, 90, 100, 110, 120).filter(x -> x< 90).map(x -> x + 10)
                .limit(3).forEach(System.out::println);

    }

}
