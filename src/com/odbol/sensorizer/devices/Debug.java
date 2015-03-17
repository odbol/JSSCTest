package com.odbol.sensorizer.devices;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
import java.io.*;
import java.util.logging.Logger;


public class Debug {
	private static boolean isEnabled = false;
	
	public static final int NONE = 0;
	public static final int OSC = 1;
	public static final byte DEBUG = 2;

	public static final int ERROR = -2;
	
	public static int printLevel = 0;
	
	public static Logger logger = Logger.getLogger("");
	private static Handler handler;

	public static final void print(int level, String msg) {
		if (level <= printLevel) {
			Level logLevel;
			
			if (level == ERROR) {
				logLevel = Level.SEVERE;
			}
			else {
				logLevel = Level.INFO;
			}
			
			
			//System.err.println(msg);
			logger.log(logLevel, msg);
		}
	}

	public static boolean isEnabled() {
		return isEnabled;
	}

	public static void setEnabled(boolean isEnabled) {
		Debug.isEnabled = isEnabled;
		
		  if (isEnabled){ 
			  Debug.printLevel = Debug.DEBUG;
			  //ControlP5.logger().setLevel(Level.ALL);
		  }
		  else {
			  Debug.printLevel = Debug.NONE;
			  //ControlP5.logger().setLevel(Level.SEVERE);
		  }
	}

	public static void error(String string) {
		error(string, null);
	}
	
	public static void error(String string, Exception e) {
		String err = string;
		
		if (e != null) {
			String msg = e.getMessage();
			if (msg != null) {
				err = err + " " + msg;
			}
			else {
				err = err + " " + e.toString();
			}
		}
		
		print(ERROR, err);
	}

	public static void debug(String string) {
		print(DEBUG, string);
	}
}
