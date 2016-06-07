package dir.gestioncredenciales.servicios;


import dir.gestioncredenciales.servicios.stub.GestionarRolesStub;

public class GestionarRolesSingleton {

	private static GestionarRoles rol = null;
	
	private GestionarRolesSingleton(){
		
	}
	
	public static GestionarRoles getGestionarRoles(){
		if(rol == null){
			rol = new GestionarRolesStub();
		}
	return rol;
		
	}
}


