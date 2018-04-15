package repo.party;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import diagram.DiagramElement;
import diagram.party.Party;
import diagram.party.Object;
import exceptions.DomainException;

public class PartyRepo {

    public static final int ACTORWIDTH = 50;
    public static final int ACTORHEIGHT = 50;

    public static final int OBJECTWIDTH = 80;
    public static final int OBJECTHEIGHT = 50;

    private Map<Party, Point2D> partyPoint2DMap;

    public PartyRepo(){
        this(new HashMap<>());
    }

    public PartyRepo(Map<Party, Point2D> labelPoint2DMap){
        this.setPartyPoint2DMap(labelPoint2DMap);
    }

    private void setPartyPoint2DMap(Map<Party, Point2D> partyPoint2DMap) throws IllegalArgumentException{
        if(partyPoint2DMap == null){
            throw new IllegalArgumentException("map may not be null");
        }
        this.partyPoint2DMap = partyPoint2DMap;
    }

    public Map<Party, Point2D> getMap(){
        return this.partyPoint2DMap;
    }

    public Party getPartyAtPosition(Point2D location){
        return this.getMap().entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(location))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public Point2D getLocationOfParty(Party party){
        return this.getMap().get(party);
    }

    public void removeParty(Party party){
        this.getMap().remove(party);
    }

    public void removePartyByPosition(Point2D location) throws DomainException{
        Party l = this.getPartyAtPosition(location);
        this.removeParty(l);
    }

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

    private int getXOffsetOfLifeline(Party party){
        if(party instanceof Object){
            return OBJECTWIDTH/2;
        }
        return 0;
    }

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

    public void updatePartyPosition(Point2D newPosition, Party party){
        this.getMap().put(party, newPosition);
    }

    public void addPartyWithLocation(Party party, Point2D location){
        this.getMap().put(party, location);
    }

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