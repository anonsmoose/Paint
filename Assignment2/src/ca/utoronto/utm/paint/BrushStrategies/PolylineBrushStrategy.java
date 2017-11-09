package ca.utoronto.utm.paint.BrushStrategies;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Shapes.Line;
import ca.utoronto.utm.paint.Shapes.Point;
/**
 * A brush that creates a line from a point clicked to the point where the mouse is release
 * from which it creates successive lines from the previouse end point of the last line.
 *
 */
public class PolylineBrushStrategy extends BrushStrategy {
	private Point origin;
	private Point destination;
	private Line line;
	
	public PolylineBrushStrategy(PaintModel model){
		this.model = model;
	}
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(this.origin == null)
			this.origin = new Point(e.getX(),e.getY());
		this.destination = new Point(e.getX(),e.getY());
		Color color = this.primaryColor;
		if(SwingUtilities.isRightMouseButton(e))
			color = this.secondaryColor;
		this.line = new Line(origin.getX(),origin.getY(),destination.getX(),destination.getY(),color,this.getBrushSize());
		this.model.addShape(line);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.origin = this.destination;

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
		destination = new Point(e.getX(),e.getY());
		this.line.setDestination(destination);

	}

}
