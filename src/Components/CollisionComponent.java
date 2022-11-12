package Components;

import Actors.AbstractActor;

import java.util.ArrayList;
import java.util.List;

public class CollisionComponent extends RealTimeComponent
{
    // TODO:
    public CollisionComponent(AbstractActor actor)
    {
        super(actor);
        actor.SetCollisionComponent(this);
    }
    private ArrayList<ICollisionListener> _listeners = new ArrayList<ICollisionListener>();

    public void Attach(ICollisionListener listener)
    {
        this._listeners.add(listener);
    }

    public void Detach(ICollisionListener listener)
    {
        this._listeners.remove(this._listeners.indexOf(listener));
    }

    @Override
    public void update(float deltaT)
    {
        // TODO:
        for (int i = 0; i < this._listeners.size(); i++)
        {
            this._listeners.get(i).aCollisionIsHappened(this.GetAbstractActor());
        }
    }
}
