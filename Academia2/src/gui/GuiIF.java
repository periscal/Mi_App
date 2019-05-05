package gui;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

public interface GuiIF{
	/**
	 * <h2><i>getAtributos</i></h2>
	 * <p><code> public ArrayList &ltHashMap&ltField,Object&gt&gt getAtributos(String id)</code></p>
	 * <p> Método definido en toda clase que implemente la interfaz GuiIF, que reune en Map los diferentes valores de cada campo
	 *  de de un registro jjunto con dicho campo como clave del Map.</p>
	 * @param id - id generado para el nuevo registro
	 * @return Maps de campos con sus respectivos valores
	 */
	public abstract List<HashMap<Field,Object>> getAtributos(String id);
	
	
	public abstract Class<?> clase();
}
