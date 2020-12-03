package ru.akaleganov.cyberforum.calculate;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
@Log4j2
class CalcTest {
    @Test
    public void testCalc() {
        log.info(new Calc().division(4, 0));
    }
}
