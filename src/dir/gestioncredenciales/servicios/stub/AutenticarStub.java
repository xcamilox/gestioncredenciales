package dir.gestioncredenciales.servicios.stub;

import dir.gestioncredenciales.servicios.Autenticar;

public class AutenticarStub implements Autenticar {

	@Override
	public Boolean login(String nombre, String password) {
		if ("cnorris".equals(nombre) && "ranger".equals(password)) {
			return true;
		}
		return false; 
	}

}
