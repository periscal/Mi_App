package registrables;

public class Alumno_Sesion extends TipoRegistrable {
	protected String claveAlumno;
	protected String claveSesion;
	
	public Alumno_Sesion() {
		nombreTablaBBDD="alumno_sesion";
	}

	public String getClaveAlumno() {return claveAlumno;}
	
	public String getClaveSesion() {return claveSesion;}
	
	@Override
	public void setId(String id) {this.id = claveAlumno+claveSesion;}
}
