package com.damiengo.cartalker.test.rxtxserial;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;

import java.util.Enumeration;

public class SerialPortDiscoverer {

    public static void main(String args[]) {
        Enumeration<CommPortIdentifier> thePorts = CommPortIdentifier
                .getPortIdentifiers();
        while (thePorts.hasMoreElements()) {
            CommPortIdentifier com = thePorts.nextElement();
            switch (com.getPortType()) {
            case CommPortIdentifier.PORT_SERIAL:
                try {
                    CommPort thePort = com.open("CommUtil", 50);
                    thePort.close();
                    System.out.println("Port " + com.getName() + " is available.");
                } catch (PortInUseException e) {
                    System.out.println("Port " + com.getName() + " is in use.");
                } catch (Exception e) {
                    System.err.println("Failed to open port " + com.getName());
                    e.printStackTrace();
                }
            }
        }
    }

}