package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;

public class EtiquetaSesion extends JPanel{

	public static HashMap<String,EtiquetaSesion> etiquetasSesiones = new HashMap<>();
	public static List<String> etiqSesiSeleccionadas = new ArrayList<>();
	private String iDsesion; //Debe ser el mismo que el id de sesion correspondiente al objeto sesion (o entidad en BBDD)
	private DayOfWeek dia;
	private boolean seleccionable;
	private boolean seleccionado;
	private MouseListener clickRaton;
	protected int label_size;
	protected double alto_tiempo=1;
	protected int time_min=15*60+30;
	protected int timeMax;
	private int posicionAbsolutaInicio;
	private int posicionAbsolutaFin;

	public EtiquetaSesion(String iDSesion,DayOfWeek dia, Time inicio, Time fin) {
		super();
		super.setName(iDSesion);
		this.dia=dia;
		this.iDsesion = iDSesion;
		seleccionable=true;
		seleccionado=false;

		clickRaton = new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				cambiarSeleccion();
				autoInsercionList();System.out.println(etiqSesiSeleccionadas);
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		};
		super.addMouseListener(clickRaton);

		/*======= ASPECTO de Etiqueta =======*/
		// Colores
		setBackground(Color.white);
		//BoxLayout b =new BoxLayout(this,BoxLayout.Y_AXIS);
		//setLayout(b);
		// Tamanos
		posicionAbsolutaInicio=inicio.getHours()*60+inicio.getMinutes();
		posicionAbsolutaFin=fin.getHours()*60+fin.getMinutes();
		define();
		//Bordes
		//Border borde=BorderFactory.createLineBorder(Color.BLACK, 1);
		//setBorder(borde);
		/*----------------------------------*/
	}

	public DayOfWeek getDia() {return dia;}

	private void autoInsercionList() {
		if(seleccionado) EtiquetaSesion.etiqSesiSeleccionadas.add(iDsesion);
		else EtiquetaSesion.etiqSesiSeleccionadas.remove(iDsesion);
	}
	public Boolean isSelecionado() {return seleccionado;}

	public String getiDsesion() {return iDsesion;}

	public void cambiarSeleccion() {
		seleccionado=Boolean.logicalXor(seleccionado,true);
		if(seleccionable) {
			if(seleccionado) {this.setBackground(Color.GREEN);}
			else {this.setBackground(Color.WHITE);}
		}

	}

	public void quitaSeleccionSesion() {
		for(EtiquetaSesion e : etiquetasSesiones.values()) {
			e.cambiarSeleccion();
			EtiquetaSesion.etiqSesiSeleccionadas.clear();
		}
	}

	/**
	 * <h2><i> define </i></h2>
	 * <p><code>public void define()</code></p>
	 * <p>Redimensiona el tamaño de las sesiones en función de todas las introducidas hasta el momento</p>
	 */
	/**/public void define() {
			if(posicionAbsolutaInicio<time_min) time_min=posicionAbsolutaInicio;
			int posicionRelativaInicio=posicionAbsolutaInicio-time_min;

			if(posicionAbsolutaFin<time_min) time_min=posicionAbsolutaFin;
			int posicionRelativaFin=posicionAbsolutaFin-time_min;

			int denominador=posicionAbsolutaFin-posicionAbsolutaInicio;
			if(denominador>0) {
				int tasa = this.getHeight()/denominador;
				if(tasa>alto_tiempo)alto_tiempo=tasa;
				double altura = alto_tiempo*denominador;
				double ancho = Horario.dimHorario.width/Horario.numDias;
				this.setBounds(0, posicionRelativaInicio, (int) Math.round(ancho), (int) Math.round(altura));
				this.repaint();
			}
		}
}

