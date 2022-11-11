package Actors;

import Components.IRealTimeComponent;
import Components.SpriteComponent;
import Util.AABB;
import Util.Position2D;

import java.awt.*;

// Meta Actor Class
// Everything in the game is an actor
public abstract class AbstractActor extends AABB
{
    // TODO:
    private String _spritePath;
    private boolean isActive;
    private SpriteComponent _spriteComponent;

    private Position2D<Float> _movedOffset = new Position2D<>(Float.valueOf(0), Float.valueOf(0));

    public AbstractActor(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        this.isActive = true;
    }



    public void update(float deltaT, Graphics2D g)
    {
        // TODO:
    }

    public boolean isDead()
    {
        return !isActive;
    }

    public void Kill()
    {
        this.isActive = false;
    }

    public String GetSpritePath()
    {
        return _spritePath;
    }

    public void SetSpritePath(String path)
    {
        _spritePath = path;
    }

    public SpriteComponent GetSpriteComponent()
    {
        return _spriteComponent;
    }

    public void SetSpriteComponent(SpriteComponent component)
    {
        this._spriteComponent = component;
    }

    public Position2D<Float> GetMovedOffset()
    {
        return _movedOffset;
    }

    public void SetMovedOffset(Float x, Float y)
    {
        _movedOffset.x = Float.sum(_movedOffset.x, x);
        _movedOffset.y = Float.sum(_movedOffset.y, y);
    }
}
