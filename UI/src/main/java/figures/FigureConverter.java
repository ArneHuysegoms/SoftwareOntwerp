package figures;

import diagram.Diagram;
import diagram.label.Label;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Actor;
import diagram.party.Object;
import diagram.party.Party;

import facade.DomainFacade;
import figures.Drawer.BoxDrawer;
import figures.Drawer.DiagramSpecificDrawers.*;
import figures.Drawer.Drawer;
import figures.Drawer.LabelDrawer;
import figures.Drawer.SelectionBoxDrawer;
import figures.helperClasses.Pair;
import repo.diagram.CommunicationRepo;
import repo.diagram.DiagramRepo;
import repo.diagram.SequenceRepo;
import repo.message.CommunicationMessageRepo;
import repo.message.MessageRepo;
import repo.message.SequenceMessageRepo;
import repo.party.PartyRepo;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

/**
 * a class that converts the data from the diagram model to drawn figures of the program's window
 */
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

    /**
     * default constructor
     */
    private FigureConverter() {

    }

    /**
     * if an instance of this class excists, it gets returned. Otherwise it gets created and then returned
     *
     * @return an instance of this class
     */
    public static FigureConverter getInstance() {
        if (instance == null)
            instance = new FigureConverter();
        return instance;
    }


    /**
     * upper draw function
     *
     * @param graphics   object used to draw on the program's window
     * @param subwindows the subwindows to be drawn on the canvas
     */
    public void draw(Graphics graphics, List<Subwindow> subwindows) {
        DiagramRepo repo;
        for (Subwindow sub : subwindows) {
            repo = sub.getFacade().getActiveRepo();
            init(repo);


            drawParties(graphics, repo.getPartyRepo());
            drawMessages(graphics, repo.getMessageRepo());
            drawLabels(graphics, repo.getMessageRepo());

        }
    }

    private void drawParties(Graphics graphics, PartyRepo partyRepo) {
        Map<Party, Point2D> partyMap =  partyRepo.getPartyPoint2DMap();


        for(Map.Entry<Party, Point2D> entry: partyMap.entrySet()){
            Point2D start = entry.getValue();
            Point2D end;

            if (entry.getKey() instanceof Actor) {
                end = new Point2D.Double(start.getX() + PartyRepo.ACTORWIDTH, start.getY() + PartyRepo.ACTORHEIGHT);
                actorDrawingStrategy.draw(graphics, start, end, "");
            } else {
                end = new Point2D.Double(start.getX() + PartyRepo.OBJECTWIDTH, start.getY() + PartyRepo.OBJECTHEIGHT);
                objectDrawingStrategy.draw(graphics, start, end, "");
            }
        }
    }

    private void drawMessages(Graphics graphics, MessageRepo messageRepo) {
        if(messageRepo instanceof SequenceMessageRepo){
            messageRepo = (SequenceMessageRepo)messageRepo;
        }
        else{
            messageRepo = (CommunicationMessageRepo)messageRepo;
        }

        Map<Message, Point2D> messageMap =  messageRepo;


        for(Map.Entry<Party, Point2D> entry: partyMap.entrySet()){
            Point2D start = entry.getValue();
            Point2D end;

            if (entry.getKey() instanceof Actor) {
                end = new Point2D.Double(start.getX() + PartyRepo.ACTORWIDTH, start.getY() + PartyRepo.ACTORHEIGHT);
                actorDrawingStrategy.draw(graphics, start, end, "");
            } else {
                end = new Point2D.Double(start.getX() + PartyRepo.OBJECTWIDTH, start.getY() + PartyRepo.OBJECTHEIGHT);
                objectDrawingStrategy.draw(graphics, start, end, "");
            }
        }
    }

    private void drawLabels(Graphics graphics, MessageRepo messageRepo) {
    }

    public void draw(Graphics graphics, Diagram diagram) {
        //draw(g, list<subwindows>)
        init(diagram);

        complexDrawings(graphics, diagram);

        drawSelectionBox(graphics, diagram);

        if (activeDiagramIsSequence)
            drawLifeline(graphics, diagram);

        for (Party p : diagram.getParties()) {
            drawLabel(graphics, p.getLabel().getCoordinate(), p.getLabel().getLabel());
            drawParties(graphics, diagram, p);
        }
    }

    /**
     * creates all drawing strategy- and other object that will be use to draw the diagram
     *
     * @param repo the active repository for a specific type of diagram
     */
    public void init(DiagramRepo repo) {
        boxDrawingStrategy = new BoxDrawer();
        selectionBoxDrawingStrategy = new SelectionBoxDrawer();
        labelDrawingStrategy = new LabelDrawer();

        if (repo instanceof SequenceRepo) {
            activeDiagramIsSequence = true;
            activeDiagramIsCommunication = false;
            lifeLineDrawer = new SequenceLifelineDrawer();
            actorDrawingStrategy = new SequenceActorDrawer();
            objectDrawingStrategy = new SequenceObjectDrawer();
            invokeMessageDrawingStrategy = new SequenceInvokeMessageDrawer();
            responseMessageDrawingStrategy = new SequenseResponseMessageDrawer();
        }
        if (repo instanceof CommunicationRepo) {
            activeDiagramIsSequence = false;
            activeDiagramIsCommunication = true;
            actorDrawingStrategy = new CommunicationActorDrawer();
            objectDrawingStrategy = new CommunicationObjectDrawer();
            invokeMessageDrawingStrategy = new CommunicationInvokeMessageDrawer();
            responseMessageDrawingStrategy = new CommunicationResponseMessageDrawer();
        }
    }

    /**
     * method that uses the a label drawer to draw the label on the canvas
     *
     * @param graphics object used to draw on the program's window
     * @param point    point slightly to the top-left of the label string
     * @param label    string to be drawn
     */
    private void drawLabel(Graphics graphics, Point2D point, String label) {
        labelDrawingStrategy.draw(graphics, point, null, label);
    }

    /**
     * method that uses the a party drawer to draw a party on the canvas
     *
     * @param graphics object used to draw on the program's window
     * @param diagram  the diagram object to be drawn on the canvas
     * @param p        party to be drawn on the canvas
     */
    private void drawParties(Graphics graphics, Diagram diagram, Party p) {
        Point2D start = p.getCoordinate();
        Point2D end = new Point2D.Double(start.getX() + Object.WIDTH, start.getY() + Object.HEIGHT);

        if (p instanceof Actor) {
            actorDrawingStrategy.draw(graphics, start, end, "");
        } else {
            objectDrawingStrategy.draw(graphics, start, end, "");
        }
    }

    /**
     * method that uses the selection box drawer to draw a box around the currently selected selectable parts of the diagram
     *
     * @param graphics object used to draw on the program's window
     * @param diagram  the diagram object to be drawn on the canvas
     */
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

    /**
     * methos that determines the lengths of the longest lifeline and draws these for every party
     *
     * @param graphics object used to draw on the program's window
     * @param diagram  the diagram object to be drawn on the canvas
     */
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

    /**
     * a method that uses helper classes to destruct data so activation bars and message arrows and labels can be drawn
     *
     * @param graphics object used to draw on the program's window
     * @param diagram  the diagram object to be drawn on the canvas
     */
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

    //Te verwijderen
    private class Subwindow {
        private DomainFacade facade;
        private Object mediator;
        private int width,height, level;
        private Point2D position;
        private boolean labelMode;
        private Label label;

        public Subwindow(){
            width = 300;
            height = 300;
        }

        public void updateLabels(char c){

        }

        public boolean isInLabelMode(){
            return false;
        }

        public Party getSelectedElement(Point2D point){
            return null;
        }

        public Diagram getDiagram(){
            return null;
        }

        public DomainFacade getFacade() {
            return facade;
        }
    }


    /**
     * helper class for calculating message arrow and label locations
     */
    private class CommunicationArrowAndLabelHelper {
        private List<PartyPair> pairs;

        /**
         * @param m the first message of the diagram
         */
        public CommunicationArrowAndLabelHelper(Message m) {
            pairs = new ArrayList<PartyPair>();
            traverserMessages(m);
        }

        /**
         * a method that counts how many arrows are between two senders and saves this data in a PartyPair-object
         *
         * @param message the first message of the diagram
         */
        private void traverserMessages(Message message) {
            Party sender, receiver;
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

        /**
         * @param graphics      object used to draw on the program's window
         * @param messageDrawer the message drawer to be used to draw messages
         * @param labelDrawer   the label drawer to be used to draw messages
         */
        public void draw(Graphics graphics, Drawer messageDrawer, Drawer labelDrawer) {
            for (PartyPair pair : pairs) {
                pair.draw(graphics, messageDrawer, labelDrawer);
            }
        }

        /**
         * helper class to save how many messages are sent from a specific sender to a speific receiver
         */
        private class PartyPair extends Pair {
            private List<Message> messages;

            /**
             * @param first  the sender of a message to the second party
             * @param second the receiver of a message from the first party
             * @param m      a message that has been sent from the first party to the second
             */
            public PartyPair(Party first, Party second, Message m) {
                super(first, second);
                messages = new ArrayList<Message>();
                messages.add(m);
            }

            /**
             * method used to check if two parties are the same as the sender and receiver stored in this pair (order matters)
             *
             * @param sender   Party that is a sender of a message to reveiver party
             * @param receiver party that is a receiver of a message from the sender party
             * @return true if the parties and order match
             */
            public boolean equalPair(Party sender, Party receiver) {
                return sender == this.getA() && receiver == this.getB();
            }

            /**
             * adds a message that has been sent from the first stored to the second stored party in this pair
             *
             * @param message the message sent from the first stored to the second stored party in this pair
             */
            public void addMessage(Message message) {
                messages.add(message);
            }

            /**
             * returns the sender of the messages
             *
             * @return the sender of the messages
             */
            public Party getSender() {
                return (Party) this.getA();
            }

            /**
             * returns the receiver of the messages
             *
             * @return the receiver of the messages
             */
            public Party getReceiver() {
                return (Party) this.getB();
            }

            /**
             * calculates start point of an arrow, position depends on how many messages are sent from the first party to the second
             *
             * @param spaceBetweenArrows
             * @return start point of the arrow
             */
            public Point2D calculateStart(int spaceBetweenArrows) {
                return new Point2D.Double(getSender().getCoordinate().getX() + Object.WIDTH, getSender().getCoordinate().getY() + spaceBetweenArrows);
            }

            /**
             * calculates end point of an arrow, position depends on how many messages are sent from the first party to the second
             *
             * @param spaceBetweenArrows
             * @return end point of the arrow
             */
            public Point2D calculateEnd(int spaceBetweenArrows) {
                return new Point2D.Double(getReceiver().getCoordinate().getX(), getReceiver().getCoordinate().getY() + spaceBetweenArrows);
            }

            /**
             * @param graphics      object used to draw on the program's window
             * @param messageDrawer message drawer to be use to draw the arrows between the two parties
             * @param labelDrawer   message drawer to be use to draw the label above the drawn arrows
             */
            public void draw(Graphics graphics, Drawer messageDrawer, Drawer labelDrawer) {
                int spread = Object.HEIGHT / messages.size();
                InvocationMessage message;
                Point2D start, end;

                for (int i = 0; i < messages.size(); i++) {
                    message = (InvocationMessage) messages.get(i);
                    start = calculateStart(i * spread);
                    end = calculateEnd(i * spread);
                    messageDrawer.draw(graphics, start, end, "");
                    String label = message.getMessageNumber() + " " + message.getLabel().getLabel();
                    labelDrawer.draw(graphics, calculateLabelStartPosition(start, end), null, label);
                }
            }

            /**
             * methos that calculates the label's start position
             *
             * @param start start possition of the message that the label belongs tho
             * @param end   end possition of the message that the label belongs tho
             * @return the label's start position
             */
            private Point2D calculateLabelStartPosition(Point2D start, Point2D end) {
                double x = Math.round((start.getX() + end.getX()) / 2);
                double y = Math.round((start.getY() + end.getY()) / 2);

                return new Point2D.Double(x, y);
            }
        }
    }

    /**
     * helper class for calculating message arrow and activation bar locations
     */
    private class SequenceActivationBarAndMessageHelper {
        private List<ActivationBar> bars;
        private Message initialMessage;

        /**
         * @param message the first message of the diagram
         */
        public SequenceActivationBarAndMessageHelper(Message message) {
            bars = new ArrayList<ActivationBar>();
            setInitialMessage(message);

            calculateOuterBars(getInitialMessage());
        }

        /**
         * a method that creates and stores an ActivationBar object for every activation bar on the lifeline of the first party.
         * At creating an ActivationBar object, the object calls a very similar method .
         *
         * @param message the first message of the diagram
         */
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
                    bars.add(new ActivationBar(sent, message, false));
                    sent = null;
                }

                message = message.getNextMessage();
            }
        }

        /**
         * @param graphics
         * @param boxDrawer      a box drawer object to be used to draw the activation bars
         * @param invokeDrawer   a drawer object to be used to draw invocation messages
         * @param responseDrawer a drawer object to be used to draw response messages
         */
        public void draw(Graphics graphics, Drawer boxDrawer, Drawer invokeDrawer, Drawer responseDrawer) {
            for (ActivationBar a : bars) {
                a.draw(graphics, boxDrawer, invokeDrawer, responseDrawer);
            }
        }

        /**
         * sets initial message of the diagram
         *
         * @param initialMessage initial message of the diagram
         */
        private void setInitialMessage(Message initialMessage) {
            this.initialMessage = initialMessage;
        }

        /**
         * returns initial message of the diagram
         *
         * @return initial message of the diagram
         */
        public Message getInitialMessage() {
            return initialMessage;
        }

        /**
         * class that represents an activation bar. An object of this class stores activation bars from messages that are outgoing from this one.
         */
        private class ActivationBar {
            private int barWidth = 20;
            private boolean hasParentBar = true;
            private List<ActivationBar> bars;
            private Message sent, response;

            /**
             * @param sent     first message that is outgoing from this activation bar
             * @param received response to the sent message
             */
            public ActivationBar(Message sent, Message received) {
                bars = new ArrayList<ActivationBar>();
                setSent(sent);
                setReceived(received);

                calculateBars(getSent().getNextMessage());
            }

            /**
             * @param sent         first message that is outgoing from this activation bar
             * @param received     response to the sent message
             * @param hasParentBar flag that tells if this activationbar is stacked on an other one
             */
            public ActivationBar(Message sent, Message received, boolean hasParentBar) {
                this(sent, received);
                setParent(hasParentBar);
            }

            /**
             * a method that creates and stores an ActivationBar object for every activation bar that should be stacked on this on
             *
             * @param nextMessage message that follows the first message of this activation bar
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

            /**
             * sets the first message of this activation bar
             *
             * @param sent the first message of this activation bar
             */
            private void setSent(Message sent) {
                this.sent = sent;
            }

            /**
             * sets the last message of this activation bar
             *
             * @param received the last message of this activation bar
             */
            private void setReceived(Message received) {
                this.response = received;
            }

            /**
             * @param graphics
             * @param boxDrawer      a box drawer object to be used to draw the activation bars
             * @param invokeDrawer   a drawer object to be used to draw invocation messages
             * @param responseDrawer a drawer object to be used to draw response messages
             */
            public void draw(Graphics graphics, Drawer boxDrawer, Drawer invokeDrawer, Drawer responseDrawer) {

                boxDrawer.draw(graphics, calculateOwnBarStart(), calculateOwnBarEnd(), "");
                boxDrawer.draw(graphics, calculateBrotherBarStart(), calculateBrotherBarEnd(), null);

                invokeDrawer.draw(graphics, new Point2D.Double(calculateOwnBarStartX() + barWidth, calculateBarStartY()), new Point2D.Double(calculateBrotherBarStartX(), calculateBarStartY()), null);
                responseDrawer.draw(graphics, new Point2D.Double(calculateBrotherBarStartX(), calculateBarEndY()), new Point2D.Double(calculateOwnBarStartX() + barWidth, calculateBarEndY()), null);

                for (ActivationBar a : bars) {
                    a.draw(graphics, boxDrawer, invokeDrawer, responseDrawer);
                }
            }

            /**
             * sets the flag that tells if this activation bar is stacked on another one
             *
             * @param hasParentBar the flag that tells if this activation bar is stacked on another one
             */
            private void setParent(boolean hasParentBar) {
                this.hasParentBar = hasParentBar;
            }

            /**
             * returns if this activation bar is stacked on another one
             *
             * @return
             */
            private Boolean hasParent() {
                return hasParentBar;
            }

            /**
             * return the first message of this activation bar
             *
             * @return the first message of this activation bar
             */
            public Message getSent() {
                return sent;
            }

            /**
             * return the last message of this activation bar
             *
             * @return the last message of this activation bar
             */
            public Message getResponse() {
                return response;
            }

            /**
             * returns x-coordinate for the start point of this activation bar
             *
             * @return x-coordinate for the start point of this activation bar
             */
            private double calculateOwnBarStartX() {
                if (hasParent()) {
                    return getSent().getSender().getXLocationOfLifeline();
                } else {
                    return getSent().getSender().getXLocationOfLifeline() - (barWidth / 2);
                }
            }

            /**
             * returns y-coordinate for the start point of this activation bar and for the one that is created because of the outgoing message
             *
             * @return x-coordinate for the start point of this activation bar
             */
            private double calculateBarStartY() {
                return getSent().getyLocation();
            }

            /**
             * returns y-coordinate for the end point of this activation bar and for the one that is created because of the outgoing message
             *
             * @return x-coordinate for the start point of this activation bar
             */
            private double calculateBarEndY() {
                return getResponse().getyLocation();
            }

            /**
             * returns x-coordinate for the end point of this activation bar
             *
             * @return x-coordinate for the end point of this activation bar
             */
            private double calculateOwnBarEndX() {
                if (hasParent()) {
                    return getResponse().getReceiver().getXLocationOfLifeline() + barWidth;
                } else {
                    return getSent().getSender().getXLocationOfLifeline() + (barWidth / 2);
                }
            }

            /**
             * returns x-coordinate for the end point of the activation bar that is created because of the outgoing message
             *
             * @return x-coordinate for the end point of the activation bar that is created because of the outgoing message
             */
            private double calculateBrotherBarEndX() {
                return getResponse().getSender().getXLocationOfLifeline() + (barWidth / 2);
            }

            /**
             * returns x-coordinate for the start point of the activation bar that is created because of the outgoing message
             *
             * @return x-coordinate for the start point of the activation bar that is created because of the outgoing message
             */
            private double calculateBrotherBarStartX() {
                return getSent().getReceiver().getXLocationOfLifeline() - (barWidth / 2);
            }

            /**
             * calculates the start point of this activation bar
             *
             * @return the start point of this activation bar
             */
            private Point2D calculateOwnBarStart() {
                return new Point2D.Double(calculateOwnBarStartX(), calculateBarStartY());
            }

            /**
             * calculates the end point of this activation bar
             *
             * @return the end point of this activation bar
             */
            private Point2D calculateOwnBarEnd() {
                return new Point2D.Double(calculateOwnBarEndX(), calculateBarEndY());
            }

            /**
             * calculates the start point of the activation bar that is created because of the outgoing message
             *
             * @return the start point of the activation bar that is created because of the outgoing message
             */
            private Point2D calculateBrotherBarStart() {
                return new Point2D.Double(calculateBrotherBarStartX(), calculateBarStartY());
            }

            /**
             * calculates the end point of the activation bar that is created because of the outgoing message
             *
             * @return the end point of the activation bar that is created because of the outgoing message
             */
            private Point2D calculateBrotherBarEnd() {
                return new Point2D.Double(calculateBrotherBarEndX(), calculateBarEndY());
            }
        }
    }
}