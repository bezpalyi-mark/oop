package ua.khpi.oop.bezpalyi10;

import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;

import java.util.Comparator;

public class Sorting {
    ListContainer<AddressBook> listContainer;
    ListContainer.Node<AddressBook> head;
    Comparator<ListContainer.Node<AddressBook>> comparator;

    public Sorting(ListContainer<AddressBook> listContainer,
                   Comparator<ListContainer.Node<AddressBook>> comparator) {
        this.listContainer = listContainer;
        head = listContainer.getHead();
        this.comparator = comparator;
    }

    public void sort() {

    }

    private void merge(ListContainer.Node<AddressBook> a,
                       ListContainer.Node<AddressBook> b,
                       ListContainer.Node<AddressBook> c) {
        ListContainer.Node<AddressBook> temp = new ListContainer.Node<>();
        c = null;
        if(a == null) {
            c = b;
            return;
        }
        if(b == null) {
            c = a;
            return;
        }
        if(comparator.compare(a, b) < 0){
            c = a;
            a = a.getNext();
        } else {
            c = b;
            b = b.getNext();
        }
        temp.setNext(c);
        while(a != null && b != null) {
            if()
        }
    }
}
