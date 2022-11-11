package Components;

import Actors.AbstractActor;

public abstract class RealTimeComponent extends AbstractActor implements IRealTimeComponent
{
    protected AbstractActor actor;

    public RealTimeComponent(AbstractActor actor)
    {
        super(actor.getPos(),actor.getSizeX(), actor.getSizeY());
        this.actor = actor;
    }
    public void update(float deltaT)
    {
    }
}
