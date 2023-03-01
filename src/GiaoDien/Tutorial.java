package GiaoDien;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.DropMode;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class Tutorial extends JFrame {

	public Tutorial() {
		setTitle("Trò chơi Mã đi tuần");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 657);
		getContentPane().setLayout(null);

		JTextArea Tutorial_Text = new JTextArea();
		Tutorial_Text.setBounds(35, 292, 533, 158);
		Tutorial_Text.setForeground(new Color(0, 0, 0));
		Tutorial_Text.setBackground(new Color(255, 204, 102));
		Tutorial_Text.setFont(new Font("Windsor", Font.BOLD, 17));
		Tutorial_Text.setLineWrap(true);
		Tutorial_Text.setText(
				"Mã đi tuần hay hành trình của quân mã là bài toán về  việc di chuyển một quân mã trên bàn cờ vua (8 x 8)"
						+ ".    Quân mã được đặt ở một ô trên một bàn cờ trống, nó   phải di chuyển theo quy tắc của cờ vua để đi"
						+ " qua mỗi ô trên bàn cờ đúng một lần.");
		getContentPane().add(Tutorial_Text);

		JLabel BackgroundImage = new JLabel();
		BackgroundImage.setBackground(SystemColor.desktop);
		BackgroundImage.setBounds(0, -49, 603, 620);
		BackgroundImage.setFont(new Font("Tahoma", Font.BOLD, 11));
		BackgroundImage.setToolTipText("");
		BackgroundImage.setIcon(new ImageIcon(Tutorial.class.getResource("/Ficture/knight.png")));
		getContentPane().add(BackgroundImage);
	}
}
