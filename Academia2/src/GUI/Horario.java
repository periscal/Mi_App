package GUI;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import Principal.Marco;

/**
 * La clase {@code Horario} muestra graficamente...
 * El horario es un objeto que se crea cada vez que se inicia la aplicación o cada vez que se añade/modifica una sesión.
 * No se guarda en un archivo.
 * @author juan
 *
 */
public class Horario extends Container{
	private static final long serialVersionUID = 1L;
	protected EnumMap<DayOfWeek,Container> dias;
	protected static Dimension dimHorario;
	protected static int numDias=7;

	public Horario() {
		/*======= ASPECTO de Horario =======*/
		this.setBackground(Color.white);
		BoxLayout bb =new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(bb);
		dimHorario = new Dimension(Marco.ventana.getWidth()*8/10/****/,Marco.ventana.getHeight()*4/5/****/);
		this.setPreferredSize(dimHorario);
		/*----------------------------------*/

		dias=new EnumMap<>(DayOfWeek.class);
		System.out.println("* Dimensiones horario: "+dimHorario);
	}

	/*=============== METODOS =================*/
	public List<String> devuelveSelecionSesion() {return EtiquetaSesion.etiqSesiSeleccionadas;}	
	public List<String> devuelveSelecionAlumno() {return EtiquetaUsuario.etiqAlumSeleccionadas;}
	/**
	 * <h2><i> insertarFranjaHoraria </i></h2>
	 * <p><code> public void insertarFranjaHoraria(String idSesion, DayOfWeek dia, Time inicio, Time fin)</code></p>
	 * <p>Inserta una sesion en el horario</p>
	 * @param idSesion - identificador de la sesion
	 * @param dia - dia de la semana
	 * @param inicio - hora de inicio de la sesion
	 * @param fin - hora de terminacion de la sesion
	 */
	public void insertarFranjaHoraria(String idSesion, DayOfWeek dia, Time inicio, Time fin) {
		EtiquetaSesion eS= new EtiquetaSesion(idSesion,dia,inicio, fin);
		EtiquetaSesion.etiquetasSesiones.put(idSesion,eS);
	}

	/**
	 * <h2><i> insertarUsuario </i></h2>
	 * <p><code> public void insertarUsuario(String idUsuario, String nombre, String idSesion)</code></p>
	 * <p>Inserta un alumno en el horario en una sesión concreta</p>
	 * @param idUsuario - identificador del alumno y la etiqueta del mismo
	 * @param nombre - cadena de caracteres que se escribiran en la correspondiente etiqueta del alumno
	 * @param idSesion - identificador de la sesion en la que se introduce la etiqueta del alumno
	 */
	public void insertarUsuario(String idUsuario, String nombre, String idSesion) {
		EtiquetaUsuario eA= new EtiquetaUsuario(idUsuario,nombre);
		EtiquetaSesion eS = EtiquetaSesion.etiquetasSesiones.get(idSesion);
		EtiquetaUsuario.etiquetasAlumnos.put(idUsuario, eA);
		eS.add(eA);
		/*for(EtiquetaSesion es : EtiquetaSesion.etiquetasSesiones.values()) {
			es.define();
		}*/
	}
	
	public void creaSemana() {
		this.removeAll();
		for(int i=0; i<numDias; i++) {
			JPanel c= new JPanel(); // Panel para cada dia
			c.setName(DayOfWeek.of(i+1).name());
			
			/*======= ASPECTO de los panles de cada dia =======*/
			c.setPreferredSize(new Dimension(dimHorario.width/numDias,dimHorario.height));
			Border borde = null;
			borde=BorderFactory.createTitledBorder(borde,c.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, Color.black);
			c.setBorder(borde);
			//c.setBackground(Color.white);
			c.setLayout(null);
			/*-------------------------------------------------*/
			for(EtiquetaSesion eS : EtiquetaSesion.etiquetasSesiones.values()) {
				if(eS.dia.compareTo(DayOfWeek.of(i+1))==0) c.add(eS); 
			}		
			dias.put(DayOfWeek.of(i+1), c);
			this.add(c);//Se añade panel dia a panel horario
		}
		
	}

	//==============================================
	//=========== CLASES INTERNAS ==================
	//==============================================
	protected static class EtiquetaUsuario extends JLabel{
		private static HashMap<String,EtiquetaUsuario> etiquetasAlumnos = new HashMap<>();
		private static List<String> etiqAlumSeleccionadas = new ArrayList<>();
		private String iDUsuario;
		private Boolean seleccionable;
		private Boolean seleccionado;
		private MouseListener clickRaton;

		public EtiquetaUsuario(String iDUsuario, String nombre) {
			super(nombre);
			this.iDUsuario=iDUsuario;
			seleccionable=true;
			seleccionado=false;

			clickRaton = new MouseListener() {
				public void mouseClicked(MouseEvent e) {
					cambiarSeleccion();
					autoInsercionList();
				}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
			};
			this.addMouseListener(clickRaton);
			
			Border borde=BorderFactory.createLineBorder(Color.BLACK, 3);
			setBorder(borde);
			setPreferredSize(new Dimension(60,20));
			setBackground(Color.ORANGE);
		}

		public Boolean isSelecionado() {return seleccionado;}
		public String getiDAlumno() {return iDUsuario;}
		public void cambiarSeleccion() {
			seleccionado=Boolean.logicalXor(seleccionado,true);

			if(seleccionable) {
				if(seleccionado) {this.setBackground(Color.BLUE);}
				else {this.setBackground(Color.ORANGE);}
			}
		}
		private void autoInsercionList() {
			if(seleccionado) etiqAlumSeleccionadas.add(iDUsuario);
			else etiqAlumSeleccionadas.remove(iDUsuario);
		}

		public void quitaSeleccionAlumno() {
			for(EtiquetaUsuario e : EtiquetaUsuario.etiquetasAlumnos.values()) {
				e.cambiarSeleccion();
				EtiquetaUsuario.etiqAlumSeleccionadas.clear();
			}
		}
	}


	protected static class EtiquetaSesion extends JPanel{

		private static HashMap<String,EtiquetaSesion> etiquetasSesiones = new HashMap<>();
		private static List<String> etiqSesiSeleccionadas = new ArrayList<>();
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
					autoInsercionList();
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

		private void autoInsercionList() {
			if(seleccionado) EtiquetaUsuario.etiqAlumSeleccionadas.add(iDsesion);
			else EtiquetaUsuario.etiqAlumSeleccionadas.remove(iDsesion);
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
		public void define() {
			if(posicionAbsolutaInicio<time_min) time_min=posicionAbsolutaInicio;
			int posicionRelativaInicio=posicionAbsolutaInicio-time_min;

			if(posicionAbsolutaFin<time_min) time_min=posicionAbsolutaFin;
			int posicionRelativaFin=posicionAbsolutaFin-time_min;

			int denominador=posicionAbsolutaFin-posicionAbsolutaInicio;
			if(denominador>0) {
				int tasa = this.getHeight()/denominador;
				if(tasa>alto_tiempo)alto_tiempo=tasa;
				double altura = alto_tiempo*denominador;
				double ancho = dimHorario.width/numDias;
				this.setBounds(0, posicionRelativaInicio, (int) Math.round(ancho), (int) Math.round(altura));
				this.repaint();
			}
		}
	}
}
