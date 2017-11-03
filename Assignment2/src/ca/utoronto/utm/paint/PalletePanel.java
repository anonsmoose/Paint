package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;

public class PalletePanel extends JPanel implements ChangeListener, ActionListener {
	private JSlider thicknessSlider;
	private JLabel thicknessLabel;
	private GridBagConstraints c;
	private int brushSize;
	private Color brushColor;
	private PaintModel model;
	private JColorChooser colorChooser;

	public PalletePanel(PaintModel model) {
		this.setLayout(new GridBagLayout());
		this.c = new GridBagConstraints();
		this.brushSize = 1;
		this.model = model;

		thicknessSlider = new JSlider(1, 10, 1);
		thicknessLabel = new JLabel("Brush Size 1");

		// Labels
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.weightx = 2;
		c.gridy = 0;
		this.add(thicknessLabel, c);

		c.gridx = 1;
		this.add(new JLabel("Fill Type: "), c);

		c.gridx = 2;
		this.add(new JLabel("Color: "), c);
		JButton solidButton = new JButton("Solid");
		JButton outLineButton = new JButton("Outline");
		JButton colorButton = new JButton("Color Picker");
		this.add(colorButton);
		this.add(solidButton);
		this.add(outLineButton);
		colorButton.addActionListener(this);
		solidButton.addActionListener(this);
		outLineButton.addActionListener(this);
		// Position and Configure Slider
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		thicknessSlider.setMinorTickSpacing(1);
		thicknessSlider.setMajorTickSpacing(10);
		thicknessSlider.setPaintTicks(true);
		thicknessSlider.addChangeListener(this);
		this.add(thicknessSlider, c);

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		this.brushSize = ((JSlider) e.getSource()).getValue();
		this.model.setBrushSize(this.brushSize);
		thicknessLabel.setText("Brush Size: " + this.brushSize);
	}

	public int getBrushSize() {
		return this.brushSize;
	}

	public void setColor(Color c) {
		this.brushColor = c;
	}

	public Color getColor() {
		return this.brushColor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jb = (JButton) e.getSource();

		if (jb.getText() == "Solid") {
			model.setSolid(true);
		} else if (jb.getText() == "Outline") {
			model.setSolid(false);
		}
		System.out.println(jb.getText());
		if (jb.getText() == "Color Picker") {
			this.colorChooser = new JColorChooser();
			final JLabel preview = new JLabel(
					"" + "The proof is trivial and has been left as an " + "exercise for the reader.", JLabel.CENTER);
			preview.setFont(new Font("Arial", Font.BOLD, 40));
			preview.setSize(preview.getPreferredSize());
			preview.setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));
			colorChooser.setPreviewPanel(preview);
			ActionListener okActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					System.out.println(colorChooser.getColor());
					setColor(colorChooser.getColor());
					model.setColor(getColor());
				}
			};

			ActionListener cancelActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {

				}
			};

			final JDialog dialog = JColorChooser.createDialog(null, "Choose a color", true, colorChooser,
					okActionListener, cancelActionListener);

			dialog.setVisible(true);

		}
	}
}
