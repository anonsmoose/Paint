package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ca.utoronto.utm.paint.BrushStrategies.BrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.CircleBrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.ConcreteBrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.PolylineBrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.RectangleBrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.SquareBrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.SquiggleBrushStrategy;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PalletePanel extends JPanel implements ChangeListener, ActionListener {
	private ConcreteBrushStrategy brushStrategy;
	private PaintModel model;
	
	private JSlider thicknessSlider;
	private JLabel thicknessLabel;
	private JColorChooser colorChooser;
	private JButton primaryColorButton;
	private JButton secondaryColorButton;
	
	private int brushSize;
	private Color primaryColor = Color.white;
	private Color secondaryColor = Color.white;
	private boolean isFilled;
	
	private boolean isChanged;
	private GridBagConstraints c;
	
	private final Font font = new Font("Arial", Font.BOLD, 16);
	private final Color textColor = new Color(255, 3, 118);
	private JButton selectedBrushButton;
	private JButton selectedFillButton;
	
	public PalletePanel(PaintModel model) {
		this.setLayout(new GridBagLayout());
		this.c = new GridBagConstraints();
		this.brushSize = 1;
		this.model = model;
		this.brushStrategy = new CircleBrushStrategy(this.model);
		
		this.setBackground(Color.DARK_GRAY);
		
		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline"};
		String[] icons = {"/circle1.png", "/rectangle1.png", "/square1.png", "/pencil1.png", "/polyline1.png"};
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2;
		
		ActionListener brushButtonListener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(e.getActionCommand()){
				case "Circle":
					createBrushStrategy(new CircleBrushStrategy(model));
					break;
					
				case "Squiggle":
					createBrushStrategy(new SquiggleBrushStrategy(model));
					break;
					
				case "Rectangle":
					createBrushStrategy(new RectangleBrushStrategy(model));
					break;
					
				case "Polyline":
					createBrushStrategy(new PolylineBrushStrategy(model));
					break;
					
				case "Square":
					createBrushStrategy(new SquareBrushStrategy(model));
					break;
				}
				JButton button = (JButton)e.getSource();
				toggleButton(selectedBrushButton, button);
				selectedBrushButton = button;
			}	
		};
		
		ActionListener fillButtonListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton jb = (JButton) e.getSource();
				if(jb.getText() == "Solid") 
					isFilled = true;
				else isFilled = false;
				updateBrushStrategy();
				model.notifyBrushChanged();
				toggleButton(selectedFillButton,jb);
				selectedFillButton = jb;
			}	
		};
		
		
		c.gridx = 0;
		c.gridy = 1;
		for(int i = 0; i < icons.length; i++) {
			JButton button = new JButton(new ImageIcon("images\\" + icons[i]));
			button.setActionCommand(buttonLabels[i]);
			styleButton(button);
			if(i==4)
				c.insets = new Insets(0,0,0,20);
			this.add(button,c);
			button.addActionListener(brushButtonListener);
			if(i == 0){
				this.toggleButton(this.selectedBrushButton,button);
				this.selectedBrushButton = button;
			}

			c.gridx++;
		}
		c.insets = new Insets(0,0,0,0);

		thicknessSlider = new JSlider(1, 10, 1);
		thicknessLabel = new JLabel("Brush Size 1");

		// Labels
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 8;
		JLabel brushTypeLabel = new JLabel("Brush Type: ");
		brushTypeLabel.setFont(this.font);
		brushTypeLabel.setForeground(this.textColor);
		this.add(brushTypeLabel);
		
		c.gridx=5;
		this.thicknessLabel = new JLabel("Brush Size: 1 ");
		thicknessLabel.setFont(this.font);
		thicknessLabel.setForeground(this.textColor);
		this.add(thicknessLabel, c);

		c.gridx = 6;
		JLabel fillLabel = new JLabel("Fill Type: ");
		fillLabel.setFont(this.font);
		fillLabel.setForeground(this.textColor);
		this.add(fillLabel, c);

		c.gridx = 9;
		JLabel colorLabel = new JLabel("Color : ");
		colorLabel.setFont(this.font);
		colorLabel.setForeground(this.textColor);
		this.add(colorLabel, c);
		
		c.ipady = 0;
		c.gridx = 6;
		c.gridy = 1;
		JButton solidButton = new JButton("Solid");
		styleButton(solidButton);
		solidButton.addActionListener(fillButtonListener);
		this.add(solidButton,c);
		
		c.gridx =  7;
		JButton outLineButton = new JButton("Outline");
		styleButton(outLineButton);
		toggleButton(this.selectedFillButton,outLineButton);
		this.selectedFillButton = outLineButton;
		outLineButton.addActionListener(fillButtonListener);
		c.insets = new Insets(0,0,0,20);
		this.add(outLineButton,c);
		c.insets = new Insets(0,0,0,0);
		
		
		c.gridx = 9;
		c.ipady = 24;
		primaryColorButton = new JButton();
		secondaryColorButton = new JButton();
		primaryColorButton.setActionCommand("1");
		secondaryColorButton.setActionCommand("2");
		styleButton(primaryColorButton);
		styleButton(secondaryColorButton);
		primaryColorButton.setBackground(Color.WHITE);
		secondaryColorButton.setBackground(Color.WHITE);
		c.insets = new Insets(0,0,0,20);
		this.add(primaryColorButton,c);
		c.gridx = 10;
		c.ipadx = 19;
		this.add(secondaryColorButton,c);
		c.insets = new Insets(0,0,0,0);
		c.ipady = 0;
		c.ipadx = 0;
		primaryColorButton.addActionListener(this);
		secondaryColorButton.addActionListener(this);
		
		// Position and Configure Slider
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 1;
		thicknessSlider.setMinorTickSpacing(1);
		thicknessSlider.setMajorTickSpacing(10);
		thicknessSlider.setBackground(Color.DARK_GRAY);
		thicknessSlider.setPaintTicks(true);
		thicknessSlider.addChangeListener(this);
		c.insets = new Insets(0,0,0,20);
		this.add(thicknessSlider, c);

	}
	
	public void updateBrushStrategy(){
		this.brushStrategy.setPrimaryColor(this.primaryColor);
		this.brushStrategy.setSecondaryColor(this.secondaryColor);
		this.brushStrategy.setBrushSize(this.brushSize);
		this.brushStrategy.setFilled(this.isFilled);
		this.isChanged = true;
		this.model.notifyBrushChanged();
	}
	
	public void createBrushStrategy(ConcreteBrushStrategy brushStrategy){
		this.brushStrategy = brushStrategy;
		this.isChanged = true;
		this.updateBrushStrategy();
	}
	
	public boolean toggleChanged(){
		if(this.isChanged){
			this.isChanged = false;
			return true;
		}
		return false;
	}
	
	public void toggleButton(JButton selectedButton, JButton clickedButton){
		Border line = new LineBorder(Color.GREEN);
		Border margin = new EmptyBorder(3, 7, 3, 7);
		Border compound = new CompoundBorder(line, margin);
		clickedButton.setBorder(compound);
		if(selectedButton != null) {
			line = new LineBorder(Color.BLACK);
			compound = new CompoundBorder(line, margin);
			selectedButton.setBorder(compound);
			selectedButton.setEnabled(true);
		}
		clickedButton.setEnabled(false);
	}
	
	public ConcreteBrushStrategy getBrushStrategy(){
		return this.brushStrategy;
	}
	
	private static void styleButton(JButton button) {
		button.setFocusPainted(false);
		button.setFont(new Font("Arial", Font.BOLD, 12));
		button.setVerticalTextPosition(3);
		button.setHorizontalTextPosition(0);
		button.setBackground(new Color(60, 60, 60));
		button.setForeground(new Color(255, 3, 118));
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(3, 7, 3, 7);
		Border compound = new CompoundBorder(line, margin);
		button.setBorder(compound);
	}

	public int getBrushSize() {
		return this.brushSize;
	}

	public void setColor(Color c) {
		this.primaryColor = c;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		this.brushSize = ((JSlider) e.getSource()).getValue();
		this.model.notifyBrushChanged();
		this.updateBrushStrategy();
		thicknessLabel.setText("Brush Size: " + this.brushSize);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jb = (JButton) e.getSource();
			this.colorChooser = new JColorChooser();

			ActionListener okActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					if(e.getActionCommand() == "1"){
						primaryColor = colorChooser.getColor();	
						primaryColorButton.setBackground(primaryColor);
					}
					else{
						secondaryColor = colorChooser.getColor();
						secondaryColorButton.setBackground(secondaryColor);
					}
					model.notifyBrushChanged();
				}
			};

			ActionListener cancelActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {

				}
			};

			final JDialog dialog = JColorChooser.createDialog(null, "Choose a color", true, colorChooser,
					okActionListener, cancelActionListener);

			dialog.setVisible(true);

	
		this.primaryColorButton.setBackground(this.primaryColor);
		this.updateBrushStrategy();
	}
}
