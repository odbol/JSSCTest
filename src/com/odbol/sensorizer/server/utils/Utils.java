package com.odbol.sensorizer.server.utils;

import java.io.File;


public class Utils {

	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0 || s.trim().length() == 0;
	}
	
	
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	/***
	 * Converts a byte array to a hex string.
	 * 
	 * From http://stackoverflow.com/questions/9655181/convert-from-byte-array-to-hex-string-in-java
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public static String byteToHex(byte inputData) {
		return bytesToHex(new byte[] { inputData });
	}
	
	/***
	 * Makes JSON string safe for passing through WebSockets.
	 * 
	 * @param json
	 * @return
	 */
	public static String ensafenJson(String json) {
		return json.replaceAll("\n", " ");
	}

	/***
	 * Does the most obvious thing ever, which somehow Java forgot about.
	 * 
	 * @param command
	 * @param delim
	 * @return
	 */
	public static String join(String[] command, String delim) {
		StringBuilder res = new StringBuilder(command.length * 5);

		for (String s : command) {
			if (res.length() > 0) {
				res.append(delim);
			}
			
			res.append(s);
		}
		
		return res.toString();
	}

	public static String getCleanAbsolutePath(final File bossac) {
		return bossac.toString().replaceFirst("file://", "");
	}

	
}
