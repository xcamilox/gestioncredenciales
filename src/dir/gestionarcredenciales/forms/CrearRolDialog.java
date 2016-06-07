package dir.gestionarcredenciales.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;


import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dir.gestioncredenciales.modelo.Rol;

import dir.gestioncredenciales.servicios.ServiciosLocator;

public class CrearRolDialog extends JDialog {

	private Boolean creado;
	private BotonesPanel botPanel;
	private JLabel rolLabel;
	private JTextField rolText;
		
	public CrearRolDialog(){
		this.creado = false;
		initDialog();
		initComponents();
	}
	
	private void initDialog(){
		setTitle("Crear Roles");
		setSize(270, 120);
		setMinimumSize(this.getSize());
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModal(true);
	}
	
	@SuppressWarnings("serial")
	private void initComponents(){
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		rolLabel = new JLabel("Nombre");
		rolText = new JTextField();
		rolText.setPreferredSize(new Dimension(180, 20));
		panel.add(rolLabel);
		panel.add(rolText);
		getContentPane().add(panel, BorderLayout.CENTER);
				
		botPanel = new BotonesPanel() {
			
			@Override
			protected void onCancelarButtonActionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				creado = false;
			}
			
			@Override
			protected void onAceptarButtonActionPerformed(ActionEvent e) {
				onCrearButtonActionPerformer();
				
			}
		};
		
		getContentPane().add(botPanel, BorderLayout.SOUTH);
		
				
	}
		
	protected void onCrearButtonActionPerformer(){
		
		Rol rol = new Rol();
		rol.setNombre(rolText.getText()); //recoger el nombre de un cuadro de texto.
		ServiciosLocator.getGestionarRoles().crearRol(rol);
		creado = true;
		JOptionPane.showMessageDialog(this, "Se cre� el rol " + rol.getNombre(), 
				"Alta de roles", JOptionPane.INFORMATION_MESSAGE);
		cerrar();
	}
	
	public Boolean getCreado(){
		return creado;
	}
	
	protected void onCancelarButtonActionPerformer(ActionEvent e){
		//TODO Cancel Button action		
	}
	
	public void cerrar(){
		dispose();
	}
	
}
