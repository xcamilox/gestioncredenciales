package dir.gestioncredenciales.servicios;

import java.util.List;

import dir.gestioncredenciales.modelo.Rol;

public interface GestionarRoles {

	public void crearRol(Rol rol);
	public void modificarRol(Rol rol);
	public void eliminarRol(Rol rol);
	public List<Rol> obtenerRoles();
		
}
