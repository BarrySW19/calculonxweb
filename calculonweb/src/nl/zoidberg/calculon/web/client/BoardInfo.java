package nl.zoidberg.calculon.web.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class BoardInfo implements Serializable {
	
	private List<String> board;
	
	private String currentFEN;
	private Map<String, List<String>> possibleMoves;
	private byte[] squares;
	private Map<String, Short> history;
	private short flags;
	private String lastMove;
	private String result;
	
//	public String getStateInfo() {
//		return stateInfo;
//	}
//
//	public void setStateInfo(String stateInfo) {
//		this.stateInfo = stateInfo;
//	}

	public String getLastMove() {
		return lastMove;
	}

	public void setLastMove(String lastMove) {
		this.lastMove = lastMove;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public short getFlags() {
		return flags;
	}

	public void setFlags(short flags) {
		this.flags = flags;
	}

	public byte[] getSquares() {
		return squares;
	}

	public void setSquares(byte[] squares) {
		this.squares = squares;
	}

	public Map<String, Short> getHistory() {
		return history;
	}

	public void setHistory(Map<String, Short> history) {
		this.history = history;
	}

	public char getPieceAt(int file, int rank) {
		return board.get(rank).charAt(file); 
	}
	
	public String getCurrentFEN() {
		return currentFEN;
	}
	
	public void setCurrentFEN(String fen) {
		this.currentFEN = fen;
		board = new ArrayList<String>();
		StringBuffer buf = new StringBuffer(fen.substring(0, fen.indexOf(" ")));
		buf.append("/");
	
		while(buf.length() > 0) {
			String fenRank = buf.substring(0, buf.indexOf("/"));
			StringBuffer rank = new StringBuffer();
			for(int i = 0; i < fenRank.length(); i++) {
				if(Character.isDigit(fenRank.charAt(i))) {
					for(int j = 0; j < (fenRank.charAt(i) - '0'); j++) {
						rank.append(" ");
					}
				} else {
					rank.append(fenRank.charAt(i));
				}
			}
			buf.delete(0, buf.indexOf("/") + 1);
			board.add(0, rank.toString());
		}
	}
	
	public Map<String, List<String>> getPossibleMoves() {
		return possibleMoves;
	}
	public void setPossibleMoves(Map<String, List<String>> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}
}
