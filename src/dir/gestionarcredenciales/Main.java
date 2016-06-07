package dir.gestionarcredenciales;

import dir.gestionarcredenciales.forms.GestionarUsuariosRolesDialog;
import dir.gestionarcredenciales.forms.LoginDialog;
import dir.gestioncredenciales.modelo.Rol;
import dir.gestioncredenciales.modelo.Usuario;
import dir.gestioncredenciales.servicios.ServiciosLocator;

public class Main {
	
	private Main(){}
	
	public static void main(String[] args) {
		
		Usuario user = new Usuario();
		user.setId(1);
		user.setNombre("Oliver");
		user.setPassword("43826914");
		user.setBloqueado(false);
		user.setRol(new Rol());
		user.getRol().setNombre("Administrador");
		ServiciosLocator.getGestionarUsuarios().crearUsuario(user);
				
		LoginDialog l = new LoginDialog();
		l.show(true);
		if(l.getValido()){
			GestionarUsuariosRolesDialog g = new GestionarUsuariosRolesDialog();
			g.show(true);
		}
		
		l.getDialog().dispose();
		
	}

}
