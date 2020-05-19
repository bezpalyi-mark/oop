package ua.khpi.oop.bezpalyi13;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import ua.khpi.oop.bezpalyi05.StringContainer;
import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

public class DemoThreader {
    public static void main(String[] args) {
        ListContainer<AddressBook> books = new ListContainer<>();
        try {
            File file = new File("src/main/resources/dataMay-19-2020.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("record");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = Date.from(Instant.now());

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    AddressBook.Builder builder = new AddressBook.Builder();
                    Element eElement = (Element) node;
                    try {
                        date = simpleDateFormat.parse(eElement.getElementsByTagName("dateOfBirth").item(0).getTextContent());
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    StringContainer phones = new StringContainer();
                    phones.add(eElement.getElementsByTagName("phoneNumbers").item(0).getTextContent());
                    builder
                            .setLastName(eElement.getElementsByTagName("lastName").item(0).getTextContent())
                            .setFirstName(eElement.getElementsByTagName("firstName").item(0).getTextContent())
                            .setSecondName(eElement.getElementsByTagName("secondName").item(0).getTextContent())
                            .setDateOfBirth(date)
                            .setPhoneNumbers(phones)
                            .setAddress(eElement.getElementsByTagName("address").item(0).getTextContent())
                            .setEditTime(eElement.getElementsByTagName("editDateAndTime").item(0).getTextContent());
                   books.add(builder.build());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Size of container: " + books.size());
        System.out.print("Enter waiting time in seconds: ");
        int timer = new Scanner(System.in).nextInt();
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
            Thread.sleep(timer);
            for(int i = 0; i < oldRecords.length; i++) {
                oldRecords[i].stop();
                evenNumbersInPhones[i].stop();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Main thread done!");
    }
}

