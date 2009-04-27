package nl.zoidberg.calculon.web.client;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class BoardInfo implements Serializable {
	
	private String currentFEN;
	private Map<String, List<String>> possibleMoves;
	
	public String getCurrentFEN() {
		return currentFEN;
	}
	public void setCurrentFEN(String currentFEN) {
		this.currentFEN = currentFEN;
	}
	public Map<String, List<String>> getPossibleMoves() {
		return possibleMoves;
	}
	public void setPossibleMoves(Map<String, List<String>> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}
}
