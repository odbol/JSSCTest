package com.odbol.jssctest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.odbol.sensorizer.server.utils.Utils;

import jssc.SerialPortException;
import junit.framework.TestCase;

/**
 * This tests an Arduino for corrupted Sysex responses.
 * You must have an Arduino plugged in when you start it.
 * 
 * @author tyler
 *
 */
public class ArduinoJSSCTest extends TestCase {

	public static final int MAX_RETRIES = 55;
	private static final long TIMEOUT = MAX_RETRIES * 200;
	
	private static final byte END_SYSEX = (byte) 0xF7;
	private static final byte START_SYSEX = (byte) 0xF0;
	
	List<Byte> curSysex;
	private List<List<Byte>> previousSysexes = new CopyOnWriteArrayList<List<Byte>>();
	private byte[] requestUpdatePacket = {
				(byte)(0xBF), 0x7B, 0x01
			};
	
	private ArduinoJSSC dev;

	protected void setUp() throws Exception {
		super.setUp();
		
		connect();
	}
	
	private void connect() {
        String port = ArduinoJSSC.findNextAvailablePort(null);

    	dev = new ArduinoJSSC(port) {
    		@Override
    		protected synchronized void onDataAvailable(byte[] buffer) {
    			System.err.println("-");
    			
				for (byte b : buffer) {
					parseByte(b);
				}
    		}
    	};
    	
    	dev.initialize(115200);
	}
	
	private void disconnect() {
		dev.close();
	}

	protected void tearDown() throws Exception {
		disconnect();
		
		super.tearDown();
	}

	private synchronized void parseByte(byte b) {
		System.err.print(">" + Utils.byteToHex(b));
		//int b = SensorConfig.safelyConvertByteToInt(origByte);
		
		if (curSysex != null) {
			
			assertTrue(curSysex.size() < 4096);
			
			if (b == END_SYSEX) {
				finishSysex(curSysex);
				curSysex = null;
			}
			else {
				curSysex.add(b);
			}
		}
		else {
			if (b == START_SYSEX) {
				System.err.println("\nStart Sysex");
				curSysex = new ArrayList<Byte>(530);
			}
			else {
				System.err.print(".");
			}
		}
	}
	
	private void finishSysex(List<Byte> sysex) {
		System.err.println("\nfinishSysex of length " + sysex.size());
		
		previousSysexes.add(sysex);
	}

	public void testSysexResponses() throws SerialPortException {
		long timeoutTime = System.currentTimeMillis() + TIMEOUT;
		
    	int lastSize = -1;
		int curSize = previousSysexes.size();
    	while(curSize < MAX_RETRIES) {
    		assertTrue(System.currentTimeMillis() < timeoutTime);
    		
    		if (lastSize != curSize) {
    			System.err.println("\nRequesting update " + lastSize + ": " + Utils.bytesToHex(requestUpdatePacket));
    			dev.sendData(requestUpdatePacket);
    		}
    		
    		lastSize = curSize;
    		curSize = previousSysexes.size();
    	}
    	
    	for (int i = 1; i < previousSysexes.size(); i++) {
    		System.err.println("\nVerifying sysex " + i);
			
    		List<Byte> lastInputData = previousSysexes.get(i-1);
    		List<Byte> newInputData = previousSysexes.get(i);
    		
    		assertArraysEqual(lastInputData, newInputData);
    	}
    	

		System.err.println("\nNo errors! Try again...");
	}
	
	public static void assertArraysEqual(List<Byte> one,
			List<Byte> two) {
		Byte[] lastInputData = one.toArray(new Byte[one.size()]); 
		Byte[] newInputData = two.toArray(new Byte[two.size()]);
		
		boolean eq = Arrays.equals(lastInputData, newInputData);
		
		if (!eq) {
			System.err.println("\nERROR: Arrays not equal:\n" +
					Arrays.toString(lastInputData) + "\n" +
					Arrays.toString(newInputData));
//				
//				for (int i = 0; i < lastInputData.length; i++) {
//					System.err.print(lastInputData[i])
//				}
			
		}
		
		assertTrue(eq);
	}
	
	
	
	
	public static void main(String[] args) {
		System.err.println("\nRunning tests");
			
		ArduinoJSSCTest t = new ArduinoJSSCTest();
		
		try {
			System.err.println("Connecting");
			t.connect();

			System.err.println("Testing");
			t.testSysexResponses();

			System.err.println("Disconnecting");
			t.disconnect();
		}
		catch (Exception e) {

			System.err.println("\nTest failed: ");
			e.printStackTrace();
		}
		
		System.err.println("\nFinished tests");
	}
}
