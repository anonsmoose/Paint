package ca.utoronto.utm.paint.BrushStrategies;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Point;
import ca.utoronto.utm.paint.Shapes.Circle;
import ca.utoronto.utm.paint.Shapes.Shape;

public class CircleBrushStrategy extends ConcreteBrushStrategy{
	private Circle circle;

	public CircleBrushStrategy(PaintModel model){
		this.model = model;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point centre = new Point(e.getX(), e.getY());
		int radius = 0;
		Color color = this.primaryColor;
		if(SwingUtilities.isRightMouseButton(e))
			color = this.secondaryColor;
		this.circle=new Circle(centre, radius,color,this.brushSize, this.isFilled);
		this.model.addShape(this.circle);
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
		if(this.circle != null){
			int radius = this.circle.getCentre().getX() - e.getX();
			this.circle.setRadius(radius);
		}
		this.model.addShape(this.circle);
	}

}
