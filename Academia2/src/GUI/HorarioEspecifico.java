package GUI;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.HashMap;
import Eventos.MiObjectoEvento;
import Eventos.OyenteEventos;
import Principal.Marco;
import Principal.Registrador;
import Registrables.*;

public class HorarioEspecifico extends Horario implements OyenteEventos{

	/**
	 * <h2><i> construir </i></h2>
	 * <p><code> public void construir()</code></p>
	 * <p>Inserta las sesiones y en cada una de las mismas sus correspondientes alumnos.</p>
	 * <p> Para ello, consigue las sesiones, alumnos y la relación entre sesiones y alumnos
	 * de sus correspondientes 'Registradores' que están declarados en la clase principal.</p>
	 */
	public void construir() {

		Registrador r_Sesion		= Marco.registradores.get(Sesion.class);
		Registrador r_AlumnoSesion	= Marco.registradores.get(Alumno_Sesion.class); 
		Registrador r_Alumno		= Marco.registradores.get(Alumno.class);

		//Insercion de todas las sesiones en el horario
		for(TipoRegistrable s: r_Sesion.getActuales().values()) {this.insertarSesion((Sesion) s);}

		// Insercion de todos los alumnos en su sesion correspondiente
		for(TipoRegistrable as : r_AlumnoSesion.getActuales().values()) {
			Alumno a = (Alumno)  r_Alumno.getActuales().get(((Alumno_Sesion) as).getClaveAlumno());
			this.insertarAlumno(a, ((Alumno_Sesion) as).getClaveSesion());
		}
		this.revalidate();
		this.repaint();
	}

	/**
	 * <h2><i> reConstruir </i></h2>
	 * <p><code> public void reConstruir()</code></p>
	 * <p>Elimina todos los componentes del horario, las sesiones con sus alumnos.</p>
	 * <p> Luego, hace uso del método <code>reConstruir()</code></p>
	 */
	public void reConstruir() {
		this.removeAll();
		this.construir();
	}

	/**
	 * <h2><i> insertarSesion </i></h2>
	 * <p><code> public void insertarSesion(Sesion sesion)</code></p>
	 * <p>Inserta una nueva sesion en el horario</p>
	 * @param sesion - Objeto tipo 'Sesion' que propocina la información para mostrar la nueva sesión
	 */
	public void insertarSesion(Sesion sesion) {
		String idSesion = sesion.getId();
		DayOfWeek dia = sesion.getDia();
		Time inicio =sesion.getHoraInicio();
		Time fin = sesion.getHoraFin();
		insertarFranjaHoraria(idSesion,dia,inicio,fin);
	}

	/**
	 * <h2><i> insertarAlumno </i></h2>
	 * <p><code> public void insertarAlumno(Alumno alumno, String idSesion) </code></p>
	 * <p>Inserta un alumno en el horario en una sesión concreta</p>
	 * @param alumno - objeto de tipo 'Alumno' que proporciona la información que se expresará en el horario
	 * @param idSesion - id de la sesión en la que se insertará el nuevo alumno
	 */
	public void insertarAlumno(Alumno alumno, String idSesion) {
		String idAlumno=alumno.getId();
		String nombre = alumno.getNombre();
		insertarUsuario(idAlumno,nombre,idSesion);
		System.out.println("Se ha insertado alumno en horario");
	}

	@Override
	public void capturarMiEvento(MiObjectoEvento evt) {
		Object o =evt.getTrasnportado();
		Class<? > oclass = o.getClass();

		if(oclass==Alumno.class)      this.insertarAlumno((Alumno) o,(String) evt.getId());
		else if(oclass==Sesion.class) this.insertarSesion((Sesion) o);

		this.repaint();
	}
}
