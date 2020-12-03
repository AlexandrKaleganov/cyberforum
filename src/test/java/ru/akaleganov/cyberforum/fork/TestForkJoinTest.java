package ru.akaleganov.cyberforum.fork;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("тест Fork/join")
@Log4j2
class TestForkJoinTest {
    ForkJoinPool forkJoinPool = new ForkJoinPool(4);
    @DisplayName("Fork/join: тест ")
    @Test
    public void testFork() {
        log.error(this.forkJoinPool.getPoolSize());
        List<Long> forkJoinList = new LinkedList<>();
        for (long i = 0; i < 2 ; i++) {
            log.error(this.forkJoinPool.invoke(new TestForkJoin(i)));
        }
    }
}
