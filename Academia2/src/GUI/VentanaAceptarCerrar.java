package GUI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class VentanaAceptarCerrar extends JFrame{

	private Box cajaBotones;
	private JButton cancelar;
	private JButton aceptar;
	private Action accionCancelar;

	public VentanaAceptarCerrar(Action accionAceptar, Component comp) {
		Aspecto.aplicarAspecto(this);
		BoxLayout b =  new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS);
		this.getContentPane().setLayout(b);
		cajaBotones=Box.createHorizontalBox();

		aceptar = new JButton(accionAceptar);
		accionCancelar = new AbstractAction() {public void actionPerformed(ActionEvent e) {dispose();}};
		cancelar= new JButton(accionCancelar);

		aceptar.setText("Aceptar");
		cancelar.setText("Cerrar");

		cajaBotones.add(aceptar);
		cajaBotones.add(cancelar);	

		this.add(comp);
		this.add(cajaBotones);
	}
}
