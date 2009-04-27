package nl.zoidberg.calculon.web.client;


import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface EngineServiceAsync {
	void getCurrentFEN(AsyncCallback<BoardInfo> callback);
	void getMoveUpdate(String move, AsyncCallback<BoardInfo> callback);
	void getResponseMove(AsyncCallback<BoardInfo> callback);
}
