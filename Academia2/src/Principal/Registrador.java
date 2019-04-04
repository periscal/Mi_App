package Principal;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import GUI.GuiIF;
import Registrables.TipoRegistrable;

public class Registrador extends GestorDatos <String, TipoRegistrable>{
	protected GuiIF ui;
	private Class<?> c;

	public Registrador(Class<?> nameclass, GuiIF gui){
		super("Registros/"+nameclass.getSimpleName()+".obj");
		this.c=nameclass;
		this.ui=gui;			//Cada Registrador de Entidad tendra su propio formulario
	}


	public void nuevoRegistro(String iD){

		try {
			TipoRegistrable nuevoRegistro =  (TipoRegistrable) c.getConstructor().newInstance();

			ArrayList<HashMap<Field, Object>> l = ui.getAtributos(iD);
			for(HashMap<Field, Object> h:l) {
				for(Field campoClase:h.keySet()) nuevoRegistro.setGeneral(campoClase, h.get(campoClase));

				nuevoRegistro.setiDprovisional(iD);
				nuevoRegistro.setId(iD);
				this.nuevoDato(iD, nuevoRegistro);
				System.out.println("Se ha introducido nuevo Registro");
			}
		} 
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {e.printStackTrace();
		}
	}
	public GuiIF getGUI() {return ui;}
}
