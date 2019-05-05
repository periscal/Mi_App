package eventos;
import java.util.EventObject;

public class MiObjectoEvento extends EventObject {
	// Variable de instancia para diferencia a cada objeto de este tipo
	private Object id;
	private Object transportado;

	// Constructor parametrizado
	public MiObjectoEvento(Object fuente, Object transportado, Object idProvisional) {
		// Se le pasa el objeto como parametro a la superclase
		super( fuente );
		// Se guarda el identificador del objeto
		this.id = idProvisional;
		this.transportado=transportado;
	}

	// Método para recuperar el identificador del objeto

	public Object getTrasnportado() {return this.transportado;}

	public Object getId() {return id;}
	
}

