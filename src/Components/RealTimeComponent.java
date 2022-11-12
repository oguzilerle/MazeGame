package Components;

import Actors.AbstractActor;

import java.awt.*;

public class RealTimeComponent implements IRealTimeComponent{

    protected AbstractActor actor;

    public RealTimeComponent(AbstractActor actor)
    {
        this.actor = actor;
    }
    @Override
    public void update(float deltaT, Graphics2D currentDrawBuffer) {

    }
}
