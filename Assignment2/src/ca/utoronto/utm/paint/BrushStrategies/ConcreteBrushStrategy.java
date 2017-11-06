package ca.utoronto.utm.paint.BrushStrategies;

import java.awt.Color;
import java.awt.event.MouseEvent;

import ca.utoronto.utm.paint.PaintModel;

public abstract class ConcreteBrushStrategy implements BrushStrategy{
	protected Color primaryColor = Color.white;
	protected Color secondaryColor = Color.white;
	protected int brushSize = 1;
	protected String fillStyle = "Outline";
	protected PaintModel model;
	
	public void setPrimaryColor(Color color){
		this.primaryColor = color;
	}
	
	public Color getPrimaryColor(){
		return this.primaryColor;
	}
	
	public void setSecondaryColor(Color color){
		this.secondaryColor = color;
	}
	
	public Color getSecondaryColor(){
		return this.secondaryColor;
	}
	
	public void setBrushSize(int brushSize){
		this.brushSize = brushSize;
	}
	
	public int getBrushSize(){
		return this.brushSize;
	}
	
	public void setFillStyle(String fillStyle){
		this.fillStyle = fillStyle;
	}
	
	public String getFillStyle(){
		return this.fillStyle;
	}
	
}
