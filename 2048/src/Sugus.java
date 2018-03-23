
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sugus {

	private JLabel label;
	private boolean status;
	private JPanel panel;
	private ImageSu image;
	private int num;

	public Sugus() {
		initComponents();
	}

	private void initComponents() {
		panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(100, 100);
		label = new JLabel();
		label.setSize(100, 100);
		panel.add(label);
		image = new ImageSu();
	}

	public void numberRandoms() {
		Random rd = new Random();
		int no;
		do {
			no = rd.nextInt(3);
		} while (no == 0);
		label.setIcon(image.getIa()[no]);
		status = true;
		num = no;
	}

	public void setImage(int i) {
		label.setIcon(image.getIa()[i]);
		status = i != 0;
		num = i;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	};

}
