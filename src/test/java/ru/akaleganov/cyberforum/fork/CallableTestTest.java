package ru.akaleganov.cyberforum.fork;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("тест Fork/join")
@Log4j2
class CallableTestTest {
    ForkJoinPool forkJoinPool = new ForkJoinPool(4);
    ExecutorService executorService  = Executors.newFixedThreadPool(4);
    private Random random;
    @DisplayName("Fork/join: тест ")
    @Test
    public void testFork() throws ExecutionException, InterruptedException {
        log.error(this.forkJoinPool.getPoolSize());
        List<Future<Long>> forkJoinList = new LinkedList<>();
        for (long i = 0; i < 2 ; i++) {
          forkJoinList.add(executorService.submit(new CallableTest(i)));
        }
        System.out.println(" Задачи поставлены");
        forkJoinList.stream().forEach(e->{
            try {
                log.error(e.get());
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ExecutionException executionException) {
                executionException.printStackTrace();
            }
        });
    }
}
