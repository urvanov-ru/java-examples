package ru.urvanov.javaexamples.jackson.polymorphicdeserialization;

public class Vampire extends Monster {
    
    private int bloodCollected;

    public int getBloodCollected() {
        return bloodCollected;
    }

    public void setBloodCollected(int bloodCollected) {
        this.bloodCollected = bloodCollected;
    }
    
}
