package Core;

import Actors.*;
import Components.*;
import Util.AABB;
import Util.GameMapLoader;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

public class GameEngine
{
    private final Dimension screenSize;
    private final String currentMap;
    // Game Objects
    private Player player;
    // Concrete Types of the game
    private ArrayList<Wall> walls;
    private ArrayList<Enemy> enemies;
    private ArrayList<PowerUp> powerUps;
    private ArrayList<Bullet> bulletsInCirculation;
    // Add extra components if you like
    private ArrayList<IRealTimeComponent> miscComponents;

    private void ResetGame() {
        bulletsInCirculation.clear();
        walls.clear();
        enemies.clear();
        powerUps.clear();
        miscComponents.clear();
        if (player != null) player.Kill();
        GameMapLoader map = new GameMapLoader(screenSize);
        boolean mapOK = map.loadMap(this.currentMap);

        if (!mapOK) {
            System.out.println("Util.Map Load Failed!");
            System.exit(1);
        }

        InitializeWalls(map);
        InitializePowerUps(map);
        InitializeEnemies(map);
        InitializePlayer(map);
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
        miscComponents.forEach(c -> c.update(deltaT, currentDrawBuffer));
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

    private void InitializePlayer(GameMapLoader map)
    {
        this.player = new Player(
                map.getLoadedPlayerAABB().getPos(),
                map.getLoadedPlayerAABB().getSizeX(),
                map.getLoadedPlayerAABB().getSizeY(), this.bulletsInCirculation, this.miscComponents, this.enemies);

        RealTimeComponent playerComponent;
        try {playerComponent = new SpriteComponent(this.player, "./data/img/player.png");
            this.miscComponents.add(playerComponent);}
        catch (IOException e){throw new RuntimeException(e);}

        playerComponent = new PlayerInputComponent(this.player);
        this.miscComponents.add(playerComponent);

        playerComponent = new CollisionComponent(this.player);
        this.miscComponents.add(playerComponent);
        CollisionComponent colComp = (CollisionComponent) playerComponent;
        for (int i = 0; i < this.walls.size(); i++) {colComp.attach(this.walls.get(i));}
        for (int i = 0; i < this.powerUps.size(); i++) {colComp.attach(this.powerUps.get(i));}
        for (int i = 0; i < this.enemies.size(); i++) {colComp.attach(this.enemies.get(i));}
    }

    private void InitializeWalls(GameMapLoader map)
    {
        for (int i = 0; i < map.getLoadedWallAABBs().size(); i++)
        {
            Wall currentWall = new Wall(
                    map.getLoadedWallAABBs().get(i).getPos(),
                    map.getLoadedWallAABBs().get(i).getSizeX(),
                    map.getLoadedWallAABBs().get(i).getSizeY());

            RealTimeComponent wallComponent;
            try
            {
                wallComponent = new SpriteComponent(currentWall, "./data/img/wall.png");
                this.miscComponents.add(wallComponent);
            }
            catch (IOException e)
            {
                System.out.println("Wall load failed");
                throw new RuntimeException(e);
            }

            this.walls.add(currentWall);
        }
    }

    private void InitializeEnemies(GameMapLoader map)
    {
        for (int i = 0; i < map.getLoadedEnemyXAABBs().size(); i++)
        {
            Enemy currentEnemy = new Enemy(
                    map.getLoadedEnemyXAABBs().get(i).getPos(),
                    map.getLoadedEnemyXAABBs().get(i).getSizeX(),
                    map.getLoadedEnemyXAABBs().get(i).getSizeY());
            RealTimeComponent enemyComponent;
            try
            {
                enemyComponent = new SpriteComponent(currentEnemy, "./data/img/enemy.png");
                this.miscComponents.add(enemyComponent);
            }
            catch (IOException e)
            {
                System.out.println("Wall load failed");
                throw new RuntimeException(e);
            }

            enemyComponent = new HorizontalPatrolStrategy(currentEnemy);
            this.miscComponents.add(enemyComponent);

            enemyComponent = new CollisionComponent(currentEnemy);
            this.miscComponents.add(enemyComponent);

            CollisionComponent enemyCollider = (CollisionComponent) enemyComponent;
            for (int j = 0; j < this.walls.size(); j++)
            {
                enemyCollider.attach(this.walls.get(j));
            }

            this.enemies.add(currentEnemy);
        }

        for (int i = 0; i < map.getLoadedEnemyYAABBs().size(); i++)
        {
            Enemy currentEnemy = new Enemy(
                    map.getLoadedEnemyYAABBs().get(i).getPos(),
                    map.getLoadedEnemyYAABBs().get(i).getSizeX(),
                    map.getLoadedEnemyYAABBs().get(i).getSizeY());
            RealTimeComponent enemyComponent;
            try
            {
                enemyComponent = new SpriteComponent(currentEnemy, "./data/img/enemy.png");
                this.miscComponents.add(enemyComponent);
            }
            catch (IOException e)
            {
                System.out.println("Wall load failed");
                throw new RuntimeException(e);
            }

            enemyComponent = new VerticalPatrolStrategy(currentEnemy);
            this.miscComponents.add(enemyComponent);

            enemyComponent = new CollisionComponent(currentEnemy);
            this.miscComponents.add(enemyComponent);

            CollisionComponent enemyCollider = (CollisionComponent) enemyComponent;
            for (int j = 0; j < this.walls.size(); j++)
            {
                enemyCollider.attach(this.walls.get(j));
            }

            this.enemies.add(currentEnemy);
        }

        for (int i = 0; i < map.getLoadedEnemyStationaryAABBs().size(); i++)
        {
            Enemy currentEnemy = new Enemy(
                    map.getLoadedEnemyStationaryAABBs().get(i).getPos(),
                    map.getLoadedEnemyStationaryAABBs().get(i).getSizeX(),
                    map.getLoadedEnemyStationaryAABBs().get(i).getSizeY());
            RealTimeComponent enemyComponent;
            try
            {
                enemyComponent = new SpriteComponent(currentEnemy, "./data/img/enemy.png");
                this.miscComponents.add(enemyComponent);
            }
            catch (IOException e)
            {
                System.out.println("Wall load failed");
                throw new RuntimeException(e);
            }

            enemyComponent = new CollisionComponent(currentEnemy);
            this.miscComponents.add(enemyComponent);

            CollisionComponent enemyCollider = (CollisionComponent) enemyComponent;
            for (int j = 0; j < this.walls.size(); j++)
            {
                enemyCollider.attach(this.walls.get(j));
            }

            this.enemies.add(currentEnemy);
        }
    }

    private void InitializePowerUps(GameMapLoader map)
    {
        for (int i = 0; i < map.getLoadedPowerUpAABBs().size(); i++)
        {
            PowerUp currentPowerUp = new PowerUp(
                    map.getLoadedPowerUpAABBs().get(i).getPos(),
                    map.getLoadedPowerUpAABBs().get(i).getSizeX(),
                    map.getLoadedPowerUpAABBs().get(i).getSizeY());

            RealTimeComponent powerUpComponent;
            try
            {
                powerUpComponent = new SpriteComponent(currentPowerUp, "./data/img/scroll.png");
                this.miscComponents.add(powerUpComponent);
            }
            catch (IOException e)
            {
                System.out.println("Wall load failed");
                throw new RuntimeException(e);
            }

            this.powerUps.add(currentPowerUp);
        }
    }
}
