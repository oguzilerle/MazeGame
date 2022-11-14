package Components;

import Actors.AbstractActor;
import Actors.Direction;

import java.awt.*;
import java.util.ArrayList;

public class CollisionComponent extends RealTimeComponent implements ICollisionListener
{
    private ArrayList<AbstractActor> listeners;

    public CollisionComponent(AbstractActor actor) {
        super(actor);
        listeners = new ArrayList<AbstractActor>();
    }

    public void attach(AbstractActor listener)
    {
        listeners.add(listener);
    }

    @Override
    public void update(float deltaT, Graphics2D currentDrawBuffer)
    {
        for (int i = 0; i < listeners.size(); i++)
        {
            if(this.actor.collides(this.listeners.get(i))) aCollisionIsHappened(this.listeners.get(i));
        }
    }


    @Override
    public void aCollisionIsHappened(AbstractActor actor) {
        if (actor.getClass().getName() == "Actors.Enemy" && this.actor.getClass().getName() == "Actors.Player")
        {
            if (actor.isDead()) return;
            this.actor.Kill();
        }
        if (actor.getClass().getName() == "Actors.Wall" && this.actor.getClass().getName() == "Actors.Enemy")
        {
            switch (this.actor.GetDirection())
            {
                case UP:
                    this.actor.SetDirection(Direction.DOWN);
                    break;
                case DOWN:
                    this.actor.SetDirection(Direction.UP);
                    break;
                case RIGHT:
                    this.actor.SetDirection(Direction.LEFT);
                    break;
                case LEFT:
                    this.actor.SetDirection(Direction.RIGHT);
                    break;
            }
        }
        if (actor.getClass().getName() == "Actors.PowerUp" && this.actor.getClass().getName() == "Actors.Player")
        {
            actor.Kill();
        }
        if (actor.getClass().getName() == "Actors.Wall" && this.actor.getClass().getName() == "Actors.Player")
        {
            this.actor.moveIfCollide(actor);
        }
    }
}
