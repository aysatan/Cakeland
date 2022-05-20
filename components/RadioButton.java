package components;

import javax.swing.JRadioButton;

import assets.ColorPalette;
import assets.FontFamily;

public final class RadioButton {
	private JRadioButton radioButton;
	
	public RadioButton(String text) {
		radioButton = new JRadioButton(text);
		setDefault();
	}
	
	public JRadioButton getRadioButton() {
		return radioButton;
	}

	private void setDefault() {
		radioButton.setOpaque(false);
		radioButton.setForeground(ColorPalette.navy);
		radioButton.setFont(FontFamily.lblFont1);
	}

}
