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
                "3f15685c-4386-497a-8d8e-ae75528a8dde",
                "521b4c39-f8cc-4e62-ab76-a53f4fdd5ba9",
                "38cf4039-4637-4944-bfe2-bcbfe8145bb9",
                "a47b49d2-1c4a-4bda-be66-86745d4a6361",
                "25a25cda-91a5-4c28-b27c-894876162832",
                "83a7acd2-d025-43fd-a60e-41e75444c0e6",
                "a481d01b-5f03-414a-a258-eac97ad91f3f",
                "a5861923-78e6-458d-bd57-a33126fcb256",
                "bb46a070-2ef8-4e6a-b1b6-b4ad2e8a8b63",
                "7f320224-8065-479c-8996-84e6e194c5e6");
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