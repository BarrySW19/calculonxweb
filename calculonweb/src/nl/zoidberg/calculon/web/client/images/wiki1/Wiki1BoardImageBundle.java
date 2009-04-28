package nl.zoidberg.calculon.web.client.images.wiki1;

import nl.zoidberg.calculon.web.client.BoardImageBundle;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

public interface Wiki1BoardImageBundle extends ImageBundle, BoardImageBundle {
	
	@ImageBundle.Resource(value="Chess_d40.png")
	public AbstractImagePrototype getEmptyDark();
	@ImageBundle.Resource(value="Chess_l40.png")
	public AbstractImagePrototype getEmptyLight();
	
	@ImageBundle.Resource(value="Chess_pdd40.png")
	public AbstractImagePrototype getBlackPawnDark();
	@ImageBundle.Resource(value="Chess_pdl40.png")
	public AbstractImagePrototype getBlackPawnLight();
	@ImageBundle.Resource(value="Chess_pld40.png")
	public AbstractImagePrototype getWhitePawnDark();
	@ImageBundle.Resource(value="Chess_pll40.png")
	public AbstractImagePrototype getWhitePawnLight();

	@ImageBundle.Resource(value="Chess_rdd40.png")
	public AbstractImagePrototype getBlackRookDark();
	@ImageBundle.Resource(value="Chess_rdl40.png")
	public AbstractImagePrototype getBlackRookLight();
	@ImageBundle.Resource(value="Chess_rld40.png")
	public AbstractImagePrototype getWhiteRookDark();
	@ImageBundle.Resource(value="Chess_rll40.png")
	public AbstractImagePrototype getWhiteRookLight();
	
	@ImageBundle.Resource(value="Chess_ndd40.png")
	public AbstractImagePrototype getBlackKnightDark();
	@ImageBundle.Resource(value="Chess_ndl40.png")
	public AbstractImagePrototype getBlackKnightLight();
	@ImageBundle.Resource(value="Chess_nld40.png")
	public AbstractImagePrototype getWhiteKnightDark();
	@ImageBundle.Resource(value="Chess_nll40.png")
	public AbstractImagePrototype getWhiteKnightLight();
	
	@ImageBundle.Resource(value="Chess_bdd40.png")
	public AbstractImagePrototype getBlackBishopDark();
	@ImageBundle.Resource(value="Chess_bdl40.png")
	public AbstractImagePrototype getBlackBishopLight();
	@ImageBundle.Resource(value="Chess_bld40.png")
	public AbstractImagePrototype getWhiteBishopDark();
	@ImageBundle.Resource(value="Chess_bll40.png")
	public AbstractImagePrototype getWhiteBishopLight();
	
	@ImageBundle.Resource(value="Chess_qdd40.png")
	public AbstractImagePrototype getBlackQueenDark();
	@ImageBundle.Resource(value="Chess_qdl40.png")
	public AbstractImagePrototype getBlackQueenLight();
	@ImageBundle.Resource(value="Chess_qld40.png")
	public AbstractImagePrototype getWhiteQueenDark();
	@ImageBundle.Resource(value="Chess_qll40.png")
	public AbstractImagePrototype getWhiteQueenLight();
	
	@ImageBundle.Resource(value="Chess_kdd40.png")
	public AbstractImagePrototype getBlackKingDark();
	@ImageBundle.Resource(value="Chess_kdl40.png")
	public AbstractImagePrototype getBlackKingLight();
	@ImageBundle.Resource(value="Chess_kld40.png")
	public AbstractImagePrototype getWhiteKingDark();
	@ImageBundle.Resource(value="Chess_kll40.png")
	public AbstractImagePrototype getWhiteKingLight();
}
