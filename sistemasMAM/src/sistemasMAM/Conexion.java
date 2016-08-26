package sistemasMAM;
import java.sql.*;


public class Conexion {
    private Connection conexion=null;
    private String servidor = "";
    private String database = "";
    private String usuario  = "";
    private String password = "";
    private String url      = "";
 
    public Conexion(String servidor, String database, String usuario, String password){
        try {
 
            this.servidor = servidor;
            this.database = database;
 
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            url="jdbc:mysql://"+servidor+"/"+database+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            conexion=DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexion a Base de Datos "+url+" . . . . .Ok");
 
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
 
    public Connection getConexion(){
        return conexion;
    }
 
    public Connection cerrarConexion(){
        try {
            conexion.close();
             System.out.println("Cerrando conexion a "+url+" . . . . . Ok");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        conexion=null;
        return conexion;
    }
}
