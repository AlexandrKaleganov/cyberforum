package ru.akaleganov.cyberforum.calculate;

import lombok.extern.log4j.Log4j2;

/**
 * Class Calc
 * пример проброса исключений
 * @author Kaleganov Alexander
 * @since 03 дек. 20
 */
@Log4j2
public class Calc {

    public int division(int first, int second) {
        int res = 0;
        try {
            res = first / second;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return res;
    }
}
