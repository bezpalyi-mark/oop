package ua.khpi.oop.bezpalyi13;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ua.khpi.oop.bezpalyi05.StringContainer;
import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi09.ListContainer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class LoadData {
    public static ListContainer<AddressBook> loadList() {
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
        return books;
    }
}
