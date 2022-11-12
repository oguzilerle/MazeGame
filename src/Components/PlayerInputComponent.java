package Components;

import Actors.AbstractActor;
import Actors.Direction;
import Actors.Player;
import Core.GameWindow;
import Util.Position2D;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerInputComponent extends RealTimeComponent implements KeyListener
{
    // Internal States
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;
    private boolean firePressed;
    // TODO: Add additional properties if required

    // TODO: Implement a constructor
    public PlayerInputComponent(AbstractActor actor)
    {
        super(actor);
        GameWindow.GetInstance().attachKeyListener(this);
    }

    @Override
    public void update(float deltaT, Graphics2D currentDrawBuffer)
    {
        // TODO:
        if (this.actor.isDead()) return;
        move();
        fire();
    }

    @Override
    public void keyTyped(KeyEvent e) { /* Do nothing */ }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = true;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = true;
        if(e.getKeyCode() == KeyEvent.VK_UP) upPressed = true;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) downPressed = true;
        // TODO: You can also change this code if you want to handle inputs differently
        // this is given as a guideline to read key events
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = false;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = false;
        if(e.getKeyCode() == KeyEvent.VK_UP) upPressed = false;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) downPressed = false;
        // Enforce release operation on fire
        if(e.getKeyCode() == KeyEvent.VK_SPACE) firePressed = true;

        // TODO: You can also change this code if you want to handle inputs differently
        // this is given as a guideline to read key events

    }

    public void move()
    {
        Float currentX = this.actor.getPos().x;
        Float currentY = this.actor.getPos().y;
        //TODO: Move player when input occurs
        if(leftPressed)
        {
            this.actor.setPos(new Position2D<Float>(Float.sum(currentX, Float.valueOf(-0.55f)), currentY));
            this.actor.SetDirection(Direction.LEFT);
        }
        if (rightPressed)
        {
            this.actor.setPos(new Position2D<Float>(Float.sum(currentX, Float.valueOf(0.55f)), currentY));
            this.actor.SetDirection(Direction.RIGHT);
        }
        if (upPressed)
        {
            this.actor.setPos(new Position2D<Float>(currentX, Float.sum(currentY, Float.valueOf(-0.6f))));
            this.actor.SetDirection(Direction.UP);
        }
        if (downPressed)
        {
            this.actor.setPos(new Position2D<Float>(currentX, Float.sum(currentY, Float.valueOf(0.6f))));
            this.actor.SetDirection(Direction.DOWN);
        }
    }

    public void fire()
    {
        if (firePressed)
        {
            Player thisPlayer = (Player) this.actor;
            thisPlayer.Fire();
            firePressed = false;
        }
    }

}
