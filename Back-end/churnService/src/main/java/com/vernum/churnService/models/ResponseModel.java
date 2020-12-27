package com.vernum.churnService.models;

import java.util.List;
import java.util.Map;

public class ResponseModel {

    private List<Map<String,Object>> payload;

    public ResponseModel(List<Map<String,Object>> payload) {
        this.payload = payload;
    }

    public List<Map<String,Object>> getPayload() {
        return payload;
    }

    public void setPayload(List<Map<String,Object>> payload) {
        this.payload = payload;
    }
}
