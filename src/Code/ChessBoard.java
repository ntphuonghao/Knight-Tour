package Code;

public class ChessBoard {
	private Square[][] banCo; // Mảng 2 chiều, đại diện cho bàn cờ.
	private Knight quanMaHienTai; // Tham chiếu đến Knight hiện tại trên
									// bàn cờ.
	private final int KICH_THUOC_BAN_CO; // Kích thước của bàn cờ, (2x2, 3x3, ... ).

	// Tạo bàn cờ vua.
	public ChessBoard(Knight quanma) {
		KICH_THUOC_BAN_CO = 8; // bàn cờ 8x8
		banCo = new Square[KICH_THUOC_BAN_CO][KICH_THUOC_BAN_CO];
		quanMaHienTai = quanma;
		taoOco();
		createHeuristics();
	}

	public ChessBoard(Knight quanMa, int dimension) {
		KICH_THUOC_BAN_CO = 8; // bàn cờ kích thước bất kì.
		banCo = new Square[KICH_THUOC_BAN_CO][KICH_THUOC_BAN_CO];
		quanMaHienTai = quanMa;
		taoOco();
		createHeuristics();
	}

	// tạo các ô trong bàn cờ.
	public void taoOco() {
		for (int hang = 0; hang < KICH_THUOC_BAN_CO; hang++) {
			for (int cot = 0; cot < KICH_THUOC_BAN_CO; cot++) {
				banCo[hang][cot] = new Square();
			}
		}
	}

	public void themQuanMa(Knight quanma) {
		quanMaHienTai = quanma;
	}

	public int giaTriCuaKichThuocBanCo() {
		return KICH_THUOC_BAN_CO;
	}

	// Trả về đối tượng hình vuông tại một hàng và cột xác định.
	public Square getSquareAt(int hang, int cot) {
		return banCo[hang][cot];
	}

	// Đặt giá trị 'true' trên một hình vuông để chỉ ra rằng nó đã được
	// truy cập.
	public void datODaTruyCap(int hang, int cot) {
		banCo[hang][cot].datODaTruyCap(true);
	}

	// Trả lại giá trị 'true' trên một hình vuông để chỉ ra rằng nó đã
	// được truy cập.
	public boolean isSquareVisited(int hang, int cot) {
		return banCo[hang][cot].isVisited();
	}

	// Thiết lập mức truy cập của một hình vuông nằm ở các tham số
	// hàng và cột.
	public void setSquareAccessibility(int hang, int cot, int ocothedi) {
		banCo[hang][cot].setAccessibility(ocothedi);
	}

	// Trả lại mức truy cập của một hình vuông nằm ở hàng và cột
	// được xác định bởi các tham số.
	public int getSquareAccessibility(int hang, int cot) {
		return banCo[hang][cot].getAccessibility();
	}

	// Giảm khả năng tiếp cận của hình vuông bằng 1 hàng và cột
	// được xác định bởi các tham số.
	public void decrSquareAccessibility(int hang, int cot) {
		banCo[hang][cot].decrAccessibility();
	}

	// Thiết lập một hình vuông cho giá trị truy cập di chuyển hiện tại
	// của Knight tại vị trí được chỉ định bởi các tham số.
	public void setSquareMoveNumber(int hang, int cot, int moveCounter) {
		banCo[hang][cot].setMoveNumber(moveCounter);
	}

	// Trả lại giá trị bộ đếm truy cập tại vị trí
	// được chỉ định bởi các tham số.
	public int getSquareMoveNumber(int hang, int cot) {
		return banCo[hang][cot].getMoveNumber();
	}

	// Đánh dấu hình vuông của bảng ở hàng và cột được cung cấp
	// bởi các tham số với số biến số truy cập của Knight.
	public void markBoardSquare(int hanghientai, int cothientai, int moveCounter) {
		datODaTruyCap(hanghientai, cothientai);
		setSquareMoveNumber(hanghientai, cothientai, moveCounter);
	}

	// Sử dụng một đối tượng knight dummy để chơi qua tất cả các di chuyển
	// có thể cho mỗi hình vuông trên bàn cờ.
	// Số lượng di chuyển có thể từ mỗi hình vuông được ghi lại trong mảng.
	private void createHeuristics() {
		int linkCount = 0, // Lưu trữ số ô liên kết cho một hình vuông cụ thể
				kiemtrahang, // Hàng hiện tại của Knight.
				kiemtracot; // Cột hiện tại của Knight.

		// Thêm giá trị số di chuyển vào hàng hiện tại của Knight
		// và kiểm tra xem đó có phải là ứng cử viên di chuyển hay không.
		// Nếu vị trí nằm trên bàn cờ, nó sẽ tăng số liên kết.
		for (int hang = 0; hang < KICH_THUOC_BAN_CO; hang++) {
			for (int cot = 0; cot < KICH_THUOC_BAN_CO; cot++) {
				for (int moveNum = 0; moveNum < Knight.SO_DI_CHUYEN_HOP_LE; moveNum++) {
					kiemtrahang = hang + quanMaHienTai.giaTriCuaCotDangDiChuyen(moveNum);
					kiemtracot = cot + quanMaHienTai.giaTriCuaHangDangDiChuyen(moveNum);

					if (checkSquareExistsAndIsUnVisted(kiemtrahang, kiemtracot) == true) {
						linkCount++;
					}
				}

				// Đặt tính truy cập và đặt lại số liên kết cho cột tiếp theo.
				setSquareAccessibility(hang, cot, linkCount);
				linkCount = 0;
			}
		}
	}

	// Sử dụng một hàng và cột hiện tại của bàn cờ để xác định xem hàng
	// và cột nằm trong giới hạn của bàn cờ và chưa được truy cập.
	// Trả về true, nếu không gian là hợp lệ, nếu không thì trả về false.
	public boolean checkSquareExistsAndIsUnVisted(int kiemtrahang, int kiemtracot) {
		if ((kiemtrahang >= 0 && kiemtrahang < KICH_THUOC_BAN_CO) && (kiemtracot >= 0 && kiemtracot < KICH_THUOC_BAN_CO)) {
			if (isSquareVisited(kiemtrahang, kiemtracot) == false) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	// Kiểm tra tất cả các ô vuông có thể truy cập từ ô vuông hiện tại
	// và giảm khả năng tiếp cận của chúng xuống 1.
	public void lowerAccessibility() {
		int[] possibleMoves;
		int numOfPossibleMoves, hanghientai = quanMaHienTai.giaTriHangHienTai(), cothientai = quanMaHienTai.giaTriCuaCotHienTai(),
				thaydoihang, thaydoicot;

		// số trạng thái di chuyển có thể.
		numOfPossibleMoves = quanMaHienTai.findNumOfPossibleMoves(this);

		// Tạo một mảng với kích thước của số di chuyển có thể
		possibleMoves = new int[numOfPossibleMoves];

		// số di chuyển của các trạng thái có thể.
		possibleMoves = quanMaHienTai.findPossibleMoves(this, numOfPossibleMoves);

		for (int moveNum = 0; moveNum < possibleMoves.length; moveNum++) {
			thaydoihang = hanghientai + quanMaHienTai.giaTriCuaCotDangDiChuyen(possibleMoves[moveNum]);
			thaydoicot = cothientai + quanMaHienTai.giaTriCuaHangDangDiChuyen(possibleMoves[moveNum]);

			if (isSquareVisited(thaydoihang, thaydoicot) == false) {
				decrSquareAccessibility(thaydoihang, thaydoicot);
			}
		}
	}
}
