package org.rodias.jl;

public abstract class Patch implements Runnable {

    private final int level;

    public Patch(){
        this.level = -1;
    }

    public Patch(int level){
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
