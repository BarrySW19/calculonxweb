package nl.zoidberg.calculon.web.client;

import java.util.ArrayList;
import java.util.List;

import nl.zoidberg.calculon.web.client.images.iyt1.IYT1BoardImageBundle;
import nl.zoidberg.calculon.web.client.images.iyt2.IYT2BoardImageBundle;
import nl.zoidberg.calculon.web.client.images.iyt3.IYT3BoardImageBundle;
import nl.zoidberg.calculon.web.client.images.iyt4.IYT4BoardImageBundle;
import nl.zoidberg.calculon.web.client.images.wiki1.Wiki1BoardImageBundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class BoardDisplay extends Grid {
	private static final String RANKS = "12345678";
	private static final String FILES = "ABCDEFGH";
	
	private BoardImageBundle imageBundle = (BoardImageBundle) GWT.create(IYT4BoardImageBundle.class);
	
	private BoardInfo boardInfo;
	private String selectedFrom;
	private List<HandlerRegistration> targetHandlers = new ArrayList<HandlerRegistration>();
	private List<HandlerRegistration> sourceHandlers = new ArrayList<HandlerRegistration>();
	private Calculonweb controller;
	private boolean flipped = false;

	public BoardDisplay(Calculonweb controller) {
		super(9, 10);
		this.controller = controller;
		this.setBorderWidth(0);
		this.setCellPadding(0);
		this.setCellSpacing(0);
		
		generateLabels();

		Button button = new Button("Flip Board");
		button.setStyleName("sqLabel");
		setWidget(1, 9, button);
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				flipped = !flipped;
				setBoardInfo(boardInfo);
				generateLabels();
			}
		});

		button = new Button("Move");
		button.setStyleName("sqLabel");
		setWidget(2, 9, button);
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				BoardDisplay.this.controller.getComputerResponse();
			}
		});
		
		button = new Button("Reset");
		button.setStyleName("sqLabel");
		setWidget(3, 9, button);
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				BoardDisplay.this.controller.resetGame();
			}
		});
		
		ListBox style = new ListBox();
		style.setStyleName("margin10");
		style.addItem("Merida Blue", "4");
		style.addItem("Merida Green", "1");
		style.addItem("Merida Purple", "2");
		style.addItem("Harlequin Brown", "3");
		style.addItem("Wiki Style 1", "5");
		setWidget(5, 9, style);
		style.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				ListBox lb = (ListBox) event.getSource();
				setImageBundle(lb.getValue(lb.getSelectedIndex()));
			}
		});
	}
	
	private void generateLabels() {
		for(int i = 0; i < 8; i++) {
			Label label = new Label(String.valueOf(flipped ? FILES.charAt(7-i) : FILES.charAt(i)));
			setWidget(8, i+1, label);
			label.setStyleName("sqLabel");
			label = new Label(String.valueOf(flipped ? RANKS.charAt(i) : RANKS.charAt(7-i)));
			label.setStyleName("sqLabel");
			setWidget(i, 0, label);
		}
	}
	
	public void setImageBundle(String id) {
		switch(Integer.parseInt(id)) {
		case 1:
			imageBundle = (BoardImageBundle) GWT.create(IYT1BoardImageBundle.class);
			break;
		case 2:
			imageBundle = (BoardImageBundle) GWT.create(IYT2BoardImageBundle.class);
			break;
		case 3:
			imageBundle = (BoardImageBundle) GWT.create(IYT3BoardImageBundle.class);
			break;
		case 4:
			imageBundle = (BoardImageBundle) GWT.create(IYT4BoardImageBundle.class);
			break;
		case 5:
			imageBundle = (BoardImageBundle) GWT.create(Wiki1BoardImageBundle.class);
			break;
		}
		populateBoard();
	}

	public void setBoardInfo(BoardInfo boardInfo) {
		this.boardInfo = boardInfo;
		selectedFrom = null;
		populateBoard();
	}
	
	private void populateBoard() {
		clearHandlers(targetHandlers);
		clearHandlers(sourceHandlers);
		
		for(int rank = 0; rank < 8; rank++) {
			for(int file = 0; file < 8; file++) {
				char piece = boardInfo.getPieceAt(file, rank);
				Image img = getPieceImage(piece, (rank+file)%2);
				img = this.setBoardImage(file, rank, img);
				img.setStyleName("normalBorder");
				if(piece != ' ' && boardInfo.getPossibleMoves().get(getKey(file, rank)) != null) {
					ClickHandler clickHandler = new FromClickHandler(getKey(file, rank));
					sourceHandlers.add(img.addClickHandler(clickHandler));
				}
			}
		}
	}
	
	/**
	 * Takes care of mapping a file/rank co-ord to a grid co-ord, e.g. E4 -> (3,4).
	 * 
	 * @param file
	 * @param rank
	 * @param w
	 */
	private Image setBoardImage(int file, int rank, Image w) {
		int row = flipped ? rank : 7 - rank;
		int col = file + 1;
		
		Image img = (Image) this.getWidget(row, col);
		if(img != null) {
			img.setUrlAndVisibleRect(w.getUrl(), w.getOriginLeft(), w.getOriginTop(), w.getWidth(), w.getHeight());
		} else {
			img = w;
			this.setWidget(row, col, w);
		}
		return img;
	}
	
	private Image setBoardImage(String square, Image w) {
		return this.setBoardImage(FILES.indexOf(square.charAt(0)), RANKS.indexOf(square.charAt(1)), w);
	}

	private Image getBoardImage(String coord) {
		return getBoardImage(FILES.indexOf(coord.charAt(0)), RANKS.indexOf(coord.charAt(1)));
	}
	
	private Image getBoardImage(int file, int rank) {
		Image image = (Image) this.getWidget(flipped ? rank : 7 - rank, file + 1);
		return image;
	}
	
	private void deselect() {
		if(selectedFrom != null) {
			clearHandlers(targetHandlers);
			Image image = getBoardImage(selectedFrom);
			image.setStyleName("normalBorder");
			selectedFrom = null;
		}
	}
	
	private void select(String square) {
		this.selectedFrom = square;
		Image image = getBoardImage(selectedFrom);
		image.setStyleName("selectedBorder");
		List<String> targets = boardInfo.getPossibleMoves().get(square);
		for(String target: targets) {
			image = (Image) getBoardImage(target);
			targetHandlers.add(image.addClickHandler(new ToClickHandler(target)));
		}
	}
	
	private void moveSelectedPiece(String target) {
		clearHandlers(targetHandlers);
		clearHandlers(sourceHandlers);
		
		int file = FILES.indexOf(selectedFrom.charAt(0));
		int rank = RANKS.indexOf(selectedFrom.charAt(1));
		Image img = (rank+file)%2 == 1 ? 
				imageBundle.getEmptyLight().createImage() : imageBundle.getEmptyDark().createImage();
		img = this.setBoardImage(file, rank, img);
		img.setStyleName("normalBorder");
		
		char piece = boardInfo.getPieceAt(file, rank);
		img = getPieceImage(piece, (FILES.indexOf(target.charAt(0)) + RANKS.indexOf(target.charAt(1))) % 2);
		img = this.setBoardImage(target, img);
		img.setStyleName("normalBorder");
	}
	
	private void clearHandlers(List<HandlerRegistration> handlers) {
		for(HandlerRegistration reg: handlers) {
			reg.removeHandler();
		}
		handlers.clear();
	}
	
	private String getKey(int file, int rank) {
		return String.valueOf(FILES.charAt(file)) + String.valueOf(RANKS.charAt(rank));
	}

	private Image getPieceImage(char c, int color) {
		switch(c) {
		case ' ':
			return color == 1 ? imageBundle.getEmptyLight().createImage() : imageBundle.getEmptyDark().createImage();
		case 'p':
			return color == 1 ? imageBundle.getBlackPawnLight().createImage() : imageBundle.getBlackPawnDark().createImage();
		case 'r':
			return color == 1 ? imageBundle.getBlackRookLight().createImage() : imageBundle.getBlackRookDark().createImage();
		case 'n':
			return color == 1 ? imageBundle.getBlackKnightLight().createImage() : imageBundle.getBlackKnightDark().createImage();
		case 'b':
			return color == 1 ? imageBundle.getBlackBishopLight().createImage() : imageBundle.getBlackBishopDark().createImage();
		case 'q':
			return color == 1 ? imageBundle.getBlackQueenLight().createImage() : imageBundle.getBlackQueenDark().createImage();
		case 'k':
			return color == 1 ? imageBundle.getBlackKingLight().createImage() : imageBundle.getBlackKingDark().createImage();
		case 'P':
			return color == 1 ? imageBundle.getWhitePawnLight().createImage() : imageBundle.getWhitePawnDark().createImage();
		case 'R':
			return color == 1 ? imageBundle.getWhiteRookLight().createImage() : imageBundle.getWhiteRookDark().createImage();
		case 'N':
			return color == 1 ? imageBundle.getWhiteKnightLight().createImage() : imageBundle.getWhiteKnightDark().createImage();
		case 'B':
			return color == 1 ? imageBundle.getWhiteBishopLight().createImage() : imageBundle.getWhiteBishopDark().createImage();
		case 'Q':
			return color == 1 ? imageBundle.getWhiteQueenLight().createImage() : imageBundle.getWhiteQueenDark().createImage();
		case 'K':
			return color == 1 ? imageBundle.getWhiteKingLight().createImage() : imageBundle.getWhiteKingDark().createImage();
		}
		return null;
	}

	/* ------------------------------------- Private Classes --------------------------------------- */
	
	private class ToClickHandler implements ClickHandler {
		private String toSquare;
		
		public ToClickHandler(String target) {
			this.toSquare = target;
		}

		public void onClick(ClickEvent event) {
			moveSelectedPiece(toSquare);
			controller.moveSelected(selectedFrom + toSquare);
		}
	}
	
	private class FromClickHandler implements ClickHandler {
		private String fromSquare;
		
		private FromClickHandler(String fromSquare) {
			this.fromSquare = fromSquare;
		}

		public void onClick(ClickEvent event) {
			if(fromSquare.equals(BoardDisplay.this.selectedFrom)) {
				BoardDisplay.this.deselect();
			} else {
				BoardDisplay.this.deselect();
				BoardDisplay.this.select(fromSquare);
			}
		}
	}
}
