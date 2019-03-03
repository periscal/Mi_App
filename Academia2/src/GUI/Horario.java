package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import Principal.Marco;

//El horario es un objeto que se crea cada vez que se inicia la aplicación o cada vez que se añade/modifica una sesión
// No se guarda en un archivo
public class Horario extends JPanel{

	protected int diasLaborales=5;
	protected HashMap<DayOfWeek,JPanel> dias;
	protected int tamanoMaximoSesion=16;
	protected JPanel vista_horario;

	protected HashMap<String,EtiquetaSesion> etiquetasSesiones;
	protected HashMap<String,EtiquetaAlumno> etiquetasAlumnos;
	protected ArrayList<String> etiqSesiSeleccionadas;
	protected List<String> etiqAlumSeleccionadas;

	//// NUEVO ///////
	protected Dimension dimHorario;
	protected int num_dias=7;
	protected int label_size;
	protected double alto_tiempo=1;
	protected int time_min=15*60+30;
	protected int time_max;

	/////////////////


	public Horario() {

		/*======= ASPECTO de Horario =======*/
		this.setBackground(Color.white);
		BoxLayout bb =new BoxLayout(this,BoxLayout.Y_AXIS);
		this.setLayout(bb);
		dimHorario = new Dimension(Marco.ventana.getWidth()*8/10/****/,Marco.ventana.getHeight()*4/5/****/);
		this.setPreferredSize(dimHorario);
		/*----------------------------------*/

		vista_horario =new JPanel();
		/*======= ASPECTO de Horario =======*/
		vista_horario.setBackground(Color.white);
		BoxLayout b =new BoxLayout(vista_horario,BoxLayout.X_AXIS);
		vista_horario.setLayout(b);
		vista_horario.setSize(dimHorario);
		/*----------------------------------*/

		dias=new HashMap<DayOfWeek,JPanel>();

		for(int i=0; i<num_dias; i++) {

			JPanel p= new JPanel(); // Panel para cada dia
			/*======= ASPECTO de los panles de cada dia =======*/
			//p.setPreferredSize();
			p.setPreferredSize(new Dimension(dimHorario.width/num_dias,dimHorario.height));
			Border borde = null;
			borde=BorderFactory.createTitledBorder(borde, DayOfWeek.of(i+1).name(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, Color.black);
			p.setBorder(borde);
			p.setBackground(Color.white);
			p.setLayout(null);
			/*-------------------------------------------------*/
			dias.put(DayOfWeek.of(i+1), p);
			vista_horario.add(p);//Se añade panel dia a panel horario
		}

		/*-------------------------------------*/
		this.add(vista_horario);
		//this.add(opciones_horario);

		etiquetasSesiones = new HashMap<String,EtiquetaSesion>();
		etiquetasAlumnos = new HashMap<String,EtiquetaAlumno>();
		etiqSesiSeleccionadas = new ArrayList<String>();
		etiqAlumSeleccionadas = new ArrayList<String>();
	}

	/* =============== CLASES INTERNAS ========================*/
	public class EtiquetaSesion extends JPanel{
		private String iDsesion; //De ser el mismo qie el id de sesion correspondiente al objeto sesion (o entidad en BBDD)
		private boolean seleccionable;
		private boolean selecionado;
		private MouseListener click_raton;

		private int posicionAbsolutaInicio;
		private int posicionAbsolutaFin;

		public EtiquetaSesion(String iDSesion,DayOfWeek dia, Time inicio, Time fin) {
			super();

			/*======= ASPECTO de Etiqueta =======*/
			// Colores
			this.setBackground(Color.white);
			BoxLayout b =new BoxLayout(this,BoxLayout.Y_AXIS);
			this.setLayout(b);
			// Tama�os
			posicionAbsolutaInicio=inicio.getHours()*60+inicio.getMinutes();
			posicionAbsolutaFin=fin.getHours()*60+fin.getMinutes();
			define();
			/*----------------------------------*/
			seleccionable=true;
			selecionado=false;

			this.iDsesion = iDSesion;
			//this.add(new JLabel(iDsesion));
			Border borde=BorderFactory.createLineBorder(Color.BLACK, 1);
			this.setBorder(borde);

			click_raton = new MouseListener() {
				public void mouseClicked(MouseEvent e) {cambiarSeleccion();}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
			};

			this.addMouseListener(click_raton);
		// Comprobacion de que acepta Etiquetas alumnos
		this.add(new EtiquetaAlumno("00000", "Prueba"));
		//TODO eliminar caso prueba
		}

		/*===================== METODOS ===========================*/	
		public void cambiarSeleccion() {
			selecionado=Boolean.logicalXor(selecionado,true);
			if(seleccionable==true) {
				if(selecionado==true) {this.setBackground(Color.GREEN);}
				else {this.setBackground(null);}
			}
		}
		public Boolean isSelecionado() {return selecionado;}
		public String getiDsesion() {return iDsesion;}

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

			this.setBounds(0, posicionRelativaInicio, dimHorario.width/num_dias, (int) Math.round(altura));
			//TODO revisar tama�o (ancho) etiquetas
			}
		}
	}
	
	
	public class EtiquetaAlumno extends JLabel{
		private String iDAlumno;
		private String nombreAlumno;
		private Boolean seleccionable;
		private Boolean selecionado;
		private MouseListener click_raton;

		public EtiquetaAlumno(String ID, String nombre) {
			super();
			this.iDAlumno=ID;
			this.nombreAlumno=nombre;
			//this.setText(alumno.getiDprovisional()/*alumno.getNombre()+alumno.getApellido1().substring(0, 2)*/);
			this.setText(nombreAlumno);
			seleccionable=true;
			selecionado=false;
			Border borde=BorderFactory.createLineBorder(Color.BLACK, 1);
			this.setBorder(borde);
			this.setBackground(Color.ORANGE);
			click_raton = new MouseListener() {
				public void mouseClicked(MouseEvent e) {cambiarSeleccion();}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
			};

			this.addMouseListener(click_raton);
		}

		/*===================== METODOS ===========================*/	
		public void cambiarSeleccion() {
			selecionado=Boolean.logicalXor(selecionado,true);

			if(seleccionable==true) {
				if(selecionado==true) {this.setBackground(Color.BLUE);}
				else {this.setBackground(null);}
			}
		}
		
		public Boolean isSelecionado() {return selecionado;}
		public String getiDAlumno() {return iDAlumno;}
	}

	/*=========================================================*/

	/*===================== METODOS ===========================*/
	//----------------------------------------------------------------------------//
	public ArrayList<String> devuelveSelecionSesion() {
		for(EtiquetaSesion e : etiquetasSesiones.values()) {
			if(e.isSelecionado()) {
				etiqSesiSeleccionadas.add(e.getiDsesion());
			}
		}
		return etiqSesiSeleccionadas;
	}	

	public void quitaSeleccionSesion() {
		for(EtiquetaSesion e : etiquetasSesiones.values()) {
			e.cambiarSeleccion();
			etiqSesiSeleccionadas.clear();
		}
	}
	//----------------------------------------------------------------------------//
	public List<String> devuelveSelecionAlumno() {
		for(EtiquetaAlumno e : etiquetasAlumnos.values()) {
			if(e.isSelecionado()) {
				etiqAlumSeleccionadas.add(e.getiDAlumno());
			}
		}
		System.out.println("Los Alumnos selecionados son: " + etiqAlumSeleccionadas);
		return etiqAlumSeleccionadas;
	}

	public void quitaSeleccionAlumno() {
		for(EtiquetaAlumno e : etiquetasAlumnos.values()) {
			e.cambiarSeleccion();
			etiqAlumSeleccionadas.clear();
		}
	}
	//----------------------------------------------------------------------------//
	public void insertarSesion(String ID, DayOfWeek dia, Time inicio, Time fin) {
		EtiquetaSesion eS= new EtiquetaSesion(ID,dia,inicio, fin);
		/** //Si se inserta una sesion, se redefinen las demas existentes
		for(EtiquetaSesion es : etiquetasSesiones.values()) {
			es.define();
		}**/
		etiquetasSesiones.put(ID,eS);
		dias.get(dia).add(eS);
		eS.add(new JLabel("Eii"));
	}
	
	//----------------------------------------------------------------------------//
	public void insertarAlumno(String IDAlumno, String nombre, String IDSesion) {
		EtiquetaAlumno eA= new EtiquetaAlumno(IDAlumno,nombre);
		etiquetasSesiones.get(IDSesion).add(eA);
		etiquetasAlumnos.put(IDAlumno, eA);
	}
	//----------------------------------------------------------------------------//
}
