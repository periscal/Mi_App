package Eventos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class EmisorEventos {

	protected HashMap<OyenteEventos,OyenteEventos> conjuntoOyentes=new HashMap<>();

	public void avisaOyente(OyenteEventos listener, Object transportado, Object idProvisional) {
		// Creamos el objeto que tiene la información del evento
		MiObjectoEvento personaEvObj = new MiObjectoEvento (listener, transportado, idProvisional);
		// Ejecutamos el metodo manejador del evento con los parametros necesarios
		(listener).capturarMiEvento(personaEvObj);
	}
	
	public void avisarOyentes(Object fuente, Object transportado, Object idProvisional) {
		if(!conjuntoOyentes.isEmpty()) {

			// Creamos el objeto que tiene la información del evento
			MiObjectoEvento personaEvObj = new MiObjectoEvento (fuente, transportado, idProvisional);

			// Recorremos la lista para ejecutar el metodo NombreCambiado de cada manejador almacenado
			for(OyenteEventos listener: conjuntoOyentes.values()){

				System.out.println("Emisor de eventos de "+ fuente.getClass().getSimpleName()+" ha avisado a: \n"+listener.getClass().getSimpleName());

				// Ejecutamos el metodo manejador del evento con los parametros necesarios
				(listener).capturarMiEvento(personaEvObj);
			}
		}
		else {System.out.println("No hay oyentes para avisar");
		}
	}
	
	public void setOyente(OyenteEventos oyente) {conjuntoOyentes.put(oyente,oyente);}

}
