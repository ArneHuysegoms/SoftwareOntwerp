package figures;

import canvascomponents.diagram.*;
import canvascomponents.diagram.Object;
import figures.Drawer.*;
import figures.Drawer.DiagramSpecificDrawers.CommunicationActorDrawer;
import figures.Drawer.DiagramSpecificDrawers.CommunicationMessageDrawer;
import figures.Drawer.DiagramSpecificDrawers.SequenceActorDrawer;
import figures.Drawer.DiagramSpecificDrawers.SequenceMessageDrawer;
import figures.helperClasses.Pair;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FigureConverter {
    private static FigureConverter instance = null;

    private Drawer actorDrawingStrategy;
    private Drawer boxDrawingStrategy;
    private Drawer messageDrawingStrategy;

    private FigureConverter() {

    }

    public static FigureConverter getInstance() {
        if (instance == null)
            instance = new FigureConverter();
        return instance;
    }


    public void draw(Graphics graphics, Diagram diagram) {
        boxDrawingStrategy = BoxDrawer.getInstance();

        if (diagram instanceof SequenceDiagram) {
            actorDrawingStrategy = SequenceActorDrawer.getInstance();
            messageDrawingStrategy = SequenceMessageDrawer.getInstance();
        }
        if (diagram instanceof SequenceDiagram) {
            actorDrawingStrategy = CommunicationActorDrawer.getInstance();
            messageDrawingStrategy = CommunicationMessageDrawer.getInstance();
        }

        drawParties(graphics, diagram);
        drawMessages(graphics, diagram);
    }

    private void drawLabel(Graphics graphics, Point2D point, String label) {
        LabelDrawer.getInstance().draw(graphics, point, null, label);
    }

    private void drawParties(Graphics graphics, Diagram diagram) {
        for (Party p : diagram.getParties()) {
            if (p instanceof Actor) {
                actorDrawingStrategy.draw(graphics, p.getCoordinate(), null, "");
            } else {
                Point2D start = p.getCoordinate();
                boxDrawingStrategy.draw(graphics, start, new Point2D.Double(start.getX() + Object.WIDTH, start.getY() + Object.HEIGHT), "");
            }

            drawLabel(graphics, p.getLabel().getCoordinate(), p.getLabel().toString());
        }
    }

    private void drawActivationBar() {

    }

    private void drawMessages(Graphics graphics, Diagram diagram) {
        Message m = diagram.getFirstMessage();
        List<Pair<Party, Integer>> activationBarCount2 = calculateActivationBars2(m);

        //Map<Integer, Integer> activationBarCount = calculateActivationBars(dissectionMessages);

        //drawActivationBars(#######);

        drawFirstActivationBar(graphics,activationBarCount2.get(0));

        Point2D start, end;
        while(m != null){
            start = new Point2D.Double(m.getSender().getCoordinate().getX(),m.getyLocation());
            end = new Point2D.Double(m.getReceiver().getCoordinate().getX(),m.getyLocation());

            this.messageDrawingStrategy.draw(graphics,start,end,m.getLabel().getLabel());

            this.drawLabel(graphics, m.getLabel().getCoordinate(), m.getLabel().toString());

            m = m.getNextMessage();
        }
    }

    private List<Boolean> dissectMessages(Message m) {
        //true = invoke, false = response
        List<Boolean> dissectionMessages = new ArrayList<Boolean>();


        while (m != null) {
            if (m instanceof InvocationMessage)
                dissectionMessages.add(true);
            else
                dissectionMessages.add(false);

            m = m.getNextMessage();
        }

        return dissectionMessages;
    }

    private HashMap<Integer, Integer> calculateActivationBars(List<Boolean> dissectionMessages) {
        HashMap<Integer, Integer> activationBarCount = new HashMap<Integer, Integer>();

        int invokeCounter = 0, responseCounter = 0, activationBarsCalculated = 0;
        for (Boolean b : dissectionMessages) {
            if (b) {
                invokeCounter++;
            }
            if (!b) {
                if (responseCounter < invokeCounter) {
                    responseCounter++;
                }
            }

            if (responseCounter > 0 && invokeCounter == responseCounter) {
                activationBarsCalculated++;
                activationBarCount.put(activationBarsCalculated, invokeCounter);
                invokeCounter = 0 - activationBarsCalculated;
                responseCounter = 0;
            }
        }
        return activationBarCount;
    }

    private ArrayList<Pair<Party, Integer>> calculateActivationBars2(Message m) {
        ArrayList<Pair<Party, Integer>> activationBarCount = new ArrayList<Pair<Party, Integer>>();

        int invokeCounter = 0, responseCounter = 0, activationBarsCalculated = 0;
        while (m != null) {
            if (m instanceof InvocationMessage)
                invokeCounter++;
            else {
                if (responseCounter < invokeCounter) {
                    responseCounter++;
                }
            }

            if (responseCounter > 0 && invokeCounter == responseCounter) {
                activationBarsCalculated++;
                activationBarCount.add(new Pair<Party, Integer>(m.getSender(), invokeCounter));
                invokeCounter = 0 - activationBarsCalculated;
                responseCounter = 0;
            }

            m = m.getNextMessage();
        }
        return activationBarCount;
    }

    private void drawActivationBars(List<Pair<Party, Integer>> list){
        //TODO draw them bars

        Point2D start, end;

        //to calculate y
        int passedMessages = 0;

        for(Pair<Party, Integer> p:list){
        }

    }

    private void drawFirstActivationBar(Graphics graphics, Pair<Party, Integer> pair){
        Point2D start, end;
        int messageHeight = 16;
        start = new Point2D.Double(pair.getA().getCoordinate().getX()+15, 78);
        end = new Point2D.Double(pair.getA().getCoordinate().getX()+35, 78+(messageHeight*pair.getB()*2));
        boxDrawingStrategy.draw(graphics,start,end,"");
    }


}
