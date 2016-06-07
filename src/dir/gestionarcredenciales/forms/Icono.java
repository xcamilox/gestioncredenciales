package dir.gestionarcredenciales.forms;

import java.net.URL;
import javax.swing.ImageIcon;

public class Icono {
	
	private ImageIcon iconoItem;
	
	public Icono(String nombre){
		cargarIcono(nombre);
	}
	
	private void cargarIcono(String nombre){
		URL url = getClass().getClassLoader().getResource(nombre);
		this.iconoItem = new ImageIcon(url);
	}

	public ImageIcon getIcono() {
		return iconoItem;
	}
	
}
