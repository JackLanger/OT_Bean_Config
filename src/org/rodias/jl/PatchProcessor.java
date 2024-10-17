package org.rodias.jl;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public enum PatchProcessor {
INSTANCE;
    private  final Set<Configuration> CONFIGURATION_REGISTRY = new HashSet<>();
    private  final Set<Patch> patches = new TreeSet<>(Comparator.comparing(Patch::getLevel));

    public  void register(Configuration config){
        CONFIGURATION_REGISTRY.add(config);
        collectPatches(config);
    }

    public void runPatches(){
        for (Patch patch : patches) {
            patch.run();
        }
    }

    public  void collectAllPatches(){
        for(var conf : CONFIGURATION_REGISTRY){
            collectPatches(conf);
        }
    }

    public  void collectPatches(Configuration conf){
            for (var method :conf.getClass().getMethods()){
                var annotation = method.getAnnotation(PatchBean.class);
                if(annotation instanceof PatchBean pb){

                    patches.add(new Patch(pb.level()) {
                        @Override
                        public void run() {
                            try {
                                method.invoke(conf);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            }

    }

}
