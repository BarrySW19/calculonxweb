package nl.zoidberg.calculon.web.client.images.iyt3;

import nl.zoidberg.calculon.web.client.BoardImageBundle;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

public interface IYT3BoardImageBundle extends ImageBundle, BoardImageBundle {
	
	@ImageBundle.Resource(value="ccb2-0x.gif")
	public AbstractImagePrototype getEmptyDark();
	@ImageBundle.Resource(value="ccb2-0y.gif")
	public AbstractImagePrototype getEmptyLight();
	
	@ImageBundle.Resource(value="ccb2-px.gif")
	public AbstractImagePrototype getBlackPawnDark();
	@ImageBundle.Resource(value="ccb2-py.gif")
	public AbstractImagePrototype getBlackPawnLight();
	@ImageBundle.Resource(value="ccb2-ax.gif")
	public AbstractImagePrototype getWhitePawnDark();
	@ImageBundle.Resource(value="ccb2-ay.gif")
	public AbstractImagePrototype getWhitePawnLight();

	@ImageBundle.Resource(value="ccb2-rx.gif")
	public AbstractImagePrototype getBlackRookDark();
	@ImageBundle.Resource(value="ccb2-ry.gif")
	public AbstractImagePrototype getBlackRookLight();
	@ImageBundle.Resource(value="ccb2-ox.gif")
	public AbstractImagePrototype getWhiteRookDark();
	@ImageBundle.Resource(value="ccb2-oy.gif")
	public AbstractImagePrototype getWhiteRookLight();
	
	@ImageBundle.Resource(value="ccb2-nx.gif")
	public AbstractImagePrototype getBlackKnightDark();
	@ImageBundle.Resource(value="ccb2-ny.gif")
	public AbstractImagePrototype getBlackKnightLight();
	@ImageBundle.Resource(value="ccb2-tx.gif")
	public AbstractImagePrototype getWhiteKnightDark();
	@ImageBundle.Resource(value="ccb2-ty.gif")
	public AbstractImagePrototype getWhiteKnightLight();
	
	@ImageBundle.Resource(value="ccb2-bx.gif")
	public AbstractImagePrototype getBlackBishopDark();
	@ImageBundle.Resource(value="ccb2-by.gif")
	public AbstractImagePrototype getBlackBishopLight();
	@ImageBundle.Resource(value="ccb2-ix.gif")
	public AbstractImagePrototype getWhiteBishopDark();
	@ImageBundle.Resource(value="ccb2-iy.gif")
	public AbstractImagePrototype getWhiteBishopLight();
	
	@ImageBundle.Resource(value="ccb2-qx.gif")
	public AbstractImagePrototype getBlackQueenDark();
	@ImageBundle.Resource(value="ccb2-qy.gif")
	public AbstractImagePrototype getBlackQueenLight();
	@ImageBundle.Resource(value="ccb2-ux.gif")
	public AbstractImagePrototype getWhiteQueenDark();
	@ImageBundle.Resource(value="ccb2-uy.gif")
	public AbstractImagePrototype getWhiteQueenLight();
	
	@ImageBundle.Resource(value="ccb2-kx.gif")
	public AbstractImagePrototype getBlackKingDark();
	@ImageBundle.Resource(value="ccb2-ky.gif")
	public AbstractImagePrototype getBlackKingLight();
	@ImageBundle.Resource(value="ccb2-gx.gif")
	public AbstractImagePrototype getWhiteKingDark();
	@ImageBundle.Resource(value="ccb2-gy.gif")
	public AbstractImagePrototype getWhiteKingLight();
}
