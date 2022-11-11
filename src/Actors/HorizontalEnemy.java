package Actors;

import Util.Position2D;

public class HorizontalEnemy extends Enemy{

    public HorizontalEnemy(Position2D<Float> pos, float szX, float szY) {
        super(pos, szX, szY);
    }

    @Override
    public void UpdatePosition()
    {
        float diffX = this.getSizeX() - 0.3f * this.getSizeX();

    }
}
