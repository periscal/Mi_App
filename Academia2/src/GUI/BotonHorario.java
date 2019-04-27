package GUI;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import Registrables.Alumno_Sesion;

public class BotonHorario extends BotonAbreVentana implements GuiIF{

	private Class<?> clase;
	private List<String> sesiones;
	public HorarioEspecifico horario;

	public BotonHorario(String nombre, ImageIcon icono, Horario h) {
		super(nombre, icono/*, horario*/);
		sesiones=new ArrayList<>();
		horario = new HorarioEspecifico();
		
		Action accionAceptar = new AbstractAction() {public void actionPerformed(ActionEvent e) {
			System.out.println("Boton aceptar horario");
			sesiones=horario.devuelveSelecionSesion();
			v.dispose();
			}
		};
		v.aceptarCancelar.accionAceptar(accionAceptar);
	}

	@Override
	public List<HashMap<Field, Object>> getAtributos(String id) {
		// Devuelve la seleccion de sesiones marcadas en el horario
		sesiones=((Horario) horario).devuelveSelecionSesion();
		
		Field[] declaredFields = (Alumno_Sesion.class).getDeclaredFields();
		List<HashMap<Field, Object>> a = new ArrayList<>();
		
		for(String s: sesiones) {
			HashMap<Field, Object> o = new HashMap<>();
			o.put(declaredFields[0], id);
			o.put(declaredFields[1], s);
			a.add(o);
		}
		return a;
	}

	public Class<?> clase() {return clase;}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		horario.construir();
		v.add(horario);
		v.setVisible(true);
	}
}
