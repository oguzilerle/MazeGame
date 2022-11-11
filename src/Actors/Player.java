package Actors;

import Components.SpriteComponent;
import Util.Position2D;

import java.awt.*;
import java.io.IOException;

public class Player extends AbstractActor
{
    private int _movementSpeed;

    public Player(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        SetSpritePath("./data/img/player.png");
    }


    // TODO:

    @Override
    public void update(float deltaT, Graphics2D g)
    {
        // TODO: or delete
        this.GetSpriteComponent().draw(g, this);
    }
}
