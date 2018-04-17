package figures;

import diagram.Diagram;
import diagram.label.Label;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.message.ResultMessage;
import diagram.party.Actor;
import diagram.party.Object;
import diagram.party.Party;

import facade.DomainFacade;
import figures.Drawer.*;
import figures.Drawer.DiagramSpecificDrawers.*;
import figures.helperClasses.Pair;
import repo.diagram.CommunicationRepo;
import repo.diagram.DiagramRepo;
import repo.diagram.SequenceRepo;
import repo.label.LabelRepo;
import repo.message.CommunicationMessageRepo;
import repo.message.MessageRepo;
import repo.message.SequenceMessageRepo;
import repo.party.PartyRepo;
import subwindow.Subwindow;
import util.PartyPair;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

/**
 * a class that converts the data from the diagram model to drawn figures of the program's window
 */
public class FigureConverter {

    //TODO Die lijn waar parties mogen in een sequence diagram
    //TODO ooit: Sequence helper voor activation bar op een manier verplaatsen naar domain(?).

    private SequenceFigureConverter sequenceFC;
    private CommunicationFigureConverter communicationFC;

    private Drawer subwindowDrawer;

    /**
     * default constructor
     */
    public FigureConverter() {
        sequenceFC = new SequenceFigureConverter();
        communicationFC = new CommunicationFigureConverter();
        subwindowDrawer = new SubwindowDrawer();
    }


    /**
     * main draw function
     *
     * @param graphics   object used to draw on the program's window
     * @param subwindows the subwindows to be drawn on the controller
     */
    public void draw(Graphics graphics, List<Subwind> subwindows) {

        //SubwindowLevel, klasse met een subwindow en een level. Deze draw krijgt een lijst van
        //SubwindowLevels ipv Subwindows en itereer hierover in de foreach en doe nekeer getSub (that's probably it).

        for (Subwindow sub : subwindows) {
            drawSubwindow(graphics, sub.getPosition(), sub.getWidth(), sub.getHeight());

            if (sub.getFacade().getActiveRepo() instanceof SequenceRepo) {
                sequenceFC.draw(graphics, sub.getFacade().getActiveRepo(), sub.getFacade().getDiagram(), sub.getSelectedElement());
            } else if (sub.getFacade().getActiveRepo() instanceof SequenceRepo) {
                communicationFC.draw(graphics, sub.getFacade().getActiveRepo(), sub.getFacade().getDiagram(), sub.getSelectedElement());
            }
        }
    }

    /**
     * method that draws a subwindow
     *
     * @param graphics object used to draw on the program's window
     * @param position coordinate of the top-left point of the subwindow
     * @param width the subwindow's width
     * @param height the subwindow's height
     */
    private void drawSubwindow(Graphics graphics, Point2D position, int width, int height) {
        subwindowDrawer.draw(graphics, position, new Point2D.Double(position.getX() + width, position.getY() + height), null,0,0,2000,2000);
    }

    /*
    //x-coordinaat fout, houd geen rekening met activation bars (denk ik (?))
    private void drawSequenceMessages(Graphics graphics, SequenceMessageRepo messageRepo, Map<Party, Point2D> partyMap) {
        //integer is y-location
        Map<Message, Integer> messageMap = ((SequenceMessageRepo) messageRepo).getMap();

        for (Map.Entry<Message, Integer> entry : messageMap.entrySet()) {
            Point2D startLocation = partyMap.get(entry.getKey().getSender());
            Point2D endLocation = partyMap.get(entry.getKey().getReceiver());
            startLocation.setLocation(startLocation.getX(), entry.getValue());
            endLocation.setLocation(endLocation.getX(), entry.getValue());

            if (entry.getKey() instanceof ResultMessage) {
                responseMessageDrawingStrategy.draw(graphics, startLocation, endLocation, "");
            } else {
                invokeMessageDrawingStrategy.draw(graphics, startLocation, endLocation, "");
            }
        }
    }
    */

    /*
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
     * method that uses the a label drawer to draw the label on the controller
     *
     * @param graphics object used to draw on the program's window
     * @param point    point slightly to the top-left of the label string
     * @param label    string to be drawn

    private void drawLabel(Graphics graphics, Point2D point, String label) {
        labelDrawingStrategy.draw(graphics, point, null, label);
    }

    /**
     * method that uses the a party drawer to draw a party on the controller
     *
     * @param graphics object used to draw on the program's window
     * @param diagram  the diagram object to be drawn on the controller
     * @param p        party to be drawn on the controller

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
     * a method that uses helper classes to destruct data so activation bars and message arrows and labels can be drawn
     *
     * @param graphics object used to draw on the program's window
     * @param diagram  the diagram object to be drawn on the controller

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
    */

    /*
    /**
     * helper class for calculating message arrow and label locations

    private class CommunicationArrowAndLabelHelper {
        private List<PartyPair> pairs;

        /**
         * @param m the first message of the diagram

        public CommunicationArrowAndLabelHelper(Message m) {
            pairs = new ArrayList<PartyPair>();
            traverserMessages(m);
        }

        /**
         * a method that counts how many arrows are between two senders and saves this data in a PartyPair-object
         *
         * @param message the first message of the diagram

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

        public void draw(Graphics graphics, Drawer messageDrawer, Drawer labelDrawer) {
            for (PartyPair pair : pairs) {
                pair.draw(graphics, messageDrawer, labelDrawer);
            }
        }


        /**
         * helper class to save how many messages are sent from a specific sender to a speific receiver

        private class PartyPair extends Pair {
            private List<Message> messages;

            /**
             * @param first  the sender of a message to the second party
             * @param second the receiver of a message from the first party
             * @param m      a message that has been sent from the first party to the second

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

            public boolean equalPair(Party sender, Party receiver) {
                return sender == this.getA() && receiver == this.getB();
            }

            /**
             * adds a message that has been sent from the first stored to the second stored party in this pair
             *
             * @param message the message sent from the first stored to the second stored party in this pair

            public void addMessage(Message message) {
                messages.add(message);
            }

            /**
             * returns the sender of the messages
             *
             * @return the sender of the messages

            public Party getSender() {
                return (Party) this.getA();
            }

            /**
             * returns the receiver of the messages
             *
             * @return the receiver of the messages

            public Party getReceiver() {
                return (Party) this.getB();
            }

            /**
             * calculates start point of an arrow, position depends on how many messages are sent from the first party to the second
             *
             * @param spaceBetweenArrows
             * @return start point of the arrow

            public Point2D calculateStart(int spaceBetweenArrows) {
                return new Point2D.Double(getSender().getCoordinate().getX() + Object.WIDTH, getSender().getCoordinate().getY() + spaceBetweenArrows);
            }

            /**
             * calculates end point of an arrow, position depends on how many messages are sent from the first party to the second
             *
             * @param spaceBetweenArrows
             * @return end point of the arrow

            public Point2D calculateEnd(int spaceBetweenArrows) {
                return new Point2D.Double(getReceiver().getCoordinate().getX(), getReceiver().getCoordinate().getY() + spaceBetweenArrows);
            }

            /**
             * @param graphics      object used to draw on the program's window
             * @param messageDrawer message drawer to be use to draw the arrows between the two parties
             * @param labelDrawer   message drawer to be use to draw the label above the drawn arrows

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

            private Point2D calculateLabelStartPosition(Point2D start, Point2D end) {
                double x = Math.round((start.getX() + end.getX()) / 2);
                double y = Math.round((start.getY() + end.getY()) / 2);

                return new Point2D.Double(x, y);
            }
        }

    }
    */


    /**
     * helper class for calculating message arrow and activation bar locations
     */

}