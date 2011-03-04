package com.damiengo.cartalker.communicator.impl;

import java.io.IOException;
import java.io.OutputStream;

import com.damiengo.cartalker.communicator.Writer;
import com.damiengo.cartalker.interpretor.ResponseInterpretor;

public class WriterImpl implements Writer {

    OutputStream out;
    ResponseInterpretor reponseInterpretor;

    public WriterImpl(OutputStream out, ResponseInterpretor reponseInterpretor) {
        this.out = out;
        this.reponseInterpretor = reponseInterpretor;
    }

    public void run() {
        try {
            int c = 0;
            while ((c = System.in.read()) > -1) {
                this.out.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}