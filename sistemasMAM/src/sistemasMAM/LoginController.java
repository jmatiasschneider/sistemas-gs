package sistemasMAM;

import java.sql.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
	public static String usuario = null;

	public static void init() {

	}

	@GET
	public Response login(@QueryParam("nombreUsuario") String nombreUsuario,
			@QueryParam("pass") String pass) {

		// Llamamos al metodo que nos crea la conexion
		Connection conexion = Conexion.getConexion();

		// Comprobamos que la conexion no sea nula (es decir hay un error)
		if (conexion == null) {
			System.out.println("Error con la conexion a la BD!!");
			return Response.status(401).entity("ERROR").build();
		} else {

			System.out.println("Conexion a la BD correcta!!");

			try {
				Statement consulta = conexion.createStatement();
				String aEjecutar = "SELECT * FROM usuarios where usuario = \""
						+ nombreUsuario + "\" and pass = \"" + pass + "\"";
				ResultSet resultado = consulta.executeQuery(aEjecutar);

				System.out.println("Consulta ejecutada");

				if (resultado.first()) {
					consulta.close();
					usuario = nombreUsuario;
					return Response.status(200).entity("OK").build();
				} else {
					consulta.close();
					return Response.status(401).entity("ERROR").build();
				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return Response.status(401).entity("ERROR").build();
			}

		}

	}
}
