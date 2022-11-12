package Components;

import Actors.AbstractActor;
import Actors.Direction;
import Util.Position2D;

import java.awt.*;

public class HorizontalPatrolStrategy extends AbstractPatrolStrategy
{
    public HorizontalPatrolStrategy(AbstractActor actor) {
        super(actor);
        this.actor.SetDirection(Direction.RIGHT);
    }

    @Override
    public void update(float deltaT, Graphics2D currentDrawBuffer)
    {
        if (this.actor.isDead()) return;
        // TODO:
        if (this.actor.GetDirection() == Direction.RIGHT)
        {
            this.actor.setPos(new Position2D<Float>(Float.sum(this.actor.getPos().x, Float.valueOf(-0.66f)), this.actor.getPos().y));
        }
        else if (this.actor.GetDirection() == Direction.LEFT)
        {
            this.actor.setPos(new Position2D<Float>(Float.sum(this.actor.getPos().x, Float.valueOf(0.66f)), this.actor.getPos().y));
        }
    }
}
