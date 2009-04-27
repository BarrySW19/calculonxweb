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
import com.google.gwt.user.client.ui.Widget;

public class BoardDisplay extends Grid {
	private static final String RANKS = "12345678";
	private static final String FILES = "ABCDEFGH";
	private BoardImageBundle imageBundle = (BoardImageBundle) GWT.create(IYT1BoardImageBundle.class);
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
			label = new Label(String.valueOf(flipped ? RANKS.charAt(7-i) : RANKS.charAt(i)));
			label.setStyleName("sqLabel");
			setWidget(i, 0, label);
		}
		
	}
	
	public void setBoardInfo(BoardInfo boardInfo) {
		this.boardInfo = boardInfo;
		selectedFrom = null;
		for(HandlerRegistration reg: targetHandlers) {
			reg.removeHandler();
		}
		targetHandlers.clear();
		populateBoard();
	}
	
	private void populateBoard() {
		String fen = boardInfo.getCurrentFEN();
		StringBuffer buf = new StringBuffer(fen.substring(0, fen.indexOf(" ")));
		buf.append("/");
		int rank = 0;
		while(buf.length() > 0) {
			String sRank = buf.substring(0, buf.indexOf("/"));
			buf.delete(0, buf.indexOf("/") + 1);
			int file = 0;
			for(int i = 0; i < sRank.length(); i++) {
				if(Character.isDigit(sRank.charAt(i))) {
					for(int j = 0; j < (sRank.charAt(i) - '0'); j++) {
						Widget w = (rank+file)%2 == 0 ? 
								imageBundle.getEmptyLight().createImage() : imageBundle.getEmptyDark().createImage();
						w.setStyleName("normalBorder");
						this.setWidget(rank, file+1, w);
						file++;
					}
				} else {
					int color = (rank+file)%2;
					Widget w = getPieceImage(sRank.charAt(i), color);
					if(boardInfo.getPossibleMoves().get(getKey(file, 7-rank)) != null) {
						((Image) w).addClickHandler(new FromClickHandler(getKey(file, 7-rank)));
					}
					w.setStyleName("normalBorder");
					this.setWidget(flipped ? 7-rank : rank, file+1, w);
					file++;
				}
			}
			rank++;
		}
	}
	
	private void deselect() {
		if(selectedFrom != null) {
			for(HandlerRegistration reg: targetHandlers) {
				reg.removeHandler();
			}
			targetHandlers.clear();
			Image image = (Image) getWidget(7 - RANKS.indexOf(selectedFrom.charAt(1)), FILES.indexOf(selectedFrom.charAt(0)));
			image.setStyleName("normalBorder");
			selectedFrom = null;
		}
	}
	
	private void select(String square) {
		this.selectedFrom = square;
		Image image = (Image) getWidget(7 - RANKS.indexOf(selectedFrom.charAt(1)), FILES.indexOf(selectedFrom.charAt(0)+1));
		image.setStyleName("selectedBorder");
		List<String> targets = boardInfo.getPossibleMoves().get(square);
		for(String target: targets) {
			image = (Image) getWidget(7 - RANKS.indexOf(target.charAt(1)), FILES.indexOf(target.charAt(0)+1));
			targetHandlers.add(image.addClickHandler(new ToClickHandler(target)));
		}
	}
	
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
	
	private void moveSelectedPiece(String target) {
		int file = FILES.indexOf(selectedFrom.charAt(0));
		int rank = RANKS.indexOf(selectedFrom.charAt(1));
		if( ! flipped) {
			rank = 7 - rank;
		}

		Widget w = (rank+file)%2 == 0 ? 
				imageBundle.getEmptyLight().createImage() : imageBundle.getEmptyDark().createImage();
		w.setStyleName("normalBorder");
		this.setWidget(rank, file+1, w);
		
		file = FILES.indexOf(target.charAt(0));
		rank = RANKS.indexOf(target.charAt(1));
		if( ! flipped) {
			rank = 7 - rank;
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
	
	private String getKey(int file, int rank) {
		return String.valueOf(FILES.charAt(file)) + String.valueOf(RANKS.charAt(rank));
	}

	private Widget getPieceImage(char c, int color) {
		switch(c) {
		case 'p':
			return color == 0 ? imageBundle.getBlackPawnLight().createImage() : imageBundle.getBlackPawnDark().createImage();
		case 'r':
			return color == 0 ? imageBundle.getBlackRookLight().createImage() : imageBundle.getBlackRookDark().createImage();
		case 'n':
			return color == 0 ? imageBundle.getBlackKnightLight().createImage() : imageBundle.getBlackKnightDark().createImage();
		case 'b':
			return color == 0 ? imageBundle.getBlackBishopLight().createImage() : imageBundle.getBlackBishopDark().createImage();
		case 'q':
			return color == 0 ? imageBundle.getBlackQueenLight().createImage() : imageBundle.getBlackQueenDark().createImage();
		case 'k':
			return color == 0 ? imageBundle.getBlackKingLight().createImage() : imageBundle.getBlackKingDark().createImage();
		case 'P':
			return color == 0 ? imageBundle.getWhitePawnLight().createImage() : imageBundle.getWhitePawnDark().createImage();
		case 'R':
			return color == 0 ? imageBundle.getWhiteRookLight().createImage() : imageBundle.getWhiteRookDark().createImage();
		case 'N':
			return color == 0 ? imageBundle.getWhiteKnightLight().createImage() : imageBundle.getWhiteKnightDark().createImage();
		case 'B':
			return color == 0 ? imageBundle.getWhiteBishopLight().createImage() : imageBundle.getWhiteBishopDark().createImage();
		case 'Q':
			return color == 0 ? imageBundle.getWhiteQueenLight().createImage() : imageBundle.getWhiteQueenDark().createImage();
		case 'K':
			return color == 0 ? imageBundle.getWhiteKingLight().createImage() : imageBundle.getWhiteKingDark().createImage();
		}
		return null;
	}
}
