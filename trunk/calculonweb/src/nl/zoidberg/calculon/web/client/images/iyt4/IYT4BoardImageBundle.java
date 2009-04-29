package nl.zoidberg.calculon.web.client.images.iyt4;

import nl.zoidberg.calculon.web.client.BoardImageBundle;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

public interface IYT4BoardImageBundle extends ImageBundle, BoardImageBundle {
	
	@ImageBundle.Resource(value="ceb2-0x.gif")
	public AbstractImagePrototype getEmptyDark();
	@ImageBundle.Resource(value="ceb2-0y.gif")
	public AbstractImagePrototype getEmptyLight();
	
	@ImageBundle.Resource(value="ceb2-px.gif")
	public AbstractImagePrototype getBlackPawnDark();
	@ImageBundle.Resource(value="ceb2-py.gif")
	public AbstractImagePrototype getBlackPawnLight();
	@ImageBundle.Resource(value="ceb2-ax.gif")
	public AbstractImagePrototype getWhitePawnDark();
	@ImageBundle.Resource(value="ceb2-ay.gif")
	public AbstractImagePrototype getWhitePawnLight();

	@ImageBundle.Resource(value="ceb2-rx.gif")
	public AbstractImagePrototype getBlackRookDark();
	@ImageBundle.Resource(value="ceb2-ry.gif")
	public AbstractImagePrototype getBlackRookLight();
	@ImageBundle.Resource(value="ceb2-ox.gif")
	public AbstractImagePrototype getWhiteRookDark();
	@ImageBundle.Resource(value="ceb2-oy.gif")
	public AbstractImagePrototype getWhiteRookLight();
	
	@ImageBundle.Resource(value="ceb2-nx.gif")
	public AbstractImagePrototype getBlackKnightDark();
	@ImageBundle.Resource(value="ceb2-ny.gif")
	public AbstractImagePrototype getBlackKnightLight();
	@ImageBundle.Resource(value="ceb2-tx.gif")
	public AbstractImagePrototype getWhiteKnightDark();
	@ImageBundle.Resource(value="ceb2-ty.gif")
	public AbstractImagePrototype getWhiteKnightLight();
	
	@ImageBundle.Resource(value="ceb2-bx.gif")
	public AbstractImagePrototype getBlackBishopDark();
	@ImageBundle.Resource(value="ceb2-by.gif")
	public AbstractImagePrototype getBlackBishopLight();
	@ImageBundle.Resource(value="ceb2-ix.gif")
	public AbstractImagePrototype getWhiteBishopDark();
	@ImageBundle.Resource(value="ceb2-iy.gif")
	public AbstractImagePrototype getWhiteBishopLight();
	
	@ImageBundle.Resource(value="ceb2-qx.gif")
	public AbstractImagePrototype getBlackQueenDark();
	@ImageBundle.Resource(value="ceb2-qy.gif")
	public AbstractImagePrototype getBlackQueenLight();
	@ImageBundle.Resource(value="ceb2-ux.gif")
	public AbstractImagePrototype getWhiteQueenDark();
	@ImageBundle.Resource(value="ceb2-uy.gif")
	public AbstractImagePrototype getWhiteQueenLight();
	
	@ImageBundle.Resource(value="ceb2-kx.gif")
	public AbstractImagePrototype getBlackKingDark();
	@ImageBundle.Resource(value="ceb2-ky.gif")
	public AbstractImagePrototype getBlackKingLight();
	@ImageBundle.Resource(value="ceb2-gx.gif")
	public AbstractImagePrototype getWhiteKingDark();
	@ImageBundle.Resource(value="ceb2-gy.gif")
	public AbstractImagePrototype getWhiteKingLight();
}
