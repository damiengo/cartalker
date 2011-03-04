package com.damiengo.cartalker;

import com.damiengo.cartalker.communicator.Communication;
import com.damiengo.cartalker.communicator.impl.SerialCommunication;
import com.damiengo.cartalker.communicator.impl.SerialParameters;
import com.damiengo.cartalker.interpretor.impl.QueryOBD;
import com.damiengo.cartalker.interpretor.impl.ResponseOBD;

/**
 * This class creates communicators.
 * 
 * @author damiengo
 * @date 04/03/2011
 */
public class CommunicationFactory {

    /** Unique instance of CommunicationFactory **/
    private static CommunicationFactory instance;

    /**
     * The private constructor.
     */
    private CommunicationFactory() {

    }

    /**
     * Returns the unique instance of CommunicationFactory.
     * 
     * @return CommunicationFactory
     */
    public static CommunicationFactory getInstance() {
        if (CommunicationFactory.instance == null) {
            CommunicationFactory.instance = new CommunicationFactory();
        }

        return CommunicationFactory.instance;
    }

    /**
     * Creates a communicator for OBD on serial port.
     * 
     * @return Communication
     */
    public Communication createSerialOBDCommunicator(String portName,
            int bauldRate, int databits, int stopbits, int parity) {

        SerialParameters parameters = new SerialParameters();
        parameters.setBauldRate(bauldRate);
        parameters.setDatabits(databits);
        parameters.setStopbits(stopbits);
        parameters.setParity(parity);
        Communication communication = new SerialCommunication(portName,
                parameters, new QueryOBD(), new ResponseOBD());

        return communication;
    }

}