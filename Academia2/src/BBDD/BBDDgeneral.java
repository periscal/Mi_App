package BBDD;
import java.lang.reflect.InvocationTargetException;
//---------------------------------------------------------------------------
//---------1. IMOPORTACION DE LAS CLASES NECESARIAS ------------------------------
//---------------------------------------------------------------------------
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class BBDDgeneral{
	protected Connection conexion=null;
	private Statement statement =null;
	protected ResultSet result;
	private String driver="com.mysql.cj.jdbc.Driver"/*"com.mysql.jdbc.Driver"   "org.hsqldb.jdbcDriver"   "sun.jdbc.odbc.JdbcOdbcDriver"*/;
	private String fuenteURL;

	public BBDDgeneral(String url) throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		fuenteURL=url;
		//---------------------------------------------------------------------------
		//---------2. CARGA DEL CONTROLADOR DE LA BBDD ------------------------------
		//---------------------------------------------------------------------------	

		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//---------------------------------------------------------------------------
		// --------3. CONECTAR A LA BBDD (Acces)-------------------------------------
		//--------------------------------------------------------------------------- 

		try{
			/*Se establece la conexión a la base de datos. 
			 * Para ello necesitamos crear un objeto Connection al cual le asignamos el tipo y nombre del controlador (driver), 
			 * la ruta donde se encuentra la BD, el usuario y contraseña en caso de que la base de datos esté protegida con ellos */
			conexion=DriverManager.getConnection(fuenteURL,"root","Ingenieria88");
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null,"Error conectando la base de datos: " + ex);
		}

	}

	public ResultSet getResult() {return result;}
	public void setResult(ResultSet result) {this.result = result;}

	//---------------------------------------------------------------------------
	// ---------4. DESCONECTAR DE LA BBDD (Acces)--------------------------------
	//---------------------------------------------------------------------------
	public void DesconectarBD(){
		try{
			conexion.close();
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null,"Error desconectando la base de datos: " + ex);    
		}  
	}  
}
