package sample;

import java.awt.*;

//All the variables used
public class ShippingData {
    String fromport;
    String toport;
    String vessel;
    Integer flow;
    String capacity;

    public ShippingData(String fromport, String toport, String vessel, Integer flow, String capacity) {
        this.fromport = fromport;
        this.toport = toport;
        this.vessel = vessel;
        this.flow = flow;
        this.capacity = capacity;
    }

    public ShippingData(String fromportname, String toportname, String capacityCount) {
        fromport = fromportname;
        toport = toportname;
        capacity = capacityCount;
    }

    public String getFromPortName() {
        return fromport;
    }

    public String getToPortName() {
        return toport;
    }

    //Converts values to string
    @Override
    public String toString() {
        return fromport + " " + toport + " " + vessel + " " + flow + " " + capacity;
    }
}