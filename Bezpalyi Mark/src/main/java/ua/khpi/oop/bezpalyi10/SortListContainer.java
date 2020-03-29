package ua.khpi.oop.bezpalyi10;

import ua.khpi.oop.bezpalyi09.ListContainer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortListContainer<T> {
    Comparator<ListContainer.Node<T>> comparator;

    public SortListContainer(Comparator<ListContainer.Node<T>> comparator) {
        this.comparator = comparator;
    }

    public void setComparator(Comparator<ListContainer.Node<T>> comparator) {
        this.comparator = comparator;
    }

    public List<ListContainer.Node<T>> splitUp(ListContainer.Node<T> inHead) {
        List<ListContainer.Node<T>> list = new ArrayList<>();

        if (inHead == null || inHead.getNext() == null) {
            list.add(inHead);
            list.add(null);
            return list;
        }
        ListContainer.Node<T> slow = inHead;
        ListContainer.Node<T> fast = inHead.getNext();

        while (fast != null) {
            fast = fast.getNext();
            if (fast != null) {
                slow = slow.getNext();
                fast = fast.getNext();
            }
        }

        list.add(inHead);
        list.add(slow.getNext());
        slow.setNext(null);
        return list;
    }

    public ListContainer.Node<T> merge(ListContainer.Node<T> first, ListContainer.Node<T> second) {
        if (first == null) {
            return second;
        }

        if (second == null) {
            return first;
        }

        ListContainer.Node<T> result;

        if (comparator.compare(first, second) <= 0) {
            result = first;
            result.setNext(merge(first.getNext(), second));
        } else {
            result = second;
            result.setNext(merge(first, second.getNext()));
        }

        return result;
    }

    public ListContainer.Node<T> sort(ListContainer.Node<T> inHead) {
        if (inHead == null || inHead.getNext() == null) {
            return inHead;
        }
        List<ListContainer.Node<T>> array = splitUp(inHead);
        ListContainer.Node<T> firstPart = array.get(0);
        ListContainer.Node<T> secondPart = array.get(1);

        firstPart = sort(firstPart);
        secondPart = sort(secondPart);

        return merge(firstPart, secondPart);
    }

}
