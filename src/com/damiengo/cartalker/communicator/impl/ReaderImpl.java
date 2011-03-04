package com.damiengo.cartalker.communicator.impl;

import java.io.IOException;
import java.io.InputStream;

import com.damiengo.cartalker.communicator.Reader;
import com.damiengo.cartalker.interpretor.QueryInterpretor;

public class ReaderImpl implements Reader {
    
    InputStream in;
    ResponseInterpretor queryInterpretor;

    public ReaderImpl(InputStream in, ResponseInterpretor queryInterpretor) {
        this.in = in;
        this.queryInterpretor = queryInterpretor;
    }

    public void run() {
        byte[] buffer = new byte[1024];
        int len = -1;
        try {
            while ((len = this.in.read(buffer)) > -1) {
                this.queryInterpretor.process(new String(buffer, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}