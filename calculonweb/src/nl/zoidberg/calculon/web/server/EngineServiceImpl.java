package nl.zoidberg.calculon.web.server;

import nl.zoidberg.calculon.engine.MoveGenerator;
import nl.zoidberg.calculon.engine.SearchNode;
import nl.zoidberg.calculon.model.Board;
import nl.zoidberg.calculon.notation.FENUtils;
import nl.zoidberg.calculon.web.client.BoardInfo;
import nl.zoidberg.calculon.web.client.EngineService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class EngineServiceImpl extends RemoteServiceServlet implements EngineService {
	
	public BoardInfo resetGame() {
		return getBoardInfo(new Board().initialise());
	}

	public BoardInfo getMoveUpdate(String move, BoardInfo currentBoard) {
		if(currentBoard == null) {
			return resetGame();
		}

		Board board = new Board(currentBoard.getSquares(), currentBoard.getFlags(), currentBoard.getHistory());
		board.applyMove(move);

		return getBoardInfo(board);
	}

	public BoardInfo getResponseMove(BoardInfo currentBoard) {
		if(currentBoard == null) {
			return resetGame();
		}
		
		Board board = new Board(currentBoard.getSquares(), currentBoard.getFlags(), currentBoard.getHistory());
		SearchNode node = new SearchNode(board);
		board.applyMove(node.getPreferredMove());

		return getBoardInfo(board);
	}
	
	private static BoardInfo getBoardInfo(Board board) {
		BoardInfo boardInfo = new BoardInfo();
		boardInfo.setCurrentFEN(FENUtils.generate(board));
		boardInfo.setPossibleMoves(MoveGenerator.get().getPossibleMoves(board));
		boardInfo.setHistory(board.getHistory());
		boardInfo.setSquares(board.getSquares());
		boardInfo.setFlags(board.getFlags());
		return boardInfo;
	}
}
