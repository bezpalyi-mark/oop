package ua.khpi.oop.bezpalyi05;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

public class StringContainer implements Iterable<String>, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String[] array;
    private int currentSize;

    public int getCurrentSize() {
        return currentSize;
    }

    public String[] getArray() {
        return array;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public StringContainer() {
        array = new String[1];
        currentSize = 0;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public StringContainer(String[] arrayToSet) {
        array = arrayToSet;
        currentSize = arrayToSet.length;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return (currentIndex < currentSize) && (array[currentSize + 1] != null);
            }

            @Override
            public String next() {
                currentIndex++;
                return array[currentIndex];
            }

            @Override
            public void remove() {
                String curString = get(currentIndex);
                removeElement(curString);
            }

        };
    }

    public void add(String string) {
        array = Arrays.copyOf(array, currentSize + 1);
        array[currentSize] = string;
        currentSize++;
    }

    public void clear() {
        array = new String[currentSize];
    }

    @Override
    public String toString() {
        final int capacity = 1000;
        StringBuilder strBuilder = new StringBuilder(capacity);
        for (String s : array) {
            strBuilder.append(s).append(' ');
        }
        return strBuilder.toString();
    }

    public boolean removeElement(String string) {
        for (int i = 0; i < currentSize; i++) {
            if (array[i].equals(string)) {
                String[] tempBeginStrings = new String[i];
                System.arraycopy(array, 0, tempBeginStrings, 0, i);
                String[] endStrings = new String[currentSize - i];
                for (int j = (i + 1), k = 0; j < currentSize; j++, k++) {
                    endStrings[k] = array[j];
                }
                array = new String[currentSize - 1];
                int k = 0;
                for (int j = 0; j < tempBeginStrings.length; j++, k++) {
                    array[j] = tempBeginStrings[j];
                }
                for (int j = 0; j < endStrings.length; j++, k++) {
                    array[k] = endStrings[j];
                }
                return true;
            }
        }
        return false;
    }

    public Object[] toArray() {
        return array;
    }

    public int size() {
        return currentSize;
    }

    boolean contains(String string) {
        for (int i = 0; i < currentSize; i++) {
            if (array[i].equals(string))
                return true;
        }
        return false;
    }

    boolean containsAll(String[] strings) {
        if (strings.length > currentSize) {
            return false;
        }
        for (int i = 0; i < currentSize && i < strings.length; i++) {
            if (!array[i].equals(strings[i])) {
                return false;
            }
        }
        return true;
    }

    public String get(int index) {
        if ((index >= 0) && (index <= currentSize)) {
            return array[index];
        }
        return null;
    }
}
