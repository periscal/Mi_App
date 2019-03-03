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
	static HashMap<Class<?>,Registrador> registradores;
	static HashMap<Class<?>, GrupoRegistradores> grupos;
	
	static class Boton extends JButton implements ActionListener{
		private GrupoRegistradores rE;
		public Boton(String nombre, GrupoRegistradores rE,ImageIcon icono) {
			super(nombre, icono);
			this.rE=rE;
			this.addActionListener((ActionListener) this);
		}
		public void actionPerformed(ActionEvent e) {
			rE.rePintar();
			this.rE.setVisible(true);
			}
		
		public void addToFrame(Component componente) {rE.add(componente);}
	}
	
	public static void main(String[] args){
		//================== Ventana Principal =================//
		ventana = new JFrame();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Aspecto.aplicarAspecto(ventana);
		
		//================== Registradores =================//
		registradores=new HashMap<Class<?>,Registrador>(); 
		for(Class<?> c1 : entidades) {
			registradores.put(c1, new Registrador(c1, new FormularioGeneral(c1)));
		}
		//================== Pestana Horario =================//
		HorarioEspecifico horarioPrincipal = new HorarioEspecifico();		
		HorarioEspecifico horarioFormulario = new HorarioEspecifico();		
		//================= Pestana Registros ================//
		/** Registradores de RELACIONES**/
		BotonHorario bHorario = new BotonHorario("Horario", null,horarioFormulario);
		Registrador rHorario = new Registrador(Alumno_Sesion.class, bHorario);
		horarioPrincipal.construir(registradores, rHorario);
		horarioFormulario.construir(registradores, rHorario);
		// Relaciones GUIs
		/**
		 * Esto debe ser editado segun corresponda a las diferentes relaciones de la BBDD 
		 **/
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
			if(rel!=null) for(Registrador r:rel.values()) g.insertarRegistrador(r);
		}
		
		// --- Panel Menu Grupos de Registradores ----//
		JPanel registros = new JPanel(); 
		Aspecto.aplicarAspecto(registros);
		registros.setLayout(new BoxLayout(registros, BoxLayout.LINE_AXIS ));
		for(GrupoRegistradores r:grupos.values()) {
			registros.add(new Boton(r.getClaseAsociada().getSimpleName(), r, null));
			/**Action accionAceptar = new AbstractAction() {public void actionPerformed(ActionEvent e) {r.nuevosRegistros();}};
			registros.add(new BotonAbreVentana(r.getClaseAsociada().getSimpleName(), null, accionAceptar, r));**/
		}
		
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
		p.addTab("Horario", horarioPrincipal);
		p.addTab("Registros", registros);
		ventana.add(p);
		ventana.setVisible(true);
	}
}


