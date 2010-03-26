package com.theOpenSourceU.webserver.http;


import junit.framework.Assert;

import org.junit.Test;

public final class StringUtilsTests {

	@Test
	public void testRepeat() {
		final String _dash = "-----";
		final String _equals = "=====";
		
		Assert.assertEquals(_dash, StringUtils.repeat('-', 5));
		Assert.assertEquals(_equals, StringUtils.repeat('=', 5));
	}
	
}
