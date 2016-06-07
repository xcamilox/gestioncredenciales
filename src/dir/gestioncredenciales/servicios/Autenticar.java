package dir.gestioncredenciales.servicios;

@FunctionalInterface
public interface Autenticar {

	public Boolean login(String nombre, String password);
		
}
