package ca.ulaval.glo2004.domain;

import java.util.LinkedList;
import java.util.List;

public class MicroRoulotte {

    private final int PLAN_HEIGHT = 480;
    private final int PLAN_WIDTH = 960;
    private final int PLAN_SIZE = PLAN_HEIGHT * PLAN_WIDTH;

    private List<Composante> composanteList;

    public MicroRoulotte(){
        composanteList = new LinkedList<>();
    }

    public void add(Composante aComposante){
        composanteList.add(aComposante);
    }

    public void scaleElements(){
        for(Composante aComposante : composanteList) {

        }
    }

    public boolean isEmpty() {
        return composanteList.isEmpty();
    }

    public List<Composante> getElementList() {
        return composanteList;
    }

    public int getHeight() {
        return PLAN_HEIGHT;
    }
    public int getWidth() {
        return PLAN_WIDTH;
    }
    public int getSize(){return PLAN_SIZE;}

    public int getNumberOfElements() {
        return composanteList.size();
    }

}
