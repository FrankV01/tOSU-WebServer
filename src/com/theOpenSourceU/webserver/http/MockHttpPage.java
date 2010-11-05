package com.theOpenSourceU.webserver.http;

import java.io.InputStream;

final class MockHttpPage implements HttpContent {

	@Override
	public InputStream generate() { return null; }

	@Override
	public String render() throws UnsupportedOperationException { return ""; }

	@Override
	public int size() { return 0; }

	@Override
	public String type() { return "mock"; }

}
