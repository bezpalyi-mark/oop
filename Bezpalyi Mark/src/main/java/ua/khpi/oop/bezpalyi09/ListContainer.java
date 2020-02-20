package ua.khpi.oop.bezpalyi09;

import java.io.Serializable;
import java.util.Iterator;

public class ListContainer<T> implements Serializable, Iterable<ListContainer.Node<T>> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Iterator<Node<T>> iterator() {
        return new Iterator<>() {

            private Node<T> tempNode = head;

            @Override
            public boolean hasNext() {
                return tempNode.next != null;
            }

            @Override
            public Node<T> next() {
                Node<T> next = tempNode;
                tempNode = tempNode.next;
                return next;
             }

        };
    }

    private Node<T> head;

    public ListContainer() {
        head = null;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public static class Node<T> implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        T value;
        Node<T> next;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node() {

        }

        public Node(T book) {
            value = book;
            next = null;
        }
    }

    public void insert(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = null;

        if (head == null) {
            head = newNode;
        } else {
            Node<T> endNode = head;
            while (endNode.next != null) {
                endNode = endNode.next;
            }
            endNode.next = newNode;
        }
    }

    public String toString() {
        Node<T> tempNode = head;
        if (head == null) {
            return "";
        } else {
            final int capacity = 50000;
            StringBuilder stringBuilder = new StringBuilder(capacity);
            while (tempNode != null) {
                stringBuilder.append(tempNode.value.toString()).append('\n');
                tempNode = tempNode.next;
            }
            return stringBuilder.toString();
        }
    }

    public T get(int index) {
        Node<T> tempNode = head;
        if (head == null) {
            return null;
        } else {
            int length = 0;
            while (tempNode.next != null && length != index) {
                tempNode = tempNode.next;
                length++;
            }

            if (length == index) {
                return tempNode.value;
            } else {
                return null;
            }
        }
    }

    public boolean remove(int index) {
        if (index < 0) {
            return false;
        } else if (index == 0) {
            head = head.next;
            return true;
        }
        Node<T> prevNode = head;
        Node<T> tempNode = head;
        int counter = 0;
        while (tempNode != null && counter != index) {
            counter++;
            prevNode = tempNode;
            tempNode = tempNode.next;
        }

        if (tempNode == null) {
            return false;
        } else if (tempNode.next == null) {
            prevNode.next = null;
            return true;
        }

        prevNode.next = tempNode.next;
        return true;
    }

    public Node<T> getHead() {
        return head;
    }

    public int size() {
        Node<T> tempNode = head;
        int size = 0;
        while(tempNode != null) {
            size++;
            tempNode = tempNode.next;
        }
        return size;
    }

    public void clear() {
        head = null;
    }

}
