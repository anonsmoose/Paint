package ca.utoronto.utm.paint;

import javax.swing.*;  
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

	private String mode; // modifies how we interpret input (could be better?)
	private BrushStrategy brushStrategy;
	private Circle circle; // the circle we are building
	private Rectangle rectangle;
	
	public PaintPanel(PaintModel model, View view){
		this.setBackground(Color.blue);
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.mode="Circle"; // bad code here?
		
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
		g2d.setColor(Color.white);
        g2d.drawString ("i="+i, 50, 75);
		i=i+1;

		// Draw Lines
		ArrayList<Point> points = this.model.getPoints();
		for(int i=0;i<points.size()-1; i++){
			Point p1=points.get(i);
			Point p2=points.get(i+1);
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
		
		// Draw Circles
		ArrayList<Circle> circles = this.model.getCircles();
		for(Circle c: this.model.getCircles()){
			int x = c.getCentre().getX();
			int y = c.getCentre().getY();
			int radius = c.getRadius();
			if(radius > 0)
			{
				g2d.drawOval(x - radius, y - radius, radius, radius);
			}
			else {
				g2d.drawOval(x, y, -radius, -radius);
			}
		}
		
		//Draw Rectangles
		ArrayList<Rectangle> rectangles = this.model.getRectangles();
		for(Rectangle r: this.model.getRectangles())
		{
			int x = r.getCentre().getX();
			int y= r.getCentre().getY();
			int height = r.getheight();
			int width = r.getWidth();
			System.out.println("x: " + r.getCentre().getX());
			System.out.println("y: " + r.getCentre().getY());
			if(height  > 0 && width > 0)
			{
				g2d.drawRect(x - width, y - height, width, height);
			}
			else if(height > 0 && width < 0)
			{
				g2d.drawRect(x, y - height, -width, height);
			}
			else if(height < 0 && width > 0)
			{
				g2d.drawRect(x - width, y, width, -height);
			}
			else {
				g2d.drawRect(Math.abs(x), Math.abs(y), -width, -height);
			}
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
		this.mode=mode; // change later
	}
	
	// MouseMotionListener below
	@Override
	public void mouseMoved(MouseEvent e) {
		brushStrategy.mouseMoved(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		brushStrategy.mouseDragged(e);
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
