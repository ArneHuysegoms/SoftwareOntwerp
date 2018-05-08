package repo.party;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import diagram.DiagramElement;
import diagram.party.Party;
import diagram.party.Object;

/**
 * a class containing the state of every party in the diagram, and everything positional on parties
 */
public class PartyRepo implements Serializable {

    public static final int ACTORWIDTH = 50;
    public static final int ACTORHEIGHT = 50;

    public static final int OBJECTWIDTH = 80;
    public static final int OBJECTHEIGHT = 50;

    private Map<Party, Point2D> partyPoint2DMap;

    /**
     * constructs a new partyrepo
     */
    public PartyRepo(){
        this(new HashMap<>());
    }

    /**
     * constructs a new partyrepo of which the state is equal to the given state
     * @param labelPoint2DMap the new map for this PartyRepo
     */
    public PartyRepo(Map<Party, Point2D> labelPoint2DMap){
        this.setPartyPoint2DMap(labelPoint2DMap);
    }

    /**
     * sets the map containing the state of the partyrepo to the given map
     * @param partyPoint2DMap the map containing the state which we want this partyrepo to have
     * @throws IllegalArgumentException if the given map is null
     */
    private void setPartyPoint2DMap(Map<Party, Point2D> partyPoint2DMap) throws IllegalArgumentException{
        if(partyPoint2DMap == null){
            throw new IllegalArgumentException("map may not be null");
        }
        this.partyPoint2DMap = partyPoint2DMap;
    }

    /**
     * @return the map containing the state of this repo
     */
    public Map<Party, Point2D> getMap(){
        return this.partyPoint2DMap;
    }

    /**
     * gets the party that is located at the given location, null if no party exists on that location
     * @param location the location on which we want to find a party
     * @return the party that is located at the given location, null if no party exists on that location
     */
    public Party getPartyAtPosition(Point2D location){
        return this.getMap().entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(location))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    /**
     * gets the location of the provided party
     * @param party the party we want to know the location of
     * @return the location of the given party
     */
    public Point2D getLocationOfParty(Party party){
        return this.getMap().get(party);
    }

    /**
     * removes the given party from this repository
     * @param party the party to remove
     */
    public void removeParty(Party party){
        this.getMap().remove(party);
    }

    /**
     * removes the party that is located at the given location, if such party exists
     * @param location the location of the party we want to remove
     */
    public void removePartyByPosition(Point2D location){
        Party l = this.getPartyAtPosition(location);
        this.removeParty(l);
    }

    /**
     * gets the distance of every party to the given point
     * @param point the point we want the distances too
     * @return a map containing the distance of every party to the given point
     */
    public Map<Party, Double> getDistancesFromPointForParties(Point2D point){
        return this.getMap().entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> getDistance(point, e.getKey())));
    }

    /**
     * @param point2D
     *        The coordinates of the mouse where the user clicked
     * @param party
     *        the party to find the distance to
     * @return
     *       returns the distance between the party and the given point
     */
    public double getDistance(Point2D point2D, Party party) {
        return this.getLocationOfParty(party).distance(point2D);
    }

    /**
     * method to find the correct location for the label of a Party
     *
     * @param party
     *          the party to find a correct labelposition for
     *
     * @return a Point2D indicating the location
     */
    public Point2D getCorrectLabelPosition(Party party){
        if(party instanceof Object){
            return new Point2D.Double(this.getLocationOfParty(party).getX() + 10,
                    this.getLocationOfParty(party).getY() + 25);
        }
        else{
            return new Point2D.Double(this.getLocationOfParty(party).getX() - 10,
                    this.getLocationOfParty(party).getY() + 50);
        }
    }

    /**
     * method to get the x location of the lifeline belonging to the party
     *
     * @param party
     *        the party to find the x location of the lifeline for
     *
     * @return returns a double which denotes the x location of the lifeline belonging to the party
     */
    public double getXLocationOfLifeline(Party party){
        return this.getLocationOfParty(party).getX() + getXOffsetOfLifeline(party);
    }

    /**
     * gets the offset on the x-axis of the lifeline of the given party
     * @param party the party we want the offset of
     * @return a integer specifying the x-axis offset of the lifeline of given party
     */
    private int getXOffsetOfLifeline(Party party){
        if(party instanceof Object){
            return OBJECTWIDTH/2;
        }
        return 0;
    }

    /**
     * gets every party that is clicked on by clicking at the specified location
     * @param clickedLocation the location on which was clicked
     * @return a set containing every party that was clicked on
     */
    public Set<DiagramElement> getClickedParties(Point2D clickedLocation){
        return this.getMap().entrySet()
                .stream()
                .filter(pair -> {
                    if(pair.getKey() instanceof Object){
                        return isClickedObject(clickedLocation, pair.getValue());
                    }
                    else if(pair.getKey() instanceof Party){
                        return isClickedActor(clickedLocation, pair.getValue());
                    }
                    return false;
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    /**
     * updates the location of the given party to the given newPosition
     * @param newPosition the new position for the party
     * @param party the party  to change the position off
     */
    public void updatePartyPosition(Point2D newPosition, Party party){
        this.getMap().put(party, newPosition);
    }

    /**
     * adds the provided party to the map with the provided location
     * @param party the party to add
     * @param location the location of the party
     */
    public void addPartyWithLocation(Party party, Point2D location){
        this.getMap().put(party, location);
    }

    /**
     * gets all the parties contained in this repo
     * @return all parties in this repo
     */
    public Set<Party> getAllParties(){
        return this.getMap().keySet();
    }

    /**
     * @param clickedLocation
     *        The coordinates of the mouse where the user clicked
     * @param actorLocation
     *        The coordinates of the actor to check
     * @return
     *        True if the clicked coordinates are within the coordinates of the image of this actor
     */
    private boolean isClickedActor(Point2D clickedLocation, Point2D actorLocation) {
        double clickX = clickedLocation.getX();
        double clickY = clickedLocation.getY();
        double startX = actorLocation.getX() - ACTORWIDTH/2;
        double startY = actorLocation.getY();
        double endX = startX + ACTORWIDTH;
        double endY = startY + ACTORHEIGHT;
        return (clickX >= startX && clickX <= endX) && (clickY >= startY && clickY <= endY);
    }

    /**
     * @param clickedLocation
     *        The coordinates of the mouse where the user clicked
     * @param objectLocation
     *        The location of the object to check
     * @return
     *        True if the clicked coordinates are within the coordinates of the image of this message
     */
    private boolean isClickedObject(Point2D clickedLocation, Point2D objectLocation) {
        double clickX = clickedLocation.getX();
        double clickY = clickedLocation.getY();
        double startX = objectLocation.getX();
        double startY = objectLocation.getY();
        double endX = startX + OBJECTWIDTH;
        double endY = startY + OBJECTHEIGHT;
        return (clickX >= startX && clickX <= endX) && (clickY >= startY && clickY <= endY);
    }
}