package Core;

import Actors.*;
import Components.*;
import Util.AABB;
import Util.GameMapLoader;
import Util.Position2D;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

public class GameEngine
{
    private final Dimension screenSize;
    private final String currentMap;
    // Game Objects
    private AbstractActor player;
    // Concrete Types of the game
    private ArrayList<Wall> walls;
    private ArrayList<Enemy> enemies;
    private ArrayList<PowerUp> powerUps;
    private ArrayList<Bullet> bulletsInCirculation;
    // Add extra components if you like
    private ArrayList<IRealTimeComponent> miscComponents;

    private void ResetGame()
    {
        bulletsInCirculation.clear();
        walls.clear();
        enemies.clear();
        powerUps.clear();

        GameMapLoader map = new GameMapLoader(screenSize);
        boolean mapOK = map.loadMap(this.currentMap);

        if(!mapOK)
        {
            System.out.println("Util.Map Load Failed!");
            System.exit(1);
        }

        // TODO: Add code if your design requires so
        System.out.println("Initializing Game Objects");
        InitializeGameObjects(map);
    }

    public GameEngine(String mapFilePath, Dimension screenSize)
    {
        this.currentMap = mapFilePath;
        this.screenSize = screenSize;

        this.walls = new ArrayList<Wall>();
        this.enemies = new ArrayList<Enemy>();
        this.powerUps = new ArrayList<PowerUp>();
        this.bulletsInCirculation = new ArrayList<Bullet>();
        this.miscComponents = new ArrayList<IRealTimeComponent>();
        Position2D<Float> playerPos = new Position2D<>(Float.valueOf(1), Float.valueOf(1));

        // TODO: Add code if your design requires so

        ResetGame();
    }

    public synchronized void update(float deltaT, Graphics2D currentDrawBuffer)
    {
        // ==================================== //
        // YOU SHOULD NOT CHANGE THIS FUNCTION  //
        // ============================================= //
        // THIS SHOULD ALREADY DOES EVERYTHING YOU NEED  //
        // ============================================= //
        // You can still change it though with a penalty.

        // Do update first
        walls.forEach(actor -> actor.update(deltaT, currentDrawBuffer));
        enemies.forEach(actor -> actor.update(deltaT, currentDrawBuffer));
        powerUps.forEach(actor -> actor.update(deltaT, currentDrawBuffer));
        bulletsInCirculation.forEach(actor-> actor.update(deltaT, currentDrawBuffer));
        player.update(deltaT, currentDrawBuffer);
        miscComponents.forEach(c -> c.update(deltaT));
        // Now stuff would die etc. check the states and delete
        enemies.removeIf(actor -> actor.isDead());
        powerUps.removeIf(actor -> actor.isDead());
        bulletsInCirculation.removeIf(actor -> actor.isDead());
        // If player dies game is over reset the system
        if(player.isDead())
        {
            ResetGame();
        }
        // If there are no power-ups left,
        // Player won the game!, still reset..
        if(powerUps.isEmpty())
        {
            ResetGame();
        }
        // And the game goes on forever...
    }

    private void InitializeGameObjects(GameMapLoader map)
    {
        this.player = new CollisionComponent(new Player(map.getLoadedPlayerAABB().getPos(),
                map.getLoadedPlayerAABB().getSizeX(),
                map.getLoadedPlayerAABB().getSizeY()));
        this.player = new Player(
                map.getLoadedPlayerAABB().getPos(),
                map.getLoadedPlayerAABB().getSizeX(),
                map.getLoadedPlayerAABB().getSizeY());
        SpriteComponent playerSprite;
        try
        {
            playerSprite = new SpriteComponent(this.player.GetSpritePath());
            this.player.SetSpriteComponent(playerSprite);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < map.getLoadedWallAABBs().size(); i++)
        {
            Wall currentWall = new Wall(
                    map.getLoadedWallAABBs().get(i).getPos(),
                    map.getLoadedWallAABBs().get(i).getSizeX(),
                    map.getLoadedWallAABBs().get(i).getSizeY());
            this.walls.add(currentWall);
            SpriteComponent wallSprite;
            try
            {
                wallSprite = new SpriteComponent(currentWall.GetSpritePath());
                currentWall.SetSpriteComponent(wallSprite);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i < map.getLoadedEnemyXAABBs().size(); i++)
        {
            Enemy currentEnemy = new HorizontalEnemy(
                    map.getLoadedEnemyXAABBs().get(i).getPos(),
                    map.getLoadedEnemyXAABBs().get(i).getSizeX(),
                    map.getLoadedEnemyXAABBs().get(i).getSizeY());
            this.enemies.add(currentEnemy);
            SpriteComponent enemySprite;
            try
            {
                enemySprite = new SpriteComponent(currentEnemy.GetSpritePath());
                currentEnemy.SetSpriteComponent(enemySprite);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i < map.getLoadedEnemyYAABBs().size(); i++)
        {
            Enemy currentEnemy = new VerticalEnemy(
                    map.getLoadedEnemyYAABBs().get(i).getPos(),
                    map.getLoadedEnemyYAABBs().get(i).getSizeX(),
                    map.getLoadedEnemyYAABBs().get(i).getSizeY());
            this.enemies.add(currentEnemy);
            SpriteComponent enemySprite;
            try
            {
                enemySprite = new SpriteComponent(currentEnemy.GetSpritePath());
                currentEnemy.SetSpriteComponent(enemySprite);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i < map.getLoadedEnemyStationaryAABBs().size(); i++)
        {
            Enemy currentEnemy = new StationaryEnemy(
                    map.getLoadedEnemyStationaryAABBs().get(i).getPos(),
                    map.getLoadedEnemyStationaryAABBs().get(i).getSizeX(),
                    map.getLoadedEnemyStationaryAABBs().get(i).getSizeY());
            this.enemies.add(currentEnemy);
            SpriteComponent enemySprite;
            try
            {
                enemySprite = new SpriteComponent(currentEnemy.GetSpritePath());
                currentEnemy.SetSpriteComponent(enemySprite);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i < map.getLoadedPowerUpAABBs().size(); i++)
        {
            PowerUp currentPowerUp = new PowerUp(
                    map.getLoadedPowerUpAABBs().get(i).getPos(),
                    map.getLoadedPowerUpAABBs().get(i).getSizeX(),
                    map.getLoadedPowerUpAABBs().get(i).getSizeY());
            this.powerUps.add(currentPowerUp);
            SpriteComponent powerUpSprite;
            try
            {
                powerUpSprite = new SpriteComponent(currentPowerUp.GetSpritePath());
                currentPowerUp.SetSpriteComponent(powerUpSprite);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
