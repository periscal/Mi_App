package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class EtiquetaUsuario2 extends JButton{
	public static HashMap<String,EtiquetaUsuario2> etiquetasAlumnos = new HashMap<>();
	public static List<String> etiqAlumSeleccionadas = new ArrayList<>();
	private String iDUsuario;
	private Boolean seleccionable;
	private Boolean seleccionado;
	private MouseListener clickRaton;

	public EtiquetaUsuario2(String iDUsuario, String nombre) {
		super(nombre);
		this.iDUsuario=iDUsuario;
		seleccionable=true;
		seleccionado=false;
		
		Action accion = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiarSeleccion();
				autoInsercionList();
			}
		};
		this.setAction(accion);
		
		Border borde=BorderFactory.createLineBorder(Color.BLACK, 0);
		setBorder(borde);
		this.setPreferredSize(new Dimension(50,25));
		setBackground(Color.ORANGE);
		this.setText(nombre);//TODO nombre boton alumno
	}

	public Boolean isSelecionado() {return seleccionado;}
	
	public String getiDAlumno() {return iDUsuario;}
	
	public void cambiarSeleccion() {
		seleccionado=Boolean.logicalXor(seleccionado,true);

		if(seleccionable) {
			if(seleccionado) {this.setBackground(Color.BLUE);}
			else {this.setBackground(Color.ORANGE);}
		}
	}
	private void autoInsercionList() {
		if(seleccionado) etiqAlumSeleccionadas.add(iDUsuario);
		else etiqAlumSeleccionadas.remove(iDUsuario);
	}

	public void quitaSeleccionAlumno() {
		for(EtiquetaUsuario2 e : EtiquetaUsuario2.etiquetasAlumnos.values()) {
			e.cambiarSeleccion();
			EtiquetaUsuario.etiqAlumSeleccionadas.clear();
		}
	}
}
