package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class EtiquetaUsuario extends JLabel{
	public static HashMap<String,EtiquetaUsuario> etiquetasAlumnos = new HashMap<>();
	public static List<String> etiqAlumSeleccionadas = new ArrayList<>();
	private String iDUsuario;
	private Boolean seleccionable;
	private Boolean seleccionado;
	private MouseListener clickRaton;

	public EtiquetaUsuario(String iDUsuario, String nombre) {
		super(nombre);
		this.iDUsuario=iDUsuario;
		seleccionable=true;
		seleccionado=false;

		clickRaton = new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				cambiarSeleccion();
				autoInsercionList();
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		};
		this.addMouseListener(clickRaton);
		Border borde=BorderFactory.createLineBorder(Color.BLACK, 0);
		setBorder(borde);
		this.setPreferredSize(new Dimension(50,25));
		setBackground(Color.ORANGE);
		this.setText("Prueba");
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
		for(EtiquetaUsuario e : EtiquetaUsuario.etiquetasAlumnos.values()) {
			e.cambiarSeleccion();
			EtiquetaUsuario.etiqAlumSeleccionadas.clear();
		}
	}
}
