package sistemasMAM;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.*;
import java.util.*;

@Path("/ordenes")
public class OrdenesController {

	public static void init() {

	}

	@POST
	@Path("/{orden}")
	public static Response guardarOrden(String body,
			@PathParam("orden") String nuevaOrden) {
		Statement consulta;

		try {

			consulta = Conexion.getConexion().createStatement();

			String aEjecutar = "INSERT INTO ordenes (nro_orden, descripcion, usuario) VALUES ('"
					+ nuevaOrden + "', '" + body + "', 'ma')";

			consulta.executeUpdate(aEjecutar);
			consulta.close();

			System.out.println("Se ha registrado Exitosamente");

			return Response.status(200).entity("Orden Guardada").build();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("No se Registro la orden");

			return Response.status(512).entity("No se Registro la orden")
					.build();
		}

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public static Response listaOrdenes() {
		Statement consulta;
		String ordenes = "";

		try {

			consulta = Conexion.getConexion().createStatement();

			String aEjecutar = "SELECT * FROM ordenes";

			ResultSet resultado = consulta.executeQuery(aEjecutar);

			while (resultado.next()) {
				if (resultado.isFirst())
					ordenes = "[";
				else
					ordenes = ordenes + ",";

				ordenes = ordenes + "{\"numero\":\"" + resultado.getString(1)
						+ "\"," + "\"descripcion\":\"" + resultado.getString(2)
						+ "\"," + "\"usuario\":\"" + resultado.getString(3)
						+ "\"}";

				if (resultado.isLast())
					ordenes = ordenes + "]";
			}

			return Response.status(200).entity(ordenes).build();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error en select");

			return Response.status(500).entity("Error en select").build();
		}

	}
}
