package ua.khpi.oop.bezpalyi14;

import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;
import ua.khpi.oop.bezpalyi13.DemoThreader;
import ua.khpi.oop.bezpalyi13.LoadData;
import ua.khpi.oop.bezpalyi13.ProcedureOfGettingOldRecords;
import ua.khpi.oop.bezpalyi13.ProcedureWithEvenNumbersInPhone;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeMeter {
    public static void main(String[] args) throws InterruptedException {
        DemoThreader demoThreader = new DemoThreader();
        Thread thread = new Thread(demoThreader);
        LocalDateTime endTime;
        LocalDateTime beginTime = LocalDateTime.now();
        thread.start();
        Thread.sleep(1);
        while (thread.isAlive()) {
        }
        endTime = LocalDateTime.now();

        long parallelDifference = Duration.between(beginTime, endTime).getSeconds();
        if (parallelDifference < 0) {
            System.out.println("Error, getting a negative time, please, retry...");
            return;
        }

        System.out.println("\n/-------------------------------\\");
        System.out.println("|Time of parallel handle: " + parallelDifference + " sec.|");
        System.out.println("\\-------------------------------/");

        ListContainer<AddressBook> books = LoadData.loadList();
        ProcedureWithEvenNumbersInPhone procedure1 = new ProcedureWithEvenNumbersInPhone(books, 5);
        ProcedureOfGettingOldRecords procedure2 = new ProcedureOfGettingOldRecords(books, 6);

        beginTime = LocalDateTime.now();
        procedure1.run();
        procedure1.run();
        procedure2.run();
        procedure2.run();
        endTime = LocalDateTime.now();

        long chainDifference = Duration.between(beginTime, endTime).getSeconds();
        if (chainDifference < 0) {
            System.out.println("Error, getting a negative time, please, retry...");
            return;
        }
        System.out.println("\n/-----------------------------\\");
        System.out.println("|Time of chain handle: " + chainDifference + " sec.|");
        System.out.println("\\-----------------------------/");
        System.out.println("\n---------------------------------------------------------");
        if (parallelDifference < chainDifference) {
            System.out.println("----Parallel handle " + (chainDifference - parallelDifference) + " seconds faster----");
        } else if (chainDifference < parallelDifference) {
            System.out.println("----Chain handle " + (parallelDifference - chainDifference) + " seconds faster----");
        } else {
            System.out.println("----Equal runtime----");
        }
    }
}
