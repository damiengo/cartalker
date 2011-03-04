package com.damiengo.cartalker.communicator.impl;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.damiengo.cartalker.communicator.Communication;
import com.damiengo.cartalker.communicator.Parameters;
import com.damiengo.cartalker.interpretor.QueryInterpretor;
import com.damiengo.cartalker.interpretor.ResponseInterpretor;
import com.damiengo.cartalker.interpretor.impl.QueryOBD;
import com.damiengo.cartalker.interpretor.impl.ResponseOBD;

public class SerialCommunication implements Communication {
    
    private InputStream in;
    private OutputStream out;
    
    private QueryInterpretor query;
    private ResponseInterpretor response;
    
    public SerialCommunication(String portName, Parameters parameters, QueryInterpretor query, ResponseInterpretor response) {
        try {
            CommPortIdentifier portIdentifier;
            portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            SerialParameters serialParams = (SerialParameters) parameters;
            if (portIdentifier.isCurrentlyOwned()) {
                System.out.println("Error: Port is currently in use");
            } else {
                CommPort commPort = portIdentifier.open(this.getClass()
                        .getName(), 2000);

                if (commPort instanceof SerialPort) {
                    SerialPort serialPort = (SerialPort) commPort;
                    serialPort.setSerialPortParams(serialParams.getBauldRate(), serialParams.getDatabits(),
                            serialParams.getStopbits(), serialParams.getParity());

                    this.in = serialPort.getInputStream();
                    this.out = serialPort.getOutputStream();
                    
                    this.query = query;                    
                    this.response = response;
                } else {
                    System.out
                            .println("Error: Only serial ports are handled by this example.");
                }
            }
        } catch (NoSuchPortException e) {
            e.printStackTrace();
        } catch (PortInUseException e) {
            e.printStackTrace();
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Runs the communication.
     */
    public void run() {
        (new Thread(new ReaderImpl(this.in, this.response))).start();
        (new Thread(new WriterImpl(this.out, this.query))).start();
    }

}