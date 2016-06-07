package dir.gestioncredenciales.servicios.stub;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import dir.gestioncredenciales.modelo.Rol;
import dir.gestioncredenciales.servicios.GestionarRoles;

public class GestionarRolesStub implements GestionarRoles {

	private List<Rol> roles = new ArrayList<>();
	
	private static final Logger log = Logger.getLogger(GestionarRolesStub.class.getName());
	
	public GestionarRolesStub() {
		
		Rol rol1 = new Rol();
		rol1.setId(1);
		rol1.setNombre("administrador");

		Rol rol2 = new Rol();
		rol2.setId(2);
		rol2.setNombre("usuario");
		
		roles.add(rol1);
		roles.add(rol2);
		
	}
	
	@Override
	public void crearRol(Rol rol) {
		roles.add(rol);
	}

	@Override
	public void modificarRol(Rol rol) {
		log.info("se modific� el rol " + rol.getNombre());
		
		
	}

	@Override
	public void eliminarRol(Rol rol) {
		log.info("se elimin� el rol " + rol.getNombre());
		roles.remove(rol);
	}

	@Override
	public List<Rol> obtenerRoles() {
		return roles;
	}
}











