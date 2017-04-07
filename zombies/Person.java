package cs2113.zombies;

import cs2113.util.Helper;

/**
 * Created by Garrett on 11/8/2016.
 */

public class Person
{
    public int xLoc;
    public int yLoc;
    public int whichWay;

    public Person(int xLoc, int yLoc)
    {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        whichWay = Helper.nextInt(4);
    }

    public void move(boolean walls[][], int changeInt)
    {
        //ZombieSim.dp.drawDot(xLoc, yLoc);
        //xLoc++;
        //yLoc++;

        int change = Helper.nextInt(changeInt);

        if(change == 0)
        {
            this.changeDirection();
        }

        if(whichWay == 0)
        {
            //System.out.println("TRUE");
            ZombieSim.dp.drawDot(xLoc, yLoc);
            if(xLoc < 200)
            {
                xLoc++;
            }

            if(xLoc >= 200 || walls[xLoc][yLoc] == true)
            {
                whichWay++;
            }
        }


        if(whichWay == 1)
        {
            ZombieSim.dp.drawDot(xLoc, yLoc);


            if(xLoc > 0)
            {
                xLoc--;
            }

            if(xLoc <= 0 || walls[xLoc][yLoc] == true)
            {
                whichWay--;
            }
        }

        if(whichWay == 2)
        {
            ZombieSim.dp.drawDot(xLoc, yLoc);
            //yLoc++;

            if(yLoc < 200)
            {
                yLoc++;
            }

            if(yLoc >= 200 || walls[xLoc][yLoc] == true)
            {
                whichWay++;
            }
        }

        if(whichWay == 3)
        {
            ZombieSim.dp.drawDot(xLoc, yLoc);
            //yLoc--;

            if(yLoc > 0)
            {
                yLoc--;
            }

            if(yLoc <= 0 || walls[xLoc][yLoc] == true)
            {
                whichWay--;
            }
        }

    }

    public void changeDirection()
    {
        int x = Helper.nextInt(4);

        whichWay = x;
    }

    public boolean collidingWith(Person p)
    {
        if(this.xLoc == p.xLoc && this.yLoc == p.yLoc)
        {
            return true;
        }

        else if(this.xLoc == p.xLoc - 1 && this.yLoc == p.yLoc)
        {
            return true;
        }

        else if(this.xLoc == p.xLoc + 1 && this.yLoc == p.yLoc)
        {
            return true;
        }

        else if(this.xLoc == p.xLoc && this.yLoc == p.yLoc +1)
        {
            return true;
        }

        else if(this.xLoc == p.xLoc && this.yLoc == p.yLoc - 1)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    public void remove(boolean[][] walls)
    {
        int xLocation = 0;
        int yLocation = 0;

        while(walls[xLocation][yLocation] == false)
        {
            xLocation = Helper.nextInt(200);
            yLocation = Helper.nextInt(200);
        }

        this.xLoc = xLocation;
        this.yLoc = yLocation;
        this.whichWay = 10;
    }
}

