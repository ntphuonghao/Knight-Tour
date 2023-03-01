package Code;

public class Knight {
	private int[] giaTriHangNgang; // Giá trị di chuyển ngang có sẵn cho knight.
	private int[] giaTriHangDoc; // Giá trị di chuyển dọc có sẵn cho knight.
	private int hangHienTai; // Vị trí hàng hiện tại của knight.
	private int cotHienTai; // Vị trí cột hiện tại củaknight
	private int hangTruocKhiDiChuyen; // Hàng của knight trước khi di chuyển.
	private int cotTruocKhiDiChuyen; // cột của knight trước khi di chuyển.
	private int soLanDiChuyen; // Theo dõi số lần di chuyển của hiệp sỹ (1-64)
	public static final int SO_DI_CHUYEN_HOP_LE = 8; // Số di chuyển mà hiệp sĩ được phép thực hiện

	public Knight() {
		giaTriHangNgang = new int[SO_DI_CHUYEN_HOP_LE];
		giaTriHangDoc = new int[SO_DI_CHUYEN_HOP_LE];
		soLanDiChuyen = 1;
		datHangHienTai(0);
		datCotHienTai(0);
		datHangTruocKhiDiChuyen(0);
		datCotTruocKhiDiChuyen(0);
		fillMoves();
	}

	public Knight(int hangbatdau, int cotbatdau) {
		giaTriHangNgang = new int[SO_DI_CHUYEN_HOP_LE];
		giaTriHangDoc = new int[SO_DI_CHUYEN_HOP_LE];
		soLanDiChuyen = 1;
		datHangHienTai(hangbatdau);
		datCotHienTai(cotbatdau);
		datHangTruocKhiDiChuyen(hangbatdau);
		datCotTruocKhiDiChuyen(cotbatdau);
		fillMoves();
	}

	// Đặt hàng và cột trước đó, và tăng bộ đếm di chuyển.
	public void move(int moveNumber) {
		if (moveNumber < 0 || moveNumber > SO_DI_CHUYEN_HOP_LE - 1) {
			System.out.println("ERROR: Invalid move: " + moveNumber);
			return;
		} else {
			datHangTruocKhiDiChuyen(hangHienTai);
			datCotTruocKhiDiChuyen(cotHienTai);
			datHangHienTai(hangHienTai + giaTriHangDoc[moveNumber]);
			datCotHienTai(cotHienTai + giaTriHangNgang[moveNumber]);
			incMoveCounter();
		}
	}

	public void moveP(int moveNumber) {
		if (moveNumber < 0 || moveNumber > SO_DI_CHUYEN_HOP_LE - 1) {
			System.out.println("ERROR: Invalid move: " + moveNumber);
			return;
		} else {
			datHangTruocKhiDiChuyen(hangHienTai);
			datCotTruocKhiDiChuyen(cotHienTai);
			datHangHienTai(hangHienTai + giaTriHangDoc[moveNumber]);
			datCotHienTai(cotHienTai + giaTriHangNgang[moveNumber]);
			incMoveCounter();
		}
	}

	// Tìm thấy số lần di chuyển có thể trên bàn cờ từ hàng và cột bắt đầu.
	public int findNumOfPossibleMoves(ChessBoard banco) {
		int possibleMoveCounter = 0, // Đếm số lần di chuyển có thể
				hangbatdau = giaTriHangHienTai(), cotbatdau = giaTriCuaCotHienTai(), testHang, testCot;

		// thêm một giá trị số di chuyển vào hàng hiện tại của knight và
		// kiểm tra xem nó có phải là một ứng cử viên di chuyển hay không
		// nếu vị trí nằm trên bàn cờ và không được báo trước,
		// bộ đếm di chuyển có thể được tăng lên.
		for (int moveNum = 0; moveNum < Knight.SO_DI_CHUYEN_HOP_LE; moveNum++) {
			testHang = hangbatdau + giaTriCuaCotDangDiChuyen(moveNum);
			testCot = cotbatdau + giaTriCuaHangDangDiChuyen(moveNum);

			if (banco.checkSquareExistsAndIsUnVisted(testHang, testCot) == true) {
				possibleMoveCounter++;
			}
		}

		return possibleMoveCounter;
	}

	// Tìm các chuyển động trên bàn cờ từ vị trí hiện tại của knight
	// và trả về một loạt các trạng thái có thể.
	public int[] findPossibleMoves(ChessBoard banco, int numPossibleMoves) {
		int[] possibleMoves = new int[numPossibleMoves];
		int storeCurRow = giaTriHangHienTai(), // lưu hàng hiện tại của knight
				storeCurCol = giaTriCuaCotHienTai(), // lưu cột hiện tại của knight
				testHang, testCot, goodMoveCount = 0;

		for (int sodichuyen = 0; sodichuyen < Knight.SO_DI_CHUYEN_HOP_LE; sodichuyen++) {
			testHang = storeCurRow + giaTriCuaCotDangDiChuyen(sodichuyen);
			testCot = storeCurCol + giaTriCuaHangDangDiChuyen(sodichuyen);

			if (banco.checkSquareExistsAndIsUnVisted(testHang, testCot) == true) {
				possibleMoves[goodMoveCount++] = sodichuyen;
			}
		}

		return possibleMoves;
	}

	public int findBestMove(ChessBoard banco, int[] cothedichuyen) {
		int storeCurRow = giaTriHangHienTai(), storeCurCol = giaTriCuaCotHienTai(), testHang, testCot, oCoKhaNangTruyCapThapNhat,
				testOCoTheDiChuyen, soDiChuyenThapNhat;

		// Nếu mảng di chuyển có thể lớn hơn 1, thì có ít nhất 2 di
		// chuyển để so sánh khả năng truy cập.
		if (cothedichuyen.length > 1) {
			// Give iLowestAccessibility a starting value to compare the rest to
			testHang = storeCurRow + giaTriCuaCotDangDiChuyen(cothedichuyen[0]);
			testCot = storeCurCol + giaTriCuaHangDangDiChuyen(cothedichuyen[0]);
			oCoKhaNangTruyCapThapNhat = banco.getSquareAccessibility(testHang, testCot);
			soDiChuyenThapNhat = cothedichuyen[0];

			// Kiểm tra mỗi lần di chuyển trong mảng so với giá trị thấp nhất
			// iMoveNum in loop header giá trị ban đầu thay đổi từ 0 đến 1
			// Lý do: Giá trị thấp nhất ban đầu được thiết lập để di chuyển 0,
			// nếu iMoveNum còn lại ở 0, thì nó không cần thiết phải
			// so sánh nó trong vòng lặp
			for (int moveNum = 1; moveNum < cothedichuyen.length; moveNum++) {
				testHang = storeCurRow + giaTriCuaCotDangDiChuyen(cothedichuyen[moveNum]);
				testCot = storeCurCol + giaTriCuaHangDangDiChuyen(cothedichuyen[moveNum]);

				testOCoTheDiChuyen = banco.getSquareAccessibility(testHang, testCot);

				// Nếu giá trị được kiểm tra thấp hơn giá trị
				// thấp nhất hiện thời lưu trữ giá trị trợ năng
				// và lưu trữ số di chuyển với giá trị thấp nhất
				// được tìm thấy trong vị trí mảng đó

				if (testOCoTheDiChuyen < oCoKhaNangTruyCapThapNhat || oCoKhaNangTruyCapThapNhat < 1) {
					oCoKhaNangTruyCapThapNhat = testOCoTheDiChuyen;
					soDiChuyenThapNhat = cothedichuyen[moveNum];
				}
			}

			return soDiChuyenThapNhat;
		}
		// Trả lại giá trị đầu tiên trong mảng nếu chiều dài mảng là 1,
		// bởi vì không có chuyển động nào khác để so sánh
		else
			return cothedichuyen[0];
	}

	// đặt hàng hiện tại.
	public void datHangHienTai(int hang) {
		hangHienTai = hang;
	}

	// Trả lại giá trị của hàng hiện tại.
	public int giaTriHangHienTai() {
		return hangHienTai;
	}

	// đặt cột hiện tại.
	public void datCotHienTai(int cot) {
		cotHienTai = cot;
	}

	// Trả lại giá trị của cột hiện tại
	public int giaTriCuaCotHienTai() {
		return cotHienTai;
	}

	// đặt hàng trước đó.
	public void datHangTruocKhiDiChuyen(int hang) {
		hangTruocKhiDiChuyen = hang;
	}

	// trả lại giá trị hàng trước đó.
	public int giaTriCuaHangTruocKhiDiChuyen() {
		return hangTruocKhiDiChuyen;
	}

	// đặt cột trước đó.
	public void datCotTruocKhiDiChuyen(int cot) {
		cotTruocKhiDiChuyen = cot;
	}

	// trả lại giá trị cột trước đó.
	public int giaTriCuaCotTruocKhiDiChuyen() {
		return cotTruocKhiDiChuyen;
	}

	// Tăng biến thành viên truy cập di chuyển bằng 1.
	public void incMoveCounter() {
		soLanDiChuyen++;
	}

	// Trả lại giá trị của bộ đếm di chuyển
	public int getMoveCounter() {
		return soLanDiChuyen;
	}

	// số di chuyển và trả về giá trị của hàng đang di chuyển.
	public int giaTriCuaHangDangDiChuyen(int sodichuyen) {
		return giaTriHangNgang[sodichuyen];
	}

	// số di chuyển và trả về giá trị của cột đang di chuyển.
	public int giaTriCuaCotDangDiChuyen(int sodichuyen) {
		return giaTriHangDoc[sodichuyen];
	}

	// Lập bản đồ di chuyển theo chiều ngang và chiều dọc của knight.
	public void fillMoves() {
		giaTriHangNgang[0] = 2; // phải
		giaTriHangNgang[1] = 1; // phải
		giaTriHangNgang[2] = -1; // trái
		giaTriHangNgang[3] = -2; // trái
		giaTriHangNgang[4] = -2; // trái
		giaTriHangNgang[5] = -1; // trái
		giaTriHangNgang[6] = 1; // phải
		giaTriHangNgang[7] = 2; // phải

		giaTriHangDoc[0] = -1; // lên
		giaTriHangDoc[1] = -2; // lên
		giaTriHangDoc[2] = -2; // lên
		giaTriHangDoc[3] = -1; // lên
		giaTriHangDoc[4] = 1; // xuống
		giaTriHangDoc[5] = 2; // xuống
		giaTriHangDoc[6] = 2; // xuống
		giaTriHangDoc[7] = 1; // xuống
	}
}
