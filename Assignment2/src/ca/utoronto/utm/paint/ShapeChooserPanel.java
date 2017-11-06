package ca.utoronto.utm.paint;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class ShapeChooserPanel extends JPanel implements ActionListener {
	private View view; // So we can talk to our parent or other components of the view
	private JButton previousButtonClick;
	private int i = 0;
	
	public ShapeChooserPanel(View view) {	
		this.view=view;
		
		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline"};
		String[] icons = {"/circle1.png", "/rectangle1.png", "/square1.png", "/pencil1.png", "/polyline1.png"};
		
		this.setLayout(new GridLayout(buttonLabels.length, 1));
		for (String label : buttonLabels) {
			JButton button = new JButton(label);
			
			ImageIcon icon = new ImageIcon("images\\" + icons[i]);
			button.setIcon(icon);
			i++;
			this.add(button);
			styleButtons(button);
			button.addActionListener(this);
		}
	}
	
	private static void styleButtons(JButton button) {
		button.setFocusPainted(false);
		button.setFont(new Font("Arial", Font.BOLD, 22));
		button.setVerticalTextPosition(3);
		button.setHorizontalTextPosition(0);
		button.setBackground(new Color(60, 60, 60));
		button.setForeground(new Color(255, 3, 118));
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(3, 7, 3, 7);
		Border compound = new CompoundBorder(line, margin);
		button.setBorder(compound);
	}
	
	/**
	 * Controller aspect of this
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		Border line = new LineBorder(Color.GREEN);
		Border margin = new EmptyBorder(3, 7, 3, 7);
		Border compound = new CompoundBorder(line, margin);
		button.setBorder(compound);
		if(this.previousButtonClick != null) {
			line = new LineBorder(Color.BLACK);
			compound = new CompoundBorder(line, margin);
			this.previousButtonClick.setBorder(compound);
			this.previousButtonClick.setEnabled(true);
		}
		//this.view.getPaintPanel().setMode(e.getActionCommand());
		button.setEnabled(false);
		this.previousButtonClick = button;
		
	}

	
}
