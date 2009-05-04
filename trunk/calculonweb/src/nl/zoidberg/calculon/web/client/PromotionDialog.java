package nl.zoidberg.calculon.web.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;

public class PromotionDialog extends DialogBox {
	private BoardDisplay parent;
	private String target;
	
	public PromotionDialog(BoardImageBundle images, int color, BoardDisplay parent, String target) {
		this.parent = parent;
		this.target = target;

		this.setText("Promote to...");
		Grid grid = new Grid(1, 4);
		
		Image imgQueen = 	(color == 1 ? images.getBlackQueenLight() : images.getWhiteQueenDark()).createImage();
		Image imgRook = 	(color == 1 ? images.getBlackRookLight() : images.getWhiteRookDark()).createImage();
		Image imgBishop = 	(color == 1 ? images.getBlackBishopLight() : images.getWhiteBishopDark()).createImage();
		Image imgKnight = 	(color == 1 ? images.getBlackKnightLight() : images.getWhiteKnightDark()).createImage();
		
		imgQueen.addClickHandler(new PieceClickHandler("Q"));
		imgRook.addClickHandler(new PieceClickHandler("R"));
		imgBishop.addClickHandler(new PieceClickHandler("B"));
		imgKnight.addClickHandler(new PieceClickHandler("N"));

		grid.setWidget(0, 0, imgQueen);
		grid.setWidget(0, 1, imgRook);
		grid.setWidget(0, 2, imgBishop);
		grid.setWidget(0, 3, imgKnight);
		
		setWidget(grid);
	}

	public void fireClick(String piece) {
		this.hide();
		parent.promotePawn(target + "=" + piece);
	}
	
	private class PieceClickHandler implements ClickHandler {
		private String piece;
		
		public PieceClickHandler(String piece) {
			this.piece = piece;
		}
		
		public void onClick(ClickEvent event) {
			PromotionDialog.this.fireClick(piece);
		}
	}
}
