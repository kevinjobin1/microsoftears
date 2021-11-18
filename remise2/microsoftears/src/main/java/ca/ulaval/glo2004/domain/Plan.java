package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Plan {

    private final int PLAN_HEIGHT = 480;
    private final int PLAN_WIDTH = 960;
    private final int PLAN_SIZE = PLAN_HEIGHT * PLAN_WIDTH;

    private List<Element> elementList;

    public Plan(){
        elementList = new LinkedList<>();
    }

    public void add(Element aElement){
        elementList.add(aElement);
    }

    public void scaleElements(){
        for(Element aElement : elementList) {

        }
    }

    public boolean isEmpty() {
        return elementList.isEmpty();
    }

    public List<Element> getElementList() {
        return elementList;
    }

    public int getHeight() {
        return PLAN_HEIGHT;
    }
    public int getWidth() {
        return PLAN_WIDTH;
    }
    public int getSize(){return PLAN_SIZE;}

    public int getNumberOfElements() {
        return elementList.size();
    }

}
