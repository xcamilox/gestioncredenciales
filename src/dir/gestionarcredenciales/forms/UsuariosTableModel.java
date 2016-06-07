package dir.gestionarcredenciales.forms;

import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import dir.gestioncredenciales.modelo.Rol;
import dir.gestioncredenciales.modelo.Usuario;
import dir.gestioncredenciales.servicios.ServiciosLocator;

public class UsuariosTableModel implements TableModel {

	private List<Usuario> usuarios;
	private TableModelListener listener;
		
	public UsuariosTableModel(){
		this.usuarios = ServiciosLocator.getGestionarUsuarios().obtenerUsuarios();
	}
	@Override
	public void addTableModelListener(TableModelListener l) {
		listener = l;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void actualizarModelo(){
		this.usuarios = ServiciosLocator.getGestionarUsuarios().obtenerUsuarios();
		listener.tableChanged(new TableModelEvent(this));
	}
	
	//Retorna la Clase a la que pertenece el tipo de dato que almacena cada columna.
	@Override
	public Class<?> getColumnClass(int columIndex) {
		switch (columIndex){
			case 0: return Integer.class;
			case 3: return Boolean.class;
			case 4: return Rol.class;
			default : return String.class;
		}
	}

	//Numero de columnas del JTable
	@Override
	public int getColumnCount() {
		return 5;
	}
	
	//Devuelve el nombre de las columnas seg�n columIndex.
	@Override
	public String getColumnName(int columIndex) {
		switch (columIndex){
			case 0: return "Id";
			case 1: return "Nombre";
			case 2: return "Password";
			case 3: return "Bloqueado";
			default: return "Rol";
		}
		
	}
	@Override
	public int getRowCount() {
		return usuarios.size();
	}
	@Override
	public Object getValueAt(int rowIndex, int columIndex) {
		Object tmp = null;
		if (usuarios.isEmpty()){
			tmp = this.getUserByColumn(rowIndex,columIndex);
		}
		return tmp;	
		
	}
	private Object getUserByColumn(int rowIndex,int columIndex){
		Usuario user = usuarios.get(rowIndex);
		switch (columIndex){
			case 0: return user.getId();
			case 1: return user.getNombre();
			case 2: return user.getPassword();
			case 3: return user.getBloqueado();
			default: return user.getRol().getNombre();
		}
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columIndex) {
		if(columIndex == 0){
			return false;
		}
		return true;
	}
	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		//TODO remoe table model listener
	}
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Usuario user = usuarios.get(rowIndex);
		switch (columnIndex){
			case 1: 
				user.setNombre(value.toString()); 
				break;
			case 2: 
				user.setPassword((String)value); 
				break;
			case 3: 
				user.setBloqueado((Boolean)value); 
				break;
			default: 
				user.setRol((Rol)value); 
				break;
		}
	}
}
