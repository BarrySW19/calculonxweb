package nl.zoidberg.calculon.web.client;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("engine")
public interface EngineService extends RemoteService {
	BoardInfo resetGame();
	BoardInfo getMoveUpdate(String move, BoardInfo boardInfo);
	BoardInfo getResponseMove(BoardInfo boardInfo);
}
