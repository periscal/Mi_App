package Principal;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			
			List<HashMap<Field, Object>> l = ui.getAtributos(iD);
			
			for(HashMap<Field, Object> h:l) {
				TipoRegistrable nuevoRegistro =  (TipoRegistrable) c.getConstructor().newInstance();
				for(Map.Entry<Field, Object> entry:h.entrySet()) nuevoRegistro.setGeneral(entry.getKey(),entry.getValue());

				nuevoRegistro.setiDprovisional(iD);
				nuevoRegistro.setId(iD);
				this.nuevoDato(iD, nuevoRegistro);
				
			}
		} 
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {e.printStackTrace();
		}
	}
	public GuiIF getGUI() {return ui;}
	public Class<?> clase() {return c;}
}
