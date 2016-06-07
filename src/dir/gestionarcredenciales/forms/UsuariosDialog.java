package dir.gestionarcredenciales.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dir.gestioncredenciales.modelo.Rol;
import dir.gestioncredenciales.modelo.Usuario;
import dir.gestioncredenciales.servicios.GestionarRolesSingleton;
import dir.gestioncredenciales.servicios.ServiciosLocator;

@SuppressWarnings("serial")
public class UsuariosDialog  {

	private BotonesPanel botPanel;
	private Usuario usuario;
	private JLabel nombreLabel;
	private JLabel passLabel;
	private JCheckBox bloqueadoCheck;
	private JLabel bloqueadoLabel;
	private JLabel rolLabel;
	private JTextField nombreText;
	private JPasswordField passText;
	private JComboBox rolCombo;
	private List<Rol> roles;
	private JFrame frame;
	private JDialog dialog;
		
	public UsuariosDialog(){
		initDialog();
		initComponents();
		this.roles = GestionarRolesSingleton.getGestionarRoles().obtenerRoles();
		cargarComboRoles();
	}
	
	private void initDialog(){
		
		frame = new JFrame("Alta de Usuarios");

		frame.setBounds(30,30,300, 220);
		frame.setMinimumSize(frame.getSize());
		dialog = new JDialog(frame);
		dialog.setSize(300, 220);
		
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModal(true);
	}
		
	private void initComponents(){
		
		Insets margen = new Insets(5, 5, 5, 5);
		JPanel datosPanel = new JPanel();
		datosPanel.setLayout(new GridBagLayout());
		
		nombreLabel = new JLabel("Nombre:");
		datosPanel.add(nombreLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 
				GridBagConstraints.EAST, GridBagConstraints.EAST, margen, 0, 0));
			
		passLabel = new JLabel("Password:");
		datosPanel.add(passLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 
				GridBagConstraints.EAST, GridBagConstraints.EAST, margen, 0, 0));
		
		bloqueadoLabel = new JLabel("Bloqueado:");
		datosPanel.add(bloqueadoLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, 
				GridBagConstraints.EAST, GridBagConstraints.EAST, margen, 0, 0));
		
		bloqueadoCheck = new JCheckBox();
		datosPanel.add(bloqueadoCheck, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0, 
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
				
		rolLabel = new JLabel("Rol:");
		datosPanel.add(rolLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, 
				GridBagConstraints.EAST, GridBagConstraints.EAST, margen, 0, 0));
		
		nombreText = new JTextField();
		nombreText.setPreferredSize(new Dimension(150,20));
		datosPanel.add(nombreText, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, 
				GridBagConstraints.WEST, GridBagConstraints.BOTH, margen, 0, 0));
			
		passText = new JPasswordField();
		passText.setPreferredSize(new Dimension(150,20));
		datosPanel.add(passText, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, 
				GridBagConstraints.WEST, GridBagConstraints.BOTH, margen, 0, 0));
		
		rolCombo = new JComboBox();
		rolCombo.setPreferredSize(new Dimension(150,20));
		datosPanel.add(rolCombo, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, 
				GridBagConstraints.EAST, GridBagConstraints.BOTH, margen, 0, 0));
		
		dialog.getContentPane().add(datosPanel, BorderLayout.WEST);
		botPanel = new BotonesPanel() {
			
			@Override
			protected void onCancelarButtonActionPerformed(ActionEvent e) {
				dialog.dispose();
			}
			
			@Override
			protected void onAceptarButtonActionPerformed(ActionEvent e) {
				crearUsuario();
			}
		};
		dialog.getContentPane().add(botPanel, BorderLayout.SOUTH);
	}
	
	public Usuario getUsuario(){
		return this.usuario;
	}
	
	private void cargarComboRoles() {
		for(Rol rol : roles){
			rolCombo.addItem(rol.getNombre());
		}
	}
	
	private void crearUsuario(){
		usuario = new Usuario();
		usuario.setNombre(nombreText.getText());
		usuario.setPassword(new String(passText.getPassword()));
		usuario.setBloqueado(bloqueadoCheck.isSelected());
		for(Rol r : roles){
			if(r.getNombre() == rolCombo.getSelectedItem().toString()){
				usuario.setRol(r);
			}
		}
		ServiciosLocator.getGestionarUsuarios().crearUsuario(usuario);
		JOptionPane.showMessageDialog(dialog, "Se creo el usuario " + usuario.getNombre(), "Alta de usuarios", JOptionPane.INFORMATION_MESSAGE);
		cerrar();
		
	}
	
	public void show(Boolean state){
		
		dialog.setVisible(state);
	}
	
	private void cerrar(){
		dialog.dispose();
	}
}
