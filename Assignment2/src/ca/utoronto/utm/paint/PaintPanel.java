package ca.utoronto.utm.paint;

import javax.swing.*;

import ca.utoronto.utm.paint.BrushStrategies.BrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.CircleBrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.RectangleBrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.SquiggleBrushStrategy;
import ca.utoronto.utm.paint.Shapes.*;
import ca.utoronto.utm.paint.Shapes.Rectangle;
import ca.utoronto.utm.paint.Shapes.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class PaintPanel extends JPanel implements Observer{
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	private BrushStrategy brushStrategy;
	
	public PaintPanel(PaintModel model, View view){
		this.setBackground(new Color(43, 43, 43));
		this.setPreferredSize(new Dimension(600,600));

		this.model = model;
		this.model.addObserver(this);
		this.brushStrategy = new CircleBrushStrategy(this.model);
		this.addMouseListener(brushStrategy);
		this.addMouseMotionListener(brushStrategy);
		this.view=view;
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g); //paint background 
        Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.black);
	    //draw shapes
		for(Shape shape: this.model.getShapes()){
				shape.drawShape(g);	
		}
		g2d.dispose();
	}

	@Override
	public void update(Observable o, Object arg) {
		this.repaint(); // Schedule a call to paintComponent
	}
	
	public void updateBrushStrategy(BrushStrategy brushStrategy){
		//Unhook old brush strategy
		this.removeMouseListener(this.brushStrategy);
		this.removeMouseMotionListener(this.brushStrategy);
		
		//Hook up new brush strategy
		this.brushStrategy = brushStrategy;
		this.addMouseListener(this.brushStrategy);
		this.addMouseMotionListener(this.brushStrategy);
	}
}
	


