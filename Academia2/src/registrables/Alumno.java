package registrables;

import java.sql.Date;

public class Alumno extends Persona{
	/* ATRIBUTOS */
	Date fechaInicio;
	Date fechaFin;
	float tasa=0;
	/*String tutor1Id;
	String tutor2Id;
	String IdCentro;
	String referencia;*/
	
	public Alumno() {
		nombreTablaBBDD="alumno";
	}
		
	public Date getFechaInicio() {return fechaInicio;}
	public void setFechaInicio(Date fechaInicio) {this.fechaInicio = fechaInicio;}
	
	public Date getFechaFin() {return fechaFin;}
	public void setFechaFin(Date fechaFin) {this.fechaFin = fechaFin;}
	
	public float getTasa() {return tasa;}
	public void setTasa(float tasa) {this.tasa = tasa;}
	/*
	public String getReferencia() {return referencia;}
	public void setReferencia(String referencia) {this.referencia = referencia;}*/
}
