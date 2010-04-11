package com.theOpenSourceU.webserver.http;

import java.net.Socket;

import junit.framework.Assert;

import org.junit.Test;

import com.theOpenSourceU.webserver.debugutil.DebugPrintable;

final public class WorkerFactoryTests {
	
	@Test
	public void testNewServerWorker() {
		Socket s = new Socket();
		DebugPrintable dp = new DebugPrintable() {

			@Override
			public void printError(String Message) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void printMessage(String Message) {
				// TODO Auto-generated method stub
				
			}
			
		}
		try {
			WorkerFactory.newServerWorker(null, null, null);
			Assert.fail();
		} catch( IllegalArgumentException a ) { }
		
		try {
			WorkerFactory.newServerWorker(null, "", null);
			Assert.fail();
		} catch( IllegalArgumentException a ) { }
		
		try {
			WorkerFactory.newServerWorker(s, null, null);
			Assert.fail();
		} catch( IllegalArgumentException a ) { }
		
		try {
			WorkerFactory.newServerWorker(null, ".", null);
			Assert.fail();
		} catch( IllegalArgumentException a ) { }
		
		try {
			WorkerFactory.newServerWorker(null, null, dp);
			Assert.fail();
		} catch( IllegalArgumentException a ) { }
		
		Assert.assertTrue( WorkerFactory.newServerWorker(s, ".", dp) != null );
		Assert.assertTrue( WorkerFactory.newServerWorker(s, ".", dp) instanceof Thread );
	}
	
	@Test
	public void testNewListener() {
		Assert.fail();
	}
	
}
