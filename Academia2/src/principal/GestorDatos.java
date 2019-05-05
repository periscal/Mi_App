package principal;
import java.util.ArrayList;
import java.util.HashMap;

import eventos.EmisorEventos;
import eventos.MiObjectoEvento;
import eventos.OyenteEventos;


public class GestorDatos <K, V> implements OyenteEventos, Ficherador{

	protected K idProvisional; //Tipo generico para el id
	protected V valorProvisional;
	protected HashMap<K,V> pendientesInsertar;//DATOS pendientes de insertar en base de datos
	protected ArrayList<K> pendientesBorrar;//DATOS pendientes de borrar en base de datos
	protected HashMap<K,V> actuales;
	protected String ruta;
	protected EmisorEventos emisor;

	/**
	 * <p>Crea un objeto <code>GestorDatos</code> que gestiona el almacenamiento y borrado de registros
	 * @param ruta - direccion del <i>archivo</i>.obj que contiene registros de uso actual
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

		System.out.println(actuales.size());//TODO quitar mensaje de GestorDatos
	}
	
	/**
	 * <h1><i> nuevoDato </i></h1>
	 * <p><code> public void nuevoDato(K clave, V valor)</code></p>
	 * <p>Almacena un nuevo dato con su clave asociada</p>
	 * @param clave - Clave del objeto que almacenará GestorDatos
	 * @param valor - Objeto a almacenar
	 */
	public void nuevoDato(K clave, V valor) {
		this.actuales.put(clave, valor);
		this.idProvisional=clave;
		this.valorProvisional=valor;
		this.pendientesInsertar.put(idProvisional, valorProvisional);
		guardado();
	}
	
	/**
	 * <h1><i> borraDato </i></h1>
	 * <p><code>public void borraDato(K clave)</code></p>
	 * <p>Borra, si existe, el dato asociado a la clave introducida</p>
	 * @param clave - Clave del objeto que almacenará GestorDatos
	 */
	public void borraDato(K clave) {
		if(actuales.containsKey(clave)) actuales.remove(clave);
		if(pendientesInsertar.containsKey(clave)) pendientesInsertar.remove(clave); //Si no existe en "pendientesInsertar", entonces esta en la base de datos
		else pendientesBorrar.add(clave);
	}

	public void guardado() {escribir(ruta,actuales);}

	@Override
	public void capturarMiEvento(MiObjectoEvento evt) {// TODO Revisar en GestorDatos
		/*	
		this.setDato((K) evt.getId(),(V) evt.getTrasnportado());
		pendientes.put(idProvisional, valorProvisional);
		avisarOyentes(this, valorProvisional, idProvisional);*/
	}

	public HashMap<K, V> getActuales() {return actuales;}
}

