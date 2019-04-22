package Principal;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import BBDD.BBDDconcreta;
import GUI.Aspecto;
import GUI.BotonHorario;
import GUI.FormularioGeneral;
import GUI.GrupoRegistradores;
import GUI.HorarioEspecifico;
import GUI.Pestanas;
import Registrables.*;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class Marco{

	public static JFrame ventana;
	static Class<?>[] entidades = {Sesion.class, Alumno.class, Profesor.class,Persona.class};
	static HashMap<Class<?>,HashMap<Class<?>, Registrador>> relaciones; //La clase clave sera la que muestra la clase valorstatic HashMap <Class<?>[],Relacionador> rel; //Asocia a cada para de entidades (relacion) con su Interfaz Grafica de Usuario
	public static HashMap<Class<?>,Registrador> registradores;
	static HashMap<Class<?>, GrupoRegistradores> grupos;
	public static HorarioEspecifico horarioGeneral;


	public static void main(String[] args){
		//================== Ventana Principal =================//
		ventana = new JFrame();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Aspecto.aplicarAspecto(ventana);
		registradores = new HashMap<>();
		/**
		//================== Registradores =================//
		registradores=new HashMap<Class<?>,Registrador>(); 
		for(Class<?> c1 : entidades) {
			registradores.put(c1, new Registrador(c1, new FormularioGeneral(c1)));
		}
		//================== Pestana Horario =================//
		HorarioEspecifico horarioPrincipal = new HorarioEspecifico();		
		HorarioEspecifico horarioFormulario = new HorarioEspecifico();		
		//================= Pestana Registros ================//
		// Registradores de RELACIONES
		BotonHorario bHorario = new BotonHorario("Horario", null,horarioFormulario);
		Registrador rHorario = new Registrador(Alumno_Sesion.class, bHorario);
		horarioPrincipal.construir(registradores, rHorario);
		horarioFormulario.construir(registradores, rHorario);

		relaciones = new HashMap<Class<?>,HashMap<Class<?>,Registrador>>();
		relaciones.put(Alumno.class, new HashMap<Class<?>, Registrador>()); 
		relaciones.get(Alumno.class).put(Sesion.class, rHorario);
		relaciones.put(Sesion.class, new HashMap<Class<?>, Registrador>()); 
		//relaciones.get(Sesion.class).put(Sesion.class, listaProfesores);

		// --- Grupos de registradores  ----//

		grupos = new HashMap <Class<?>, GrupoRegistradores>();
		for(Class<?> c1 : entidades) {
			GrupoRegistradores g = new GrupoRegistradores(c1);
			g.creaGrupo(c1,registradores.keySet(),registradores);
			grupos.put((Class<?>) c1, g);

			HashMap<Class<?>, Registrador> rel = relaciones.get(c1);
			if(rel!=null) for(Registrador r:rel.values()) g.getgD().add(r);
		}

		// --- Panel Menu Grupos de Registradores ----//
		JPanel registros = new JPanel(); 
		Aspecto.aplicarAspecto(registros);
		registros.setLayout(new BoxLayout(registros, BoxLayout.LINE_AXIS ));
		for(GrupoRegistradores r:grupos.values()) {
			registros.add(new Boton(r.getClaseAsociada().getSimpleName(), r, null));
		}
		 **/
		
		//Instanciacion GUIs
		FormularioGeneral f_Persona  = new FormularioGeneral(Persona.class);
		FormularioGeneral f_Alumno 	 = new FormularioGeneral(Alumno.class);
		FormularioGeneral f_Profesor = new FormularioGeneral(Profesor.class);
		FormularioGeneral f_Sesion 	 = new FormularioGeneral(Sesion.class);
		BotonHorario      b_Horario  = new BotonHorario("Horario",null, new HorarioEspecifico());
		//Instanciacion Registradores
		Registrador r_Persona 		= new Registrador(Persona.class,f_Persona);			registradores.put(Persona.class, r_Persona);
		Registrador r_Alumno 		= new Registrador(Alumno.class,f_Alumno);			registradores.put(Alumno.class, r_Alumno);
		Registrador r_Profesor  	= new Registrador(Profesor.class,f_Profesor);		registradores.put(Profesor.class, r_Profesor);
		Registrador r_Sesion 		= new Registrador(Sesion.class,f_Sesion);			registradores.put(Sesion.class, r_Sesion);
		Registrador r_AlumnoSesion	= new Registrador(Alumno_Sesion.class, b_Horario);	registradores.put(Alumno_Sesion.class, r_AlumnoSesion);
		//Instanciacion GruposRegistradores
		GrupoRegistradores gr_Persona				= new GrupoRegistradores();
		GrupoRegistradores gr_PersonaAlumnoHorario  = new GrupoRegistradores(); 
		GrupoRegistradores gr_PersonaProfesor 		= new GrupoRegistradores(); 
		GrupoRegistradores gr_Sesion 		        = new GrupoRegistradores(); 
		//Insertar  registradores
		gr_PersonaAlumnoHorario.insertarReg(r_Persona);
		gr_PersonaAlumnoHorario.insertarReg(r_Alumno);
		gr_PersonaAlumnoHorario.insertarReg(r_AlumnoSesion);

		gr_PersonaProfesor.insertarReg(r_Persona);
		gr_PersonaProfesor.insertarReg(r_Profesor);

		gr_Sesion.insertarReg(r_Sesion);

		//Preparacion Botones de Grupos de Registradores
		JPanel registros = new JPanel(); 
		Aspecto.aplicarAspecto(registros);
		registros.setLayout(new BoxLayout(registros, BoxLayout.Y_AXIS ));

		registros.add(gr_Persona.botonGrupo("Persona", null));
		registros.add(gr_PersonaAlumnoHorario.botonGrupo("Alumno",null));
		registros.add(gr_PersonaProfesor.botonGrupo("Profesor", null));
		registros.add(gr_Sesion.botonGrupo("Sesion", null));
		
		//=========== Construccion Horario ==================
		//Horario Principal
		horarioGeneral    = new HorarioEspecifico();
		horarioGeneral.construir();
		/*//Insercion de todas las sesiones en el horario
		for(TipoRegistrable s: r_Sesion.getActuales().values()) {horarioGeneral.insertarSesion((Sesion) s);}

		// Insercion de todos los alumnos en su sesion correspondiente
		for(TipoRegistrable as : r_AlumnoSesion.getActuales().values()) {
			Alumno a = (Alumno)  r_Alumno.getActuales().get(((Alumno_Sesion) as).getClaveAlumno());
			horarioGeneral.insertarAlumno(a, ((Alumno_Sesion) as).getClaveSesion());
		}
		horarioGeneral.repaint();
		//=====================================================
		 */

		// =================== BASE de DATOS =====================//
		String server ="jdbc:mysql://localhost:3306/academia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"  /*"jdbc:mysql://C:\\Users\\aquem\\Desktop\\Programing\\WorkSpace PC\\Academia2\\Drivers\\academia.sql"*/;
		try {
			BBDDconcreta bd = new BBDDconcreta(server);
		} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//======================= GUI Principal ========================//
		Pestanas p = new Pestanas();
		p.addTab("Horario", horarioGeneral);
		p.addTab("Registros", registros);
		ventana.add(p);
		ventana.pack();
		ventana.setVisible(true);
	}
}


