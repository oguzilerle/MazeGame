package Components;

import java.awt.*;

// On a real-time system all components may want to update wrt.
// time change
public interface IRealTimeComponent
{
    public void update(float deltaT, Graphics2D currentDrawBuffer);
}
