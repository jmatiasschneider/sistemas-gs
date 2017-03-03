package sistemasMAM;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/usuarios")
public class ServiciosUsuario {

	@GET
	@Path("/{param}")
	@Produces(MediaType.TEXT_HTML)
	public String cargoUsuario(@PathParam("param") String nombre) {
		// Llamamos al metodo que nos crea la conexion
		Connection conexion = Conexion.getConexion();
		// Comprobamos que la conexion no sea nula (es decir hay un error)
		if (conexion == null) {
			System.out.println("Error con la conexion a la BD!!");
		} else {
			try {
				System.out.println("Conexion a la BD correcta!!");
				// Si la conexion es correta podemos usarla para hacer consultas
				// sobre la BD
				// CONSULTAS AQUI
				Usuario usu = new Usuario(nombre, "serv", "1");
				// REALIZO LA CONSULTA O INSTRUCCION SQL
				try {
					Statement consulta = conexion.createStatement();
					String aEjecutar = "INSERT INTO usuarios (usuario, nombre, id_perfil) VALUES ('"
							+ usu.getUsuario()
							+ "', '"
							+ usu.getNombre()
							+ "', '" + usu.getId_perfil() + "')";
					consulta.executeUpdate(aEjecutar);
					System.out.println("Se ha registrado Exitosamente");
					consulta.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					System.out.println("No se Registro la persona");
				}
				// Al terminar de hacer las consultas siempre debemos de cerrar
				// la conexion
				conexion.close();
			} catch (SQLException ex) {
				Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
		// //////////////////////////////////////////////////////
		return "<html> " + "<title>" + "Alta Usuario" + "</title>"
				+ "<body><h1>" + "Cargue el usuario : " + nombre
				+ "</body></h1>" + "</html> ";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String listaUsuarios() {
		String s = "";
		// Llamamos al metodo que nos crea la conexion
		Connection conexion = Conexion.getConexion();
		// Comprobamos que la conexion no sea nula (es decir hay un error)
		if (conexion == null) {
			return s = "ERROR";
		}

		try {
			// Si la conexion es correta podemos usarla para hacer consultas
			// sobre la BD
			// CONSULTAS AQUI
			// REALIZO LA CONSULTA O INSTRUCCION SQL
			Statement consulta = conexion.createStatement();
			String aEjecutar = "select * from usuarios;";
			ResultSet resultado = consulta.executeQuery(aEjecutar);

			while (resultado.next()) {
				if (resultado.isFirst())
					s = "[";
				else
					s = s + ",";

				s = s + "{\"id\":\"" + resultado.getString(1) + "\","
						+ "\"usuario\":\"" + resultado.getString(2) + "\","
						+ "\"nombre\":\"" + resultado.getString(3) + "\","
						+ "\"id_perfil\":\"" + resultado.getString(3) + "\"}";

				if (resultado.isLast())
					s = s + "]";
			}

			consulta.close();

			return s;
		} catch (SQLException e) {

			return s = e.toString();
		}

	}

}
