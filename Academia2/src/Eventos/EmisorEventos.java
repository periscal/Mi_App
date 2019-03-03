package Eventos;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class EmisorEventos {

	protected List<OyenteEventos> conjuntoOyentes=new ArrayList<OyenteEventos>();

	public void avisarOyentes(Object fuente, Object transportado, Object idProvisional) {
		if(!conjuntoOyentes.isEmpty()) {

			ListIterator<OyenteEventos> li = conjuntoOyentes.listIterator();

			// Creamos el objeto que tiene la información del evento
			MiObjectoEvento personaEvObj = new MiObjectoEvento (fuente, transportado, idProvisional);

			// Recorremos la lista para ejecutar el metodo NombreCambiado de cada manejador almacenado
			while (li.hasNext()) {

				// Convertimos (CAST) de nuestro objeto
				OyenteEventos listener = (OyenteEventos)li.next();
				System.out.println("Emisor de eventos de "+ fuente.getClass().getSimpleName()+" ha avisado a: \n"+listener.getClass().getSimpleName());


				// Ejecutamos el metodo manejador del evento con los parametros necesarios
				(listener).capturarMiEvento(personaEvObj);
			}
		}
		else {System.out.println("No hay oyentes para avisar");
		}
	}
	public void setOyente(OyenteEventos oyente) {
		conjuntoOyentes.add(oyente);
	}

}
