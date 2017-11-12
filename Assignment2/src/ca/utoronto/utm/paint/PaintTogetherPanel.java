package ca.utoronto.utm.paint;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PaintTogetherPanel extends JPanel {
	private PaintModel model;
	private JTextField startServerPortField;
	private JTextField connectToAddressField;
	private JTextField connectToPortField;
	
	public PaintTogetherPanel(PaintModel model){
		this.model = model;
		this.setLayout(new GridBagLayout());
		ActionListener hostListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.startServer(Integer.parseInt(startServerPortField.getText()));
			}
			
		};
		
		ActionListener connectListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.connectToServer(connectToAddressField.getText(), Integer.parseInt(connectToPortField.getText()));
				
			}
			
		};
		//Paint together label
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		this.add(new JLabel("Paint Together"), c);
		
		//Host label
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.insets = new Insets(0,0,0,10);
		this.add(new JLabel("Host on Port:"),c);
		
		//Host Port Text Field
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		this.startServerPortField = new JTextField("27854");
		this.add(startServerPortField, c);
		
		//Host Button
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		c.insets = new Insets(0,0,0,50);
		JButton hostButton = new JButton("Host");
		hostButton.addActionListener(hostListener);
		this.add(hostButton, c);
		
		//Connect to Address Label
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 1;
		c.insets = new Insets(0,0,0,10);
		this.add(new JLabel("Connect to: "), c);
		
		//Connect to Address Text Field
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 1;
		this.connectToAddressField = new JTextField("localhost");
		this.add(connectToAddressField, c);
		
		//Connect to Port Label
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 1;
		c.insets = new Insets(0,10,0,10);
		this.add(new JLabel("Port: "), c);
		
		//Connect to Port Text Field
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 7;
		c.gridy = 1;
		this.connectToPortField = new JTextField("27854");
		this.add(connectToPortField, c);
		
		//Connect button
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 8;
		c.gridy = 1;
		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(connectListener);
		this.add(connectButton,c);
		

	}

}
