package figures.converters.diagramSub;

import diagram.Diagram;
import diagram.DiagramElement;
import diagram.label.Label;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Actor;
import diagram.party.Object;
import diagram.party.Party;
import figures.drawable.basicShapes.Arrow;
import figures.drawable.basicShapes.Box;
import figures.drawable.basicShapes.DashedArrow;
import figures.drawable.basicShapes.DashedLine;
import figures.drawable.diagramFigures.LifeLineFigure;
import view.diagram.DiagramView;
import view.label.LabelView;
import view.message.MessageView;
import view.message.SequenceMessageView;
import view.party.PartyView;
import window.diagram.DiagramSubwindow;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SequenceConverter extends DiagramConverter {

    public SequenceConverter(DiagramSubwindow diagramSubwindow) {
        super(diagramSubwindow);
        //lifeLineDrawer = new SequenceLifelineDrawer();
        //actorDrawingStrategy = new SequenceActorDrawer();
        //objectDrawingStrategy = new SequenceObjectDrawer();
        //invokeMessageDrawingStrategy = new SequenceInvokeMessageDrawer();
        //responseMessageDrawingStrategy = new SequenseResponseMessageDrawer();
    }

    /**
     * method that draws messages
     *
     * @param graphics     object used to draw on the program's window
     * @param messageView  repository containing all the coordinates of the messages in the window.diagram's diagram
     * @param partyMap     list of Party and Point2D entries
     * @param firstMessage the first message in the diagram
     */
    @Override
    protected void drawMessages(Graphics graphics, MessageView messageView, Map<Party, Point2D> partyMap, Message firstMessage) {
        SequenceActivationBarAndMessageHelper helper;
        if (firstMessage != null) {
            helper = new SequenceActivationBarAndMessageHelper(firstMessage);
            helper.draw(graphics, partyMap, ((SequenceMessageView) messageView).getMap());
        }
    }

    /**
     * method that draws the label of a message
     *
     * @param graphics  object used to draw on the program's window
     * @param message   message to be drawn
     * @param labelView repository containing all the coordinates of the labels in the diagramSubwindow's diagram
     */
    @Override
    protected void drawMessageLabel(Graphics graphics, Message message, LabelView labelView) {
        String messageNumber = "";

        if (message instanceof InvocationMessage) {
            messageNumber = ((InvocationMessage) message).getMessageNumber();
        }
        Map<Label, Point2D> labelMap = labelView.getMap();

        Point2D start = getDiagramSubwindow().getAbsolutePosition(labelMap.get(message.getLabel()));
        drawLabel(graphics, start, message.toString(), getX1(), getY1(), getX2(), getY2());
    }

    /**
     * method that draws diagram specific stuff, in this case for sequence diagrams
     *
     * @param graphics        object used to draw on the program's window
     * @param repo            repository containing all the coordinates of a diagram
     * @param diagram         the diagram that will be drawn
     * @param selectedElement the currently selected element in the window.diagram
     */
    @Override
    protected void drawDiagramSpecificStuff(Graphics graphics, DiagramView repo, Diagram diagram, DiagramElement selectedElement) {
        Point2D start = getDiagramSubwindow().getAbsolutePosition(new Point2D.Double(0, 50));
        Point2D end = getDiagramSubwindow().getAbsolutePosition(new Point2D.Double(2000, 50));
        Point2D start2 = getDiagramSubwindow().getAbsolutePosition(new Point2D.Double(0, 100));
        Point2D end2 = getDiagramSubwindow().getAbsolutePosition(new Point2D.Double(2000, 100));
        new DashedLine(start, end).draw(graphics, 0, 0, (int) getDiagramSubwindow().getPosition().getX() + getDiagramSubwindow().getWidth(), (int) getDiagramSubwindow().getPosition().getY() + getDiagramSubwindow().getHeight());
        new DashedLine(start2, end2).draw(graphics, 0, 0, (int) getDiagramSubwindow().getPosition().getX() + getDiagramSubwindow().getWidth(), (int) getDiagramSubwindow().getPosition().getY() + getDiagramSubwindow().getHeight());

        drawLifeline(graphics, repo.getPartyView().getMap(), ((SequenceMessageView) repo.getMessageView()).getMap(), diagram.getFirstMessage());
    }

    /**
     * method that determines the lengths of the longest lifeline and draws these for every party
     *
     * @param graphics     object used to draw on the program's window
     * @param partyMap     list of Party and Point2D entries
     * @param messageMap   list of Message and y-coördinate entries
     * @param firstMessage the first message in the diagram
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
                Point2D point = getDiagramSubwindow().getAbsolutePosition(entry.getValue());
                if (entry.getKey() instanceof Actor) {
                    start = new Point2D.Double(point.getX(), (messageMap.get(first) + getDiagramSubwindow().getPosition().getY()) - MessageView.HEIGHT);
                    end = new Point2D.Double(point.getX(), (messageMap.get(last) + getDiagramSubwindow().getPosition().getY()) + MessageView.HEIGHT * 2);
                    new LifeLineFigure(start, end).draw(graphics, getX1(), getY1(), getX2(), getY2());
                } else {
                    start = new Point2D.Double(point.getX() + (PartyView.OBJECTWIDTH / 2), (messageMap.get(first) + getDiagramSubwindow().getPosition().getY()) - MessageView.HEIGHT);
                    end = new Point2D.Double(point.getX() + (PartyView.OBJECTWIDTH / 2), (messageMap.get(last) + getDiagramSubwindow().getPosition().getY()) + MessageView.HEIGHT * 2);
                    new LifeLineFigure(start, end).draw(graphics, getX1(), getY1(), getX2(), getY2());
                }
            }
        } else {
            for (Map.Entry<Party, Point2D> entry : partyMap.entrySet()) {
                Point2D point = getDiagramSubwindow().getAbsolutePosition(entry.getValue());
                if (entry.getKey() instanceof Actor) {
                    start = new Point2D.Double(point.getX(), point.getY() + PartyView.OBJECTHEIGHT);
                    end = new Point2D.Double(point.getX(), point.getY() + point.getY() + PartyView.OBJECTHEIGHT * 4);
                    new LifeLineFigure(start, end).draw(graphics, getX1(), getY1(), getX2(), getY2());
                } else {
                    start = new Point2D.Double(point.getX() + (PartyView.OBJECTWIDTH / 2), point.getY() + PartyView.OBJECTHEIGHT);
                    end = new Point2D.Double(point.getX() + (PartyView.OBJECTWIDTH / 2), point.getY() + PartyView.OBJECTHEIGHT * 4);
                    new LifeLineFigure(start, end).draw(graphics, getX1(), getY1(), getX2(), getY2());
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
         * method that draws activation bars
         *
         * @param graphics   object used to draw on the program's window
         * @param partyMap   list of Party and Point2D entries
         * @param messageMap list of Message and y-coördinate entries
         */
        public void draw(Graphics graphics, Map<Party, Point2D> partyMap, Map<Message, Integer> messageMap) {
            for (ActivationBar a : bars) {
                a.draw(graphics, partyMap, messageMap);
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
             * method that draws anactivation bars
             *
             * @param graphics   object used to draw on the program's window
             * @param partyMap   list of Party and Point2D entries
             * @param messageMap list of Message and y-coördinate entries
             */
            public void draw(Graphics graphics, Map<Party, Point2D> partyMap, Map<Message, Integer> messageMap) {
                new Box(calculateOwnBarStart(partyMap, messageMap), calculateOwnBarEnd(partyMap, messageMap)).draw(graphics, getX1(), getY1(), getX2(), getY2());
                new Box(calculateBrotherBarStart(partyMap, messageMap), calculateBrotherBarEnd(partyMap, messageMap)).draw(graphics, getX1(), getY1(), getX2(), getY2());

                new Arrow(new Point2D.Double(calculateOwnBarStartX(partyMap) + barWidth, calculateBarStartY(messageMap)), new Point2D.Double(calculateBrotherBarStartX(partyMap), calculateBarStartY(messageMap)))
                        .draw(graphics, getX1(), getY1(), getX2(), getY2());
                new DashedArrow(new Point2D.Double(calculateBrotherBarStartX(partyMap), calculateBarEndY(messageMap)), new Point2D.Double(calculateOwnBarStartX(partyMap) + barWidth, calculateBarEndY(messageMap)))
                        .draw(graphics, getX1(), getY1(), getX2(), getY2());

                for (ActivationBar a : bars) {
                    a.draw(graphics, partyMap, messageMap);
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
             * @param partyMap list of Party and Point2D entries
             * @return x-coordinate for the start point of this activation bar
             */
            private double calculateOwnBarStartX(Map<Party, Point2D> partyMap) {
                int partyObjectExtraOffset = 0;
                if (getSent().getSender() instanceof Object) {
                    partyObjectExtraOffset = PartyView.OBJECTWIDTH / 2;
                }
                if (hasParent()) {
                    return getDiagramSubwindow().getAbsolutePosition(partyMap.get(getSent().getSender())).getX() + partyObjectExtraOffset;
                } else {
                    return getDiagramSubwindow().getAbsolutePosition(partyMap.get(getSent().getSender())).getX() - (barWidth / 2) + partyObjectExtraOffset;
                }
            }

            /**
             * returns y-coordinate for the start point of this activation bar and for the one that is created because of the outgoing message
             *
             * @param messageMap list of Message and y-coördinate entries
             * @return y-coordinate for the start point of this activation bar
             */
            private double calculateBarStartY(Map<Message, Integer> messageMap) {
                return messageMap.get(getSent()) + getDiagramSubwindow().getPosition().getY();
            }

            /**
             * returns y-coordinate for the end point of this activation bar and for the one that is created because of the outgoing message
             *
             * @param messageMap list of Message and y-coördinate entries
             * @return y-coordinate for the start point of this activation bar
             */
            private double calculateBarEndY(Map<Message, Integer> messageMap) {
                return messageMap.get(getResponse()) + getDiagramSubwindow().getPosition().getY();
            }

            /**
             * returns x-coordinate for the end point of this activation bar
             *
             * @param partyMap list of Party and Point2D entries
             * @return x-coordinate for the end point of this activation bar
             */
            private double calculateOwnBarEndX(Map<Party, Point2D> partyMap) {
                int partyObjectExtraOffset = 0;
                if (getSent().getSender() instanceof Object) {
                    partyObjectExtraOffset = PartyView.OBJECTWIDTH / 2;
                }
                if (hasParent()) {
                    return getDiagramSubwindow().getAbsolutePosition(partyMap.get(getResponse().getReceiver())).getX() + barWidth + partyObjectExtraOffset;
                } else {
                    return getDiagramSubwindow().getAbsolutePosition(partyMap.get(getSent().getSender())).getX() + (barWidth / 2) + partyObjectExtraOffset;
                }
            }

            /**
             * returns x-coordinate for the end point of the activation bar that is created because of the outgoing message
             *
             * @param partyMap list of Party and Point2D entries
             * @return x-coordinate for the end point of the activation bar that is created because of the outgoing message
             */
            private double calculateBrotherBarEndX(Map<Party, Point2D> partyMap) {
                int partyObjectExtraOffset = 0;
                if (getSent().getReceiver() instanceof Object) {
                    partyObjectExtraOffset = PartyView.OBJECTWIDTH / 2;
                }
                return getDiagramSubwindow().getAbsolutePosition(partyMap.get((getResponse().getSender()))).getX() + (barWidth / 2) + partyObjectExtraOffset;
            }

            /**
             * returns x-coordinate for the start point of the activation bar that is created because of the outgoing message
             *
             * @param partyMap list of Party and Point2D entries
             * @return x-coordinate for the start point of the activation bar that is created because of the outgoing message
             */
            private double calculateBrotherBarStartX(Map<Party, Point2D> partyMap) {
                int partyObjectExtraOffset = 0;
                if (getSent().getReceiver() instanceof Object) {
                    partyObjectExtraOffset = PartyView.OBJECTWIDTH / 2;
                }
                return getDiagramSubwindow().getAbsolutePosition(partyMap.get(getSent().getReceiver())).getX() - (barWidth / 2) + partyObjectExtraOffset;
            }

            /**
             * calculates the start point of this activation bar
             *
             * @param partyMap   list of Party and Point2D entries
             * @param messageMap list of Message and y-coördinate entries
             * @return the start point of this activation bar
             */
            private Point2D calculateOwnBarStart(Map<Party, Point2D> partyMap, Map<Message, Integer> messageMap) {
                return new Point2D.Double(calculateOwnBarStartX(partyMap), calculateBarStartY(messageMap));
            }

            /**
             * calculates the end point of this activation bar
             *
             * @param partyMap   list of Party and Point2D entries
             * @param messageMap list of Message and y-coördinate entries
             * @return the end point of this activation bar
             */
            private Point2D calculateOwnBarEnd(Map<Party, Point2D> partyMap, Map<Message, Integer> messageMap) {
                return new Point2D.Double(calculateOwnBarEndX(partyMap), calculateBarEndY(messageMap));
            }

            /**
             * calculates the start point of the activation bar that is created because of the outgoing message
             *
             * @param partyMap   list of Party and Point2D entries
             * @param messageMap list of Message and y-coördinate entries
             * @return the start point of the activation bar that is created because of the outgoing message
             */
            private Point2D calculateBrotherBarStart(Map<Party, Point2D> partyMap, Map<Message, Integer> messageMap) {
                return new Point2D.Double(calculateBrotherBarStartX(partyMap), calculateBarStartY(messageMap));
            }

            /**
             * calculates the end point of the activation bar that is created because of the outgoing message
             *
             * @param partyMap   list of Party and Point2D entries
             * @param messageMap list of Message and y-coördinate entries
             * @return the end point of the activation bar that is created because of the outgoing message
             */
            private Point2D calculateBrotherBarEnd(Map<Party, Point2D> partyMap, Map<Message, Integer> messageMap) {
                return new Point2D.Double(calculateBrotherBarEndX(partyMap), calculateBarEndY(messageMap));
            }
        }
    }


}

