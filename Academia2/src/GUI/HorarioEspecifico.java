package GUI;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.HashMap;

import javax.swing.JLabel;

import Eventos.MiObjectoEvento;
import Eventos.OyenteEventos;
import Principal.Registrador;
import Registrables.*;

public class HorarioEspecifico extends Horario implements OyenteEventos{

	
	public void construir(HashMap<Class<?>,Registrador> registradores, Registrador registradorHorario) {
		// Construir Horario
		HashMap<String, TipoRegistrable> sesiones = registradores.get(Sesion.class).getActuales();
		HashMap<String, TipoRegistrable> alumnos = registradores.get(Alumno.class).getActuales();
		for(TipoRegistrable s: sesiones.values()) {
			this.insertarSesion((Sesion) s);
		}
		for(TipoRegistrable as : registradorHorario.getActuales().values()) {
			Alumno a = (Alumno) alumnos.get(((Alumno_Sesion) as).getClaveAlumno());
			this.insertarAlumno(a, ((Alumno_Sesion) as).getClaveSesion());
		}
	}

	/**
	 * Inserta una nueva sesión en el hoario
	 * @param sesion - Objeto tipo 'Sesion' que propocina la información para mostrar la nueva sesión
	 */
	public void insertarSesion(Sesion sesion) {
		String idses = sesion.getId();
		DayOfWeek dia = sesion.getDia();
		Time inicio =sesion.getHoraInicio();
		Time fin = sesion.getHoraFin();
		EtiquetaSesion eS= new EtiquetaSesion(idses,dia,inicio, fin);
		/** //Si se inserta una sesion, se redefinen las demas existentes
		for(EtiquetaSesion es : etiquetasSesiones.values()) {
			es.define();
		}**/
		etiquetasSesiones.put(idses,eS);
		dias.get(dia).add(eS);
		eS.add(new JLabel("Eii"));
	}

	/**
	 * Inserta un alumno en el horario en una sesión concreta
	 * @param alumno - objeto de tipo 'Alumno' que proporciona la información que se expresará en el horario
	 * @param idSesion - id de la sesión en la que se insertará el nuevo alumno
	 */
	public void insertarAlumno(Alumno alumno, String idSesion) {
		String idAlumno=alumno.getId();
		String nombre = alumno.getNombre();
		EtiquetaAlumno eA= new EtiquetaAlumno(idAlumno,nombre);
		etiquetasSesiones.get(idSesion).add(eA);
		etiquetasAlumnos.put(idAlumno, eA);
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
