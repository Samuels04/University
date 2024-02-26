/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Samuel
 */
public class Element {
    private String name;
    private String symbol;
    private int atomicNumber;
    private double massNumber;
    private boolean favourite;
    private String latinName;
    private int yearDiscovered;
    //private String group;
    
    
    
    public Element(String name, int atomicNumber, double massNumber, String symbol, String latinName, int yearDiscovered) {
    
        this.name = name;
        this.symbol = symbol;
        this.atomicNumber = atomicNumber;
        this.massNumber = massNumber;
        this.favourite = false;
        this.latinName = latinName;
        this.yearDiscovered = yearDiscovered;
    
    }
    
    public String getName() {
        return this.name;
    }
    public int getAtomicNumber(){
        return this.atomicNumber;
    }
    public double getMassNumber(){
        return this.massNumber;
    }
    public String getSymbol(){
        return this.symbol;
    }
    
    public int getYearDiscovered(){
        return this.yearDiscovered;
    }
    
    public String getYearDiscoveredString(){
        if(this.yearDiscovered < 0){
            return String.format("%d B.C", Math.abs(this.yearDiscovered));
        }
        return String.format("%d A.D", this.yearDiscovered);
    }
    
    public String getLatinName(){
        return this.latinName;
    }
    
    public void favourite(){
        favourite = true;
    }
    public void unfavourite(){
        favourite = false;
    }
    public boolean isFavourite(){
        return favourite;
    }
}
