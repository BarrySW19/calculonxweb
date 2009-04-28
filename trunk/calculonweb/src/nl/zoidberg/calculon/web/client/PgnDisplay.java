package nl.zoidberg.calculon.web.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;

public class PgnDisplay extends FlexTable {

	private int nextRow;
	private int nextPlayer;
	private List<String> moveHistory = new ArrayList<String>();
	
	public PgnDisplay() {
		this.getFlexCellFormatter().setColSpan(0, 0, 3);
		Label title = new Label("Move History");
		title.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.setWidget(0, 0, title);
		this.setWidth("175px");		
		reset();
	}

	public void reset() {
		nextRow = 1;
		nextPlayer = 0;
		while(this.getRowCount() > 1) {
			this.removeRow(1);
		}
		moveHistory.clear();
	}
	
	public void addMove(String move) {
		moveHistory.add(move);
		if(nextPlayer == 0) {
			Label moveNum = new Label(String.valueOf(nextRow) + ".");
			this.setWidget(nextRow, 0, moveNum);
		}
		Label moveLabel = new Label(move);
		this.setWidget(nextRow, nextPlayer == 0 ? 1 : 2, moveLabel);
		nextPlayer++;
		if(nextPlayer == 2) {
			nextPlayer = 0;
			nextRow++;
		}
	}

	public void setResult(String result) {
		if(nextPlayer == 1) {
			nextRow++;
		}
		this.getFlexCellFormatter().setColSpan(nextRow, 0, 3);
		Label lResult = new Label(result);
		lResult.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.setWidget(nextRow, 0, lResult);
	}
}
