package components;

import java.util.Vector;

import javax.swing.JComboBox;

import assets.ColorPalette;

public final class ComboBox {
	private JComboBox comboBox;
	public ComboBox(Vector data) {
		comboBox = new JComboBox(data);
		setDefault();
	}
	
	public JComboBox getComboBox() {
		return comboBox;
	}

	private void setDefault() {
		comboBox.setBackground(ColorPalette.lightPink);
		comboBox.setForeground(ColorPalette.navy);
	}
}
