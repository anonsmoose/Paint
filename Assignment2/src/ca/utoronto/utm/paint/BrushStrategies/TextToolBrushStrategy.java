package ca.utoronto.utm.paint.BrushStrategies;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Shapes.Point;
import ca.utoronto.utm.paint.Shapes.Rectangle;
import ca.utoronto.utm.paint.Shapes.Text;
import javax.swing.*;

public class TextToolBrushStrategy extends BrushStrategy{

	protected Text text;
	public TextToolBrushStrategy(PaintModel model){
		this.model = model;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] options = ge.getAvailableFontFamilyNames();
		Point origin = new Point(e.getX(), e.getY());
		String text = JOptionPane.showInputDialog(null, "Enter your text");
		int fontSize = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter text size"));
		String fontFamily = (String)JOptionPane.showInputDialog(null,"Choose your font family",
                "Font Chooser Dialog", JOptionPane.PLAIN_MESSAGE, null, options, "ham");
		Color color1 = this.primaryColor;
		this.text = new Text(origin,fontSize, text, fontFamily, color1);
		this.model.addShape(this.text);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
