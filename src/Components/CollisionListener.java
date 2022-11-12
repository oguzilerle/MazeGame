package Components;

import Actors.AbstractActor;

public class CollisionListener extends RealTimeComponent implements ICollisionListener{

    public CollisionListener(AbstractActor actor) {
        super(actor);
        actor.SetListener(this);
    }
    @Override
    public void aCollisionIsHappened(AbstractActor actor) {
        //REACT
        actor.moveIfCollide(this.GetAbstractActor());
    }
}
