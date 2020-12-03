package ru.akaleganov.cyberforum.task1;


import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.*;

/**
 * Class TestList
 *
 * @author Kaleganov Alexander
 * @since 02 дек. 20
 */
@DisplayName("тест линкедлист")
@Log4j2
public class TestList {

    @DisplayName("тест UnsupportedOperationException")
    @Test(expected = UnsupportedOperationException.class)
    public void testList() {
        List<String> list = Arrays.asList("asd", "qwewqe", "qwwqq");
        list.add("assasa");
    }

    @DisplayName("тест Удаления объектов из списка")
    @Test()
    public void testList2() {
        List<String> list = new ArrayList<>(Arrays.asList("asd", "qwewqe", "qwwqq"));
        list.add("assasa");
        list.add(1, "res");
        log.info(list.toString());
        for (int i = 0; i < list.size(); i++) {
            log.debug(list.remove(i));
        }
        log.info(list.toString());
        list.add("2");
        list.add("2");
        list.add("3");

        for (int i = 0; i < list.size(); i++) {
            if ("2".equals(list.get(i))) {
                list.remove("2");

            }
        }
        log.debug(list.toString());
    }

    @DisplayName("тест hashSet")
    @Test()
    public void testHashSet() {
        Set<NewEntity> entry = new HashSet<>();
        entry.add(new NewEntity("1"));
        entry.add(new NewEntity("1"));
        entry.forEach(log::info);
    }

    @ToString
    private static class NewEntity {
        String str;
        public NewEntity(String str) {
            this.str = str;
        }
        @Override
        public int hashCode() {
            return Objects.hash(str);
        }
    }


    @DisplayName("тест LinkedList")
    @Test()
    public void testLinkedList() {
        List<String> list = new LinkedList<>();
        log.info(list.get(50));
    }

}
