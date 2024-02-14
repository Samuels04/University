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
    //private String group;
    
    
    
    public Element(String name, int atomicNumber, double massNumber, String symbol) {
    
        this.name = name;
        this.symbol = symbol;
        this.atomicNumber = atomicNumber;
        this.massNumber = massNumber;
        this.favourite = false;
    
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
