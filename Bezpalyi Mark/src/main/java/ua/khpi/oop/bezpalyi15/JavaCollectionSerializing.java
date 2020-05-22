package ua.khpi.oop.bezpalyi15;

import ua.khpi.oop.bezpalyi06.SerializeHandler;
import ua.khpi.oop.bezpalyi07.AddressBook;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class JavaCollectionSerializing {
    private final SerializeHandler serializeHandler;

    public JavaCollectionSerializing() {
        serializeHandler = new SerializeHandler();
    }

    public void standardSerialize(Collection<AddressBook> addressBooks, String path) {
        serializeHandler.serializeObject(addressBooks, path);
    }

    public List<AddressBook> standardDeserialize(String path) {
        return (List<AddressBook>) serializeHandler.deserializeObject(path);
    }

    public String XmlSerialize(Collection<AddressBook> addressBooks, String path) {
        try {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateInString = simpleDateFormat.format(date);
            FileOutputStream fos = new FileOutputStream(new File(path + dateInString + ".xml"));
            XMLEncoder xmlEncoder = new XMLEncoder(fos);
            xmlEncoder.writeObject(addressBooks);
            xmlEncoder.close();
            fos.close();
            return dateInString;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public Collection<AddressBook> XmlDeserialize(String path) {
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            XMLDecoder xmlDecoder = new XMLDecoder(fis);
            Collection<AddressBook> value = (Collection<AddressBook>) xmlDecoder.readObject();
            xmlDecoder.close();
            fis.close();
            return value;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
