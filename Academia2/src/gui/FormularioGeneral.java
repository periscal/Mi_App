package gui;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

public class FormularioGeneral extends JPanel implements GuiIF{
	private static final long serialVersionUID = 1L;
	
	protected Field[] atributos;
	protected HashMap<Field, JComponent> fields; /*NOTA: No aceptan objetos nulos*/
	protected HashMap<Field, Object> leidos;
	private JComponent campo;
	private NumberFormat numberField;
	protected Class<?> clase;
	private GroupLayout layout;
	private GroupLayout.SequentialGroup horizontalGroup;
	private GroupLayout.SequentialGroup verticalGroup;
	private GroupLayout.ParallelGroup grupoEtiquetas;
	private GroupLayout.ParallelGroup grupoScan;

	public FormularioGeneral(Class<?> cl){

		/*=============== Aspecto ====================================*/
		Aspecto.aplicarAspecto(this);
		BoxLayout b =new BoxLayout(this,BoxLayout.Y_AXIS);
		this.setLayout(b);

		// Oracle, como usar GroupLayout -> https://docs.oracle.com/javase/tutorial/uiswing/layout/group.html
		// Es necesario definir tanto la disposicion horizontal como vertical de los mismos elementos a ordenar
		JPanel panel=new JPanel();
		Aspecto.aplicarAspecto(panel);
		this.add(panel);
		layout= new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		horizontalGroup = layout.createSequentialGroup();
		verticalGroup = layout.createSequentialGroup();
		grupoEtiquetas=layout.createParallelGroup(Alignment.LEADING);
		grupoScan=layout.createParallelGroup(Alignment.LEADING);
		horizontalGroup.addGroup(grupoEtiquetas).addGroup(grupoScan);

		layout.setHorizontalGroup(horizontalGroup);/**/
		layout.setVerticalGroup(verticalGroup);

		/*-------------------------------------------------------------------*/

		fields = new HashMap<>();
		leidos = new HashMap<>();
		this.f(cl);
	}

	public void f(Class<?> clase) {	

		atributos = clase.getDeclaredFields();
		for(Field campoClase:atributos) { 

			Class<?> c= campoClase.getType();
			String nombreCampo=campoClase.getName(); // Nombre del atributo, deberia de coincidir con el de la base de datos
			JLabel etiqueta =new JLabel(nombreCampo);
			Aspecto.aplicarAspecto(etiqueta);// Se crea la etiqueta con el nombre del campo
			etiqueta.setSize(20,12);

			/*Se crean los paneles que contendran las etiquetas y los cuadros de texto; según el tipo de atributo*/

			if(c.isAssignableFrom(Date.class)) {
				MaskFormatter mascara;
				try {
					// Mascara para cada cuadro de texto
					mascara = new MaskFormatter("##-##-####");
					mascara.setPlaceholderCharacter('0');
					campo= new JFormattedTextField(mascara/**/);
					//((JFormattedTextField) campo).setValue(Date.valueOf("2017-02-01"));

				} catch (ParseException e) {e.printStackTrace();}
				((JFormattedTextField) campo).setColumns(10);
			}
			if(c.isAssignableFrom(Time.class)) {
				MaskFormatter mascara;
				try {
					// Mascara para cada cuadro de texto
					mascara = new MaskFormatter("##:##:00");
					mascara.setPlaceholderCharacter('0');
					campo= new JFormattedTextField(mascara/**/);
					//((JFormattedTextField) campo).setValue(Date.valueOf("16:00:00"));

				} catch (ParseException e) {e.printStackTrace();}
				((JFormattedTextField) campo).setColumns(10);
			}
			else if(c.isAssignableFrom(Integer.class) | c.isAssignableFrom(int.class)| c.isAssignableFrom(short.class)| c.isAssignableFrom(float.class)) {
				campo= new JFormattedTextField(numberField); //Cuadro de texto
				((JFormattedTextField) campo).setColumns(2);
				((JFormattedTextField) campo).setValue(0000);
			}
			else if(c.isAssignableFrom(Boolean.class)) {
				campo=new JCheckBox(); //Casilla de marcar
			}
			else if(c.isAssignableFrom(Enum.class)) {

			}
			else if(c.isAssignableFrom(String.class)) {
				campo= new JFormattedTextField(); //Cuadro de texto
				((JFormattedTextField) campo).setColumns(4);
				/*((JFormattedTextField) campo).setValue(stringField);*/
			}
			else if(c.isAssignableFrom(DayOfWeek.class)) {
				campo= new JComboBox<DayOfWeek>(DayOfWeek.values());
			}

			if(campo!=null) {
				campo.setName(nombreCampo);
				//System.out.println(nombreCampo);
				fields.put(campoClase, campo);

				/*--- Inserrción en el GroupLayout ----*/
				verticalGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(etiqueta).addComponent(campo));
				grupoEtiquetas.addComponent(etiqueta);
				grupoScan.addComponent(campo);
			}
		}
	}

	/**
	 * <h2><i> leerCampos </i></h2>
	 * <p><code>public void leerCampos()</code></p>
	 * <p>Obtiene el valor recogido por los diferentes JComponent como: {@link #JFormattedTextField},
	 * {@link #JCheckBox},{@link #JComboBox}</p>
	 * <p> El <code>JComponent</code>JComponent usado dependerá del tipo de dato que debe recoger el mismo:
	 * <table>
	 * <tr><th></th><th></th></tr>
	 * <tr><td>JFormattedTextField:
	 *     <td> Para los objetos tipo <code>String</code>,<code>Date</code>,<code>Time</code>
	 * <tr><td>JCheckBox:
	 *     <td> Para los objetos tipo<code>Boolean</code>
	 * <tr><td>JComboBox:
	 *     <td> Para los objetos tipo<code>DayOfWeek</code>
	 * </table></p>
	 */
	public void leerCampos() {
		for(Field campoClase:atributos) {		
			Class<?> c= campoClase.getType();
			JComponent componente=fields.get(campoClase);
			Object elementoLeido = null;

			if(c.isAssignableFrom(Date.class)) {
				elementoLeido= ((JFormattedTextField) componente).getValue();
			}
			else if(c.isAssignableFrom(Time.class)) {
				elementoLeido=Time.valueOf((String) ((JFormattedTextField) componente).getText());
			}
			else if(c.isAssignableFrom(Integer.class) | c.isAssignableFrom(int.class)| c.isAssignableFrom(short.class)| c.isAssignableFrom(float.class)) {
				elementoLeido= ((JFormattedTextField) componente).getValue();
			}
			else if(c.isAssignableFrom(Boolean.class)) {
				elementoLeido= ((JCheckBox) componente).isSelected();
			}
			else if(c.isAssignableFrom(Enum.class)) {
				//elementoLeido= ((???) componente)
			}
			else if(c.isAssignableFrom(String.class)) {
				elementoLeido= ((JFormattedTextField) componente).getText();			
			}
			else if(c.isAssignableFrom(DayOfWeek.class)) {
				elementoLeido= ((JComboBox<DayOfWeek>) componente).getSelectedItem();
			}

			if( elementoLeido !=null) {leidos.put(campoClase, elementoLeido);}
		}
	}
	
	/**
	 * <h2><i> setCampo </i></h2>
	 * <p><code>public void setCampo(Field campo, Object valor)</code></p>
	 * <p> Pone valor que se visualizará en los diferentes JComponent </p>
	 * @param campo - objeto tipo <code>Field</code> sobre el que se fijará su valor
	 * @param valor - objeto del tipo correspondiente al valor que acepta el argumento campo
	 */
	public void setCampo(Field campo, Object valor) {
		JComponent jc = fields.get(campo);
		Class<?> c = jc.getClass();
		
		if(c.isAssignableFrom(JFormattedTextField.class)) {
			((JFormattedTextField) jc).setValue(valor);
		}
		else if(c.isAssignableFrom(JCheckBox.class)) {
			((JCheckBox) jc).setSelected((Boolean) valor);
		}
		else if(c.isAssignableFrom(JComboBox.class)) {
			((JComboBox) jc).setSelectedItem(valor);;
		}		
	}
	
	@Override
	public ArrayList<HashMap<Field, Object>> getAtributos(String id) {
		leerCampos();
		ArrayList<HashMap<Field, Object>> a = new ArrayList<>();
		a.add(leidos);
		return a;
	}

	public Class<?> clase() {return clase;}
}
