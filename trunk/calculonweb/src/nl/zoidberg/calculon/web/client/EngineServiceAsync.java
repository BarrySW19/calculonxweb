package nl.zoidberg.calculon.web.client;


import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface EngineServiceAsync {
	void resetGame(AsyncCallback<BoardInfo> callback);
	void getMoveUpdate(String move, BoardInfo boardInfo, AsyncCallback<BoardInfo> callback);
	void getResponseMove(BoardInfo boardInfo, AsyncCallback<BoardInfo> callback);
}
