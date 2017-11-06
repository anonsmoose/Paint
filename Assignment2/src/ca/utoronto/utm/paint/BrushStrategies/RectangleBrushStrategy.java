package ca.utoronto.utm.paint.BrushStrategies;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Point;
import ca.utoronto.utm.paint.Shapes.Rectangle;
import ca.utoronto.utm.paint.Shapes.Shape;

public class RectangleBrushStrategy extends ConcreteBrushStrategy{
	private Rectangle rectangle;

	public RectangleBrushStrategy(PaintModel model){
		this.model = model;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point origin = new Point(e.getX(), e.getY());
		int width = 0;
		int height = 0;
		Color color1 = this.primaryColor;
		Color color2 = this.secondaryColor;
		if(SwingUtilities.isRightMouseButton(e)){
			color1 = this.secondaryColor;
			color2 = this.primaryColor;
		}
		this.rectangle = new Rectangle(origin, width, height, color1, color2, this.brushSize, this.fillStyle);
		this.model.addShape(this.rectangle);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
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
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) { 
		if(this.rectangle != null){
			int width = this.rectangle.getOrigin().getX() - e.getX();
			int height = this.rectangle.getOrigin().getY() - e.getY();
			this.rectangle.setheight(height);
			this.rectangle.setWidth(width);
		}
		this.model.addShape(this.rectangle);
		
	}

}
