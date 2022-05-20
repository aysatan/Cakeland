package components;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class Panel extends JPanel{
	public Panel() {
		setDefault();
	}
	
	public Panel(LayoutManager layout) {
		setLayout(layout);
		setDefault();
	}
	
	private void setDefault() {
		setOpaque(false);
	}
}
