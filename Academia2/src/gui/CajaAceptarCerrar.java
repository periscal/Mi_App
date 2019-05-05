package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class CajaAceptarCerrar extends Box{
	private static final long serialVersionUID = 1L;

	private JButton cancelar;
	private JButton aceptar;
	private Action accionCancelar;

	public CajaAceptarCerrar(Container o) {
		super(BoxLayout.X_AXIS);
		
		aceptar = new JButton();
		cancelar= new JButton();
		
		aceptar.setBackground(Color.GRAY);
		cancelar.setBackground(Color.GRAY);

		if(Window.class.isAssignableFrom(o.getClass())) {
			accionCancelar = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
					((java.awt.Window) o).dispose();
				}
			};
		}else {
			System.out.println("Has pulsado el boton \"Cerrar\" del objeto 'CajaAcetparCerrar' y no tiene acción asignada");
		}
		cancelar.setAction(accionCancelar);
		
		aceptar.setText("Aceptar");
		cancelar.setText("Cerrar");
		
		this.add(aceptar);
		this.add(cancelar);
	}
	
	public void accionAceptar(Action accionAceptar) {
		aceptar.setAction(accionAceptar);
		aceptar.setText("Aceptar");
	}
}