package Actors;

import Components.*;
import Util.Position2D;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends AbstractActor
{
    private boolean firedBullet;
    private ArrayList<Bullet> activeBullets;
    private ArrayList<IRealTimeComponent> components;
    private ArrayList<Enemy> enemiesList;
    /**
     * Constructor, directly sets the every parameter
     *
     * @param pos "top right" (wrt. the screen coordinates) of the box
     * @param szX horizontal size of the box in pixels
     * @param szY vertical size of the box in pixels
     */
    public Player(Position2D<Float> pos, float szX, float szY, ArrayList<Bullet> bulletsList, ArrayList<IRealTimeComponent> components, ArrayList<Enemy> enemiesList) {
        super(pos, szX, szY);
        this.activeBullets = bulletsList;
        this.components = components;
        this.enemiesList = enemiesList;
        this.firedBullet = false;
    }

    @Override
    public void update(float deltaT, Graphics2D g)
    {
        // TODO: or delete
        if (firedBullet)
        {
            ShootBullet();
            firedBullet = false;
        }
    }

    public void Fire()
    {
        this.firedBullet = true;
    }

    public void ShootBullet()
    {
        Bullet currentBullet = new Bullet(this.getPos(),this.getSizeX(), this.getSizeY()*0.5f);
        currentBullet.SetDirection(this.GetDirection());
        RealTimeComponent bulletComponent;
        try {bulletComponent = new SpriteComponent(currentBullet, "./data/img/bullet.png");
            components.add(bulletComponent);}
        catch (IOException e){throw new RuntimeException(e);}

        CollisionComponent nbulletComponent = new CollisionComponent(currentBullet);
        for(int i = 0; i < this.enemiesList.size();i++){nbulletComponent.attach(this.enemiesList.get(i));}
        components.add(nbulletComponent);

        bulletComponent = new BulletEnemyCollisionHandler(currentBullet, this.enemiesList);
        components.add(bulletComponent);
        this.activeBullets.add(currentBullet);
    }
}
