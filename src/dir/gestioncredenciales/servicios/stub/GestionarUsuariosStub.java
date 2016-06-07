package dir.gestioncredenciales.servicios.stub;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import dir.gestioncredenciales.modelo.Usuario;
import dir.gestioncredenciales.servicios.GestionarUsuarios;

public class GestionarUsuariosStub implements GestionarUsuarios {

	private List<Usuario> usuarios = new ArrayList<>();
	private static final Logger log = Logger.getLogger(GestionarUsuariosStub.class.getName());
	
	
	@Override
	public void crearUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		log.info("se modific� el usuario " + usuario.getNombre());
	}

	@Override
	public void eliminarUsuario(Usuario usuario) {
		usuarios.remove(usuario);
		log.info("se elimin� el usuario " + usuario.getNombre());
	}

	public List<Usuario> obtenerUsuarios() {
		return usuarios;
	}

}
