package Code;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Code.PlayersTour.SquareClickHandler;

public class PlayersTour extends JFrame {
	private Tour knightsTour; // đối tượng đi tuần.
	private int kichThuocBanCo; // kích thước của bàn cờ.
	private GUISquare guiSquare[][]; // mảng của JPanels đại diện cho ô.
	private boolean tourDangChay = false; // true nếu tour đang chạy, không thì fale
	private boolean tourDaHoanThanh = false; // true nếu tour đã hoàn thành, không thì fale.
	private boolean suDungThongSoTourMoi = true; // true nếu người dùng muốn tham số tour mới,
												// false nếu người dùng muốn giống như tour trước đó
	private Timer timer; // số lần di chuyển của knight.
	private Image imgx;

	// cài đặt hoạt động đóng cửa mặc định cho cửa sổ và khởi chạy một tour mới.
	public PlayersTour() {
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		gameMoi();
		createTimer();
	}

	// Thiết lập các thông số cho tour mới của knight.
	private void gameMoi() {
		// Nếu kích thước của bàn cờ >0, thì bàn cờ phải được xóa trước khi bắt đầu lại.
		if (kichThuocBanCo > 0) {
			// Loại bỏ các ô hiện tại khỏi bàn cờ.
			xoaOCo();

			// khởi động lại cửa sổ.
			repaint();
		}

		// Nếu người dùng lựa chọn để nhập các thông số trò chơi mới
		// nhắc người sử dụng cho các kích thước bàn cờ mới
		if (suDungThongSoTourMoi == true) {
			// Nhắc nhở người sử dụng kích thước bàn cờ.
			kichThuocBanCo = 9;
		}

		// Nếu người dùng muốn tham số trò chơi mới,
		// hãy thay đổi kích thước cửa sổ
		// nếu không để cửa sổ có cùng kích thước
		if (suDungThongSoTourMoi == true) {
			// Set size of window
			setSize(kichThuocBanCo * 75, kichThuocBanCo * 75);
		}

		// Đặt bố trí lưới, các hàng và cột bằng kích thước của bảng,
		setLayout(new GridLayout(kichThuocBanCo, kichThuocBanCo, kichThuocBanCo / kichThuocBanCo, kichThuocBanCo / kichThuocBanCo));

		// tạo đối tượng mới.
		knightsTour = new Tour(kichThuocBanCo);
		//// tạo đối tượng người chơi mới
		// tạo các ô
		drawSquares();
		guiSquare[8][0].setText("A");
		guiSquare[8][1].setText("B");
		guiSquare[8][2].setText("C");
		guiSquare[8][3].setText("D");
		guiSquare[8][4].setText("E");
		guiSquare[8][5].setText("F");
		guiSquare[8][6].setText("G");
		guiSquare[8][7].setText("H");
		guiSquare[0][8].setText("1");
		guiSquare[1][8].setText("2");
		guiSquare[2][8].setText("3");
		guiSquare[3][8].setText("4");
		guiSquare[4][8].setText("5");
		guiSquare[5][8].setText("6");
		guiSquare[6][8].setText("7");
		guiSquare[7][8].setText("8");
		
	}

	// tạo các ô lên màn hình.
	private void drawSquares() {
		// Tạo mảng mới với kích thước của bảng
		guiSquare = new GUISquare[9][9];

		// Lồng nhau lặp đi lặp lại qua tất cả các ô vuông
		for (int hang = 0; hang < guiSquare.length; hang++) {
			for (int cot = 0; cot < guiSquare.length; cot++) {
				// tạo mới đối tượng GUISquare
				guiSquare[hang][cot] = new GUISquare();

				// Thêm sự kiện Mousse vào trong mảng
//				guiSquare[hang][cot].addMouseListener(new SquareClickHandler());
				// màu các ô vuông.
				datMauOCo(hang, cot);

				// thêm ô vuông vào Frame
				add(guiSquare[hang][cot]);
			}
		}
		for (int hang = 0; hang < guiSquare.length - 1; hang++) {
			for (int cot = 0; cot < guiSquare.length - 1; cot++) {
				guiSquare[hang][cot].addMouseListener(new SquareClickHandler());
			}
		}

		// Nên được viện dẫn khi các thành phần con của vùng chứa này được sửa đổi
		// (thêm vào hoặc xoá khỏi vùng chứa, hoặc thông tin về bố cục đã thay đổi)
		// sau khi vùng chứa được hiển thị.
		validate();
	}

	// Xác định màu của ô dựa trên vị trí của nó trên bàn cờ.
	private void datMauOCo(int hang, int cot) {
		if ((hang + cot) % 2 == 0 && hang < 8 && cot < 8) {
			guiSquare[hang][cot].setBackground(Color.gray);
		} else {
			guiSquare[hang][cot].setBackground(Color.white);
		}
		if (hang == 9 || cot == 9) {
			guiSquare[hang][cot].setBackground(Color.PINK);
			
		}
	}

	// Loại bỏ các ô vuông khỏi container.
	private void xoaOCo() {
		for (int hang = 0; hang < guiSquare.length; hang++) {
			for (int cot = 0; cot < guiSquare.length; cot++) {
				// gọi hàm xóa các ô khỏi Frame
				remove(guiSquare[hang][cot]);
			}
		}
	}

	// Tạo hành động thực hiện di chuyển của một knight ở một khoảng thời gian
	private void createTimer() {
		int thoigiantre = 50; // thời gian trễ.
		// thiết lập tour đã kết thúc thành false.
		tourDaHoanThanh = false;

		// Tạo biến danh sách tác vụ vô danh và
		// ghi đè lên phương thức actionPerformed để gọi moveKnight
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// đặt màu cho ô.
				datMauOCo(knightsTour.giaTriCuaKnight().giaTriHangHienTai(),
						knightsTour.giaTriCuaKnight().giaTriCuaCotHienTai());

				diChuyenQuanMa();
				flashcolor();
			}
		};
		timer = new Timer(thoigiantre, taskPerformer);
	}

	// Thay đổi màu sắc của ô hiện tại

	// bắt đầu bộ đếm thời gian.
	private void startTimer() {
		timer.start();

	}

	// Ngừng bộ đếm thời gian đang chạy
	private void stopTimer() {
		timer.stop();

	}

	// Di chuyển knight và đặt text trên nhãn của ô hiện tại
	private void diChuyenQuanMa() {
		GUISquare ocohientai; // lưu vị trí hiện tại của knight
		int hanghientai; // Vị trí hàng hiện tại của knight
		int cothientai; // Vị trí cột hiện tại của knight

		// nhận giá trị hàng và cột hiện tại của knight.
		hanghientai = knightsTour.giaTriCuaKnight().giaTriHangHienTai();
		cothientai = knightsTour.giaTriCuaKnight().giaTriCuaCotHienTai();

		// đặt ô hiện tại.
		ocohientai = guiSquare[hanghientai][cothientai];

		// đặt nhãn trên ô.
		ocohientai.setText(Integer.toString(knightsTour.giaTriCuaKnight().getMoveCounter()));
		
		// di chuyển knights
		knightsTour.moveP();

		if (knightsTour.hasMove() == false) {
			timer.stop();
			tourDangChay = false;
			tourDaHoanThanh = true;

		}
	}
	

	private void flashcolor() {
		int hanghientai = knightsTour.giaTriCuaKnight().giaTriHangHienTai();
		int cothientai = knightsTour.giaTriCuaKnight().giaTriCuaCotHienTai();
		GUISquare currentSquare = guiSquare[hanghientai][cothientai];
		Color flashColor = Color.CYAN;

		try {
			imgx = ImageIO.read(getClass().getResource("/Ficture/horse1.png")).getScaledInstance(70, 70,
					Image.SCALE_SMOOTH);
			;
			currentSquare.add(new JLabel(new ImageIcon(imgx)));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		currentSquare.setBackground(flashColor);
	}

	// Yêu cầu người dùng nếu họ muốn có các thông số mới cho tour tiếp theo.

	// Yêu cầu người dùng nếu họ muốn có một tour mới
	// nếu người dùng nhấp vào một hình vuông trong khi tour đang chạy.
	public void tamDungTour() {
		int newGameAnswer;

		// Ngừng thời gian, tạm dừng tour
		stopTimer();

		// thiết lập TourRunning là fale.
		tourDangChay = false;

		// phản hồi từ hộp thoại
		int tongSoQuanCo;

		tongSoQuanCo = JOptionPane.showConfirmDialog(this,
				"Hành trình kết thúc số quân cờ đã được đặt vào bàn cờ là " + knightsTour.giaTriCuaKnight().getMoveCounter(),
				"Tạm dừng", JOptionPane.CLOSED_OPTION);
		newGameAnswer = JOptionPane.showConfirmDialog(this, "Bắt đầu một tour mới?", "Tạm dừng",
				JOptionPane.YES_NO_CANCEL_OPTION);

		// nếu có.
		if (newGameAnswer == 0) {
			// đặt lại knightstour, xóa trạng thái hiện tại.
			knightsTour.resetTour();

			// tạo tour mới.
			gameMoi();
		}

		else if (newGameAnswer == 1) {
			// Nếu người dùng không muốn một trò chơi mới đặt tour lại
			// và khởi độnsg lại bộ đếm thời gian.
			tourDangChay = true;
			startTimer();
		}
		// nếu hủy, thoát chương trình
		else {
//			System.exit(0);
			setDefaultCloseOperation(HIDE_ON_CLOSE);
		}
	}

	// sự kiện kích chuột.
	class SquareClickHandler extends MouseAdapter {

		// xử lý sự kiện kích chuột.
		@Override
		public void mouseClicked(MouseEvent meEvent) {

			// nếu TourRunning = false, chạy khi chuột được kích.
			if (tourDangChay == false) {
				// Nếu tour đã kết thúc,
				// nghĩa là người dùng đã không làm gián đoạn tour
				// với các nhấp chuột tiếp theo và
				// chuyến tham quan được phép hoàn tất
				if (tourDaHoanThanh == true) {
					// đặt tourfinished trở lại fale
					tourDaHoanThanh = false;

				}

				for (int rowNumber = 0; rowNumber < kichThuocBanCo; rowNumber++) {
					for (int columnNumber = 0; columnNumber < kichThuocBanCo; columnNumber++) {
						// So sánh ô với nguồn sự kiện, để tìm ra ô nào đã được nhấp vào
						if (meEvent.getSource() == guiSquare[rowNumber][columnNumber]) {

							// đặt vị trí bắt đầu
							knightsTour.datVitriBatDau(rowNumber, columnNumber);
							tourDangChay = true;
							tourDaHoanThanh = false;
//							startTimer();
//							diChuyenQuanMa();
//							break;
						}
						for ( rowNumber = 0; rowNumber < kichThuocBanCo; rowNumber++) {
							for ( columnNumber = 0; columnNumber < kichThuocBanCo; columnNumber++) {
								if (meEvent.getSource() == guiSquare[rowNumber][columnNumber]) {
									diChuyenQuanMa();
									flashcolor();
								}
							}
								
						}
					}
						

				}

				
		} else {
				tamDungTour();
		}
}}}
