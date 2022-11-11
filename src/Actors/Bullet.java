package Actors;

import Util.Position2D;

import java.awt.*;

public class Bullet extends AbstractActor
{
    // TODO:
    private float _lifeTime = 0.7f;
    private int _speed = 300;

    public Bullet(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        SetSpritePath("./data/img/wall.png");
    }

    @Override
    public void update(float deltaT, Graphics2D g)
    {
        // TODO: or delete
    }
}
