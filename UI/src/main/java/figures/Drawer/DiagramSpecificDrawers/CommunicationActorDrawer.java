package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.ActorDrawer;

import java.awt.*;
import java.awt.geom.Point2D;

public class CommunicationActorDrawer extends ActorDrawer{

    private static CommunicationActorDrawer instance = null;

    private CommunicationActorDrawer(){

    }

    public static CommunicationActorDrawer getInstance(){
        if(instance == null)
            instance = new CommunicationActorDrawer();
        return instance;
    }

    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        //TODO
    }
}
