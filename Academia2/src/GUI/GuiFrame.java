package GUI;

import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public  class GuiFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	protected JPanel panel;
	protected CajaAceptarCerrar aceptarCancelar;
	protected Container pane;

	public GuiFrame() {
		super();
		pane = this.getContentPane();
		panel= new JPanel();

		Aspecto.aplicarAspecto(this);
		//Layout
		BoxLayout layout=new BoxLayout(pane, BoxLayout.Y_AXIS);
		pane.setLayout(layout);	
		//Panel Principal
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		pane.add(panel);
		//Botones Aceptar y Cancelar
		aceptarCancelar= new CajaAceptarCerrar(this);
		//Anadir componentes al JFrame
		pane.add(aceptarCancelar);
	}
}
