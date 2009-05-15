package nl.zoidberg.calculon.web.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nl.zoidberg.calculon.engine.Board;
import nl.zoidberg.calculon.engine.ChessEngine;
import nl.zoidberg.calculon.engine.MoveGenerator;
import nl.zoidberg.calculon.notation.FENUtils;
import nl.zoidberg.calculon.notation.PGNUtils;
import nl.zoidberg.calculon.web.client.BoardInfo;
import nl.zoidberg.calculon.web.client.EngineService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class EngineServiceImpl extends RemoteServiceServlet implements EngineService {
	
	public BoardInfo resetGame() {
		Board board = new Board().initialise();
//		FENUtils.loadPosition("7k/8/P7/8/8/8/p7/1R5K b - - 0 1", board);
		return getBoardInfo(board);
	}

	public BoardInfo getMoveUpdate(String move, BoardInfo currentBoard) {
		if(currentBoard == null) {
			return resetGame();
		}

		Board board = new Board(currentBoard.getSquares(), currentBoard.getFlags(), currentBoard.getHistory());
		String pgn = PGNUtils.translateMove(board, move);
		board.applyMove(move);

		BoardInfo rv = getBoardInfo(board);
		rv.setLastMove(pgn);
		return rv;
	}

	public BoardInfo getResponseMove(BoardInfo currentBoard) {
		if(currentBoard == null) {
			return resetGame();
		}
		
		Board board = new Board(currentBoard.getSquares(), currentBoard.getFlags(), currentBoard.getHistory());
		ChessEngine node = new ChessEngine();
		String move = node.getPreferredMove(board);
		String pgn = PGNUtils.translateMove(board, move);
		
		board.applyMove(move);

		BoardInfo rv = getBoardInfo(board);
		rv.setLastMove(pgn);
		return rv;
	}
	
	private static BoardInfo getBoardInfo(Board board) {
		BoardInfo boardInfo = new BoardInfo();
		boardInfo.setCurrentFEN(FENUtils.generate(board));
		boardInfo.setHistory(board.getHistory());
		boardInfo.setSquares(board.getSquares());
		boardInfo.setFlags(board.getFlags());
		boardInfo.setResult(board.getResult());
		
		Map<String, List<String>> allMoves = MoveGenerator.getPossibleMoves(board);
		Map<String, Set<String>> possibleMoves = new HashMap<String, Set<String>>();
		for(String key: allMoves.keySet()) {
			List<String> moves = allMoves.get(key);
			Set<String> setMoves = new HashSet<String>();
			for(String move: moves) {
				if(move.indexOf('=') >= 0) {
					setMoves.add(move.substring(0, move.indexOf('=')));
				} else {
					setMoves.add(move);
				}
			}
			possibleMoves.put(key, setMoves);
		}
		boardInfo.setPossibleMoves(possibleMoves);
		
		return boardInfo;
	}
}
