package ca.utoronto.utm.paint.BrushStrategies;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Point;
import ca.utoronto.utm.paint.Shapes.Circle;
import ca.utoronto.utm.paint.Shapes.Shape;

public class CircleBrushStrategy implements BrushStrategy{
	private Circle circle;
	private PaintModel model;
	private int brushSize;
	private Color color = Color.black;
	
	public CircleBrushStrategy(PaintModel model){
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
		Point centre = new Point(e.getX(), e.getY());
		int radius = 0;
		this.circle=new Circle(centre, 0,this.color,this.brushSize);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.circle!=null){
			// Problematic notion of radius and centre!!
/*			int radius = Math.abs(this.circle.getCentre().getX()-e.getX());
			this.circle.setRadius(radius);
			this.model.addCircle(this.circle);
			this.circle=null;*/
		}
		
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
			ArrayList<Shape> shapes = this.model.getShapes();
			int radius = this.circle.getCentre().getX() - e.getX();
			this.circle.setRadius(radius);
			if(!(shapes.contains(this.circle)))
			{
			this.model.addShape(this.circle);
			}
		}
	}

}
