package nl.zoidberg.calculon.web.client.images.iyt1;

import nl.zoidberg.calculon.web.client.BoardImageBundle;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

public interface IYT1BoardImageBundle extends ImageBundle, BoardImageBundle {
	
	@ImageBundle.Resource(value="cfb2-0x.gif")
	public AbstractImagePrototype getEmptyDark();
	@ImageBundle.Resource(value="cfb2-0y.gif")
	public AbstractImagePrototype getEmptyLight();
	
	@ImageBundle.Resource(value="cfb2-px.gif")
	public AbstractImagePrototype getBlackPawnDark();
	@ImageBundle.Resource(value="cfb2-py.gif")
	public AbstractImagePrototype getBlackPawnLight();
	@ImageBundle.Resource(value="cfb2-ax.gif")
	public AbstractImagePrototype getWhitePawnDark();
	@ImageBundle.Resource(value="cfb2-ay.gif")
	public AbstractImagePrototype getWhitePawnLight();

	@ImageBundle.Resource(value="cfb2-rx.gif")
	public AbstractImagePrototype getBlackRookDark();
	@ImageBundle.Resource(value="cfb2-ry.gif")
	public AbstractImagePrototype getBlackRookLight();
	@ImageBundle.Resource(value="cfb2-ox.gif")
	public AbstractImagePrototype getWhiteRookDark();
	@ImageBundle.Resource(value="cfb2-oy.gif")
	public AbstractImagePrototype getWhiteRookLight();
	
	@ImageBundle.Resource(value="cfb2-nx.gif")
	public AbstractImagePrototype getBlackKnightDark();
	@ImageBundle.Resource(value="cfb2-ny.gif")
	public AbstractImagePrototype getBlackKnightLight();
	@ImageBundle.Resource(value="cfb2-tx.gif")
	public AbstractImagePrototype getWhiteKnightDark();
	@ImageBundle.Resource(value="cfb2-ty.gif")
	public AbstractImagePrototype getWhiteKnightLight();
	
	@ImageBundle.Resource(value="cfb2-bx.gif")
	public AbstractImagePrototype getBlackBishopDark();
	@ImageBundle.Resource(value="cfb2-by.gif")
	public AbstractImagePrototype getBlackBishopLight();
	@ImageBundle.Resource(value="cfb2-ix.gif")
	public AbstractImagePrototype getWhiteBishopDark();
	@ImageBundle.Resource(value="cfb2-iy.gif")
	public AbstractImagePrototype getWhiteBishopLight();
	
	@ImageBundle.Resource(value="cfb2-qx.gif")
	public AbstractImagePrototype getBlackQueenDark();
	@ImageBundle.Resource(value="cfb2-qy.gif")
	public AbstractImagePrototype getBlackQueenLight();
	@ImageBundle.Resource(value="cfb2-ux.gif")
	public AbstractImagePrototype getWhiteQueenDark();
	@ImageBundle.Resource(value="cfb2-uy.gif")
	public AbstractImagePrototype getWhiteQueenLight();
	
	@ImageBundle.Resource(value="cfb2-kx.gif")
	public AbstractImagePrototype getBlackKingDark();
	@ImageBundle.Resource(value="cfb2-ky.gif")
	public AbstractImagePrototype getBlackKingLight();
	@ImageBundle.Resource(value="cfb2-gx.gif")
	public AbstractImagePrototype getWhiteKingDark();
	@ImageBundle.Resource(value="cfb2-gy.gif")
	public AbstractImagePrototype getWhiteKingLight();
}
