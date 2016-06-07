package dir.gestioncredenciales.servicios;

import dir.gestioncredenciales.servicios.stub.AutenticarStub;
import dir.gestioncredenciales.servicios.stub.GestionarRolesStub;
import dir.gestioncredenciales.servicios.stub.GestionarUsuariosStub;

public final class ServiciosLocator {

	private static Autenticar autenticar = null;
	private static GestionarRoles gestionarRoles = null;
	private static GestionarUsuarios gestionarUsuarios = null;

	private ServiciosLocator() {}
	
	public static Autenticar getAutenticar() {
		if (autenticar == null) {
			autenticar = new AutenticarStub();
		}
		return autenticar;
	}

	public static GestionarRoles getGestionarRoles() {
		if (gestionarRoles == null) {
			gestionarRoles = new GestionarRolesStub();
		}
		return gestionarRoles;
	}
	
	public static GestionarUsuarios getGestionarUsuarios() {
		if (gestionarUsuarios == null) {
			gestionarUsuarios = new GestionarUsuariosStub();
		}
		return gestionarUsuarios;
	}
	
}
