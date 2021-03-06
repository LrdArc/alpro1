public class Game {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Board board = new Board();
	}
}

class Board {
	Square[] grid;
	public Board() { grid = new Square[64]; }
	public Square getSquare(int i) { return grid[i]; }
	public void setDefault() { }
	public Boolean isMoveValid() { return null; }
}

class Square {
	private Piece piece;
	public void addPiece(Piece pieceType, String pieceColour, String pieceOwner) 
	throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		PieceFactory factory = new PieceFactory();
		Piece piece = factory.createPiece(pieceType);
		piece.setColour(pieceColour);
		piece.setOwner(pieceOwner);
		this.piece = piece; 
	}
public void addPiece(Piece pieceType) { 
	this.piece = pieceType; 
}
public void removePiece() {  
	piece = null;
}
public Piece getPiece() {
	return piece;	   
}

class PieceFactory {	 
	 @SuppressWarnings("rawtypes")
	 public Piece createPiece(Piece pieceType) 
			throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		 Class pieceClass = Class.forName(pieceType.toString());
		 Piece piece = (Piece) pieceClass.newInstance();

		 return piece;	   
	 } }

public void setColour(String colour) {

} }

abstract class Piece {

Board board;

public void setColour(String pieceColour) {
}

public void setOwner(String pieceOwner) {
}

public String getColour() {
	return "";
}

public String getOwner() {
	return "";	  
}
public void movePiece(int oldIndex, int newIndex) {
	board.getSquare(oldIndex).removePiece();
	board.getSquare(newIndex).addPiece(this);
}
public String toString() {
	return this.getClass().getSimpleName();
} }

