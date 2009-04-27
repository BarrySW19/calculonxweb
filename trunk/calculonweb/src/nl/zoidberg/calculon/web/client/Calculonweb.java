package nl.zoidberg.calculon.web.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Calculonweb implements EntryPoint {

	private EngineServiceAsync engineService = (EngineServiceAsync) GWT.create(EngineService.class);
	private BoardDisplay boardDisplay;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel.get().add(boardDisplay = new BoardDisplay(this));
		resetGame();
	}

	public void moveSelected(String move) {
		AsyncCallback<BoardInfo> callback = new AsyncCallback<BoardInfo>() {
			public void onSuccess(BoardInfo result) {
				boardDisplay.setBoardInfo(result);
				boardDisplay.setStyleName("waiting");
				getComputerResponse();
			}

			public void onFailure(Throwable caught) {
				System.out.println("Help!");
			}
		};
		engineService.getMoveUpdate(move, callback);
	}

	private void getComputerResponse() {
		AsyncCallback<BoardInfo> callback = new AsyncCallback<BoardInfo>() {
			public void onSuccess(BoardInfo result) {
				boardDisplay.setBoardInfo(result);
				boardDisplay.setStyleName("normal");
			}

			public void onFailure(Throwable caught) {
				System.out.println("Help!");
			}
		};
		engineService.getResponseMove(callback);
	}
	
	public void resetGame() {
		AsyncCallback<BoardInfo> callback = new AsyncCallback<BoardInfo>() {
			public void onSuccess(BoardInfo result) {
				boardDisplay.setBoardInfo(result);
				boardDisplay.setStyleName("normal");
			}

			public void onFailure(Throwable caught) {
				System.out.println("Help!");
			}
		};
		engineService.resetGame(callback);
	}
}
