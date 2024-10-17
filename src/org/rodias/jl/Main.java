package org.rodias.jl;

public class Main {
    public static PatchProcessor processor = PatchProcessor.INSTANCE;
    public static void main(String[] args) {
        System.out.println("entered");
        var conf = new Conf();
        processor.runPatches();
    }

}
class Conf extends Configuration{

    Conf(){
        super();
    }

    @PatchBean(level = 2)
    public void level1(){
        System.out.println("Level 1");
    }

    @PatchBean(level = 1)
    public void level2(){
        System.out.println("Level 2");
    }
}

