package ru.akaleganov.cyberforum.task1;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("тест линкедлист")
class SimpleLinkedListTest {
    @DisplayName("тест удаление элемента из середины списка")
    @Test
    public void testDeleteList() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        assertThat(list.toString(), Is.is("{one, two, three}"));
        assertThat(list.delete("two"), Is.is(true));
        assertThat(list.toString(), Is.is("{one, three}"));
    }

    @DisplayName("тест удаление элемента с начала списка")
    @Test
    public void testDeleteFirstList() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        assertThat(list.toString(), Is.is("{one, two, three}"));
        assertThat(list.delete("one"), Is.is(true));
        assertThat(list.toString(), Is.is("{two, three}"));
    }

    @DisplayName("тест удаление элемента с конца списка")
    @Test
    public void testDeleteLastList() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        assertThat(list.toString(), Is.is("{one, two, three}"));
        assertThat(list.delete("three"), Is.is(true));
        assertThat(list.toString(), Is.is("{one, two}"));
    }

}
