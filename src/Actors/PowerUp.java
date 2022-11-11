package Actors;

import Util.Position2D;

import java.awt.*;

public class PowerUp extends AbstractActor
{
    public PowerUp(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        SetSpritePath("./data/img/scroll.png");
    }
    @Override
    public void update(float deltaT, Graphics2D g)
    {
        // TODO or delete
        this.GetSpriteComponent().draw(g, this);
    }
}
