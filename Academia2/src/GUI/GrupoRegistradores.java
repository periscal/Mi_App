package GUI;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Principal.*;

public class GrupoRegistradores extends JFrame{
	private ArrayList<Registrador> gD; //TODO transformarlo en HashMap siendo la clave la clase correspondiente
	private Box cajaBotones;
	private JButton cerrar;
	private JButton aceptar;
	private Action accionCancelar;
	private Action accionAceptar;
	private Class<?> claseAsociada;
	
	public GrupoRegistradores(Class<?> claseAsociada){
		super();
		this.gD = new ArrayList<>();
		this.claseAsociada=claseAsociada;
		//----------------- ASPECTO -------------------//
		Aspecto.aplicarAspecto(this);
		
		//----------------- BOTONES ACEPTAR/CERRAR -------------------//
		cajaBotones=Box.createHorizontalBox();
		accionAceptar = new AbstractAction() {public void actionPerformed(ActionEvent e) {nuevosRegistros();}};
		aceptar = new JButton(accionAceptar);
		accionCancelar = new AbstractAction() {public void actionPerformed(ActionEvent e) {dispose();}};//
		cerrar= new JButton(accionCancelar);
		
		aceptar.setText("Aceptar");
		cerrar.setText("Cerrar");
		
		cajaBotones.add(aceptar);
		cajaBotones.add(cerrar);
		//----------------------------------------------------------//
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(cajaBotones);
	}
	
	public void insertarRegistrador(Registrador reg) {
		gD.add(reg);
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
		
		this.insertarRegistrador(registradores.get(entidad));
	}
	
	public void rePintar() {
		for(Registrador reg : gD) this.getContentPane().add((JComponent) ((Registrador) reg).getGUI());
		//for(Registrador reg : gD) this.add((JComponent) ((Registrador) reg).getGUI());
	}
	
	public ArrayList<Registrador> getgD() {return gD;}
	public void setgD(ArrayList<Registrador> gD) {this.gD = gD;}

	public Class<?> getClaseAsociada() {return claseAsociada;}
}
