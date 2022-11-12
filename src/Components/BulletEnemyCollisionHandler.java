package Components;

import Actors.AbstractActor;
import Actors.Enemy;

import java.awt.*;
import java.util.ArrayList;

public class BulletEnemyCollisionHandler extends RealTimeComponent
{
    // TODO:
    private ArrayList<Enemy> enemies;

    public BulletEnemyCollisionHandler(AbstractActor actor, ArrayList<Enemy> enemies) {
        super(actor);
        this.enemies = enemies;
    }

    @Override
    public void update(float deltaT, Graphics2D currentDrawBuffer)
    {
        if (this.actor.isDead()) return;
        // TODO:
        for(int i = 0; i < enemies.size(); i++)
        {
            if(this.actor.collides(enemies.get(i)))
            {
                enemies.get(i).Damage();
                this.actor.Kill();
            }
        }
    }
}
