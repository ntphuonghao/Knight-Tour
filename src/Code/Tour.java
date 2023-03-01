package Code;

import Code.ChessBoard;
import Code.Knight;
import Code.Square;

public class Tour {
	private ChessBoard banco; // bàn cờ.
	private Knight knightPiece; // Knight di chuyển.
	private boolean foundMove = false; // true nếu knight có thể di chuyển,
										// không thì fale

	public Tour(ChessBoard banco, Knight quanma) {
		knightPiece = quanma;
		this.banco = banco;
		this.banco.themQuanMa(knightPiece);
	}

	public Tour() {
		knightPiece = new Knight();
		banco = new ChessBoard(knightPiece);
	}
	
	public Tour(int dimension) {
		knightPiece = new Knight();
		banco = new ChessBoard(knightPiece, dimension);
	}

	public Tour(int hangbatdau, int cotbatdau) {
		knightPiece = new Knight(hangbatdau, cotbatdau);
		banco = new ChessBoard(knightPiece);
	}

	public Tour(int hangbatdau, int cotbatdau, int dimension) {
		knightPiece = new Knight(hangbatdau, cotbatdau);
		banco = new ChessBoard(knightPiece, dimension);
	}

	public void resetTour() {
		knightPiece = null;
		banco = null;
	}

	// Đặt vị trí bắt đầu của knight trên bảng cờ vua.
	public void datVitriBatDau(int hangbatdau, int cotbatdau) {
		knightPiece.datHangHienTai(hangbatdau);
		knightPiece.datCotHienTai(cotbatdau);
		knightPiece.datHangTruocKhiDiChuyen(hangbatdau);
		knightPiece.datCotTruocKhiDiChuyen(cotbatdau);
	}

	public ChessBoard giaTriCuaBanCo() {
		return banco;
	}

	public Knight giaTriCuaKnight() {
		return knightPiece;
	}

	public Square giaTriCuaOCo(int hang, int cot) {
		return banco.getSquareAt(hang, cot);
	}

	// Nếu knight không có một động thái, sau đó các tour kết thúc.
	public boolean hasMove() {
		return foundMove;
	}


	public void move() {
		int numPossibleMoves, bestMove;
		int[] possibleMoves;

		foundMove = false;
		// Đánh dấu trạng thái đã truy cập và di chuyển tại vị trí hiện tại
		banco.markBoardSquare(knightPiece.giaTriHangHienTai(), knightPiece.giaTriCuaCotHienTai(),
				knightPiece.getMoveCounter());

		// Giá trị tiếp cận thấp hơn của các ô vuông xung quanh.
		banco.lowerAccessibility();

		// Tìm số di chuyển có thể từ vị trí hiện tại.
		numPossibleMoves = knightPiece.findNumOfPossibleMoves(banco);

		// Nếu số lượng di chuyển có thể >0, có ít nhất một di chuyển có thể.
		if (numPossibleMoves > 0) {
			foundMove = true;

			// Tìm các chuyển động có thể từ vị trí hiện tại và trở lại thành một mảng
			possibleMoves = knightPiece.findPossibleMoves(banco, numPossibleMoves);

			// Sử dụng mảng di chuyển có thể, tìm động thái tốt nhất dựa trên khả năng tiếp
			// cận heuristic
			bestMove = knightPiece.findBestMove(banco, possibleMoves);

			// Di chuyển knight tốt nhất
			knightPiece.move(bestMove);
		}
	}

	public void moveP() {
		int numPossibleMoves;
		int[] possibleMoves;

		foundMove = false;//true nếu knight có thể di chuyển, không thì false
		
		// Đánh dấu trạng thái đã truy cập và di chuyển tại vị trí hiện tại
		banco.markBoardSquare(knightPiece.giaTriHangHienTai(), knightPiece.giaTriCuaCotHienTai(),
				knightPiece.getMoveCounter());

		// Tìm số di chuyển có thể từ vị trí hiện tại.
		numPossibleMoves = knightPiece.findNumOfPossibleMoves(banco);

		// Nếu số lượng di chuyển có thể >0, có ít nhất một di chuyển có thể.
		if (numPossibleMoves > 0) {
			foundMove = true;

			// Tìm các chuyển động có thể từ vị trí hiện tại và trở lại thành một mảng
			possibleMoves = knightPiece.findPossibleMoves(banco, numPossibleMoves);
			// Di chuyển knight tốt nhất
			knightPiece.moveP(possibleMoves[0]);
		}
	}
}
