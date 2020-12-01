package ru.akaleganov.cyberforum.task1;

import java.util.function.Consumer;

/**
 * реализация линкед листа
 * @param <T> тип объектов, которые содержутся в линкед листе
 */
public class SimpleLinkedList<T> {
    // эти три поля пренесём в этот класс там то они зачем
    protected ListItem<T> head = null;  // голова списка (ссылка на первый элемент списка)
    protected ListItem<T> last = null;  // хвост списка (ссылка на последний элемент списка)
    protected int size = 0;          // кол-во элементов в списке

    /**
     * реализуме метод добавления в список
     */
    public T add(T value) {
        ListItem<T> listItem = new ListItem<>(value);
        if (this.head == null) {
            this.head = listItem;
            this.last = listItem;
            listItem.prev = this.head;
        } else {
            this.last.next = listItem;
            listItem.prev = this.last;
            this.last = listItem;
        }
        this.size++;
        return value;
    }

    /**
     * метод тут
     * удаление объекта из списка
     *
     * @param value объект, который необходимо удалить
     * @return вернёт true если объект удалён и  false  если е удалось удалиь
     */
    public boolean delete(T value) {
        StringBuilder res = new StringBuilder();
        this.cycleFunction((link) -> {
            if (link.value.equals(value)) {
                this.removeElement(link);
                res.append("true");
            }
        });
        return res.toString().contains("true");
    }

    public String toString() {
        StringBuilder res = new StringBuilder("{");
        this.cycleFunction(tListItem -> {
            res.append(tListItem.value.toString()).append(", ");
        });
        res.replace(res.length() - 2, res.length(), "}");
        return res.toString();
    }

    /**
     * рефакторинг цикла
     */
    private void cycleFunction(Consumer<ListItem<T>> funcion) {
        ListItem<T> temp = head;
        int count = 0;
        while (temp != null && count < size) {
            funcion.accept(temp);
            temp = temp.next;
            count++;
        }
    }

    /**
     * метод удаления элемента из листа
     *
     * @param listItem елемент, который необходимо удалить
     */
    private void removeElement(ListItem<T> listItem) {
        if (listItem == this.head) {
            this.deleteFirst();
        } else if (listItem == this.last) {
            this.deleteLast();
        } else {
            if (listItem.prev != null) {
                listItem.prev.next = listItem.next;
            }
            if (listItem.next != null) {
                listItem.next.prev = listItem.prev;
            }
        }
        this.size--;
    }

    /**
     * удаление первого элемента
     */
    private void deleteFirst() {
        ListItem<T> temp = this.head;
        if (this.size == 1) {
            this.head = temp.next;
        } else {
            this.head = temp.next;
            head.prev = temp.prev;
        }
    }

    /**
     * удаление последнего элемента
     */
    private void deleteLast() {
        ListItem<T> temp = this.last;
        if (this.size == 1) {
            this.last = temp.prev;
        } else {
            this.last = last.prev;
            last.next = temp.next;
        }
    }

    /**
     * добавим указатель типа, сделаем приватным и статичным
     * это у тебя будет один объект списка, он будет содержать объект и ссылку на следующий объект
     */
    private static class ListItem<T> {
        T value;
        ListItem<T> next; // ссылка на следующий елемент
        ListItem<T> prev; //ссылка на предыдущий элемент

        public ListItem(T value) {
            this.value = value;
        }
    }

}
