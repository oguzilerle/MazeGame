package Actors;

import Components.CollisionListener;
import Util.Position2D;

import java.awt.*;

public class Wall extends AbstractActor
{
    public Wall(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        SetSpritePath("./data/img/wall.png");
    }

    @Override
    public void update(float deltaT, Graphics2D g)
    {
        // Does nothing
        this.GetSpriteComponent().draw(g, this);
    }


}
