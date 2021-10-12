package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;
import java.util.zip.ZipEntry;

public class PlanController {

        private Plan plan;

        public enum ElementModes {
            HAYON,PLANCHER
        }

        public PlanController (Plan plan){
            this.plan = plan;
        }

        public PlanController() {
            plan = new Plan();

        }

        private void addPlancher(Point point) {
            Plancher newPlancher = new Plancher(point);
            plan.add(newPlancher);
        }

        private void addHayon(Point point) {
            Hayon newHayon = new Hayon(point);
            plan.add(newHayon);
        }

        public void addElement(ElementModes planMode, Point mousePoint){
            Point gridPoint = new Point((int) mousePoint.getX(), (int) mousePoint.getY());
            System.out.format("Point: (%d, %d):", gridPoint.x, gridPoint.y);
            if(planMode==ElementModes.HAYON){
                addHayon(gridPoint);
            }
            else if (planMode==ElementModes.PLANCHER) {
                addPlancher(gridPoint);
            }
        }

    public List<Element> getElementList() {
            return plan.getElementList();
        }

        public int getPlanHeight(){return plan.getHeight();}

        public int getPlanWidth(){return plan.getWidth();}

        public int getPlanSize(){return plan.getSize();}

        public int getNumberOfElements() {
            return plan.getNumberOfElements();
        }

}
