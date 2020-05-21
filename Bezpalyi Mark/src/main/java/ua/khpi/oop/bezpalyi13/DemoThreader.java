package ua.khpi.oop.bezpalyi13;

import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;

import java.util.Scanner;

public class DemoThreader implements Runnable {
    public void parallelHandler(boolean needTimer) {
        ListContainer<AddressBook> books = LoadData.loadList();
        int timer = 0;
        if(needTimer) {
            System.out.print("Enter waiting time in seconds: ");
            timer = new Scanner(System.in).nextInt();
        }
        timer *= 1000;
        Thread[] threads = new Thread[4];

        int threadCounter = 0;
        int elemCounter = 0;

        ProcedureOfGettingOldRecords[] oldRecords = new ProcedureOfGettingOldRecords[2];
        ProcedureWithEvenNumbersInPhone[] evenNumbersInPhones = new ProcedureWithEvenNumbersInPhone[2];

        for(int i = 0; i < 4; i++) {
            oldRecords[elemCounter] = new ProcedureOfGettingOldRecords(books, (i + 1));
            threads[threadCounter] = new Thread(oldRecords[elemCounter]);
            threadCounter++;

            evenNumbersInPhones[elemCounter] = new ProcedureWithEvenNumbersInPhone(books, (++i + 1));
            threads[threadCounter] = new Thread(evenNumbersInPhones[elemCounter]);

            threads[threadCounter - 1].start();
            threads[threadCounter].start();

            threadCounter++;
            elemCounter++;
        }

        try {
            if(timer != 0) {
                Thread.sleep(timer);
                for (int i = 0; i < oldRecords.length; i++) {
                    oldRecords[i].stop();
                    evenNumbersInPhones[i].stop();
                }
            } else {
                while (threads[0].isAlive() || threads[1].isAlive() || threads[2].isAlive() || threads[3].isAlive());
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Main thread done!");
    }

    public static void main(String[] args) {
        DemoThreader demoThreader = new DemoThreader();
        demoThreader.parallelHandler(true);
    }

    @Override
    public void run() {
        parallelHandler(false);
    }
}

