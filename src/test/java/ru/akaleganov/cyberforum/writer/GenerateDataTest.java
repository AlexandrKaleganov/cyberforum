package ru.akaleganov.cyberforum.writer;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class GenerateDataTest {

    @Test
    public void testWrite() {
        List<String> uuid = Arrays.asList(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8");
        List<String> operations = Arrays.asList(
                "INCOMING",
                "UPLOADING");
        double weight = 70.58d;
        LocalDateTime start = LocalDateTime.of(2021, 1, 1, 10, 10);
        LocalDateTime end = LocalDateTime.of(2021, 3, 1, 10, 10);
        // генерация фактических значений
        new GenerateData().writerFileData(start, end, uuid, operations, "event.csv",
                BigDecimal.valueOf(weight));

        // гнерация плановых значений
        new GenerateData().writerFileData(start, end, uuid, operations, "plan.csv",
                BigDecimal.valueOf(weight));
    }
}