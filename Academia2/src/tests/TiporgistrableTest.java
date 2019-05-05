package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import registrables.*;

public class TiporgistrableTest {

	@Test
	public void test() {
		Persona p = new Persona();
		try {
			p.setGeneral(Persona.class.getDeclaredField("nombre"), "Federico");
		assertEquals("Federico", p.getNombre());
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}
}
