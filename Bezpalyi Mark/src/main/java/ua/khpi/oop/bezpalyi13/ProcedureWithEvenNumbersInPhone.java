package ua.khpi.oop.bezpalyi13;

import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;

import java.util.Random;

public class ProcedureWithEvenNumbersInPhone implements Runnable {

    private boolean stop = false;

    private static final int MAX_WAIT = 100;

    private final ListContainer<AddressBook> books;

    private final Integer number;

    private final Random rnd = new Random();

    public ProcedureWithEvenNumbersInPhone(ListContainer<AddressBook> books, Integer number) {
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
        ListContainer<AddressBook> evenNumbersPhoneBooks = new ListContainer<>();
        for (int i = 0; i < books.size(); i++) {
            if(stop) {
                System.out.println(Thread.currentThread().getName() + " stopped!");
                return;
            }
            String number = books.get(i).getPhoneNumbers().get(0);
            String numberWithoutDels = number.replaceAll("-", "");
            int fullSize = numberWithoutDels.length();
            int countEvens = 0;
            for(int j = 0; j < fullSize; j++) {
                byte b = Byte.parseByte(String.valueOf(numberWithoutDels.charAt(j)));
                if(b % 2 == 0) {
                    countEvens++;
                }
            }
            if(countEvens >= (fullSize/2)) {
                evenNumbersPhoneBooks.add(books.get(i));
                goSleep();
            }
        }
        System.out.println("Size of books more evens numbers in phone: " + evenNumbersPhoneBooks.size());
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
