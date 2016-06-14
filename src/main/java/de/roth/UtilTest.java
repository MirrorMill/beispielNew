package de.roth;

import junit.framework.TestCase;

public class UtilTest extends TestCase {
	
	public void testUtilIstErstesHalbjahr()
	{
		final int eingabe = 9;
		final boolean sollWert = false;
		
		assertEquals(sollWert, Util.istErstesHalbjahr(eingabe));
	}

}
