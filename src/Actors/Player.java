package Actors;

import Components.CollisionComponent;
import Components.PlayerInputComponent;
import Components.SpriteComponent;
import Util.Position2D;

import java.awt.*;
import java.io.IOException;

public class Player extends AbstractActor
{
    private int _movementSpeed;
    public Graphics2D _g;
    private PlayerInputComponent _inputComponent;

    public Player(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        SetSpritePath("./data/img/player.png");
    }


    // TODO:

    @Override
    public void update(float deltaT, Graphics2D g)
    {
        // TODO: or delete
        this.GetSpriteComponent().draw(g, this);
        move(deltaT);
    }

    public PlayerInputComponent GetInputComponent()
    {
        return _inputComponent;
    }

    public void SetInputComponent(PlayerInputComponent component)
    {
        this._inputComponent = component;
    }

    private void move(float deltaT)
    {
        _inputComponent.update(deltaT);
        SetPos(Float.sum(this.getPos().x, this.GetMovedOffset().x), Float.sum(this.getPos().y, this.GetMovedOffset().y));
        ResetMovedOffset();
    }

}
