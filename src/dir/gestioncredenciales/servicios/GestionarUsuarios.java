package dir.gestioncredenciales.servicios;

import java.util.List;

import dir.gestioncredenciales.modelo.Usuario;

public interface GestionarUsuarios {

	public void crearUsuario(Usuario usuario);
	public void modificarUsuario(Usuario usuario);
	public void eliminarUsuario(Usuario usuario);
	public List<Usuario> obtenerUsuarios();
}
