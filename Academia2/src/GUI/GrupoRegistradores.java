package GUI;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;

import Eventos.EmisorEventos;
import Principal.*;

public class GrupoRegistradores extends GuiFrame{
	private ArrayList<Registrador> gD; //TODO transformarlo en HashMap siendo la clave la clase correspondiente

	private Class<?> claseAsociada;
	private HashMap<Class<?>,Registrador> regs;
	
	public GrupoRegistradores(){
		super();
		this.gD = new ArrayList<>();
		regs = new HashMap<>();
		//----------------- BOTONES ACEPTAR/CERRAR -------------------//
		Action accionAceptar = new AbstractAction() {public void actionPerformed(ActionEvent e) {nuevosRegistros();}};
		super.aceptarCancelar.getAceptar().setAction(accionAceptar);
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
		gD.add(registradores.get(entidad));
	}
	
	public List<Registrador> getgD() {return gD;}
	public void setgD(List<Registrador> gD) {this.gD = (ArrayList<Registrador>) gD;}

	public Class<?> getClaseAsociada() {return claseAsociada;}
	public void setClaseAsociada(Class<?> claseAsociada) {this.claseAsociada = claseAsociada;}
	
	public void insertarReg(Registrador r) {regs.put(r.clase(),r);}
	
	public void  visibilizar() {
		panel.removeAll();
		for(Registrador r:regs.values()) panel.add((JComponent) r.getGUI());
		this.setVisible(true);
	}

}
