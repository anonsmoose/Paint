package ca.utoronto.utm.paint.BrushStrategies;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Shapes.Squiggle;

public class EraserBrushStrategy extends SquiggleBrushStrategy{

	public EraserBrushStrategy(PaintModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}
	
	public void mousePressed(MouseEvent e) {
		Color color = new Color(43, 43, 43);
		if(SwingUtilities.isRightMouseButton(e))
			color = this.secondaryColor;
		squiggle = new Squiggle(color, this.brushSize);
		this.model.addShape(squiggle);
		
	}

}
