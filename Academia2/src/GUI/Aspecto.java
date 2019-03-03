package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;

public interface Aspecto {
	
	Color colorFondo = Color.DARK_GRAY;
	
	public static void aplicarAspecto(JComponent gui) {
		gui.setBackground(colorFondo);
		gui.setForeground(Color.WHITE);
		gui.setBorder(BorderFactory.createEmptyBorder());
	}
	
	public static void aplicarAspecto(JFrame v) {
		/* Almacenamos en la variable objeto "mipantalla" el sistema nativo de ventanas*/
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		
		/* Almacenamos en la variable Dimension la resoluci�n de la pantalla de mi monit*/
		Dimension resolucionPantalla = miPantalla.getScreenSize();
		
		int alturaPantalla = resolucionPantalla.height;
		int anchoPantalla = resolucionPantalla.width;

		/* Se divide entre 2 para que ocupe la mitad de la pantalla, y se divide entre 4 para centrarlo*/
		// Con setResizable(false) es posible impedir la modificacion del tamamo de la ventana (p.e. no se puede maximizar)
		// Con setExtendedState(Frame.MAXIMIZED_BOTH) maximiza la ventana
		v.getContentPane().setBackground(colorFondo);
		//v.setUndecorated(true); //Elimina barra de titulo
		v.setBounds(anchoPantalla /4, alturaPantalla / 4, anchoPantalla / 2, alturaPantalla / 2);
	}
}
