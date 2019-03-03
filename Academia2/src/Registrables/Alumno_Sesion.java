package Registrables;

public class Alumno_Sesion extends TipoRegistrable {
	protected String claveAlumno;
	protected String claveSesion;
	
	public Alumno_Sesion(/*String claveAlumno, String claveSesion*/) {
		nombreTablaBBDD="alumno_sesion";
		//this.claveAlumno = claveAlumno;
		//this.claveSesion = claveSesion;
	}

	public String getClaveAlumno() {return claveAlumno;}
	
	public String getClaveSesion() {return claveSesion;}
}
