package figures;

import diagram.Clickable;
import diagram.CommunicationsDiagram;
import diagram.Diagram;
import diagram.SequenceDiagram;
import diagram.label.Label;
import diagram.party.Actor;
import diagram.party.Object;
import diagram.message.ResultMessage;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Party;
import figures.Drawer.*;
import figures.Drawer.DiagramSpecificDrawers.*;
import figures.helperClasses.CommunicationObjectHelper;
import figures.helperClasses.Pair;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FigureConverter {
    private static FigureConverter instance = null;

    private boolean activeDiagramIsSequence;
    private Drawer actorDrawingStrategy,
            objectDrawingStrategy,
            boxDrawingStrategy,
            invokeMessageDrawingStrategy,
            responseMessageDrawingStrategy,
            labelDrawingStrategy,
    //TODO LayoutDrawer maken
    layoutStrategy;

    private FigureConverter() {

    }

    public static FigureConverter getInstance() {
        if (instance == null)
            instance = new FigureConverter();
        return instance;
    }


    public void draw(Graphics graphics, Diagram diagram) {
        boxDrawingStrategy = new BoxDrawer();
        labelDrawingStrategy = new LabelDrawer();


        if (diagram instanceof SequenceDiagram) {
            activeDiagramIsSequence = true;
            actorDrawingStrategy = new SequenceActorDrawer();
            objectDrawingStrategy = new SequenceObjectDrawer();
            invokeMessageDrawingStrategy = new SequenceInvokeMessageDrawer();
            responseMessageDrawingStrategy = new SequenseResponseMessageDrawer();
        }
        if (diagram instanceof CommunicationsDiagram) {
            activeDiagramIsSequence = false;
            actorDrawingStrategy = new CommunicationActorDrawer();
            objectDrawingStrategy = new CommunicationObjectDrawer();
            invokeMessageDrawingStrategy = new CommunicationInvokeMessageDrawer();
            responseMessageDrawingStrategy = new CommunicationResponseMessageDrawer();
        }

        drawParties(graphics, diagram);

        drawMessages(graphics, diagram);

        drawSelectionBox(graphics, diagram);

    }

    private void drawLabel(Graphics graphics, Point2D point, String label) {
        labelDrawingStrategy.draw(graphics, point, new Point2D.Double(point.getX() + Label.width, point.getY() + Label.height), label);
    }

    private void drawParties(Graphics graphics, Diagram diagram) {
        Point2D start, end;

        for (Party p : diagram.getParties()) {
            start = p.getCoordinate();
            end = new Point2D.Double(start.getX() + Object.WIDTH, start.getY() + Object.HEIGHT);
            if (p instanceof Actor) {
                actorDrawingStrategy.draw(graphics, start, end, "");
            } else {

                objectDrawingStrategy.draw(graphics, start, end, "");
            }

            drawLabel(graphics, p.getLabel().getCoordinate(), p.getLabel().getLabel());
        }
    }

    private void drawSelectionBox(Graphics graphics, Diagram diagram) {
        Clickable c = diagram.getSelectedElement();

        if (diagram.selectedElementIsActor()) {
            Actor a = (Actor) c;
            Point2D start = new Point2D.Double(a.getCoordinate().getX() - (Actor.WIDTH / 2), a.getCoordinate().getY()),
                    end = new Point2D.Double(a.getCoordinate().getX() + (Actor.WIDTH / 2), a.getCoordinate().getY() + Actor.WIDTH);
            boxDrawingStrategy.draw(graphics, start, end, "");
        } else if (diagram.selectedElementIsLabel()) {
            Label l = (Label) c;
            Point2D start = l.getCoordinate();
            boxDrawingStrategy.draw(graphics, start, new Point2D.Double(start.getX() + Label.width, start.getY() + Label.height), "");
        } else if (diagram.selectedElementIsObject()) {
            //TODO selected item object
        } else if (diagram.selectedElementIsMessage()) {
            //TODO selected item Message
        }
    }

    private void drawActivationBar() {

    }

    private void drawMessages(Graphics graphics, Diagram diagram) {
        Message m = diagram.getFirstMessage();
        //TODO replace this calculation to draw-method where instanceof is checked for sequencediagram
        List<Pair<Party, Integer>> activationBarCount2 = calculateActivationBars2(m);

        //Map<Integer, Integer> activationBarCount = calculateActivationBars(dissectionMessages);

        //drawActivationBars(#######);

        if (m != null) {
            //TODO enkel sequence (wrs ergens samen met lifeline duhh)
            //drawFirstActivationBar(graphics, activationBarCount2.get(0));
        }
        if (activeDiagramIsSequence) {
            Point2D start, end;
            while (m != null) {

                start = new Point2D.Double(m.getSender().getXLocationOfLifeline(), m.getyLocation());
                end = new Point2D.Double(m.getReceiver().getXLocationOfLifeline(), m.getyLocation());

                if (m instanceof InvocationMessage)
                    this.invokeMessageDrawingStrategy.draw(graphics, start, end, "");
                if (m instanceof ResultMessage)
                    this.responseMessageDrawingStrategy.draw(graphics, start, end, "");

                this.drawLabel(graphics, m.getLabel().getCoordinate(), m.getLabel().getLabel());

                m = m.getNextMessage();
            }
        } else {
            new CommunicationObjectHelper(m).draw(graphics, invokeMessageDrawingStrategy, labelDrawingStrategy);
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

    private void drawActivationBars(List<Pair<Party, Integer>> list) {
        //TODO draw them bars

        Point2D start, end;

        //to calculate y
        int passedMessages = 0;

        for (Pair<Party, Integer> p : list) {
        }

    }

    private void drawFirstActivationBar(Graphics graphics, Pair<Party, Integer> pair) {
        Point2D start, end;
        int messageHeight = 16;
        start = new Point2D.Double(pair.getA().getCoordinate().getX() + 15, 78);
        end = new Point2D.Double(pair.getA().getCoordinate().getX() + 35, 78 + (messageHeight * pair.getB() * 2));
        boxDrawingStrategy.draw(graphics, start, end, "");
    }

}
