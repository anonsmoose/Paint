package ca.utoronto.utm.paint.BrushStrategies;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Shapes.Point;
import ca.utoronto.utm.paint.Shapes.Rectangle;
import ca.utoronto.utm.paint.Shapes.Square;
/**
 * A brush that creates the largest square that fits in the area between the point where the mouse is clicked
 * to the point where the mouse is released.
 *
 */
public class SquareBrushStrategy extends BrushStrategy {
	private Square square;

	public SquareBrushStrategy(PaintModel model){
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
		this.square = new Square(origin, width, height, color1,color2, this.brushSize, this.fillStyle);
		this.model.addShape(this.square);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.model.isConnectedToServer()){
			this.model.sendShapeToServer(this.square);
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
		if(this.square != null){
			int xdiff = this.square.getOrigin().getX() - e.getX();
			int ydiff = this.square.getOrigin().getY() - e.getY();
			this.square.setYdiff(ydiff);
			this.square.setXdiff(xdiff);
		}
		this.model.addShape(this.square);

	}

}
