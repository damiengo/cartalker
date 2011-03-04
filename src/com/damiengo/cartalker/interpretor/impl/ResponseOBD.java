package com.damiengo.cartalker.interpretor.impl;

import com.damiengo.cartalker.interpretor.ResponseInterpretor;

public class ResponseOBD implements ResponseInterpretor {

    @Override
    public boolean process(String message) {
        System.out.println(message);
        
        return true;
    }

}