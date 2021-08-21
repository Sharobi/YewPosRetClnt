package com.sharobi.yewpos.util;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

/**
 * @author Manodip Jana
 *
 */
public class ReadingPorts implements SerialPortEventListener {

	public static final ReadingPorts display = new ReadingPorts();

	private ReadingPorts() {}

	public static ReadingPorts getInstance() {
		return display;
	}

	public  double getQty() {
		getReading();
		return qty;
	}

	static CommPortIdentifier portId;
    static Enumeration portList;
    static SerialPort port;
    static InputStream inputStream;
    static OutputStream outputStream;
    //static Thread readThread;
    static byte buffer[];
    static BufferedReader br;
    public static double qty=0.00;
    boolean inside=true;
    static String defaultPort = "";
    static Integer baudRate = 0;

	public static Integer getBaudRate() {
		return baudRate;
	}

	public static void setBaudRate(Integer baudRate) {
		ReadingPorts.baudRate = baudRate;
	}

	/**
     * @param args the command line arguments
     */
  static int idz=0;

 /*   public  ReadingPorts() {
    	portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals(defaultPort)) {
                    if (!portId.isCurrentlyOwned()) {
                    	//System.out.println("defaultPort="+defaultPort);
                    	try {
                            port = (SerialPort) portId.open("Custom", 500);
                            inputStream = port.getInputStream();
                            br = new BufferedReader(new InputStreamReader(inputStream));
                          //  System.out.println("** Connected To  **" + defaultPort);
                            port.addEventListener(this);
                            port.notifyOnDataAvailable(true);
                            port.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                            port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
                            port.enableReceiveTimeout(500);
                          //  System.out.println("................................");
                            readThread = new Thread(this);
                            readThread.start();
                            idz++;
                            readThread.setName("t"+idz);

                            System.out.println("---------------"+readThread.getName()+idz);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    } else {
                       // System.out.println("This port is already used by some other program");
                    }

                }
            }
        }

    }*/

    private static void getReading()
    {
    	/*
    	try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
    	portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals(defaultPort)) {
                    if (!portId.isCurrentlyOwned()) {
                    	//System.out.println("defaultPort="+defaultPort);
                    	try {
                            port = (SerialPort) portId.open("Custom", 500);
                            inputStream = port.getInputStream();
                            br = new BufferedReader(new InputStreamReader(inputStream));
                          //  System.out.println("** Connected To  **" + defaultPort);
                            port.addEventListener(display);
                            port.notifyOnDataAvailable(true);
                            port.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                            port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
                            port.enableReceiveTimeout(500);
                          //  System.out.println("................................");
                           /* readThread = new Thread(this);
                            readThread.start();
                            idz++;
                            readThread.setName("t"+idz);

                            System.out.println("---------------"+readThread.getName()+idz);*/
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        break;

                    } else {
                       // System.out.println("This port is already used by some other program");
                    }

                }
            }
        }
    }


    public static String getDefaultPort() {
		return defaultPort;
	}

	public static void setDefaultPort(String defaultPort) {
		ReadingPorts.defaultPort = defaultPort;
	}

	public void serialEvent(SerialPortEvent event) {

        switch (event.getEventType()) {

            case SerialPortEvent.DATA_AVAILABLE:
                buffer = new byte[8];
                int numBytes=-1;
                try {

                	while (inputStream.available() > 0) {
                      numBytes = inputStream.read(buffer);
                    //  System.out.println("----------------"+new String(buffer,0,numBytes));
//                        getOrderQuantity(new String(buffer,0,numBytes));
                      String str = new String(buffer,0,numBytes);
                      str = str.replace('\n',' ');
                      if(str.trim().length() > 4){
                      try{
                              qty = Double.parseDouble(str);
                          //   System.out.println("---------------     " + new String(buffer,0,numBytes) + "  ---- data: " + qty);
                      }catch(Exception ex){

                      }

                      }
//
                    }
//                    System.out.println(new String(buffer,0,numBytes));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            }
    }




}

