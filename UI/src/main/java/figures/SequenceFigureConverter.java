package figures;

import diagram.Diagram;
import diagram.DiagramElement;
import diagram.label.Label;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Actor;
import diagram.party.Object;
import diagram.party.Party;
import figures.Drawer.DiagramSpecificDrawers.*;
import figures.Drawer.Drawer;
import figures.basicShapes.DashedLine;
import repo.diagram.DiagramRepo;
import repo.diagram.SequenceRepo;
import repo.label.LabelRepo;
import repo.message.MessageRepo;
import repo.message.SequenceMessageRepo;
import repo.party.PartyRepo;
import subwindow.Subwindow;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SequenceFigureConverter extends Converter {

    private Drawer responseMessageDrawingStrategy,
            lifeLineDrawer;

    public SequenceFigureConverter(Subwindow subwindow) {
        super(subwindow);
        lifeLineDrawer = new SequenceLifelineDrawer();
        actorDrawingStrategy = new SequenceActorDrawer();
        objectDrawingStrategy = new SequenceObjectDrawer();
        invokeMessageDrawingStrategy = new SequenceInvokeMessageDrawer();
        responseMessageDrawingStrategy = new SequenseResponseMessageDrawer();
    }

    /**
     * @param graphics     object used to draw on the program's window
     * @param messageRepo
     * @param partyMap
     * @param firstMessage
     */
    @Override
    protected void drawMessages(Graphics graphics, MessageRepo messageRepo, Map<Party, Point2D> partyMap, Message firstMessage) {
        SequenceActivationBarAndMessageHelper helper;
        if (firstMessage != null) {
            helper = new SequenceActivationBarAndMessageHelper(firstMessage);
            helper.draw(graphics, boxDrawingStrategy, invokeMessageDrawingStrategy, responseMessageDrawingStrategy, partyMap, ((SequenceMessageRepo) messageRepo).getMap());
        }
    }

    @Override
    protected void drawDiagramSpecificStuff(Graphics graphics, DiagramRepo repo, Diagram diagram, DiagramElement selectedElement) {
        Point2D start = getSubwindow().getAbsolutePosition(new Point2D.Double(0, 50));
        Point2D end = getSubwindow().getAbsolutePosition(new Point2D.Double(2000, 50));
        Point2D start2 = getSubwindow().getAbsolutePosition(new Point2D.Double(0, 100));
        Point2D end2 = getSubwindow().getAbsolutePosition(new Point2D.Double(2000, 100));
        new DashedLine(start, end).draw(graphics, 0, 0, (int) getSubwindow().getPosition().getX() + getSubwindow().getWidth(), (int) getSubwindow().getPosition().getY() + getSubwindow().getHeight());
        new DashedLine(start2, end2).draw(graphics, 0, 0, (int) getSubwindow().getPosition().getX() + getSubwindow().getWidth(), (int) getSubwindow().getPosition().getY() + getSubwindow().getHeight());

        drawLifeline(graphics,repo.getPartyRepo().getMap(),((SequenceMessageRepo)repo.getMessageRepo()).getMap(),diagram.getFirstMessage());
    }

    /**
     * method that determines the lengths of the longest lifeline and draws these for every party
     *
     * @param graphics object used to draw on the program's window
     * @param partyMap the diagram object to be drawn on the controller
     */
    private void drawLifeline(Graphics graphics, Map<Party, Point2D> partyMap, Map<Message, Integer> messageMap, Message firstMessage) {
        Message m = firstMessage;
        Point2D start, end;

        if (m != null) {
            Message last = null, first = firstMessage;
            boolean foundLast = false;
            while (!foundLast) {
                if (m.getNextMessage() != null) {
                    m = m.getNextMessage();
                } else {
                    last = m;
                    foundLast = true;
                }
            }
            for (Map.Entry<Party, Point2D> entry : partyMap.entrySet()) {
                Point2D point = getSubwindow().getAbsolutePosition(entry.getValue());
                if (entry.getKey() instanceof Actor) {
                    start = new Point2D.Double(point.getX(), (messageMap.get(first) + getSubwindow().getPosition().getY()) - MessageRepo.HEIGHT);
                    end = new Point2D.Double(point.getX(), (messageMap.get(last) + getSubwindow().getPosition().getY()) + MessageRepo.HEIGHT * 2);
                    lifeLineDrawer.draw(graphics, start, end, "", getX1(), getY1(), getX2(), getY2());
                } else {
                    start = new Point2D.Double(point.getX() + (PartyRepo.OBJECTWIDTH / 2), (messageMap.get(first) + getSubwindow().getPosition().getY()) - MessageRepo.HEIGHT);
                    end = new Point2D.Double(point.getX() + (PartyRepo.OBJECTWIDTH / 2), (messageMap.get(last) + getSubwindow().getPosition().getY()) + MessageRepo.HEIGHT * 2);
                    lifeLineDrawer.draw(graphics, start, end, "", getX1(), getY1(), getX2(), getY2());
                }
            }
        } else {
            for (Map.Entry<Party, Point2D> entry : partyMap.entrySet()) {
                Point2D point = getSubwindow().getAbsolutePosition(entry.getValue());
                if (entry.getKey() instanceof Actor) {
                    start = new Point2D.Double(point.getX(), point.getY() + PartyRepo.OBJECTHEIGHT);
                    end = new Point2D.Double(point.getX(), point.getY() + point.getY() + PartyRepo.OBJECTHEIGHT * 4);
                    lifeLineDrawer.draw(graphics, start, end, "", getX1(), getY1(), getX2(), getY2());
                } else {
                    start = new Point2D.Double(point.getX() + (PartyRepo.OBJECTWIDTH / 2), point.getY() + PartyRepo.OBJECTHEIGHT);
                    end = new Point2D.Double(point.getX() + (PartyRepo.OBJECTWIDTH / 2), point.getY() + PartyRepo.OBJECTHEIGHT * 4);
                    lifeLineDrawer.draw(graphics, start, end, "", getX1(), getY1(), getX2(), getY2());
                }
            }
        }
    }

    private class SequenceActivationBarAndMessageHelper {
        private java.util.List<ActivationBar> bars;
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
        public void draw(Graphics graphics, Drawer boxDrawer, Drawer invokeDrawer, Drawer responseDrawer, Map<Party, Point2D> partyMap, Map<Message, Integer> messageMap) {
            for (ActivationBar a : bars) {
                a.draw(graphics, boxDrawer, invokeDrawer, responseDrawer, partyMap, messageMap);
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
            public void draw(Graphics graphics, Drawer boxDrawer, Drawer invokeDrawer, Drawer responseDrawer, Map<Party, Point2D> partyMap, Map<Message, Integer> messageMap) {
                boxDrawer.draw(graphics, calculateOwnBarStart(partyMap, messageMap), calculateOwnBarEnd(partyMap, messageMap), "", getX1(), getY1(), getX2(), getY2());
                boxDrawer.draw(graphics, calculateBrotherBarStart(partyMap, messageMap), calculateBrotherBarEnd(partyMap, messageMap), null, getX1(), getY1(), getX2(), getY2());

                invokeDrawer.draw(graphics, new Point2D.Double(calculateOwnBarStartX(partyMap) + barWidth, calculateBarStartY(messageMap)), new Point2D.Double(calculateBrotherBarStartX(partyMap), calculateBarStartY(messageMap)), null, getX1(), getY1(), getX2(), getY2());
                responseDrawer.draw(graphics, new Point2D.Double(calculateBrotherBarStartX(partyMap), calculateBarEndY(messageMap)), new Point2D.Double(calculateOwnBarStartX(partyMap) + barWidth, calculateBarEndY(messageMap)), null, getX1(), getY1(), getX2(), getY2());

                for (ActivationBar a : bars) {
                    a.draw(graphics, boxDrawer, invokeDrawer, responseDrawer, partyMap, messageMap);
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
            private double calculateOwnBarStartX(Map<Party, Point2D> partyMap) {
                int partyObjectExtraOffset = 0;
                if(getSent().getSender() instanceof Object){
                    partyObjectExtraOffset = PartyRepo.OBJECTWIDTH/2;
                }
                if (hasParent()) {
                    return getSubwindow().getAbsolutePosition(partyMap.get(getSent().getSender())).getX()+partyObjectExtraOffset;
                } else {
                    return getSubwindow().getAbsolutePosition(partyMap.get(getSent().getSender())).getX() - (barWidth / 2)+partyObjectExtraOffset;
                }
            }

            /**
             * returns y-coordinate for the start point of this activation bar and for the one that is created because of the outgoing message
             *
             * @return y-coordinate for the start point of this activation bar
             */
            private double calculateBarStartY(Map<Message, Integer> messageMap) {
                return messageMap.get(getSent()) + getSubwindow().getPosition().getY();
            }

            /**
             * returns y-coordinate for the end point of this activation bar and for the one that is created because of the outgoing message
             *
             * @return y-coordinate for the start point of this activation bar
             */
            private double calculateBarEndY(Map<Message, Integer> messageMap) {
                return messageMap.get(getResponse()) + getSubwindow().getPosition().getY();
            }

            /**
             * returns x-coordinate for the end point of this activation bar
             *
             * @return x-coordinate for the end point of this activation bar
             */
            private double calculateOwnBarEndX(Map<Party, Point2D> partyMap) {
                int partyObjectExtraOffset = 0;
                if(getSent().getSender() instanceof Object){
                    partyObjectExtraOffset = PartyRepo.OBJECTWIDTH/2;
                }
                if (hasParent()) {
                    return getSubwindow().getAbsolutePosition(partyMap.get(getResponse().getReceiver())).getX() + barWidth+partyObjectExtraOffset;
                } else {
                    return getSubwindow().getAbsolutePosition(partyMap.get(getSent().getSender())).getX() + (barWidth / 2)+partyObjectExtraOffset;
                }
            }

            /**
             * returns x-coordinate for the end point of the activation bar that is created because of the outgoing message
             *
             * @return x-coordinate for the end point of the activation bar that is created because of the outgoing message
             */
            private double calculateBrotherBarEndX(Map<Party, Point2D> partyMap) {
                int partyObjectExtraOffset = 0;
                if(getSent().getReceiver() instanceof Object){
                    partyObjectExtraOffset = PartyRepo.OBJECTWIDTH/2;
                }
                return getSubwindow().getAbsolutePosition(partyMap.get((getResponse().getSender()))).getX() + (barWidth / 2)+partyObjectExtraOffset;
            }

            /**
             * returns x-coordinate for the start point of the activation bar that is created because of the outgoing message
             *
             * @return x-coordinate for the start point of the activation bar that is created because of the outgoing message
             */
            private double calculateBrotherBarStartX(Map<Party, Point2D> partyMap) {
                int partyObjectExtraOffset = 0;
                if(getSent().getReceiver() instanceof Object){
                    partyObjectExtraOffset = PartyRepo.OBJECTWIDTH/2;
                }
                return getSubwindow().getAbsolutePosition(partyMap.get(getSent().getReceiver())).getX() - (barWidth / 2)+partyObjectExtraOffset;
            }

            /**
             * calculates the start point of this activation bar
             *
             * @return the start point of this activation bar
             */
            private Point2D calculateOwnBarStart(Map<Party, Point2D> partyMap, Map<Message, Integer> messageMap) {
                return new Point2D.Double(calculateOwnBarStartX(partyMap), calculateBarStartY(messageMap));
            }

            /**
             * calculates the end point of this activation bar
             *
             * @return the end point of this activation bar
             */
            private Point2D calculateOwnBarEnd(Map<Party, Point2D> partyMap, Map<Message, Integer> messageMap) {
                return new Point2D.Double(calculateOwnBarEndX(partyMap), calculateBarEndY(messageMap));
            }

            /**
             * calculates the start point of the activation bar that is created because of the outgoing message
             *
             * @return the start point of the activation bar that is created because of the outgoing message
             */
            private Point2D calculateBrotherBarStart(Map<Party, Point2D> partyMap, Map<Message, Integer> messageMap) {
                return new Point2D.Double(calculateBrotherBarStartX(partyMap), calculateBarStartY(messageMap));
            }

            /**
             * calculates the end point of the activation bar that is created because of the outgoing message
             *
             * @return the end point of the activation bar that is created because of the outgoing message
             */
            private Point2D calculateBrotherBarEnd(Map<Party, Point2D> partyMap, Map<Message, Integer> messageMap) {
                return new Point2D.Double(calculateBrotherBarEndX(partyMap), calculateBarEndY(messageMap));
            }
        }
    }


}

