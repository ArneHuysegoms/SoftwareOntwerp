package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.ActorDrawer;
import figures.diagramFigures.Lifeline;
import figures.diagramFigures.StickMan;

import java.awt.*;
import java.awt.geom.Point2D;

public class SequenceActorDrawer extends ActorDrawer {

    private static SequenceActorDrawer instance = null;

    private SequenceActorDrawer(){

    }

    public static SequenceActorDrawer getInstance(){
        if(instance == null)
            instance = new SequenceActorDrawer();
        return instance;
    }

    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        StickMan s = new StickMan(start);
        s.draw(graphics);
    }
}