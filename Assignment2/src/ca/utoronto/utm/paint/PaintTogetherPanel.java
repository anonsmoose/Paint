package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PaintTogetherPanel extends JPanel implements Observer {
	private PaintModel model;
	private JTextField startServerPortField;
	private JTextField connectToAddressField;
	private JTextField connectToPortField;
	private JButton hostButton;
	private JButton connectButton;
	private JButton disconnectButton;
	private final Font font = new Font("Arial", Font.BOLD, 16);
	private final Color textColor = new Color(255, 3, 118);
	
	/**
	 * A part of the view of a paint program, contains controls for connecting 
	 * hosting and disconnecting from servers
	 * @param model
	 */
	public PaintTogetherPanel(PaintModel model){
		this.model = model;
		this.model.addObserver(this);
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.DARK_GRAY);
		ActionListener hostListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.startServer(Integer.parseInt(startServerPortField.getText()));
				model.connectToServer("localhost", Integer.parseInt(startServerPortField.getText()));
			}
			
		};
		
		ActionListener connectListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.connectToServer(connectToAddressField.getText(), Integer.parseInt(connectToPortField.getText()));
			}
		};
		
		ActionListener disconnectListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.disconnectFromServer();
				
			}	
		};
		
		//Paint together label
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		JLabel paintTogetherLabel =  new JLabel("Paint Online");
		paintTogetherLabel.setFont(font);
		paintTogetherLabel.setForeground(textColor);
		this.add(paintTogetherLabel, c);
		
		//Host label
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.insets = new Insets(0,0,0,10);
		JLabel hostLabel = new JLabel("Host on Port:");
		hostLabel.setFont(font);
		hostLabel.setForeground(textColor);
		this.add(hostLabel,c);
		
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
		PalletePanel.styleButton(hostButton);
		hostButton.addActionListener(hostListener);
		this.hostButton = hostButton;
		this.add(hostButton, c);
		
		//Connect to Address Label
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 1;
		c.insets = new Insets(0,0,0,10);
		JLabel connectToLabel = new JLabel("Connect to: ");
		connectToLabel.setFont(font);
		connectToLabel.setForeground(textColor);
		this.add(connectToLabel, c);
		
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
		JLabel connectToPortLabel = new JLabel("Port: ");
		connectToPortLabel.setFont(font);
		connectToPortLabel.setForeground(textColor);
		this.add(connectToPortLabel, c);
		
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
		PalletePanel.styleButton(connectButton);
		this.connectButton = connectButton;
		this.add(connectButton,c);
		
		//Disconnect Button
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 9;
		c.gridy = 1;
		JButton disconnectButton = new JButton("Disconnect");
		PalletePanel.styleButton(disconnectButton);
		this.disconnectButton = disconnectButton;
		disconnectButton.setEnabled(false);
		disconnectButton.addActionListener(disconnectListener);
		this.add(disconnectButton, c);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		boolean connected = this.model.isConnectedToServer();
		this.connectButton.setEnabled(!connected);
		this.hostButton.setEnabled(!connected);
		this.disconnectButton.setEnabled(connected);
	}
}
