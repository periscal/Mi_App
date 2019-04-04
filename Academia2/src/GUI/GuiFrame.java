package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

public abstract class GuiFrame extends JFrame implements ActionListener{
	protected CajaAceptarCerrar aceptarCancelar;

	public GuiFrame() {
		super();
		
		Aspecto.aplicarAspecto(this);
		//Layout
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));	
		
		//Botones Aceptar y Cancelar
		this.aceptarCancelar= new CajaAceptarCerrar(this);
		Action accionCancelar = new AbstractAction() {public void actionPerformed(ActionEvent e) {dispose();}};
		this.aceptarCancelar.getCancelar().setAction(accionCancelar);
		
	}
	
	

}
