package dir.gestionarcredenciales.forms;

import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public abstract class BotonesPanel extends JPanel implements ActionListener{

	private JButton aceptarButton;
	private JButton cancelarButton;
	
	public BotonesPanel(){
		super();
		initPanel();
		initComponents();
	}
	
	private void initPanel(){
		this.setLayout(new FlowLayout());
	}
	
	private void initComponents(){
		
		cancelarButton = new JButton("Cancelar", 
				new Icono("dir/gestionarcredenciales/iconos/db cancel.gif").getIcono());
		cancelarButton.setMnemonic('C');
		cancelarButton.setIconTextGap(2);
		cancelarButton.setVerticalTextPosition(AbstractButton.CENTER);
		cancelarButton.setHorizontalTextPosition(AbstractButton.TRAILING);
		cancelarButton.setMargin(new Insets(2, 2, 2, 2));
		cancelarButton.addActionListener(this);
		this.add(cancelarButton);
		
		aceptarButton = new JButton("Aceptar", 
				new Icono("dir/gestionarcredenciales/iconos/ok.gif").getIcono());
		aceptarButton.setMnemonic('A');
		aceptarButton.setIconTextGap(2);
		aceptarButton.setVerticalTextPosition(AbstractButton.CENTER);
		aceptarButton.setHorizontalTextPosition(AbstractButton.TRAILING);
		aceptarButton.setMargin(new Insets(2, 2, 2, 2));
		aceptarButton.addActionListener(this);
		this.add(aceptarButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == aceptarButton) {
			onAceptarButtonActionPerformed(e);
		}else if (e.getSource() == cancelarButton) {
			onCancelarButtonActionPerformed(e);
		}
		
	}

	protected abstract void onAceptarButtonActionPerformed(ActionEvent e);
	protected abstract void onCancelarButtonActionPerformed(ActionEvent e);
		
}
