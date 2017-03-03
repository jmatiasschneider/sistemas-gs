package sistemasMAM;

import java.sql.*;

public class Conexion {
	private static final String url = "jdbc:mysql://31.220.55.159:3306/gsdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String usuario = "root";
	private static final String password = "inicio01";

	private static Connection instancia = null;

	private Conexion() {

	}

	private static void crearConexion() {
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			instancia = DriverManager.getConnection(url, usuario, password);
			System.out.println("Conexion a Base de Datos " + url + " . . .");

		} catch (SQLException ex) {
			System.out.println(ex);
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
		}
	}

	public static Connection getConexion() {
		crearConexion();

		return instancia;
	}

	public static void cerrarConexion() {
		try {
			instancia.close();
			System.out.println("Cerrando conexion a " + url + " . . .");
		} catch (SQLException ex) {
			System.out.println(ex);
		}

		instancia = null;
	}
}
