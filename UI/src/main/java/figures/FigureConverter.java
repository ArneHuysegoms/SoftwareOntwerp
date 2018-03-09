package figures;

import diagram.Clickable;
import diagram.CommunicationsDiagram;
import diagram.Diagram;
import diagram.SequenceDiagram;
import diagram.label.Label;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Actor;
import diagram.party.Object;
import diagram.party.Party;
import figures.Drawer.BoxDrawer;
import figures.Drawer.DiagramSpecificDrawers.*;
import figures.Drawer.Drawer;
import figures.Drawer.LabelDrawer;
import figures.Drawer.SelectionBoxDrawer;
import figures.helperClasses.Pair;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


public class FigureConverter {
    private static FigureConverter instance = null;

    private boolean activeDiagramIsSequence, activeDiagramIsCommunication;
    private Drawer actorDrawingStrategy,
            objectDrawingStrategy,
            boxDrawingStrategy,
            invokeMessageDrawingStrategy,
            responseMessageDrawingStrategy,
            labelDrawingStrategy,
            lifeLineDrawer,
            selectionBoxDrawingStrategy;

    private FigureConverter() {

    }

    public static FigureConverter getInstance() {
        if (instance == null)
            instance = new FigureConverter();
        return instance;
    }


    public void draw(Graphics graphics, Diagram diagram) {

        init(graphics, diagram);

        complexDrawings(graphics, diagram);

        drawSelectionBox(graphics, diagram);

        if (activeDiagramIsSequence)
            drawLifeline(graphics, diagram);

        for (Party p : diagram.getParties()) {
            drawLabel(graphics, p.getLabel().getCoordinate(), p.getLabel().getLabel());
            drawParties(graphics, diagram, p);
        }
    }

    public void init(Graphics graphics, Diagram diagram) {
        boxDrawingStrategy = new BoxDrawer();
        selectionBoxDrawingStrategy = new SelectionBoxDrawer();
        labelDrawingStrategy = new LabelDrawer();

        if (diagram instanceof SequenceDiagram) {
            activeDiagramIsSequence = true;
            activeDiagramIsCommunication = false;
            new SequenceLayoutDrawer().draw(graphics, null, null, "");
            lifeLineDrawer = new SequenceLifelineDrawer();
            actorDrawingStrategy = new SequenceActorDrawer();
            objectDrawingStrategy = new SequenceObjectDrawer();
            invokeMessageDrawingStrategy = new SequenceInvokeMessageDrawer();
            responseMessageDrawingStrategy = new SequenseResponseMessageDrawer();
        }
        if (diagram instanceof CommunicationsDiagram) {
            activeDiagramIsSequence = false;
            activeDiagramIsCommunication = true;
            actorDrawingStrategy = new CommunicationActorDrawer();
            objectDrawingStrategy = new CommunicationObjectDrawer();
            invokeMessageDrawingStrategy = new CommunicationInvokeMessageDrawer();
            responseMessageDrawingStrategy = new CommunicationResponseMessageDrawer();
        }
    }

    private void drawLabel(Graphics graphics, Point2D point, String label) {
        labelDrawingStrategy.draw(graphics, point, new Point2D.Double(point.getX() + Label.width, point.getY() + Label.height), label);
    }

    private void drawParties(Graphics graphics, Diagram diagram, Party p) {
        Point2D start = p.getCoordinate();
        Point2D end = new Point2D.Double(start.getX() + Object.WIDTH, start.getY() + Object.HEIGHT);

        if (p instanceof Actor) {
            actorDrawingStrategy.draw(graphics, start, end, "");
        } else {
            objectDrawingStrategy.draw(graphics, start, end, "");
        }
    }

    private void drawSelectionBox(Graphics graphics, Diagram diagram) {
        Clickable c = diagram.getSelectedElement();

        if (diagram.selectedElementIsActor()) {
            Actor a = (Actor) c;
            Point2D start = new Point2D.Double(a.getCoordinate().getX() - (Actor.WIDTH / 2), a.getCoordinate().getY()),
                    end = new Point2D.Double(a.getCoordinate().getX() + (Actor.WIDTH / 2), a.getCoordinate().getY() + Actor.WIDTH);
            selectionBoxDrawingStrategy.draw(graphics, start, end, "");
        } else if (diagram.selectedElementIsLabel()) {
            Label l = (Label) c;
            Point2D start = l.getCoordinate();
            selectionBoxDrawingStrategy.draw(graphics, start, new Point2D.Double(start.getX() + Label.width, start.getY() + Label.height), "");
        } else if (diagram.selectedElementIsObject()) {
            Object object = (Object) c;
            int selectionBoxSize = 5;
            Point2D start = new Point2D.Double(object.getCoordinate().getX() - selectionBoxSize, object.getCoordinate().getY() - selectionBoxSize);
            Point2D end = new Point2D.Double(object.getCoordinate().getX() + Object.WIDTH + selectionBoxSize, object.getCoordinate().getY() + Object.HEIGHT + selectionBoxSize);
            selectionBoxDrawingStrategy.draw(graphics, start, end, "");
        } else if (diagram.selectedElementIsMessage()) {
            Message m = (Message) c;
            Point2D start = new Point2D.Double(m.getSender().getXLocationOfLifeline(), m.getyLocation() - (Message.HEIGHT / 2));
            Point2D end = new Point2D.Double(m.getReceiver().getXLocationOfLifeline(), m.getyLocation() + (Message.HEIGHT / 2));
            selectionBoxDrawingStrategy.draw(graphics, start, end, "");
        }
    }

    private void drawLifeline(Graphics graphics, Diagram diagram) {
        Message m = diagram.getFirstMessage();
        Point2D start, end;

        if (m != null) {
            Message last = null, first = diagram.getFirstMessage();
            boolean foundLast = false;
            while (!foundLast) {
                if (m.getNextMessage() != null) {
                    m = m.getNextMessage();
                } else {
                    last = m;
                    foundLast = true;
                }
            }

            for (Party p : diagram.getParties()) {
                start = new Point2D.Double(p.getXLocationOfLifeline(), first.getyLocation() - Message.HEIGHT);
                end = new Point2D.Double(p.getXLocationOfLifeline(), last.getyLocation() + Message.HEIGHT * 2);
                lifeLineDrawer.draw(graphics, start, end, "");
            }
        } else {
            for (Party p : diagram.getParties()) {
                start = new Point2D.Double(p.getXLocationOfLifeline(), p.getCoordinate().getY() + Object.HEIGHT);
                end = new Point2D.Double(p.getXLocationOfLifeline(), p.getCoordinate().getY() + Object.HEIGHT + Message.HEIGHT * 4);
                lifeLineDrawer.draw(graphics, start, end, "");
            }
        }
    }

    private void complexDrawings(Graphics graphics, Diagram diagram) {
        Message m = diagram.getFirstMessage();

        if (activeDiagramIsSequence) {
            SequenceActivationBarAndMessageHelper helper;
            if (m != null) {
                helper = new SequenceActivationBarAndMessageHelper(m);
                helper.draw(graphics, boxDrawingStrategy, invokeMessageDrawingStrategy, responseMessageDrawingStrategy);
            }

            while (m != null) {
                this.drawLabel(graphics, m.getLabel().getCoordinate(), m.getLabel().getLabel());
                m = m.getNextMessage();
            }
        } else {
            new CommunicationArrowAndLabelHelper(m).draw(graphics, invokeMessageDrawingStrategy, labelDrawingStrategy);
        }
    }
}

class CommunicationArrowAndLabelHelper {
    private List<PartyPair> pairs;

    public CommunicationArrowAndLabelHelper(Message m) {
        pairs = new ArrayList<PartyPair>();
        traverserMessages(m);
    }

    private void traverserMessages(Message message) {
        Party sender, receiver;
        PartyPair pair;
        boolean found = false;

        if (message instanceof InvocationMessage) {
            pairs.add(new PartyPair(message.getSender(), message.getReceiver(), message));
            message = message.getNextMessage();
        }

        while (message != null) {
            if (message instanceof InvocationMessage) {

                sender = message.getSender();
                receiver = message.getReceiver();

                PartyPair temp;
                int size = pairs.size();
                for (int i = 0; i < size; i++) {
                    temp = pairs.get(i);
                    if (temp.equalPair(sender, receiver)) {
                        temp.addMessage(message);
                        found = true;
                    }
                }
                if (!found) {
                    pairs.add(new PartyPair(sender, receiver, message));
                }
                found = false;
            }
            message = message.getNextMessage();
        }
    }

    public void draw(Graphics graphics, Drawer messageDrawer, Drawer labelDrawer) {
        for (PartyPair pair : pairs) {
            pair.draw(graphics, messageDrawer, labelDrawer);
        }
    }

    class PartyPair extends Pair {
        private int arrowCount = 1;
        private List<Message> messages;

        public PartyPair(Party first, Party second, Message m) {
            super(first, second);
            messages = new ArrayList<Message>();
            messages.add(m);
        }

        public boolean equalPair(Party sender, Party receiver) {
            return sender == this.getA() && receiver == this.getB();
        }

        public void addMessage(Message message) {
            messages.add(message);
        }

        public Party getSender() {
            return (Party) this.getA();
        }

        public Party getReceiver() {
            return (Party) this.getB();
        }

        public Point2D calculateStart(int spaceBetweenArrows) {
            return new Point2D.Double(getSender().getCoordinate().getX() + Object.WIDTH, getSender().getCoordinate().getY() + spaceBetweenArrows);
        }

        public Point2D calculateEnd(int spaceBetweenArrows) {
            return new Point2D.Double(getReceiver().getCoordinate().getX(), getReceiver().getCoordinate().getY() + spaceBetweenArrows);
        }

        public void draw(Graphics graphics, Drawer messageDrawer, Drawer labelDrawer) {
            int spread = Object.HEIGHT / messages.size();
            InvocationMessage message;
            Point2D start, end;

            for (int i = 0; i < messages.size(); i++) {
                message = (InvocationMessage) messages.get(i);
                start = calculateStart(i * spread);
                end = calculateEnd(i * spread);
                messageDrawer.draw(graphics, start, end, "");
                //TODO labels tekenen:
                String label = message.getMessageNumber() + " " + message.getLabel().getLabel();
                labelDrawer.draw(graphics, calculateLabelStartPosition(start, end), null, label);
            }
        }

        private Point2D calculateLabelStartPosition(Point2D start, Point2D end) {
            double x = Math.round((start.getX() + end.getX()) / 2);
            double y = Math.round((start.getY() + end.getY()) / 2);

            return new Point2D.Double(x, y);
        }
    }
}

class SequenceActivationBarAndMessageHelper {
    private Party party;
    private List<ActivationBar> bars;
    private Message initialMessage;

    public SequenceActivationBarAndMessageHelper(Message message) {
        bars = new ArrayList<ActivationBar>();
        setInitialMessage(message);

        calculateOuterBars(getInitialMessage());
    }

    private void calculateOuterBars(Message message) {
        int invokeCounter = 0, responseCounter = 0;
        Message sent = null;
        while (message != null) {
            if (message instanceof InvocationMessage) {
                if (sent == null) {
                    sent = message;
                }
                invokeCounter++;
            } else {
                responseCounter++;
            }

            if (responseCounter > 0 && invokeCounter == responseCounter) {
                bars.add(new ActivationBar(sent, message,false));
                sent = null;
            }

            message = message.getNextMessage();
        }
    }

    public void draw(Graphics graphics, Drawer boxDrawer, Drawer invokeDrawer, Drawer responseDrawer) {
        for (ActivationBar a : bars) {
            a.draw(graphics,boxDrawer, invokeDrawer, responseDrawer);
        }
    }

    private void setInitialMessage(Message initialMessage) {
        this.initialMessage = initialMessage;
    }

    public Message getInitialMessage() {
        return initialMessage;
    }


    class ActivationBar {
        private int barWidth = 20;
        private boolean hasParentBar = true;
        private List<ActivationBar> bars;
        private Message sent, response;

        public ActivationBar(Message sent, Message received) {
            bars = new ArrayList<ActivationBar>();
            setSent(sent);
            setReceived(received);

            calculateBars(getSent().getNextMessage());
        }

        public ActivationBar(Message sent, Message received, boolean hasParentBar) {
            this(sent, received);
            setParent(hasParentBar);
        }

        /**
         * Calculates next layer of activation bars within this activation bar
         */
        private void calculateBars(Message nextMessage) {
            if (!nextMessage.equals(response)) {
                int invokeCounter = 0, responseCounter = 0;
                Message sent = null;

                while (nextMessage != response) {
                    if (nextMessage instanceof InvocationMessage) {
                        if (sent == null) {
                            sent = nextMessage;
                        }
                        invokeCounter++;
                    } else {
                        responseCounter++;
                    }

                    if (responseCounter > 0 && invokeCounter == responseCounter) {
                        bars.add(new ActivationBar(sent, nextMessage));
                        sent = null;
                    }

                    nextMessage = nextMessage.getNextMessage();
                }
            }
        }

        private void setSent(Message sent) {
            this.sent = sent;
        }

        private void setReceived(Message received) {
            this.response = received;
        }

        private Party getSender() {
            return sent.getSender();
        }

        private Party getReceiver() {
            return sent.getReceiver();
        }

        public void draw(Graphics graphics, Drawer boxDrawer, Drawer invokeDrawer, Drawer responseDrawer) {

            boxDrawer.draw(graphics, calculateOwnBarStart(), calculateOwnBarEnd(), "");
            boxDrawer.draw(graphics, calculateBrotherBarStart(), calculateBrotherBarEnd(), null);

            invokeDrawer.draw(graphics, new Point2D.Double(calculateOwnBarStartX() + barWidth, calculateBarStartY()), new Point2D.Double(calculateBrotherBarStartX(), calculateBarStartY()), null);
            responseDrawer.draw(graphics, new Point2D.Double(calculateBrotherBarStartX(), calculateBarEndY()), new Point2D.Double(calculateOwnBarStartX() + barWidth, calculateBarEndY()), null);

            for (ActivationBar a : bars) {
                a.draw(graphics, boxDrawer, invokeDrawer, responseDrawer);
            }
        }

        private void setParent(boolean hasParentBar) {
            this.hasParentBar = hasParentBar;
        }

        private Boolean hasParent() {
            return hasParentBar;
        }

        public Message getSent() {
            return sent;
        }

        public Message getResponse() {
            return response;
        }

        private double calculateOwnBarStartX() {
            if (hasParent()) {
                return getSent().getSender().getXLocationOfLifeline();
            } else {
                return getSent().getSender().getXLocationOfLifeline() - (barWidth / 2);
            }
        }

        private double calculateBarStartY() {
            return getSent().getyLocation();
        }

        private double calculateBarEndY() {
            return getResponse().getyLocation();
        }

        private double calculateOwnBarEndX() {
            if (hasParent()) {
                return getResponse().getReceiver().getXLocationOfLifeline() + barWidth;
            } else {
                return getSent().getSender().getXLocationOfLifeline() + (barWidth / 2);
            }
        }

        private double calculateBrotherBarEndX() {
            return getResponse().getSender().getXLocationOfLifeline() + (barWidth / 2);
        }

        private double calculateBrotherBarStartX() {
            return getSent().getReceiver().getXLocationOfLifeline() - (barWidth / 2);
        }

        private Point2D calculateOwnBarStart() {
            return new Point2D.Double(calculateOwnBarStartX(), calculateBarStartY());
        }

        private Point2D calculateOwnBarEnd() {
            return new Point2D.Double(calculateOwnBarEndX(), calculateBarEndY());
        }

        private Point2D calculateBrotherBarStart() {
            return new Point2D.Double(calculateBrotherBarStartX(), calculateBarStartY());
        }

        private Point2D calculateBrotherBarEnd() {
            return new Point2D.Double(calculateBrotherBarEndX(), calculateBarEndY());
        }
    }
}