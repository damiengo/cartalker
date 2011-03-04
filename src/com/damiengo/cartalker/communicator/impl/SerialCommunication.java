package com.damiengo.cartalker.communicator.impl;

import com.damiengo.cartalker.communicator.Communication;
import com.damiengo.cartalker.communicator.Reader;
import com.damiengo.cartalker.communicator.Writer;

public class SerialCommunication implements Communication {

    @Override
    public boolean run(Reader reader, Writer writer) {
        return false;
    }

}