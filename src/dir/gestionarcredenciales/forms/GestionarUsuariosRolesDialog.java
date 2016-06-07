package dir.gestionarcredenciales.forms;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import dir.gestioncredenciales.modelo.Usuario;
import dir.gestioncredenciales.servicios.ServiciosLocator;

public class GestionarUsuariosRolesDialog{

	
	private JPanel rolesPanel;
	private JPanel usuariosPanel;
	private JTable usuariosTable;
	private UsuariosTableModel userTableModel;
	private RolesTableModel rolTableModel;
	private JButton crearButton;
	private JButton modificarButton;
	private JButton eliminarButton;
	private JTabbedPane datosPanel;
	private JFrame frame;
	private JDialog dialog;
		
	
	
	
	public GestionarUsuariosRolesDialog(){
		initDialog();
		initComponents();
	}
	
	private void initDialog(){
		
		frame = new JFrame("Gestion de Usuarios y Roles");

		frame.setBounds(30,30,400, 300);
		frame.setMinimumSize(frame.getSize());
		dialog = new JDialog(frame);
		dialog.setSize(400, 300);
		
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				onWindowClosing();
			}
		});
	}
		
	private void initComponents(){
		
		datosPanel = new JTabbedPane();
		datosPanel.add("Roles", crearRolesPanel());
		datosPanel.add("Usuarios", crearUsuariosPanel());
		
		
		dialog.getContentPane().add(datosPanel, BorderLayout.CENTER);
		dialog.getContentPane().add(crearBotonesPanel(), BorderLayout.SOUTH);
			
		
	}
	
	private JPanel crearRolesPanel(){
				
		rolesPanel = new JPanel(new BorderLayout());
		rolTableModel = new RolesTableModel();
		JTable rolTable = new JTable(rolTableModel);

		rolTable.setRowHeight(20);
		rolTable.getTableHeader().setReorderingAllowed(true);
		JScrollPane rolScroll = new JScrollPane(rolTable);
		rolesPanel.add(rolScroll, BorderLayout.CENTER);
		
		return rolesPanel;
	}
	
	private JPanel crearUsuariosPanel(){
		
		usuariosPanel = new JPanel();
		usuariosPanel.setLayout(new BorderLayout());
		userTableModel = new UsuariosTableModel();
		usuariosTable = new JTable(userTableModel);
		usuariosTable.setRowHeight(20);
		
		//Permitir que se recoloquen las columnas.
		usuariosTable.getTableHeader().setReorderingAllowed(true);
		JScrollPane usuariosScroll = new JScrollPane(usuariosTable);
		usuariosPanel.add(usuariosScroll, BorderLayout.CENTER);
				
		return usuariosPanel;
	}
	
	private JPanel crearBotonesPanel(){
		
		JPanel botonesPanel = new JPanel();
		
		crearButton = new JButton("Crear");
		crearButton.addActionListener(e ->crear());
		
		modificarButton = new JButton("Modificar");
		modificarButton.addActionListener(e ->modificar());
		
		eliminarButton = new JButton("Eliminar");
		eliminarButton.addActionListener(e->eliminar());
		
		botonesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));//colocar los botones por la derecha.
		botonesPanel.add(crearButton);
		botonesPanel.add(modificarButton);
		botonesPanel.add(eliminarButton);
		return botonesPanel;
		
	}
		
	private void crear(){
		if(datosPanel.getSelectedIndex() == 0){
			CrearRolDialog r = new CrearRolDialog();
			r.setVisible(true);
			rolTableModel.actualizarModelo();
		}else if (datosPanel.getSelectedIndex() == 1){
			dialog.setVisible(false);
			UsuariosDialog u = new UsuariosDialog();
			u.show(true);
			dialog.setVisible(true);
			userTableModel.actualizarModelo();
		}
		
	}
		
		
	private void modificar(){
		if(datosPanel.getSelectedIndex() == 0){
			dialog.setVisible(false);
			CrearRolDialog r = new CrearRolDialog();
			r.setVisible(true);
			
		}else if (datosPanel.getSelectedIndex() == 1){
			UsuariosDialog u = new UsuariosDialog();
			u.show(true);
			if (u.getUsuario() != null){
				Usuario user = u.getUsuario();
				ServiciosLocator.getGestionarUsuarios().modificarUsuario(user);
				userTableModel.actualizarModelo();
			}
		}
		
		
	}
	
	public void show(Boolean state){
		
		dialog.setVisible(state);
	}
	
	private void eliminar(){
		
		if (usuariosTable.getSelectedRowCount() != -1){
			int[] filaseleccionada = usuariosTable.getSelectedRows();
			for (int i = 0; i< filaseleccionada.length-1; i++){
				Usuario user = userTableModel.getUsuarios().get(filaseleccionada[i]);
				ServiciosLocator.getGestionarUsuarios().eliminarUsuario(user);
				
			}
		}
		userTableModel.actualizarModelo();
	}
	
	
	private void onWindowClosing(){
				
		int respuesta = JOptionPane.showConfirmDialog(dialog, 
				"Seguro que desea salir?", "Salir", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (respuesta == JOptionPane.YES_OPTION){
			dialog.dispose();
		}
		
	}	
}

