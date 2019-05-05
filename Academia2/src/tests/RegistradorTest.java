package tests;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import gui.FormularioGeneral;
import registrables.*;

public class RegistradorTest {

	@Test
	public void test() {
		//Se crea un formulario de Alumno
		Field[] f = Persona.class.getDeclaredFields();
		FormularioGeneral fo = new FormularioGeneral(Persona.class); 
		fo.setCampo(f[0], "NombrePrueba");
		//fail("Not yet implemented");
	}

}
