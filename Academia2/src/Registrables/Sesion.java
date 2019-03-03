package Registrables;

import java.sql.Time;
import java.time.DayOfWeek;

public class Sesion  extends TipoRegistrable{
	protected DayOfWeek dia;
	protected Time horaInicio;
	protected Time horaFin;

	public Sesion() {
		nombreTablaBBDD="sesion";
	}
	
	public DayOfWeek getDia() {return dia;}
	public void setDia(DayOfWeek dia) {this.dia = dia;}

	public Time getHoraInicio() {return horaInicio;}
	public void setHoraInicio(Time horaInicio) {this.horaInicio = horaInicio;}

	public Time getHoraFin() {return horaFin;}
	public void setHoraFin(Time horaFin) {this.horaFin = horaFin;}
}