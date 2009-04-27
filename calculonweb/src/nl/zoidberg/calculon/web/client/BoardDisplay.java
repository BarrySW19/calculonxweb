package nl.zoidberg.calculon.web.client;

import java.util.ArrayList;
import java.util.List;

import nl.zoidberg.calculon.web.client.images.iyt1.IYT1BoardImageBundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class BoardDisplay extends Grid {
	private static final String RANKS = "12345678";
	private static final String FILES = "ABCDEFGH";
	
	private final BoardImageBundle imageBundle = (BoardImageBundle) GWT.create(IYT1BoardImageBundle.class);
	
	private BoardInfo boardInfo;
	private String selectedFrom;
	private List<HandlerRegistration> targetHandlers = new ArrayList<HandlerRegistration>();
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
	
	public void setBoardInfo(BoardInfo boardInfo) {
		this.boardInfo = boardInfo;
		selectedFrom = null;
		populateBoard();
	}
	
	private void populateBoard() {
		for(HandlerRegistration reg: targetHandlers) {
			reg.removeHandler();
		}
		targetHandlers.clear();
		
		for(int rank = 0; rank < 8; rank++) {
			for(int file = 0; file < 8; file++) {
				char piece = boardInfo.getPieceAt(file, rank);
				Image img = getPieceImage(piece, (rank+file)%2);
				img.setStyleName("normalBorder");
				this.setBoardImage(file, rank, img);
				if(piece != ' ' && boardInfo.getPossibleMoves().get(getKey(file, rank)) != null) {
					img.addClickHandler(new FromClickHandler(getKey(file, rank)));
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
	private void setBoardImage(int file, int rank, Image w) {
		int row = flipped ? rank : 7 - rank;
		int col = file + 1;
		
		this.setWidget(row, col, w);
	}
	
	private void setBoardImage(String square, Image w) {
		this.setBoardImage(FILES.indexOf(square.charAt(0)), RANKS.indexOf(square.charAt(1)), w);
	}

	private Image getBoardImage(String coord) {
		System.out.println("get: " + coord);
		return getBoardImage(FILES.indexOf(coord.charAt(0)), RANKS.indexOf(coord.charAt(1)));
	}
	
	private Image getBoardImage(int file, int rank) {
		System.out.println("get: " + file + " " + rank + " " + flipped);
		Image image = (Image) this.getWidget(flipped ? rank : 7 - rank, file + 1);
		return image;
	}
	
	private void deselect() {
		if(selectedFrom != null) {
			for(HandlerRegistration reg: targetHandlers) {
				reg.removeHandler();
			}
			targetHandlers.clear();
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
		int file = FILES.indexOf(selectedFrom.charAt(0));
		int rank = RANKS.indexOf(selectedFrom.charAt(1));
		Image img = (rank+file)%2 == 1 ? 
				imageBundle.getEmptyLight().createImage() : imageBundle.getEmptyDark().createImage();
		img.setStyleName("normalBorder");
		this.setBoardImage(file, rank, img);
		
		char piece = boardInfo.getPieceAt(file, rank);
		img = getPieceImage(piece, (FILES.indexOf(target.charAt(0)) + RANKS.indexOf(target.charAt(1))) % 2);
		img.setStyleName("normalBorder");
		this.setBoardImage(target, img);
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
