package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import principal.*;

public class GrupoRegistradores extends GuiFrame{

	private Class<?> claseAsociada;
	private HashMap<Class<?>,Registrador> regs;
	private JButton botonGrupo;

	public GrupoRegistradores(String nombre,ImageIcon icono){
		super();
		regs = new HashMap<>();
		botonGrupo = new JButton();
		
		//----------------- ACCION Boton ACEPTAR-------------------//
		Action accionAceptar = new AbstractAction() {public void actionPerformed(ActionEvent e) {nuevosRegistros();}};
		super.aceptarCancelar.accionAceptar(accionAceptar);
		//----------------------------------------------------------//
	}

	public void nuevosRegistros() {
		String id = java.util.UUID.randomUUID().toString();	
		for(Registrador r:regs.values()) r.nuevoRegistro(id);
	}

	public void creaGrupo(Class<?> entidad,Set<Class<?>> entidades,HashMap<Class<?>,Registrador> registradores) {
		Class<?> superclase = entidad.getSuperclass();
		boolean superclasePresente=false;

		for(Class<?> c :entidades) if(c.equals(superclase)) superclasePresente=true;

		if(superclasePresente) this.creaGrupo((Class<?>) superclase,entidades,registradores);
		regs.put(entidad, registradores.get(entidad));
	}

	public Class<?> getClaseAsociada() {return claseAsociada;}
	public void setClaseAsociada(Class<?> claseAsociada) {this.claseAsociada = claseAsociada;}

	public void insertarReg(Registrador r) {regs.put(r.clase(),r);}

	public void  visibilizar() {
		panel.removeAll();
		for(Registrador r:regs.values()) panel.add((JComponent) r.getGUI());
		this.setVisible(true);
	}

	public JButton botonGrupo(String nombre,ImageIcon icono) {
		//botonGrupo.setIcon(icono);
		Aspecto.aplicarAspecto(botonGrupo);
		Action accionVisibilizar = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {visibilizar();}
		};
		//Necesariamente se debe fijar 1º la accion y despues el texto del boton
		botonGrupo.setAction(accionVisibilizar);
		botonGrupo.setText(nombre);
		return botonGrupo;
	}
	/*
	public void setBotonGrupo(JButton boton) {
		Action accionVisibilizar = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {visibilizar();}
		};
		boton.setAction(accionVisibilizar);
		this.botonGrupo=boton;
	}
	*/
}