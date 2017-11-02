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

class PaintPanel extends JPanel implements Observer, MouseMotionListener, MouseListener  {
	private int i=0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	private BrushStrategy brushStrategy;
	private Circle circle; // the circle we are building
	private Rectangle rectangle;
	
	public PaintPanel(PaintModel model, View view){
		this.setBackground(new Color(43, 43, 43));
		this.setPreferredSize(new Dimension(600,600));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.model = model;
		this.model.addObserver(this);
		this.brushStrategy = new CircleBrushStrategy(this.model);
		this.view=view;
	}

	/**
	 *  View aspect of this
	 */
	public void paintComponent(Graphics g) {
		// Use g to draw on the JPanel, lookup java.awt.Graphics in
		// the javadoc to see more of what this can do for you!!
		
        super.paintComponent(g); //paint background 
        Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
		// setBackground (Color.blue); 
		// Origin is at the top left of the window 50 over, 75 down
		//int strokeSize = this.view.getPalletePanel().getBrushSize();
        //g2d.setStroke(new BasicStroke(strokeSize));
        g2d.setColor(Color.black);
        //g2d.drawString ("i="+i, 50, 75);
		//i=i+1;
		
		//draw shapes
		for(Shape shape: this.model.getShapes()){
				shape.drawShape(g);
				
		}
		
		g2d.dispose();
	}

	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint(); // Schedule a call to paintComponent
	}
	
	/**
	 *  Controller aspect of this
	 */
	public void setMode(String mode){
		switch(mode){
		case "Circle":
			this.brushStrategy = new CircleBrushStrategy(this.model);
			break;
			
		case "Squiggle":
			this.brushStrategy = new SquiggleBrushStrategy(this.model);
			break;
			
		case "Rectangle":
			this.brushStrategy = new RectangleBrushStrategy(this.model);
			break;
		}
	}
	
	// MouseMotionListener below
	@Override
	public void mouseMoved(MouseEvent e) {
		brushStrategy.mouseMoved(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		brushStrategy.mouseDragged(e);
		this.updateUI();
	}

	// MouseListener below
	@Override
	public void mouseClicked(MouseEvent e) {
		brushStrategy.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		brushStrategy.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		brushStrategy.mouseReleased(e);
	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
		brushStrategy.mouseEntered(e);
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		brushStrategy.mouseExited(e);
	}
}
