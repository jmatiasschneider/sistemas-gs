package sistemasMAM;
import java.sql.*;

public class Usuario {

    private String usuario;
    private String nombre;
    private String id_perfil;
    
    public Usuario(String usuario, String nombre, String id_perfil){   
    	this.usuario   = usuario;
    	this.nombre    = nombre;
    	this.id_perfil = id_perfil;
    }
    
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getId_perfil() {
		return id_perfil;
	}
	public void setId_perfil(String id_perfil) {
		this.id_perfil = id_perfil;
	}
}
