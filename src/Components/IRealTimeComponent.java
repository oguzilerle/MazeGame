package Components;

import Actors.AbstractActor;
import Util.AABB;

// On a real-time system all components may want to update wrt.
// time change
public interface IRealTimeComponent
{
    public void update(float deltaT);
}
