package com.odbol.jssctest;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

import com.odbol.sensorizer.devices.Debug;
import com.odbol.sensorizer.server.utils.Utils;
/**
 * @author tyler
 */

public class ArduinoJSSC implements SerialPortEventListener {
	/***
     * Must call initialize() to actually connect to the serial port.
     * 
     * @param deviceName Name of the port to connect to.
     */
	// wait a bit since otherwise you get a "port open" exception if trying to open directly after calling getPortNames().
	// Try to connect to the Arduino on this port
	// Open serial port
	// add event listeners
	@java.lang.SuppressWarnings("all")
	private final java.lang.Object $lock = new java.lang.Object[0];
	private SerialPort serialPort = null;
	private String portName;
	
	public ArduinoJSSC(String deviceName) {
		this.portName = deviceName;
	}
	
	public boolean initialize(int dataRate) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException ie) {
		}
		serialPort = new SerialPort(portName);
		try {
			if (!(serialPort.openPort() && serialPort.setParams(dataRate, 8, 1, 0))) {
				throw new SerialPortException(portName, "initialize", "Failed to open port at " + dataRate);
			}
			serialPort.addEventListener(this);
		} catch (SerialPortException ex) {
			Debug.error("Failed to open serial port.", ex);
			return false;
		}
		Debug.debug("Connected to serial port." + portName);
		return true;
	}
	
	public void sendData(String data) throws SerialPortException {
		synchronized (this.$lock) {
			if (!serialPort.writeString(data)) {
				throw new SerialPortException(portName, "sendData", "Failed to write data");
			}
		}
	}
	
	public void sendData(int data) throws SerialPortException {
		synchronized (this.$lock) {
			if (!serialPort.writeInt(data)) {
				throw new SerialPortException(portName, "sendData", "Failed to write data");
			}
		}
	}
	
	public void sendData(byte[] data) throws SerialPortException {
		synchronized (this.$lock) {
			if (!serialPort.writeBytes(data)) {
				throw new SerialPortException(portName, "sendData", "Failed to write data");
			}
		}
	}
	//
	// This should be called when you stop using the port
	//
	public void close() {
		synchronized (this.$lock) {
			if (serialPort != null) {
				try {
					serialPort.removeEventListener();
					serialPort.closePort();
				} catch (SerialPortException e) {
					Debug.error("Failed to close serial port", e);
				}
			}
		}
	}
	//
	// Handle serial port event
	//
	@Override
	public void serialEvent(SerialPortEvent event) {
		if (event.isRXCHAR()) {
			//If data is available
			final int bytesAvailable = event.getEventValue();
			if (bytesAvailable >= 1) {
				//Check bytes count in the input buffer
				//Read data
				try {
					byte[] buffer = serialPort.readBytes(bytesAvailable);
					onDataAvailable(buffer);
				} catch (SerialPortException ex) {
					Debug.error("Failed to read serial port data.", ex);
				}
			}
		} else if (event.isERR()) {
			Debug.error("Failed to read serial port data: serialEvent error: " + event.getEventValue());
		}
	}
	/***
	 * Override this function with your own data processing.
	 * @param buffer The bytes received over the port.
	 * @throws IOException
	 */
	protected void onDataAvailable(byte[] buffer) {
		synchronized (this.$lock) {
			Debug.debug("read chars: " + Utils.bytesToHex(buffer));
		}
	}
	
	public static String[] list() {
		return SerialPortList.getPortNames();
	}
	
	 
	/***
	 * Finds a valid Arduino device appearing BEFORE the given port and returns its port name. 
	 * Returns null if no device found, or if there are no more ports before this one.
	 * 
	 * @param currentPort The reference port to find the next. 
	 * @return
	 */
	  public static String findNextAvailablePort(String currentPort) {
		  String[] ports = ArduinoJSSC.list();
		  
		  // go backwards because Arduino tends to start in the higher range (COM1 is usually something else.)
		  // TODO: this is a horrible way to do this. should detect via version request, or some other way when blank chip.
		  boolean pastCurrentPort = false;
		  for (int i = ports.length - 1; i >= 0; i--) {
			  String port = ports[i];
			  
			  if (currentPort != null && !pastCurrentPort) {
				  
				  if (currentPort.equals(port)) {
					  pastCurrentPort = true;
				  }
				  
				  continue;
			  }
			  
			  if (	port.contains("/dev/tty.usbmodem") ||
					port.contains("COM")) {
				  return port;
			  }
		  }
		  
		  return null;
	  }
}