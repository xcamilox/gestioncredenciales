package dir.gestionarcredenciales.forms;

import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import dir.gestioncredenciales.modelo.Rol;
import dir.gestioncredenciales.servicios.ServiciosLocator;

public class RolesTableModel implements TableModel {

	private List<Rol> roles;
	private TableModelListener listener;
	
	public RolesTableModel(){
		this.roles = ServiciosLocator.getGestionarRoles().obtenerRoles();
	}
	
	public void actualizarModelo(){
		roles = ServiciosLocator.getGestionarRoles().obtenerRoles();
		listener.tableChanged(new TableModelEvent(this));
	}
	
	@Override
	public void addTableModelListener(TableModelListener l) {
		listener = l;
	}

	@Override
	public Class<?> getColumnClass(int columIndex) {
		if(columIndex==1){
			return String.class;
		}else{
			return Integer.class;
		}
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public String getColumnName(int columIndex) {
		if(columIndex == 1){
			return "Nombre";
		}else{
			return "Id";
		}
	}

	@Override
	public int getRowCount() {
		return roles.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columIndex) {
		Rol rol =roles.get(rowIndex);
		if(columIndex==1){
			return rol.getNombre();
		}else{
			return rol.getId();
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
		//TODO remove table model listener
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columIndex) {
		Rol rol = roles.get(rowIndex);
		if (columIndex==1){
			rol.setNombre(value.toString());
		}
	}
}
