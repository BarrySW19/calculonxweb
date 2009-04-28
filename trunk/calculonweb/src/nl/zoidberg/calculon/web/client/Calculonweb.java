package nl.zoidberg.calculon.web.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Calculonweb implements EntryPoint {

	private EngineServiceAsync engineService = (EngineServiceAsync) GWT.create(EngineService.class);
	private BoardDisplay boardDisplay;
	private BoardInfo boardInfo;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		boardDisplay = new BoardDisplay(this);
		Grid layout = new Grid(2, 2);
		layout.setWidget(0, 1, boardDisplay);
		Image logo = new Image();
		logo.setUrl("resources/calculon.gif");
		layout.setWidget(0, 0, logo);
		RootPanel.get().add(layout);
		resetGame();
	}

	public void moveSelected(String move) {
		AsyncCallback<BoardInfo> callback = new AsyncCallback<BoardInfo>() {
			public void onSuccess(BoardInfo result) {
				boardInfo = result;
				boardDisplay.setBoardInfo(result);
				boardDisplay.setStyleName("waiting");
				getComputerResponse();
			}

			public void onFailure(Throwable caught) {
				System.out.println("Help!");
			}
		};
		engineService.getMoveUpdate(move, boardInfo, callback);
	}

	public void getComputerResponse() {
		AsyncCallback<BoardInfo> callback = new AsyncCallback<BoardInfo>() {
			public void onSuccess(BoardInfo result) {
				boardInfo = result;
				boardDisplay.setBoardInfo(result);
				boardDisplay.setStyleName("normal");
			}

			public void onFailure(Throwable caught) {
				System.out.println("Help!");
			}
		};
		engineService.getResponseMove(boardInfo, callback);
	}
	
	public void resetGame() {
		AsyncCallback<BoardInfo> callback = new AsyncCallback<BoardInfo>() {
			public void onSuccess(BoardInfo result) {
				boardInfo = result;
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
