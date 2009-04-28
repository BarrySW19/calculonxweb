package nl.zoidberg.calculon.web.server;

import nl.zoidberg.calculon.engine.MoveGenerator;
import nl.zoidberg.calculon.engine.SearchNode;
import nl.zoidberg.calculon.model.Board;
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
//		FENUtils.loadPosition("7k/R7/8/8/8/8/8/1R5K w - - 0 1", board);
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
		SearchNode node = new SearchNode(board);
		String move = node.getPreferredMove();
		String pgn = PGNUtils.translateMove(board, move);
		
		board.applyMove(move);

		BoardInfo rv = getBoardInfo(board);
		rv.setLastMove(pgn);
		return rv;
	}
	
	private static BoardInfo getBoardInfo(Board board) {
		BoardInfo boardInfo = new BoardInfo();
		boardInfo.setCurrentFEN(FENUtils.generate(board));
		boardInfo.setPossibleMoves(MoveGenerator.get().getPossibleMoves(board));
		boardInfo.setHistory(board.getHistory());
		boardInfo.setSquares(board.getSquares());
		boardInfo.setFlags(board.getFlags());
		boardInfo.setResult(board.getResult());
		return boardInfo;
	}
}
