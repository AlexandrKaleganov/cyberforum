package ru.akaleganov.cyberforum.fork;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.RecursiveTask;

/**
 * Class TestForkJoin
 *
 * @author Kaleganov Alexander
 * @since 02 дек. 20
 */
@Log4j2
public class TestForkJoin extends RecursiveTask<Long> {
    private Long val;
    int time;

    public TestForkJoin(Long val) {
        time = (int) (Math.random() * 10);
        this.val = val;
    }

    @Override
    protected Long compute() {
        try {
            Thread.sleep(100 * time);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return this.val;
    }
}
