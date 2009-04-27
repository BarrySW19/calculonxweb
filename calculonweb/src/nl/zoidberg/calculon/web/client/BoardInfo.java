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
