import java.util.Scanner;
import java.util.ArrayList;

public class Player
{
    private String name;
    private boolean isVertical;
    private Position coord;
    private int x;
    private int y;
    
    public Player(String name)
    {
        this.name = name;
    }
    
    public void opponentShot(Position position){
        System.out.println("Opponent shot at " + position);
    }
    
    public String toString(){
        return name;
    }
}
