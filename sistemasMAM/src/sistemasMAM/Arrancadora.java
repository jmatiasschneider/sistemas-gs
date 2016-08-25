package sistemasMAM;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Arrancadora {

	public static void main(String[] args) {
        //Declaramos un objeto de la clase Conexion
        Conexion con = new Conexion("31.220.55.159:3306", "gsdb", "root", "inicio01");		

        //Llamamos al metodo que nos crea la conexion 
        Connection conexion = con.getConexion();
      //Comprobamos que la conexion no sea nula (es decir hay un error)
        if(conexion == null){
            System.out.println("Error con la conexion a la BD!!");
        }
        else{
            try {
                System.out.println("Conexion a la BD correcta!!");
                //Si la conexion es correta podemos usarla para hacer consultas sobre la BD
                
                //CONSULTAS AQUI
                Usuario usu = new Usuario("leito", "Leonardo", "1");
                // REALIZO LA CONSULTA O INSTRUCCION SQL
                try {
                	   Statement consulta = conexion.createStatement();
                	   String aEjecutar   = "INSERT INTO usuarios (usuario, nombre, id_perfil) VALUES ('"+ usu.getUsuario()+"', '"
                      	     +usu.getNombre()+"', '"+ usu.getId_perfil()+"')";
                	   consulta.executeUpdate(aEjecutar);
                	   System.out.println("Se ha registrado Exitosamente");
                	   consulta.close();
                	   con.cerrarConexion();
                	    
                	  } catch (SQLException e) {
                	            System.out.println(e.getMessage());
                	            System.out.println("No se Registro la persona");
                	  }                
                //Al terminar de hacer las consultas siempre debemos de cerrar la conexion
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }             
	}
	}
}
