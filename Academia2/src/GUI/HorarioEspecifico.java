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

	public void insertarSesion(Sesion s) {
		String ID = s.getId();
		DayOfWeek dia = s.getDia();
		Time inicio =s.getHoraInicio();
		Time fin = s.getHoraFin();
		EtiquetaSesion eS= new EtiquetaSesion(ID,dia,inicio, fin);
		/** //Si se inserta una sesion, se redefinen las demas existentes
		for(EtiquetaSesion es : etiquetasSesiones.values()) {
			es.define();
		}**/
		etiquetasSesiones.put(ID,eS);
		dias.get(dia).add(eS);
		eS.add(new JLabel("Eii"));
	}


	public void insertarAlumno(Alumno a, String IDSesion) {
		String IDAlumno=a.getId();
		String nombre = a.getNombre();
		EtiquetaAlumno eA= new EtiquetaAlumno(IDAlumno,nombre);
		etiquetasSesiones.get(IDSesion).add(eA);
		etiquetasAlumnos.put(IDAlumno, eA);
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
