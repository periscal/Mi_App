package Registrables;

import java.io.Serializable;
import java.lang.reflect.Field;

public abstract class TipoRegistrable implements Serializable{
	protected String nombreTablaBBDD;
	protected String id;
	protected String iDprovisional;

	public TipoRegistrable() {
		super();
		this.iDprovisional = java.util.UUID.randomUUID().toString();
	}

	public String getiDprovisional() {return iDprovisional;}
	public void setiDprovisional(String iDprovisional) {this.iDprovisional = iDprovisional;}
	/**/
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}


	public Object getGeneral(Field f) {
		Object obj=null;
		try {obj= f.get(this);}
		catch (IllegalArgumentException | IllegalAccessException e) {e.printStackTrace();}
		return obj;
	}

	public void setGeneral(Field f, Object obj) {
		try {f.set(this, obj);} 
		catch (IllegalArgumentException | IllegalAccessException e) {e.printStackTrace();}
	}

	public String getNombreTablaBBDD() {return nombreTablaBBDD;}
	public void setNombreTablaBBDD(String nombreTablaBBDD) {this.nombreTablaBBDD = nombreTablaBBDD;}
	
	//public abstract void ponerNombreTabla();
}

