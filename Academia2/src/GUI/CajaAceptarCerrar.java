package GUI;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;

public class CajaAceptarCerrar extends Box{
	private JButton cancelar;
	private JButton aceptar;

	public CajaAceptarCerrar(Object o) {
		super(0);
		CajaAceptarCerrar.createHorizontalBox();


		aceptar = new JButton();
		cancelar= new JButton();

		aceptar.addActionListener((ActionListener) o);
		cancelar.addActionListener((ActionListener) o);

		aceptar.setText("Aceptar");
		cancelar.setText("Cerrar");
		
		aceptar.setBackground(Color.GRAY);
		cancelar.setBackground(Color.GRAY);
	}

	public JButton getCancelar() {return cancelar;}
	public JButton getAceptar() {return aceptar;}

}