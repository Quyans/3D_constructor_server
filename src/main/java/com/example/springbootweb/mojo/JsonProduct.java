package com.example.springbootweb.mojo;

public class JsonProduct {

    private String EngineerId;
    private int EngorderNumber;
    private int PID;

    public String getEngineerId() {
        return EngineerId;
    }

    public void setEngineerId(String engineerId) {
        EngineerId = engineerId;
    }

    public int getEngorderNumber() {
        return EngorderNumber;
    }

    public void setEngorderNumber(int engorderNumber) {
        EngorderNumber = engorderNumber;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }
}
