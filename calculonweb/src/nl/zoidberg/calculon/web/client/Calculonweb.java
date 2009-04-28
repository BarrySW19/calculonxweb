package nl.zoidberg.calculon.web.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Calculonweb implements EntryPoint {

	private EngineServiceAsync engineService = (EngineServiceAsync) GWT.create(EngineService.class);
	private BoardDisplay boardDisplay;
	private PgnDisplay pgnDisplay = new PgnDisplay();
	private BoardInfo boardInfo;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		boardDisplay = new BoardDisplay(this);
		FlexTable layout = new FlexTable();
		
//		layout.setBorderWidth(1);
		layout.getFlexCellFormatter().setRowSpan(0, 0, 2);
		layout.getFlexCellFormatter().setRowSpan(0, 2, 2);
		layout.getFlexCellFormatter().setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_TOP);
		layout.getFlexCellFormatter().setWidth(1, 0, "300px");
		
		Image logo = new Image();
		logo.setUrl("resources/calculon.gif");
		layout.setWidget(0, 0, logo);
		layout.setWidget(0, 1, boardDisplay);
		layout.setWidget(0, 2, pgnDisplay);

		Label inst = new Label(
				"Hello, I'm Calculon, and I'm here to play chess. I might be playing on FICS or ICS too. "
				+ "Playing is simple - just click on the piece you want to move and then click on the "
				+ "square to move to. To ask me to move on your go click the 'Move' button. To play as black just "
				+ "start by clicking 'Move' then 'Flip'. You can also reset the game at any time."
				);
		layout.setWidget(1, 0, inst);
		
		RootPanel.get().add(layout);
		resetGame();
	}

	public void moveSelected(String move) {
		AsyncCallback<BoardInfo> callback = new AsyncCallback<BoardInfo>() {
			public void onSuccess(BoardInfo result) {
				boardInfo = result;
				boardDisplay.setBoardInfo(result);
				pgnDisplay.addMove(boardInfo.getLastMove());
				if("*".equals(boardInfo.getResult())) {
					boardDisplay.setStyleName("waiting");
					getComputerResponse();
				} else {
					pgnDisplay.setResult(boardInfo.getResult());
				}
			}

			public void onFailure(Throwable caught) {
				Window.alert(String.valueOf(caught));
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
				pgnDisplay.addMove(boardInfo.getLastMove());
				if( ! "*".equals(boardInfo.getResult())) {
					pgnDisplay.setResult(boardInfo.getResult());
				}
			}

			public void onFailure(Throwable caught) {
				Window.alert(String.valueOf(caught));
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
				pgnDisplay.reset();
			}

			public void onFailure(Throwable caught) {
				Window.alert(String.valueOf(caught));
			}
		};
		engineService.resetGame(callback);
	}
}
