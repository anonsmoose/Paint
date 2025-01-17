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
import ca.utoronto.utm.paint.BrushStrategies.PolylineBrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.RectangleBrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.SquareBrushStrategy;
import ca.utoronto.utm.paint.BrushStrategies.SquiggleBrushStrategy;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
/**
 * 
 * This class is a panel for use in a paint program, it contains all controls for selecting
 * and modifying the brush used in the program
 * 
 *
 */
public class PalletePanel extends JPanel implements ChangeListener, ActionListener {
	private BrushStrategy brushStrategy;
	private PaintModel model;
	private View view;
	
	private JSlider thicknessSlider;
	private JLabel thicknessLabel;
	private JColorChooser colorChooser;
	private JButton primaryColorButton;
	private JButton secondaryColorButton;
	
	private int brushSize = 1;
	private Color primaryColor = Color.white;
	private Color secondaryColor = Color.white;
	private String fillStyle = "Outline";

	private boolean isChanged; // For paintpanel to check if it needs to get an updated version of the brush
	private final Font font = new Font("Arial", Font.BOLD, 16);
	private final Color textColor = new Color(255, 3, 118);
	private JButton selectedBrushButton;
	private JButton selectedFillButton;
	
	public PalletePanel(PaintModel model, View view) {
		this.setLayout(new GridBagLayout());
		this.model = model;
		this.view = view;
		this.brushStrategy = new CircleBrushStrategy(this.model);
		this.setBackground(Color.DARK_GRAY);
		
		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline", "Eraser", "Text Tool"};
		String[] icons = {"/circle1.png", "/rectangle1.png", "/square1.png", "/pencil1.png", "/polyline1.png", "/eraser1.png", "/texttool1.png"};

		
		//Action listener for choosing which fill type to update to on the current brush
		ActionListener fillButtonListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton jb = (JButton) e.getSource();
				
				if(jb.getActionCommand() == "Solid") 
					fillStyle = "Solid";
				else if(jb.getActionCommand() == "Secondary Filled")
					fillStyle = "Secondary Filled";
				else fillStyle = "Outline";
	
				updateBrushStrategy();
				toggleButton(selectedFillButton,jb);
				selectedFillButton = jb;
			}	
		};
		
		// GUI CONFIGURATION BELOW
		
		//Brush buttons
		BrushStrategyFactory brushFactory = new BrushStrategyFactory(this,this.model);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2;
		c.gridx = 0;
		c.gridy = 1;
		for(int i = 0; i < icons.length ; i++) {
			JButton button = new JButton(new ImageIcon("images//" + icons[i]));
			button.setActionCommand(buttonLabels[i]);
			styleButton(button);
			if(i==6)
				c.insets = new Insets(0,0,0,20);
			this.add(button,c);
			button.addActionListener(brushFactory);
			if(i == 0){
				this.toggleButton(this.selectedBrushButton,button);
				this.selectedBrushButton = button;
			}
			c.gridx++;
		}
		
		// Brush type Label
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2;
		c.gridx = 2;
		c.gridy = 0;
		c.ipady = 8;
		c.gridwidth = 5;
		JLabel brushTypeLabel = new JLabel("Brush Type: ");
		brushTypeLabel.setFont(this.font);
		brushTypeLabel.setForeground(this.textColor);
		this.add(brushTypeLabel,c);

		//Brush size Label
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2;
		c.gridx = 7;
		c.gridy = 0;
		thicknessLabel = new JLabel("Brush Size 1");
		this.thicknessLabel = new JLabel("Brush Size: 1 ");
		thicknessLabel.setFont(this.font);
		thicknessLabel.setForeground(this.textColor);
		this.add(thicknessLabel, c);
		
		//Fill Type Label
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2;
		c.gridx = 8;
		c.gridy = 0;
		JLabel fillLabel = new JLabel("Fill Type: ");
		fillLabel.setFont(this.font);
		fillLabel.setForeground(this.textColor);
		this.add(fillLabel, c);

		//Color Label
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2;
		c.gridx = 12;
		c.gridwidth = 2;
		c.insets = new Insets(0,0,0,20);
		JLabel colorLabel = new JLabel("Click to pick a Color");
		colorLabel.setFont(this.font);
		colorLabel.setForeground(this.textColor);
		this.add(colorLabel, c);
		
		//Solid Fill Button
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2;
		c.gridx = 8;
		c.gridy = 1;
		JButton solidButton = new JButton(new ImageIcon("images///square1BlueFill.png"));
		solidButton.setActionCommand("Solid");
		styleButton(solidButton);
		solidButton.addActionListener(fillButtonListener);
		this.add(solidButton,c);
		
		//Outline Fill Button
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2;
		c.gridx =  9;
		c.gridy = 1;
		JButton outLineButton = new JButton(new ImageIcon("images///square1BlueOutline5.png"));
		outLineButton.setActionCommand("Outline");
		styleButton(outLineButton);
		toggleButton(this.selectedFillButton,outLineButton);
		this.selectedFillButton = outLineButton;
		outLineButton.addActionListener(fillButtonListener);
		this.add(outLineButton,c);
		
		//Secondary Fill Button
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2;
		c.gridx =  10;
		c.gridy = 1;
		c.insets = new Insets(0,0,0,20);
		JButton secondaryFillButton = new JButton(new ImageIcon("images///square1BlueOutline3.png"));
		secondaryFillButton.setActionCommand("Secondary Filled");
		styleButton(secondaryFillButton);
		secondaryFillButton.addActionListener(fillButtonListener);
		this.add(secondaryFillButton,c);
		
		//Primary Color Button
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2;
		c.gridx = 12;
		c.gridy = 1;
		c.ipady = 32;
		c.ipadx = 32;
		c.insets = new Insets(0,0,0,20);
		primaryColorButton = new JButton();
		primaryColorButton.setActionCommand("1");
		styleButton(primaryColorButton);
		primaryColorButton.setBackground(Color.WHITE);
		primaryColorButton.addActionListener(this);
		this.add(primaryColorButton,c);
		
		//Secondary Color Button
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2;
		c.gridx = 13;
		c.gridy = 1;
		c.ipady = 32;
		c.ipadx = 32;
		c.insets = new Insets(0,0,0,20);
		secondaryColorButton = new JButton();
		secondaryColorButton.setActionCommand("2");
		styleButton(secondaryColorButton);
		secondaryColorButton.setBackground(Color.WHITE);
		secondaryColorButton.addActionListener(this);
		this.add(secondaryColorButton,c);
		
		// Position and Configure Slider
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 7;
		c.gridy = 1;
		c.insets = new Insets(0,0,0,20);
		thicknessSlider = new JSlider(1, 10, 1);
		thicknessSlider.setMinorTickSpacing(1);
		thicknessSlider.setMajorTickSpacing(10);
		thicknessSlider.setBackground(Color.DARK_GRAY);
		thicknessSlider.setPaintTicks(true);
		thicknessSlider.addChangeListener(this);
		this.add(thicknessSlider, c);

	}
	/**
	 * Updates the current brush with the values currently selected on
	 * the GUI controls.
	 */
	public void updateBrushStrategy(){
		this.brushStrategy.setPrimaryColor(this.primaryColor);
		this.brushStrategy.setSecondaryColor(this.secondaryColor);
		this.brushStrategy.setBrushSize(this.brushSize);
		this.brushStrategy.setFillStyle(this.fillStyle);
		this.view.getPaintPanel().updateBrushStrategy(this.brushStrategy);
	}
	
	/**
	 * Creates and updates the properties of a newly selected brush strategy.
	 * @param brushStrategy The brushStrategy whose properties are being changed.
	 */
	public void setBrushStrategy(BrushStrategy brushStrategy){
		this.brushStrategy = brushStrategy;
		this.updateBrushStrategy();
		// set brush strat of paint panel;
	}
	
	/**
	 * For use of the paintpanel to check if it needs to get a new brush strategy, resets the isChanged flag
	 * if it does.
	 * @return isChanged Whether or not the pallete panel has changed the brushStrategy it has created.
	 */
	public boolean toggleChanged(){
		if(this.isChanged){
			this.isChanged = false;
			return true;
		}
		return false;
	}
	
	/**
	 * Toggles a button off and resets the currently selected button
	 * @param selectedButton the selected button
	 * @param clickedButton the button being toggle
	 */
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
	
	public BrushStrategy getBrushStrategy(){
		return this.brushStrategy;
	}
	/**
	 * Makes cosmetic changes to a JButton. 
	 * @param button The button being stylized.
	 */
	static void styleButton(JButton button) {
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
	
	public JButton getSelectedBrushButton(){
		return this.selectedBrushButton;
	}
	
	public void setSelectedBrushButton(JButton button){
		this.selectedBrushButton = button;
	}

	// Thickness slider listener
	@Override
	public void stateChanged(ChangeEvent e) {
		this.brushSize = ((JSlider) e.getSource()).getValue();
		this.model.notifyBrushChanged();
		this.updateBrushStrategy();
		thicknessLabel.setText("Brush Size: " + this.brushSize);
	}
	
	// Color picker Listener
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
