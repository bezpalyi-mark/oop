package ua.khpi.oop.bezpalyi06;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeHandler {
	
	 public void serializeObject(Object obj, String pathToFile) {
		System.out.println("Serializing...");
		try {
			FileOutputStream fos = new FileOutputStream(pathToFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
	        oos.flush();
	        oos.close();
	        
	        fos.flush();
	        fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public Object deserializeObject(String pathToFile) {
		System.out.println("Deserializing...");
		Object obj = null;
		try {
			FileInputStream fis = new FileInputStream(pathToFile);
			ObjectInputStream ois = new ObjectInputStream(fis);	
			obj = ois.readObject();
			
			fis.close();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
