package principal;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.GuiIF;
import registrables.TipoRegistrable;

public class Registrador extends GestorDatos <String, TipoRegistrable>{
	protected GuiIF gui;
	private Class<?> c;

	/**
	 * <p>Crea un objeto <code>Registrador</code> que gestiona el almacenamiento y borrado de registros <code>TipoRegistrable</code>
	 * @param nameclass - clase que hereda de <code>TipoRegistrable</code> de los datos que registrará
	 * @param gui - Interfaz Gráfica de Usuario asociada al Registrador
	 */
	public Registrador(Class<?> nameclass, GuiIF gui){
		super("Registros/"+nameclass.getSimpleName()+".obj");
		this.c=nameclass;
		this.gui=gui; //Cada Registrador de Entidad tendra su propio formulario
	}

	/**
	 * <h1><i> nuevoRegistro </i></h1>
	 * <p><code> public void nuevoRegistro(String iD)</code></p>
	 * <p>Inserta los nuevos registros cuyos valores han sido extraídos
	 *  de la GUI asociada al objeto <code>TipoRegistrable</code></p>
	 *  @param iD - valor del ID de los nuevos registros
	 */
	public void nuevoRegistro(String iD){
		try {
			// Lista de los Maps correspondientes a cada nuevo registro
			List<HashMap<Field, Object>> l = gui.getAtributos(iD);
			
			//Se crea un TipoRegistrable por cada 'Map'
			for(HashMap<Field, Object> h:l) {
				TipoRegistrable nuevoRegistro =  (TipoRegistrable) c.getConstructor().newInstance();
				for(Map.Entry<Field, Object> entry:h.entrySet()) nuevoRegistro.setGeneral(entry.getKey(),entry.getValue());

				nuevoRegistro.setiDprovisional(iD);
				nuevoRegistro.setId(iD);
				this.nuevoDato(nuevoRegistro.getId(), nuevoRegistro);
			}
		} 
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {e.printStackTrace();}
	}
	
	/**
	 * <h1><i> getGUI </i></h1>
	 * <p><code> public GuiIF getGUI()</code></p>
	 * @return gui - Interfaz Gráfica de Usuario asociada al Registrador
	 */
	public GuiIF getGUI() {return gui;}
	
	/**
	 * <h1><i> clase </i></h1>
	 * <p><code>public Class<?> clase()</code></p>
	 * @return c - clase asociada al Registrador
	 */
	public Class<?> clase() {return c;}
}
