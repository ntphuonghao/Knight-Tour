package Code;

import javax.swing.JFrame;
import javax.swing.JPanel;
import Code.PlayersTour;

public class test extends JFrame {
	
	public void GUI() {
		JFrame jFrame = new JFrame();
		JPanel jPanel = new JPanel();
		jFrame.add(jPanel);
		
		jFrame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		PlayersTour p = new PlayersTour();
		JFrame jFrame = new JFrame();
		JPanel jPanel = new JPanel();
		jFrame.add(p);

		
		jFrame.setSize(700, 700);
		jFrame.setVisible(true);

	}

}
