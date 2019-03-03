package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

public class BotonAbreVentana extends JButton implements ActionListener{
	protected VentanaAceptarCerrar v;
	protected JComponent comp;
	
	public BotonAbreVentana(String nombre,ImageIcon icono,Action accionAceptar, JComponent comp) {
		super(nombre, icono);
		v = new VentanaAceptarCerrar(accionAceptar,comp);
		this.comp=comp;
		this.addActionListener((ActionListener) this);
		
		//----- Aspecto ----- //
		Aspecto.aplicarAspecto(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		v.add(comp, 0);
		this.v.setVisible(true);
	}
}
