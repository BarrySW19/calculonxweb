package nl.zoidberg.calculon.web.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	private static final String ATTR_BOARD = "BOARD";

	public BoardInfo resetGame() {
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession();
		
		session.setAttribute(ATTR_BOARD, new Board().initialise());
		BoardInfo boardInfo = new BoardInfo();
		Board board = (Board) session.getAttribute(ATTR_BOARD);
		boardInfo.setCurrentFEN(FENUtils.generate(board));
		
		boardInfo.setPossibleMoves(MoveGenerator.get().getPossibleMoves(board));
		
		return boardInfo;
	}

	public BoardInfo getMoveUpdate(String move) {
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession();
		Board board = (Board) session.getAttribute(ATTR_BOARD);
		if(board == null) {
			return resetGame();
		}

		BoardInfo boardInfo = new BoardInfo();
		board.applyMove(move);

		boardInfo.setCurrentFEN(FENUtils.generate(board));
		boardInfo.setPossibleMoves(MoveGenerator.get().getPossibleMoves(board));
		
		return boardInfo;
	}

	public BoardInfo getResponseMove() {
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession();
		Board board = (Board) session.getAttribute(ATTR_BOARD);
		if(board == null) {
			return resetGame();
		}
		BoardInfo boardInfo = new BoardInfo();
		SearchNode node = new SearchNode(board);
		board.applyMove(node.getPreferredMove());

		boardInfo.setCurrentFEN(FENUtils.generate(board));
		boardInfo.setPossibleMoves(MoveGenerator.get().getPossibleMoves(board));
		
		return boardInfo;
	}
}
