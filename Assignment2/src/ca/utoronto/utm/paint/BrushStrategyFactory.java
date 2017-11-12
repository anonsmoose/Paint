package ca.utoronto.utm.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ca.utoronto.utm.paint.BrushStrategies.BrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.CircleBrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.EraserBrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.PolylineBrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.RectangleBrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.SquareBrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.SquiggleBrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.TextToolBrushStrategy;

public class BrushStrategyFactory implements ActionListener{
	private PalletePanel panel;
	private PaintModel model;
	private BrushStrategy brushStrategy;
	
	public BrushStrategyFactory(PalletePanel panel, PaintModel model){
		this.panel = panel;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "Circle":
			this.brushStrategy = new CircleBrushStrategy(model);
			break;
			
		case "Squiggle":
			this.brushStrategy = new SquiggleBrushStrategy(model);
			break;
			
		case "Rectangle":
			this.brushStrategy = new RectangleBrushStrategy(model);
			break;
			
		case "Polyline":
			this.brushStrategy = new PolylineBrushStrategy(model);
			break;
			
		case "Square":
			this.brushStrategy = new SquareBrushStrategy(model);
			break;
		case "Eraser":
			this.brushStrategy = new EraserBrushStrategy(model);
			break;
		case "Text Tool":
			this.brushStrategy = new TextToolBrushStrategy(model);
			break;
		}
		JButton button = (JButton)e.getSource();
		this.panel.toggleButton(this.panel.getSelectedBrushButton(), button);
		this.panel.setSelectedBrushButton(button);
		this.panel.setBrushStrategy(brushStrategy);
	}

}
