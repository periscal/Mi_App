package Principal;
import java.util.ArrayList;
import java.util.HashMap;

import Eventos.EmisorEventos;
import Eventos.MiObjectoEvento;
import Eventos.OyenteEventos;


public class GestorDatos <K, V> implements OyenteEventos, Ficherador{

	protected K idProvisional; //Tipo generico para el id
	protected V valorProvisional;
	protected HashMap<K,V> pendientesInsertar;//DATOS pendientes de insertar en base de datos
	protected ArrayList<K> pendientesBorrar;//DATOS pendientes de borrar en base de datos
	protected HashMap<K,V> actuales;
	protected String ruta;
	protected EmisorEventos emisor;
	
	/**
	 * 
	 * @param ruta direccion del archivo.obj que contiene registros de uso actual
	 */
	@SuppressWarnings("unchecked")
	public GestorDatos(String ruta) {
		super();
		pendientesInsertar = new HashMap<>();
		pendientesBorrar   = new ArrayList<>();
		actuales           = new HashMap<>();
		emisor             = new EmisorEventos();
		
		this.ruta=ruta;
		this.actuales=(HashMap<K, V>) leer(ruta);
		if(actuales==null) actuales = new HashMap<>();
		
		System.out.println(actuales.size());
	}
	
	public void nuevoDato(K clave, V valor) {
		this.actuales.put(clave, valor);
		this.idProvisional=clave;
		this.valorProvisional=valor;
		this.pendientesInsertar.put(idProvisional, valorProvisional);
		guardado();
	}
	public void borraDato(K clave) {
		if(actuales.containsKey(clave)) actuales.remove(clave);
		if(pendientesInsertar.containsKey(clave)) pendientesInsertar.remove(clave); //Si no existe en "pendientesInsertar", entonces esta en la base de datos
		else pendientesBorrar.add(clave);
	}
	
	public void guardado() {escribir(ruta,actuales);}
	
	@Override
	public void capturarMiEvento(MiObjectoEvento evt) {
	/*	
		this.setDato((K) evt.getId(),(V) evt.getTrasnportado());
		pendientes.put(idProvisional, valorProvisional);
		avisarOyentes(this, valorProvisional, idProvisional);*/
	}

	public HashMap<K, V> getActuales() {return actuales;}
}

