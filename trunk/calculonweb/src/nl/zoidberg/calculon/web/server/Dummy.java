package nl.zoidberg.calculon.web.server;

import nl.zoidberg.calculon.model.Board;
import nl.zoidberg.calculon.notation.FENUtils;

public class Dummy {

	public static void main(String[] args) throws Exception {
		System.out.println(FENUtils.generate(new Board().initialise()));
	}
}
