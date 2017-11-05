package ca.utoronto.utm.paint.BrushStrategies;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Point;
import ca.utoronto.utm.paint.Shapes.Shape;
import ca.utoronto.utm.paint.Shapes.Squiggle;

public class SquiggleBrushStrategy extends ConcreteBrushStrategy {
	private Squiggle squiggle;
	
	public SquiggleBrushStrategy(PaintModel model){
		this.model = model;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		squiggle = new Squiggle(this.primaryColor, this.brushSize);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.model.addShape(squiggle);
		
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
		if(this.squiggle != null){
			this.squiggle.addPoint(new Point(e.getX(), e.getY()));
		}	
		this.model.addShape(this.squiggle);
	}

}
