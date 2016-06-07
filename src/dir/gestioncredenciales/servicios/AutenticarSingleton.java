package dir.gestioncredenciales.servicios;

import dir.gestioncredenciales.servicios.stub.AutenticarStub;

public class AutenticarSingleton {

	private static Autenticar autenticar = null;
	
	private AutenticarSingleton(){
		
	}
	
	public static Autenticar getAutenticar(){
		if(autenticar == null){
			autenticar = new AutenticarStub();
		}
		return autenticar;
	}
}
