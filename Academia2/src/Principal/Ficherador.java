package Principal;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface Ficherador {
	
	public default Object leer(String ruta) {
		FileInputStream fis = null;
		ObjectInputStream entrada = null;
		File datos=new File(ruta);
		Object objectoLeer=null;
		if(datos.exists()) {
			try {
				fis = new FileInputStream(ruta);
				if(fis.available()!=0) { 
					entrada=new ObjectInputStream(fis);			
					objectoLeer= entrada.readObject();
					System.out.println("Se ha logrado leer el fichero: "+ ruta);
				}else System.out.println("El fichero '"+ ruta+"' esta vacio");
			}

			catch (FileNotFoundException e1) {e1.printStackTrace();}
			catch (IOException e) {	e.printStackTrace();}
			catch (ClassNotFoundException e) {e.printStackTrace();}
			finally {
				try {
					if (fis != null) {
						fis.close();
					}
					if (entrada != null) {
						entrada.close();
					}
				} catch (IOException e) {System.out.println(e.getMessage());}
			}
		}else System.out.println("No existe archivo para la ruta indicada");
		
		return objectoLeer;
	}
/*
	public default void leer2(String ruta, Object objLector) {
		FileInputStream fis = null;
		ObjectInputStream entrada = null;
		File datos=new File(ruta);
		Object objectoLeer=null;
		if(datos.exists()) {
			try {
				fis = new FileInputStream(ruta);
				entrada=new ObjectInputStream(fis);			
				objectoLeer= entrada.readObject();
				if(objectoLeer!=null) {
					objLector=objectoLeer;
				System.out.println("Se ha logrado leer el fichero: "+ ruta);
				}
				//datos.createNewFile();
				
			}

			catch (FileNotFoundException e1) {e1.printStackTrace();}
			catch (IOException e) {	e.printStackTrace();}
			catch (ClassNotFoundException e) {e.printStackTrace();}
			finally {
				try {
					if (fis != null) {
						fis.close();
					}
					if (entrada != null) {
						entrada.close();
					}
				} catch (IOException e) {System.out.println(e.getMessage());}
			}
		}
	}
	*/
	public default void escribir(String ruta, Object objetoEscribir) {
		//Se inicializan las variables necesarias. Asi se podra hacer referencia si estan fuera del "try"
		FileOutputStream fos = null;
		ObjectOutputStream salida = null;
		File datos=new File(ruta); //Se crea una reresentacion abstracta del archivo de datos

		try {
			datos.delete();
			datos.createNewFile(); // "true" si el archivo existe, sino lo crea y devuleve un "false"
			//Se crea el fichero
			fos = new FileOutputStream(datos);
			salida = new ObjectOutputStream(fos);
			//Se escribe el objeto en el fichero
			salida.writeObject(objetoEscribir);
			System.out.println("Se ha logrado escribir en fichero");

		} 
		catch (FileNotFoundException e) {System.out.println("1. "+e.getMessage());}
		catch (IOException e) {System.out.println("2. "+e.getMessage());} 
		finally {
			try {
				if(fos!=null) {fos.close();}
				if(salida!=null) {salida.close();System.out.println("Cerrado \n ----------------------------------");}
			} catch (IOException e) {System.out.println("3. "+e.getMessage());}
		}
		//return;

	}
}
