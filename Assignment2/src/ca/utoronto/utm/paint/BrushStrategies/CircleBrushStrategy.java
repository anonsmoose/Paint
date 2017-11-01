package ca.utoronto.utm.paint.BrushStrategies;

import java.awt.event.MouseEvent;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Point;
import ca.utoronto.utm.paint.Shapes.Circle;

public class CircleBrushStrategy implements BrushStrategy{
	private Circle circle;
	private PaintModel model;
	
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
		this.circle=new Circle(centre, 0);
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
			int radius = this.circle.getCentre().getX() - e.getX();
			this.circle.setRadius(radius);
			this.model.addShape(this.circle);
		}
	}

}
