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

import Principal.*;

public class GrupoRegistradores extends GuiFrame{
	private ArrayList<Registrador> gD; //TODO transformarlo en HashMap siendo la clave la clase correspondiente

	private Class<?> claseAsociada;
	
	public GrupoRegistradores(Class<?> claseAsociada){
		super();
		this.gD = new ArrayList<>();
		this.claseAsociada=claseAsociada;
		
		//----------------- BOTONES ACEPTAR/CERRAR -------------------//
		Action accionAceptar = new AbstractAction() {public void actionPerformed(ActionEvent e) {nuevosRegistros();}};
		super.aceptarCancelar.getAceptar().setAction(accionAceptar);

		//----------------------------------------------------------//
		
		this.add(super.aceptarCancelar);
	}
	
	public void nuevosRegistros() {
		ListIterator<Registrador> iterator = gD.listIterator();
		String id = java.util.UUID.randomUUID().toString();
		while(iterator.hasNext()) {
			iterator.next().nuevoRegistro(id);
		}
	}
	
	public void creaGrupo(Class<?> entidad,Set<Class<?>> entidades,HashMap<Class<?>,Registrador> registradores) {
		Class<?> superclase = entidad.getSuperclass();
		boolean superclasePresente=false;

		for(Class<?> c :entidades) if(c.equals(superclase)) superclasePresente=true;
		
		if(superclasePresente) this.creaGrupo((Class<?>) superclase,entidades,registradores);
		
		gD.add(registradores.get(entidad));
	}
	
	public void rePintar() {
		for(Registrador reg : gD) this.getContentPane().add((JComponent) ((Registrador) reg).getGUI());
		//for(Registrador reg : gD) this.add((JComponent) ((Registrador) reg).getGUI());
	}
	
	public List<Registrador> getgD() {return gD;}
	public void setgD(List<Registrador> gD) {this.gD = (ArrayList<Registrador>) gD;}

	public Class<?> getClaseAsociada() {return claseAsociada;}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
