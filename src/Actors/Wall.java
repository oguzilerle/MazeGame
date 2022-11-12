package Actors;

import Util.Position2D;

import java.awt.*;

public class Wall extends AbstractActor
{
    /**
     * Constructor, directly sets the every parameter
     *
     * @param pos "top right" (wrt. the screen coordinates) of the box
     * @param szX horizontal size of the box in pixels
     * @param szY vertical size of the box in pixels
     */
    public Wall(Position2D<Float> pos, float szX, float szY) {
        super(pos, szX, szY);
    }

    @Override
    public void update(float deltaT, Graphics2D g)
    {
        // TODO: or delete
    }

}
