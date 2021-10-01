package ca.ulaval.glo2004.domain;

import java.util.LinkedList;
import java.util.List;

public class Plan {

    private List<Element> elementList;

    public Plan(){
        elementList = new LinkedList<>();
    }

    public void add(Element aElement){
        elementList.add(aElement);
    }

    public boolean isEmpty() {
        return elementList.isEmpty();
    }

    public List<Element> getElementList() {
        return elementList;
    }

    public int getNumberOfElements() {
        return elementList.size();
    }
}
