package Actors;

import Util.Position2D;

import java.awt.*;
import java.util.Random;
import java.util.random.RandomGenerator;

public class Enemy extends AbstractActor
{
    private int health;
    /**
     * Constructor, directly sets the every parameter
     *
     * @param pos "top right" (wrt. the screen coordinates) of the box
     * @param szX horizontal size of the box in pixels
     * @param szY vertical size of the box in pixels
     */
    public Enemy(Position2D<Float> pos, float szX, float szY) {
        super(pos, szX, szY);
        health = 100;
    }
    // TODO:


    @Override
    public void update(float deltaT, Graphics2D g)
    {
        // TODO: or delete
        if (health <= 0)
        {
            this.Kill();
        }
    }

    public void Damage()
    {
        this.health -= 30 + Math.random() * 10;
    }
}
