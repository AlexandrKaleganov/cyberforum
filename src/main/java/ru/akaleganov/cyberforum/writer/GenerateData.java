package ru.akaleganov.cyberforum.writer;

import lombok.extern.log4j.Log4j2;
import lombok.var;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

/**
 * генерация тестовых фактических данных
 */
@Log4j2
public class GenerateData {
    private final DateTimeFormatter dTf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final Random rn = new Random();

    /**
     * @param start            дата начала периода
     * @param end              дата окончания периода
     * @param uuid             список ресурсов
     * @param operation        список операци
     * @param filename         название файла
     * @param weight_one_wagon вес одного вагона
     */
    public void writerFileData(LocalDateTime start, LocalDateTime end, List<String> uuid,
                               List<String> operation, String filename, BigDecimal weight_one_wagon) {
        try (FileWriter fw = new FileWriter(filename)) {
            for (String value : uuid) {
                for (String s : operation) {
                    fw.write(this.generateByPeriod(start, end, value, s, filename, weight_one_wagon));
                    fw.flush();
                }
            }
        } catch (IOException e) {
            log.info(e.getMessage(), e);
        }


    }

    /**
     * @param start            начало периода
     * @param end              окончание периода
     * @param uuid             uuid ресурса
     * @param operation        операция
     * @param fileName         имя файла
     * @param weight_one_wagon вес одного вагона
     * @return генерация ресурса на период
     */
    private String generateByPeriod(LocalDateTime start, LocalDateTime end, String uuid,
                                    String operation, String fileName, BigDecimal weight_one_wagon) {
        var countWagon = rn.nextInt(150);
        StringBuilder res = new StringBuilder();
        LocalDateTime tmp = start.plusHours(0);
        var key = "event.csv".equals(fileName);
        while (tmp.isBefore(end)) {
            if (key) {
                generateByDayFact(tmp, uuid, operation, res, weight_one_wagon);
            } else {
                generateByDayPlan(tmp, uuid, operation, res, weight_one_wagon);
            }
            tmp = tmp.plusDays(1);
        }
        return res.toString();
    }

    /**
     * @param localDateTime    дата начала операции/ дата окончания операции
     * @param uuid             uuid ресурса
     * @param operation        операция
     * @param res              результат
     * @param weight_one_wagon вес вагона
     *                         генерация факта по ресурсу на день
     */
    private void generateByDayFact(LocalDateTime localDateTime, String uuid,
                                   String operation, StringBuilder res, BigDecimal weight_one_wagon) {
        var countWagon = rn.nextInt(150);
        for (int i = 0; i < countWagon; i++) {
            res.append(generateLine(localDateTime, uuid, operation, weight_one_wagon));
        }
    }


    /**
     * @param localDateTime    дата начала операции/ дата окончания операции
     * @param uuid             uuid ресурса
     * @param operation        операция
     * @param res              результат
     * @param weight_one_wagon вес вагона
     *                         генерация плана по ресурсу на день
     */
    private void generateByDayPlan(LocalDateTime localDateTime, String uuid,
                                   String operation, StringBuilder res, BigDecimal weight_one_wagon) {
        var countWagon = rn.nextInt(150);
        BigDecimal total_weight = weight_one_wagon.multiply(BigDecimal.valueOf(countWagon));
        res.append(String.format("%s;%s;%s;%s\n",
                localDateTime.format(df), operation, uuid, total_weight));
    }

    /**
     * @param localDateTime дата начала операции/ дата окончания операции
     * @param uuid          uuid ресурса
     * @param operation     операция
     * @return генерация строки
     */
    private String generateLine(LocalDateTime localDateTime, String uuid, String operation,
                                BigDecimal weight_one_wagon) {
        return String.format("%s;%s;%s;%s;%s\n",
                localDateTime.format(dTf), localDateTime.format(dTf), operation, uuid, weight_one_wagon);
    }

}