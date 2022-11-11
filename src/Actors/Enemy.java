package Actors;

import Util.Position2D;
import java.awt.*;

public class Enemy extends AbstractActor
{
    // TODO:
    private int _health;
    private int _movementSpeed;

    public Enemy(Position2D<Float> pos, float szX, float szY) {
        super(pos, szX, szY);
        SetSpritePath("./data/img/enemy.png");
    }

    @Override
    public void update(float deltaT, Graphics2D g)
    {
        // TODO: or delete
        this.GetSpriteComponent().draw(g, this);

        UpdatePosition();
    }

    public void UpdatePosition(){}

    public void CheckCollision()
    {

    }
}
