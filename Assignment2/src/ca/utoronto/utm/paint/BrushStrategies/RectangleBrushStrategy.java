package ca.utoronto.utm.paint.BrushStrategies;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Point;
import ca.utoronto.utm.paint.Shapes.Rectangle;
import ca.utoronto.utm.paint.Shapes.Shape;

public class RectangleBrushStrategy implements BrushStrategy{
	private PaintModel model;
	private Rectangle rectangle;
	private int brushSize = 1;
	private Color color = Color.black;
	public RectangleBrushStrategy(PaintModel model){
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
		Point origin = new Point(e.getX(), e.getY());
		int width = 0;
		int height = 0;
		this.rectangle = new Rectangle(origin, width, height, this.color, this.brushSize);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
/*		int width = Math.abs(this.rectangle.getCentre().getX() - e.getX());
		int height = Math.abs(this.rectangle.getCentre().getY() - e.getY());
		this.rectangle.setheight(height);
		this.rectangle.setWidth(width);
		this.model.addRectangle(this.rectangle);
		this.rectangle = null;*/
		
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
			ArrayList<Shape> shapes = this.model.getShapes();
			int width = this.rectangle.getOrigin().getX() - e.getX();
			int height = this.rectangle.getOrigin().getY() - e.getY();
			this.rectangle.setheight(height);
			this.rectangle.setWidth(width);
			if(!(shapes.contains(this.rectangle))) 
			{
			this.model.addShape(this.rectangle);
			}
		}
		
	}

}
