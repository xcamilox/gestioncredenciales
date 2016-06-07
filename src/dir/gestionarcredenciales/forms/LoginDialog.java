package dir.gestionarcredenciales.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import dir.gestioncredenciales.servicios.AutenticarSingleton;

public class LoginDialog {
	
	private JLabel nombreLabel;
	private JLabel passLabel;
	private JTextField nombreText;
	private JTextField passText;
	private JButton cancelarButton;
	private JButton accederButton;
	private JFrame frame;
	private JDialog dialog;
	
	private Boolean valido;
	private static final String LOGINSTR = "Login";
	
	public LoginDialog(){
		this.valido = false;
		initDialog();
		initComponents();
	}
	
	private void initDialog(){
		
		frame = new JFrame(LOGINSTR);

		frame.setBounds(30,30,250, 150);
		frame.setMinimumSize(frame.getSize());
		dialog = new JDialog(frame);
		dialog.setSize(250, 150);
		dialog.setMinimumSize(dialog.getSize());
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModal(true);
	}
		
	private void initComponents(){
		
		JPanel datosPanel = new JPanel();
		datosPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.VERTICAL;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(5,5,5,5);
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 0;
				
		nombreLabel = new JLabel("Nombre:");
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.EAST;
		datosPanel.add(nombreLabel, c);
		
		passLabel = new JLabel("Password:");;
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		datosPanel.add(passLabel, c);
		
		nombreText = new JTextField();
		nombreText.setPreferredSize(new Dimension(120, 20));
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		datosPanel.add(nombreText, c);
		
		passText = new JTextField();
		passText.setPreferredSize(new Dimension(120, 20));
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		datosPanel.add(passText, c);
		dialog.getContentPane().add(datosPanel, BorderLayout.CENTER);
		
		JPanel botonesPanel = new JPanel();
		botonesPanel.setLayout(new GridBagLayout());
		
		cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(e->cerrar());
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		botonesPanel.add(cancelarButton, c);
		
		accederButton = new JButton("Acceder");
		accederButton.addActionListener(e->onAccederButtonActionPerformer());
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		botonesPanel.add(accederButton, c);
		
		dialog.getContentPane().add(botonesPanel, BorderLayout.SOUTH);
		
	}
	
	private void onAccederButtonActionPerformer(){
		//Para campos de password usar JPasswordField
		//Para recuperar el texto: New String(passText.getPassword. 
		//porque JPasswordField.getPassword devuelve una cadena de chars
		valido = AutenticarSingleton.getAutenticar().login(nombreText.getText(), passText.getText());
		if (valido){
			JOptionPane.showMessageDialog(dialog, "Acceso permitido", LOGINSTR, JOptionPane.INFORMATION_MESSAGE);
			cerrar();
		}else{
			JOptionPane.showMessageDialog(dialog, "Acceso denegado", LOGINSTR, JOptionPane.ERROR_MESSAGE);
		}
		
	}
	public void show(Boolean state){
		
		dialog.setVisible(state);
	}
	
	public JDialog getDialog() {
		return dialog;
	}

	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

	public Boolean getValido(){
		return valido; 
	}
	
	private void cerrar(){
		dialog.dispose();
	}
	
	
}
