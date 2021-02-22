package ru.akaleganov.cyberforum.request;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;

import org.junit.jupiter.api.Test;

@SpringBootTest
@Log4j2
public class RequestBlock {

    private final RestTemplate restTemplate = new RestTemplate();


    @Test
    public void restTest() {
        CountDownLatch countDownLatch = new CountDownLatch(1000000);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000000; i++) {
            service.submit(() -> {
                log.info(Arrays.asList(Objects.requireNonNull(
                        this.restTemplate
                                .getForEntity(
                                        "http://localhost:9090/api/list",
                                        QualityIndicatorDTO[].class
                                ).getBody())));
                countDownLatch.countDown();
                log.error(countDownLatch.getCount());
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
