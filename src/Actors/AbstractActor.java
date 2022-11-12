package Actors;

import Util.AABB;
import Util.Position2D;

import java.awt.*;

// Meta Actor Class
// Everything in the game is an actor
public abstract class AbstractActor extends AABB
{
    private boolean isAlive;
    private Direction moveDirection;
    /**
     * Constructor, directly sets the every parameter
     *
     * @param pos "top right" (wrt. the screen coordinates) of the box
     * @param szX horizontal size of the box in pixels
     * @param szY vertical size of the box in pixels
     */
    public AbstractActor(Position2D<Float> pos, float szX, float szY) {
        super(pos, szX, szY);
        moveDirection = Direction.RIGHT;
        isAlive = true;
    }
    // TODO:

    public void update(float deltaT, Graphics2D g)
    {
        // TODO:
    }

    public boolean isDead()
    {
        return !isAlive;
    }

    public Direction GetDirection()
    {
        return moveDirection;
    }

    public void SetDirection(Direction value)
    {
        moveDirection = value;
    }

    public void Kill()
    {
        this.isAlive = false;
    }
}
