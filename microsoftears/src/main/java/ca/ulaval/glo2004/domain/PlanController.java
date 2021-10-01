package ca.ulaval.glo2004.domain;

import java.awt.Point;
import java.util.List;

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

        private void addPlancher(Point mousePoint) {
            Plancher newPlancher = new Plancher(mousePoint);
            plan.add(newPlancher);
        }

        private void addHayon(Point mousePoint) {
            Hayon newHayon = new Hayon(mousePoint);
            plan.add(newHayon);
        }

        public void addElement(ElementModes planMode, Point mousePoint){
            if(planMode==ElementModes.HAYON){
                addHayon(mousePoint);
            }
            else {
                addPlancher(mousePoint);
            }
        }

        public List<Element> getElementList() {
            return plan.getElementList();
        }

        public int getNumberOfElements() {
            return plan.getNumberOfElements();
        }

}
