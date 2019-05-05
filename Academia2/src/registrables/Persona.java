package registrables;
import java.sql.Date;

public class Persona extends TipoRegistrable{

	/* ATRIBUTOS */

	protected String nombre;
	protected String apellido1;
	protected String apellido2;
	protected String telefono;
	protected String email;
	protected Date nacimiento;
	protected String comentario;
	
	public Persona() {
		nombreTablaBBDD="persona";
	}
	/* METODOS */

	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}

	public String getApellido1() {return apellido1;}
	public void setApellido1(String apellido1) {this.apellido1 = apellido1;}

	public String getApellido2() {return apellido2;}
	public void setApellido2(String apellido2) {this.apellido2 = apellido2;}

	public String getTelefono() {return telefono;}
	public void setTelefono(String telefono) {this.telefono = telefono;}

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}

	public Date getNacimiento() {return nacimiento;}
	public void setNacimiento(Date nacimiento) {this.nacimiento = nacimiento;}

	public String getComentario() {return comentario;}
	public void setComentario(String comentario) {this.comentario = comentario;}

}