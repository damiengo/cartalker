package com.damiengo.cartalker.communicator.impl;

import com.damiengo.cartalker.communicator.Parameters;

/**
 * This class contains a serial communication parameters.
 * 
 * @author damiengo
 * @date 04/03/2011
 */
public class SerialParameters extends Parameters {

    /** The bauld rate **/
    private int bauldRate;
    
    /** The databits **/
    private int databits;
    
    /** The stopbits **/
    private int stopbits;
    
    /** The parity **/
    private int parity;
    
    public void setBauldRate(int bauldRate) {
        this.bauldRate = bauldRate;
    }
    
    public void setDatabits(int databits) {
        this.databits = databits;
    }
    
    public void setStopbits(int stopbits) {
        this.stopbits = stopbits;
    }
    
    public void setParity(int parity) {
        this.parity = parity;
    }
    
    public int getBauldRate() {
        return this.bauldRate;
    }
    
    public int getDatabits() {
        return this.databits;
    }
    
    public int getStopbits() {
        return this.stopbits;
    }
    
    public int getParity() {
        return this.parity;
    }
    
}