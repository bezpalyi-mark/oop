package ua.khpi.oop.bezpalyi09;

import ua.khpi.oop.bezpalyi07.AddressBook;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XMLHandler<T extends ListContainer<AddressBook>> {

    public String saveToXML(String path, ListContainer<AddressBook> obj) {
        try {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateInString = simpleDateFormat.format(date);
            FileOutputStream fos = new FileOutputStream(new File(path + dateInString + ".xml"));
            XMLEncoder xmlEncoder = new XMLEncoder(fos);
            xmlEncoder.writeObject(obj);
            xmlEncoder.close();
            fos.close();
            return dateInString;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public T readFromXML(String path) {
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            XMLDecoder xmlDecoder = new XMLDecoder(fis);
            T value = (T) xmlDecoder.readObject();
            xmlDecoder.close();
            fis.close();
            return value;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
