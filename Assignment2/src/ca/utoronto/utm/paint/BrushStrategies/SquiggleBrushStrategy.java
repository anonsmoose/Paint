package ca.utoronto.utm.paint.BrushStrategies;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Point;
import ca.utoronto.utm.paint.Shapes.Shape;
import ca.utoronto.utm.paint.Shapes.Squiggle;

public class SquiggleBrushStrategy implements BrushStrategy {
	private PaintModel model;
	private Squiggle squiggle;
	private int brushSize = 1;
	private Color color = Color.black;
	public SquiggleBrushStrategy(PaintModel model){
		this.model = model;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.brushSize = this.model.getBrushSize();
		//this.color = this.model.getColor();
		squiggle = new Squiggle(this.color, this.brushSize);
		
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
			ArrayList<Shape> shapes = this.model.getShapes();
			this.squiggle.addPoint(new Point(e.getX(), e.getY()));
			if(!(shapes.contains(this.squiggle)))
			{
			this.model.addShape(this.squiggle);
			}
		}	
	}

}
