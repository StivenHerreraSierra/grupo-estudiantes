package co.uniquindio.grupo.persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;

public class Persistencia {
	public static void serializarBin (String ruta, Object objeto)
			throws IOException {
		FileOutputStream escritor = new FileOutputStream (ruta);
		ObjectOutputStream escritorObjeto = new ObjectOutputStream(escritor);
		escritorObjeto.writeObject(objeto);
		escritor.close();
		escritorObjeto.close();
	}
	
	public static Object deserializarBin (String ruta) 
			throws IOException, ClassNotFoundException {
		FileInputStream lector = new FileInputStream (ruta);
		ObjectInputStream lectorObjeto = new ObjectInputStream (lector);
		Object objeto = lectorObjeto.readObject();
		lector.close();
		lectorObjeto.close();
		return objeto;
	}
	
	public static void serializarXML (String ruta, Object objeto)
			throws IOException {
		XMLEncoder escritorObjeto = new XMLEncoder (new FileOutputStream (ruta));
		escritorObjeto.writeObject(objeto);
		escritorObjeto.close();
	}
	
	public static Object deserializarXML (String ruta) 
			throws IOException {
		XMLDecoder lectorObjeto = new XMLDecoder (new FileInputStream (ruta));
		Object objeto = lectorObjeto.readObject();
		lectorObjeto.close();
		return objeto;
	}
}
