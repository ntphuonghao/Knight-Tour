package Code;

public class Square {
	private boolean oDaTruyCap; // true nếu ô đã được truy cập, nếu không thì fale.
	private int moveNumber; // số lượng di chuyển đã được thực hiện.
	private int oCoTheDiDuoc; // Số lượng di chuyển có thể từ ô.

	public Square() {
		oDaTruyCap = false;
		moveNumber = 0;
	}

	// Nhận giá trị boolean và đặt thuộc tính truy cập của ô.
	public void datODaTruyCap(boolean datruycap) {
		oDaTruyCap = datruycap;
	}

	// Trả về giá trị của thuộc tính đã truy cập.
	public boolean isVisited() {
		return oDaTruyCap;
	}

	// số di chuyển hiện tại của người chơi và đánh dấu ô.
	public void setMoveNumber(int moveNumber) {
		this.moveNumber = moveNumber;
	}

	// Trả lại giá trị của số di chuyển của ô.
	public int getMoveNumber() {
		return moveNumber;
	}

	// đặt giá trị khả năng tiếp cận của ô.
	public void setAccessibility(int ocothedi) {
		this.oCoTheDiDuoc = ocothedi;
	}

	// Trả lại giá trị khả năng truy cập của ô.
	public int getAccessibility() {
		return oCoTheDiDuoc;
	}

	// Giảm giá trị khả năng truy cập của ô.
	public void decrAccessibility() {
		oCoTheDiDuoc--;
	}
}
