package nl.zoidberg.calculon.web.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;

public class PgnDisplay extends FlexTable {

	private int nextRow;
	private int nextCol;
	private List<String> moveHistory = new ArrayList<String>();
	
	public PgnDisplay() {
		this.getFlexCellFormatter().setColSpan(0, 0, 6);
		Label title = new Label("Move History");
		title.addStyleName("underline");
		title.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.setWidget(0, 0, title);
		this.setWidth("175px");		
		reset();
	}

	public void reset() {
		nextRow = 1;
		nextCol = 0;
		while(this.getRowCount() > 1) {
			this.removeRow(1);
		}
		moveHistory.clear();
	}
	
	public void addMove(String move) {
		moveHistory.add(move);
		if(nextCol == 0 || nextCol == 3) {
			Label moveNum = new Label(String.valueOf((nextRow*2-1) + nextCol/3) + ".");
			moveNum.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
			moveNum.addStyleName("margin20-left");
			this.setWidget(nextRow, nextCol, moveNum);
			nextCol++;
		}
		Label moveLabel = new Label(move);
		moveLabel.setWidth("65px");
		this.setWidget(nextRow, nextCol, moveLabel);
		nextCol++;
		if(nextCol == 6) {
			nextCol = 0;
			nextRow++;
		}
	}

	public void setResult(String result) {
		if(nextCol == 1) {
			nextRow++;
		}
		this.getFlexCellFormatter().setColSpan(nextRow, 0, 6);
		Label lResult = new Label(result);
		lResult.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.setWidget(nextRow, 0, lResult);
	}
}
