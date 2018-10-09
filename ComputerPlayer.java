import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player implements PlayerInterface
{
    private ArrayList<ArrayList<String>> opponent = new ArrayList<ArrayList<String>>();
    private boolean found;
    private boolean looking;
    private int size;
    private int count;
    private Position check;
    private boolean isVertical;
    private Position coord;
    private int x;
    private int y;
    
    public ComputerPlayer(String name)
    {
        super(name);
        found = true;
        looking = true;
        for (int i=0;i<10;i++){
            ArrayList<String> row = new ArrayList<String>();
            opponent.add(row);
            for (int j=0;j<10;j++){
                opponent.get(i).add("-");
            }
        }
    }

    public Placement choosePlacement(ShipInterface ship, BoardInterface board) throws PauseException{
        size = ship.getSize();
        Random rand = new Random();
        looking = true;
        while (looking == true){
            count = 0;
            x = rand.nextInt(10-size) + 1;   
            y = rand.nextInt(10-size) + 1;  
            int num = rand.nextInt(2) + 1;
            if (num == 1){
                isVertical = true;
                for (int i=0;i<size;i++){
                    try{
                        check = new Position(x,y+i);
                        if (board.getStatus(check).equals(ShipStatus.INTACT)){
                            count+=1;
                        }
                    } catch(InvalidPositionException e){
                        System.err.println("InvalidPositionException: " + e);
                        Game.illegal = true;
                    }
                }
                if (count == 0){
                    looking = false;
                }
            }
            else {
                isVertical = false;
                for (int i=0;i<size;i++){
                    try{
                        check = new Position(x+i,y);
                        if (board.getStatus(check).equals(ShipStatus.INTACT)){
                            count+=1;
                        }
                    } catch(InvalidPositionException e){
                        System.err.println("InvalidPositionException: " + e);
                        Game.illegal = true;
                    }
                }
                if (count == 0){
                    looking = false;
                }
            }
        }
        try{
            coord = new Position(x,y);
        } catch(InvalidPositionException e){
            System.err.println("InvalidPositionException: " + e);
            Game.illegal = true;
        }
        try{
            board.placeShip(ship,coord,isVertical);
        } catch(InvalidPositionException e){
            System.err.println("InvalidPositionException: " + e);
            Game.illegal = true;
        } catch(ShipOverlapException e){
            System.err.println("ShipOverlapException: " + e);
            Game.illegal = true;
        }    
        Placement place = new Placement(coord,isVertical);
        return place;
    }
    
    public Position chooseShot() throws PauseException{
        for (int j=0;j<10;j++){
            if (opponent.get(j).contains("H") == true){
                for (int i=0;i<10;i++){
                    if (opponent.get(j).get(i).equals("H")){
                        if(i-1>=0){
                            if (opponent.get(j).get(i-1).equals("-")){
                                x = i;
                                y = j+1;
                                try{
                                    coord = new Position(x,y);
                                } catch(InvalidPositionException e){
                                    System.err.println("InvalidPositionException: " + e);
                                    Game.illegal = true;
                                }
                                return coord;
                            }
                        } 
                        if (i+1<=9){
                            if (opponent.get(j).get(i+1).equals("-")){
                                x = i+2;
                                y = j+1;
                                try{
                                    coord = new Position(x,y);
                                } catch(InvalidPositionException e){
                                    System.err.println("InvalidPositionException: " + e);
                                    Game.illegal = true;
                                }
                                return coord;
                            }
                        } 
                        if (j-1>=0){
                            if (opponent.get(j-1).get(i).equals("-")){
                                x = i+1;
                                y = j;
                                try{
                                    coord = new Position(x,y);
                                } catch(InvalidPositionException e){
                                    System.err.println("InvalidPositionException: " + e);
                                    Game.illegal = true;
                                }
                                return coord;
                            }
                        } 
                        if (j+1<=9){
                            if (opponent.get(j+1).get(i).equals("-")){
                                x = i+1;
                                y = j+2;
                                try{
                                    coord = new Position(x,y);
                                } catch(InvalidPositionException e){
                                    System.err.println("InvalidPositionException: " + e);
                                    Game.illegal = true;
                                }
                                return coord;
                            }
                        }
                    }
                }
            }
        }
        Random rand = new Random();
        while (found == true){
            int x = rand.nextInt(10) + 1;   
            int y = rand.nextInt(10) + 1;
            try{
                coord = new Position(x,y);
            } catch(InvalidPositionException e){
                System.err.println("InvalidPositionException: " + e);
                Game.illegal = true;
            }
            if (opponent.get(y-1).get(x-1) == "-"){
                return coord;
            }
        }
        return null;
    }
    
    public void shotResult(Position position, ShotStatus status){
        int x = position.getX()-1;
        int y = position.getY()-1;
        if (status.equals(ShotStatus.HIT)){
            opponent.get(y).set(x,"H");
            for (ArrayList<String> row:opponent){
                System.out.println(row);
            }
        }
        else if (status.equals(ShotStatus.SUNK)){
            opponent.get(y).set(x,"S");
            System.out.println("Opponent sunk ship at "+position);
        }
        else{
            opponent.get(y).set(x,"M");
            for (ArrayList<String> row:opponent){
                System.out.println(row);
            }
        }
    }
}

