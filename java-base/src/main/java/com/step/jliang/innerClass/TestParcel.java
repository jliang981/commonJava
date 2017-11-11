package com.step.jliang.innerClass;


/**
 * Created by jliang on 17/5/2.
 */

interface Destination {
    String readLabel();
}

class Parcel4 {

    protected class PDestination implements Destination {
        private String label;
        private PDestination(String whereTo) {
            label = whereTo;
        }
        public String readLabel() { return label; }
    }
    public Destination destination(String s) {
        return new PDestination(s);
    }

    public static void main(String[] args) {
        Parcel4 p = new Parcel4();
        Destination d = p.destination("Tasmania");
        System.out.println(d.readLabel());
        // Illegal -- can't access private class:
        //! Parcel4.PContents pc = p.new PContents();
    }
}
