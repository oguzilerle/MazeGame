package Components;

import Actors.AbstractActor;
import Actors.Direction;
import Util.Position2D;

import java.awt.*;

public class VerticalPatrolStrategy extends AbstractPatrolStrategy
{
    public VerticalPatrolStrategy(AbstractActor actor)
    {
        super(actor);
        this.actor.SetDirection(Direction.UP);
    }

    // TODO:
    @Override
    public void update(float deltaT, Graphics2D currentDrawBuffer)
    {
        if (this.actor.isDead()) return;
        // TODO:
        if (this.actor.GetDirection() == Direction.UP)
        {
            this.actor.setPos(new Position2D<Float>(this.actor.getPos().x,Float.sum(this.actor.getPos().y, Float.valueOf(-0.2f))));
        }
        else if (this.actor.GetDirection() == Direction.DOWN)
        {
            this.actor.setPos(new Position2D<Float>(this.actor.getPos().x,Float.sum(this.actor.getPos().y, Float.valueOf(0.2f))));
        }
    }
}
