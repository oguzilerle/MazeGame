package Components;

import Actors.AbstractActor;

public class CollisionListener extends RealTimeComponent implements ICollisionListener{
    public CollisionListener(AbstractActor actor) {
        super(actor);
    }

    @Override
    public void aCollisionIsHappened(AbstractActor actor) {

    }
}
