package Components;

import Actors.AbstractActor;

import java.awt.*;

public abstract class AbstractPatrolStrategy extends RealTimeComponent
{
    public AbstractPatrolStrategy(AbstractActor actor) {
        super(actor);
    }

    // TODO:
    @Override
    public void update(float deltaT, Graphics2D currentDrawBuffer)
    {
        // TODO:
    }
}
