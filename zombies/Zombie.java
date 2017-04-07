package cs2113.zombies;

/**
 * Created by Garrett on 11/19/2016.
 */
public class Zombie extends Person
{
    public Zombie(int xLoc, int yLoc)
    {
        super(xLoc, yLoc);
    }

    public void chase(Human h)
    {
        int xDiff = this.xLoc - h.xLoc;
        int yDiff = this.yLoc - h.yLoc;

        if((xDiff > -10 && xDiff < 0))
        {
            this.whichWay = 1;
        }

        if(xDiff < 10 && xDiff > 0)
        {
            this.whichWay = 0;
        }

        if((yDiff > - 10 && yDiff < 0))
        {
            this.whichWay = 3;
        }

        if(yDiff < 10 && yDiff > 10)
        {
            this.whichWay = 2;
        }
    }
}
