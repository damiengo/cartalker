package com.damiengo.cartalker.test.rxtxserial;

/*
 * @(#)SimpleWrite.java 1.12 98/06/25 SMI
 * 
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license
 * to use, modify and redistribute this software in source and binary
 * code form, provided that i) this copyright notice and license appear
 * on all copies of the software; and ii) Licensee does not utilize the
 * software in a manner which is disparaging to Sun.
 * 
 * This software is provided "AS IS," without a warranty of any kind.
 * ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES,
 * INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND
 * ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THE
 * SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS
 * BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES,
 * HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING
 * OUT OF THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * This software is not designed or intended for use in on-line control
 * of aircraft, air traffic, aircraft navigation or aircraft
 * communications; or in the design, construction, operation or
 * maintenance of any nuclear facility. Licensee represents and
 * warrants that it will not use or redistribute the Software for such
 * purposes.
 */
import java.io.*;
import java.util.*;
import gnu.io.*;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version 1.10, 08/04/00
 */
public class HelloWorld {
    static Enumeration portList;
    static CommPortIdentifier portId;
    static String messageString = "ATZ";
    static SerialPort serialPort;
    static OutputStream outputStream;
    static InputStream inputStream;
    static boolean outputBufferEmptyFlag = false;
    static int inte;

    /**
     * Method declaration
     * 
     * 
     * @param args
     * 
     * @see
     */
    public static void main(String[] args) {
        boolean portFound = false;
        // /dev/term/a
        String defaultPort = "COM9";

        if (args.length > 0) {
            defaultPort = args[0];
        }

        portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();

            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {

                if (portId.getName().equals(defaultPort)) {
                    System.out.println("Found port " + defaultPort);

                    portFound = true;

                    try {
                        serialPort = (SerialPort) portId.open("SimpleWrite",
                                2000);
                    } catch (PortInUseException e) {
                        System.out.println("Port in use.");

                        continue;
                    }

                    try {
                        outputStream = serialPort.getOutputStream();
                        inputStream = serialPort.getInputStream();
                    } catch (IOException e) {
                    }

                    try {
                        serialPort.setSerialPortParams(9600,
                                SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE);
                    } catch (UnsupportedCommOperationException e) {
                    }

                    try {
                        serialPort.notifyOnOutputEmpty(true);
                    } catch (Exception e) {
                        System.out.println("Error setting event notification");
                        System.out.println(e.toString());
                        System.exit(-1);
                    }

                    System.out.println("Writing \"" + messageString + "\" to "
                            + serialPort.getName());

                    try {
                        outputStream.write(messageString.getBytes());
                        while((inte = inputStream.read()) != -1) {
                            System.out.println("Integer: "+inte);
                        }
                        
                    } catch (IOException e) {
                    }

                    try {
                        Thread.sleep(2000); // Be sure data is xferred before
                                            // closing
                    } catch (Exception e) {
                    }
                    serialPort.close();
                    System.exit(1);
                }
            }
        }

        if (!portFound) {
            System.out.println("port " + defaultPort + " not found.");
        }
    }

}
