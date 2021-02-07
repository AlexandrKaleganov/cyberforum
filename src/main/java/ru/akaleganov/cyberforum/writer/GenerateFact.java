package ru.akaleganov.cyberforum.writer;

import lombok.extern.log4j.Log4j2;
import lombok.var;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

/**
 * генерация тестовых фактических данных
 */
@Log4j2
public class GenerateFact {
    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final Random rn = new Random();

    public void writerFile(LocalDateTime start, LocalDateTime end, List<String> uuid, List<String> operation) {
        new File("sample1.csv").delete();
        try (FileWriter fw = new FileWriter("sample1.csv")) {
            for (String value : uuid) {
                for (String s : operation) {
                    fw.write(this.generateByPeriod(start, end, value, s));
                    fw.flush();
                }
            }
        } catch (IOException e) {
            log.info(e.getMessage(), e);
        }


    }

    /**
     * @param start     начало периода
     * @param end       окончание периода
     * @param uuid      uuid ресурса
     * @param operation операция
     * @return генерация ресурса на период
     */
    private String generateByPeriod(LocalDateTime start, LocalDateTime end, String uuid, String operation) {
        var countWagon = rn.nextInt(150);
        StringBuilder res = new StringBuilder();
        LocalDateTime tmp = start.plusHours(0);
        while (tmp.isBefore(end)) {
            generateByDay(tmp, uuid, operation, res);
            tmp = tmp.plusDays(1);
        }
        return res.toString();
    }

    /**
     * @param localDateTime дата начала операции/ дата окончания операции
     * @param uuid          uuid ресурса
     * @param operation     операция
     *                      генерация по ресурсу на день
     */
    private void generateByDay(LocalDateTime localDateTime, String uuid, String operation, StringBuilder res) {
        var countWagon = rn.nextInt(150);
        for (int i = 0; i < countWagon; i++) {
            res.append(generateLine(localDateTime, uuid, operation));
        }
    }

    /**
     * @param localDateTime дата начала операции/ дата окончания операции
     * @param uuid          uuid ресурса
     * @param operation     операция
     * @return генерация строки
     */
    private String generateLine(LocalDateTime localDateTime, String uuid, String operation) {
        return String.format("%s;%s;%s;%s;70.58\n",
                localDateTime.format(df), localDateTime.format(df), operation, uuid);
    }

}