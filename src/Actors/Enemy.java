package Actors;

import Components.AbstractPatrolStrategy;
import Util.Position2D;
import java.awt.*;

public class Enemy extends AbstractActor
{
    // TODO:
    private int _health;
    private int _movementSpeed;
    private AbstractPatrolStrategy _strategy;
    public Enemy(Position2D<Float> pos, float szX, float szY) {
        super(pos, szX, szY);
        SetSpritePath("./data/img/enemy.png");
    }

    @Override
    public void update(float deltaT, Graphics2D g)
    {
        // TODO: or delete
        this.GetSpriteComponent().draw(
                g, new Enemy(
                        this.getPos(),
                        this.getSizeX(),
                        this.getSizeY()));

        UpdatePosition();
    }

    public void UpdatePosition(){}

    private void SetStrategy(AbstractPatrolStrategy strategy)
    {
        this._strategy = strategy;
    }

    private void ExecuteStrategy()
    {
        //execute
    }
}
