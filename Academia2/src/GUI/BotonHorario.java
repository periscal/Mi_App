package GUI;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import Registrables.Alumno_Sesion;

public class BotonHorario extends BotonAbreVentana implements GUI{

	//private Horario h;
	private static Action accionAceptar;
	private ArrayList<String> sesiones;

	public BotonHorario(String nombre, ImageIcon icono, Horario h) {
		super(nombre, icono, accionAceptar, h);
		//this.h = h;
		sesiones=new ArrayList<String>();
		accionAceptar = new AbstractAction() {public void actionPerformed(ActionEvent e) {
			System.out.println("Boton aceptar horario");
			sesiones=h.devuelveSelecionSesion();}};
		this.v.add(h);
	}

	@Override
	public ArrayList<HashMap<Field, Object>> getAtributos(String id) {
		sesiones=((Horario) comp).devuelveSelecionSesion();
		Field[] declaredFields = (Alumno_Sesion.class).getDeclaredFields();
		ArrayList<HashMap<Field, Object>> a = new ArrayList<HashMap<Field, Object>>();
		System.out.println("Tamaño seleccion de sesiones: "+ sesiones.size()); //TODO Quitar comprobacion
		for(String s: sesiones) {
			HashMap<Field, Object> o = new HashMap<Field, Object>();
			o.put(declaredFields[0], id);
			o.put(declaredFields[1], s);
			a.add(o);
		}
		return a;
	}
}
