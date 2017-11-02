package ca.utoronto.utm.paint;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PalletePanel extends JPanel implements ChangeListener, ActionListener{
	private JSlider thicknessSlider;
	private JLabel thicknessLabel;
	private GridBagConstraints c;
	private int brushSize;
	private PaintModel model;
	
	public PalletePanel(PaintModel model){
		this.setLayout(new GridBagLayout());
		this.c = new GridBagConstraints();
		this.brushSize = 1;
		this.model = model;
		
		thicknessSlider = new JSlider(1,10,1);
		thicknessLabel = new JLabel("Brush Size 1");
		
		//Labels
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.weightx = 2;
		c.gridy = 0;
		this.add(thicknessLabel,c);
		
		c.gridx = 1;
		this.add(new JLabel("Fill Type: "),c);
		
		c.gridx = 2;
		this.add(new JLabel("Color: "),c);
		JButton solidButton = new JButton("Solid");
		JButton outLineButton = new JButton("Outline");
		this.add(solidButton);
		this.add(outLineButton);
		solidButton.addActionListener(this);
		outLineButton.addActionListener(this);
		//Position and Configure Slider
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		thicknessSlider.setMinorTickSpacing(1);
		thicknessSlider.setMajorTickSpacing(10);
		thicknessSlider.setPaintTicks(true);
		thicknessSlider.addChangeListener(this);
		this.add(thicknessSlider,c);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		this.brushSize = ((JSlider)e.getSource()).getValue();
		this.model.setBrushSize(this.brushSize);
		thicknessLabel.setText("Brush Size: " + this.brushSize);
	}
	
	public int getBrushSize(){
		return this.brushSize;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jb = (JButton) e.getSource();
		if(jb.getText() == "Solid")
		{
			model.setSolid(true);
		}
		else if(jb.getText() == "Outline")
		{
			model.setSolid(false);
		}
		System.out.println(jb.getText());
	}
}
