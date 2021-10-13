package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.util.List;

public class RoulotteController {

        private MicroRoulotte plan;

        public enum ElementModes {
            HAYON,PLANCHER
        }

        public RoulotteController(MicroRoulotte plan){
            this.plan = plan;
        }

        public RoulotteController() {
            plan = new MicroRoulotte();

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

    public List<Composante> getElementList() {
            return plan.getElementList();
        }

        public int getPlanHeight(){return plan.getHeight();}

        public int getPlanWidth(){return plan.getWidth();}

        public int getPlanSize(){return plan.getSize();}

        public int getNumberOfElements() {
            return plan.getNumberOfElements();
        }

}
