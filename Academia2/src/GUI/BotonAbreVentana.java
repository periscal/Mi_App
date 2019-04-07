package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

public class BotonAbreVentana extends JButton implements ActionListener{
	protected GuiFrame v;
	protected JComponent comp;
	
	public BotonAbreVentana(String nombre,ImageIcon icono, JComponent comp) {
		super(nombre, icono);
		
		v= new GuiFrame();
		Aspecto.aplicarAspecto(this);
		BoxLayout b =  new BoxLayout(v.pane,BoxLayout.Y_AXIS);
		v.pane.setLayout(b);
		this.comp=comp;
		this.addActionListener((ActionListener) this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		v.add(comp, 0);
		this.v.setVisible(true);
	}
}
