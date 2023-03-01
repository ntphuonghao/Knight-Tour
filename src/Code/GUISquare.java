package Code;

import java.awt.*;
import javax.swing.*;

//JPanel đại diện cho một hình vuông trên một bảng cờ vua. 
//Panel chứa một nhãn có văn bản có thể được thay đổi theo chương trình.

public class GUISquare extends JPanel {
	private JLabel nhan; // JLabel trong Panel.
	private JLabel nhan1;
	private JTextField textField;
	// khởi tạo hình vuông.

	public GUISquare() {
		// tạo đối tượng JLabels
		nhan = new JLabel();
		// đặt màu nhãn trong ô.
		nhan.setForeground(Color.BLUE);
		this.setLayout(new GridBagLayout());
		// Add nhan vào Panel.
		add(nhan);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}

	// đặt nhãn ô bằng tham số chuỗi.
	public void setText(String text) {
		nhan.setText(text);
		nhan.setFont(new Font("Time New Roman", Font.BOLD, 25));
	}

	public void setText1(String text) {
		nhan1.setText(text);
		nhan1.setBackground(Color.RED);
		nhan1.setFont(new Font("Time New Roman", Font.BOLD, 50));
	}
	
	public void A() {
		textField.setText("A");
		textField.setBackground(Color.RED);
		textField.setFont(new Font("Time New Roman", Font.BOLD, 50));
	}

	// Đặt nhãn của ô không hợp lệ, nhãn này sẽ xóa.
	public void datLaiText() {
		nhan.setText(null);
	}

}
