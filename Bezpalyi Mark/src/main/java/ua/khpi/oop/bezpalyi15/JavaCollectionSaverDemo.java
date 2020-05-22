package ua.khpi.oop.bezpalyi15;

import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;
import ua.khpi.oop.bezpalyi13.LoadData;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class JavaCollectionSaverDemo {

    private static String date = "";

    private final static String MENU = "1. Standard Serialize\n" +
            "2. Standard Deserialize\n" +
            "3. Save to XML file\n" +
            "4. Read from XML file\n" +
            "5. Show items\n" +
            "Any else number for exit";

    private static boolean standardExists = false;

    private static boolean xmlExists = false;

    public static void main(String[] args) {
        JavaCollectionSerializing saver = new JavaCollectionSerializing();
        List<AddressBook> list = new ArrayList<>();
        ListContainer<AddressBook> bookListContainer = LoadData.loadList();
        System.out.println("Now you have 2 items: ");
        for (int i = 0; i < 2; i++) {
            list.add(bookListContainer.get(i));
            System.out.println(bookListContainer.get(i));
        }
        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println(MENU);
        try {
            choice = scanner.nextInt();
            while (choice >= 1 && choice <= 5) {
                switch (choice) {
                    case 1:
                        if (list.size() != 0) {
                            saver.standardSerialize(list, "./data/xmlData");
                            standardExists = true;
                            list = new ArrayList<>();
                            System.out.println("Now container empty");
                        } else {
                            System.out.println("List is already empty");
                        }
                        break;
                    case 2:
                        if (standardExists) {
                            list = saver.standardDeserialize("./data/xmlData");
                            System.out.println("Size now: " + list.size());
                            System.out.println(list);
                        } else {
                            System.out.println("You have not copy now, please do serialize first");
                        }
                        break;
                    case 3:
                        if (list.size() != 0) {
                            date = saver.XmlSerialize(list, "./data/fileData");
                            xmlExists = true;
                            list = new ArrayList<>();
                            System.out.println("Now container empty");
                        } else {
                            System.out.println("List is already empty");
                        }
                        break;
                    case 4:
                        if (xmlExists) {
                            list = (List<AddressBook>) saver.XmlDeserialize("./data/fileData" + date + ".xml");
                            System.out.println("Size now: " + list.size());
                            System.out.println(list);
                        } else {
                            System.out.println("You have not copy now, please save to xml first");
                        }
                        break;
                    case 5:
                        System.out.println(list);
                        break;
                }
                System.out.println(MENU);
                choice = scanner.nextInt();
            }
        } catch (InputMismatchException e) {
            System.out.println("Interrupt because mismatch input");
        }
    }
}
