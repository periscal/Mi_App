package bbdd;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import registrables.Alumno;
import registrables.Persona;
import registrables.Sesion;

public class BBDDconcreta extends BBDDgeneral{

	public BBDDconcreta(String url) throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		super(url);
	}
	//---------------------------------------------------------------------------
	// ---------5. LEER LA BBDD (Acces)------------------------------------------
	//---------------------------------------------------------------------------
	public void LeerHorario() throws SQLException{

		Statement stament=conexion.createStatement();
		result=stament.executeQuery("SELECT * FROM alumno"/*+nombreBD*/);//En el objeto ResultSet, se guardar� el conjunto de resultados provenientes de nuestra consulta:

		ResultSetMetaData rsmd;
		rsmd = result.getMetaData();
		// result.last() mueve el cursor hasta la �ltima fila del objeto ResulSet "result.
		//Es necesario para obtener la cantidad total de filas con result.getRow()
		result.last();		
		int numFils = result.getRow();

		result.beforeFirst();// Mueve el cursor hasta antes de la primera fila
		//int numCols = rsmd.getColumnCount();

		int i=0;
		while (result.next()){
			/**user[i]=new Usuario();
			user[i].setID(result.getInt(1));
**/
			i++;
		}

		return;
	}

	//---------------------------------------------------------------------------
	// ---------5. INSERTAR REGISTRO---------------------
	//---------------------------------------------------------------------------
	public void insertarRegistro(ArrayList<Persona> users) throws SQLException{	
		
		for (Persona per:users) {

			if(per instanceof Persona) {
				PreparedStatement insercionPersona=conexion.prepareStatement("INSERT INTO persona(Nombre,Apellido1,Apellido2,Asistencia,PC,Internet) VALUES (?,?,?,?,?,?)");
				insercionPersona.setString(3, per.getNombre());
				insercionPersona.setString(4, per.getApellido1());
				insercionPersona.setString(5, per.getApellido2());		
				insercionPersona.setString(6, per.getTelefono());
				insercionPersona.setString(7, per.getEmail());
				insercionPersona.setDate(8, per.getNacimiento());
				insercionPersona.setString(9, per.getComentario());
				insercionPersona.executeUpdate();
			}
			
			if(per instanceof Alumno) {
				PreparedStatement insercionAlumno=conexion.prepareStatement("INSERT INTO alumno(fecha_inicio,tasa) VALUES (?,?)");
				insercionAlumno.setDate(1, ((Alumno) per).getFechaInicio());
				insercionAlumno.setFloat(2, ((Alumno) per).getTasa());
				insercionAlumno.executeUpdate();
			}
		}

	}
	public void insertarSesion(ArrayList<Sesion> sesions) throws SQLException{
		
		for (Sesion ses:sesions) {
			
			PreparedStatement insercionSesion=conexion.prepareStatement("INSERT INTO sesion(horaInicio,horaFin,dia,persona_ID) VALUES (?,?,?,?)");
			insercionSesion.setTime(2, ses.getHoraInicio());
			insercionSesion.setTime(3, ses.getHoraFin());
			insercionSesion.setInt(4, ses.getDia().getValue());
			//insercionSesion.setInt(5, ses.getPersona_ID());
			insercionSesion.executeUpdate();
		}
	}
}

