package ru.akaleganov.cyberforum.request;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.log4j.Log4j2;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;

@SpringBootTest
@Log4j2
public class RequestBlockNonBlock {

    private final WebClient webClient = webClient();


    @Test
    public void restTest() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1; i++) {
            service.submit(
                    () -> {
                        Flux<QualityIndicatorDTO> dl =  this.webClient
                                .get().uri(uriBuilder -> uriBuilder
                                .path("/quality-indicator/list")
                                .build()).exchangeToFlux(e->e.bodyToFlux(QualityIndicatorDTO.class));
                               dl.doOnNext(e->{System.out.println(e);
                                   countDownLatch.countDown();
                               }).blockLast();

                log.error(countDownLatch.getCount());
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl("http://localhost:8080/api")
                .defaultHeader(MediaType.TEXT_EVENT_STREAM_VALUE
                )
                .build();
    }
}
