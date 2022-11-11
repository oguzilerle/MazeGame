package Components;

import Actors.AbstractActor;

public abstract class RealTimeComponent implements IRealTimeComponent
{
    protected AbstractActor actor;

    public RealTimeComponent(AbstractActor actor)
    {
        this.actor = actor;
    }
    public void update(float deltaT)
    {
    }

    public AbstractActor GetAbstractActor()
    {
        return this.actor;
    }
}
