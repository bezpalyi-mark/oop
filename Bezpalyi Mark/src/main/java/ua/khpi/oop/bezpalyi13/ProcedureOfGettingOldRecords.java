package ua.khpi.oop.bezpalyi13;

import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ProcedureOfGettingOldRecords implements Runnable {

    private boolean stop = false;

    private static final int MAX_WAIT = 150;

    private final ListContainer<AddressBook> books;

    private final Integer number;

    private final Random rnd = new Random();

    public ProcedureOfGettingOldRecords(ListContainer<AddressBook> books, Integer number) {
        this.books = books;
        this.number = number;
    }

    public void stop() {
        stop = true;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("thread number " + number);
        System.out.println("Begin of work of " + Thread.currentThread().getName());
        ListContainer<AddressBook> oldBooks = new ListContainer<>();
        LocalDate vergeDate = LocalDate.of(2009, Month.JUNE, 10);
        for (ListContainer.Node<AddressBook> book : books) {
            if(stop) {
                System.out.println(Thread.currentThread().getName() + " stopped!");
                return;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(book.getValue().getEditDateAndTime(), formatter);
            LocalDate localDate = dateTime.toLocalDate();
            if (vergeDate.isAfter(localDate)) {
                oldBooks.add(book.getValue());
                goSleep();
            }
        }
        System.out.println("Size of books with old records: " + oldBooks.size());
        System.out.println("End of work of " + Thread.currentThread().getName() + "\n");
    }

    private void goSleep() {
        try {
            Thread.sleep(rnd.nextInt(MAX_WAIT));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
