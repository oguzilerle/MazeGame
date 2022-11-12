package Actors;

import Util.Position2D;

import java.awt.*;

public class Bullet extends AbstractActor
{
    Position2D<Float> initialPosition;
    private float time;
    /**
     * Constructor, directly sets the every parameter
     *
     * @param pos "top right" (wrt. the screen coordinates) of the box
     * @param szX horizontal size of the box in pixels
     * @param szY vertical size of the box in pixels
     */
    public Bullet(Position2D<Float> pos, float szX, float szY) {
        super(pos, szX, szY);
        initialPosition = pos;
        time = 0;
    }
    // TODO:

    @Override
    public void update(float deltaT, Graphics2D g)
    {
        move();
        time += 0.01f;
        if (time > 1.25)
        {
            this.Kill();
        }
    }

    private void move()
    {
        switch (this.GetDirection())
        {
            case UP -> this.setPos(new Position2D<Float>(this.getPos().x, Float.sum(this.getPos().y, Float.valueOf(-1.5f))));
            case DOWN -> this.setPos(new Position2D<Float>(this.getPos().x, Float.sum(this.getPos().y, Float.valueOf(1.5f))));
            case LEFT -> this.setPos(new Position2D<Float>(Float.sum(this.getPos().x, Float.valueOf(-1.5f)), this.getPos().y));
            case RIGHT -> this.setPos(new Position2D<Float>(Float.sum(this.getPos().x, Float.valueOf(1.5f)), this.getPos().y));
        }
    }

}
