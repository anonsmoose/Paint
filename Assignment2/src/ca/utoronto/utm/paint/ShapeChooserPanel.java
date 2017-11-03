package ca.utoronto.utm.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class ShapeChooserPanel extends JPanel implements ActionListener {
	private View view; // So we can talk to our parent or other components of the view
	
	public ShapeChooserPanel(View view) {	
		this.view=view;
		
		
		String[] icons = {"/circle1.png", "/rectangle1.png", "/square1.png", "/pencil1.png", "/polyline1.png"};
		
		
		this.setLayout(new GridLayout(icons.length,1));
		
		for(String icon : icons) {
			JButton button = new JButton();
			button.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(icon)).getImage()));
			this.add(button);
			button.addActionListener(this);
		}
	}
	
	/**
	 * Controller aspect of this
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.view.getPaintPanel().setMode(e.getActionCommand());
		//System.out.println(e.getActionCommand());
	}

	
}
