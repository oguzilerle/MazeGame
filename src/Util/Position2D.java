package Util;

/**
 * Simple 2D position encapsulation
 */
public class Position2D<T>
{
    public T x;
    public T y;

    public Position2D(T x, T y)
    {
        this.x = x;
        this.y = y;
    }

    public static Position2D<Float> add(Position2D<Float> first, Position2D<Float> sec)
    {
        Float x = Float.sum(first.x, sec.x);
        Float y = Float.sum(first.y, sec.y);
        Position2D<Float> addition = new Position2D<>(x,y);
        return addition;
    }
}
