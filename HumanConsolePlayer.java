import java.util.Scanner;
import java.util.ArrayList;

public class HumanConsolePlayer extends Player implements PlayerInterface
{
    private ArrayList<ArrayList<String>> opponent = new ArrayList<ArrayList<String>>();
    private int size;
    private boolean isVertical;
    private Position coord;
    private int x;
    private int y;
    
    public HumanConsolePlayer(String name)
    {
        super(name);
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Place ship size "+ size + " (or pause)");
        System.out.println("xcoord (1-10): ");
        String inputx = scanner.nextLine();
        if (inputx.toLowerCase().equals("pause")){
            String[] args = {};
            Game.main(args);
            throw new PauseException("Game paused");
        }
        else{
            x = Integer.valueOf(inputx);
        }
        System.out.println("ycoord (1-10): ");
        String inputy = scanner.nextLine();
        if (inputy.toLowerCase().equals("pause")){
            String[] args = {};
            Game.main(args);
            throw new PauseException("Game paused");
        }
        else{
            y = Integer.valueOf(inputy);
        }
        System.out.println("Vertical (v) or horizontal (h): ");
        String angle = scanner.nextLine();
        if (angle.toLowerCase().equals("pause")){
            String[] args = {};
            Game.main(args);
            throw new PauseException("Game paused");
        }
        else if (angle .equals("v")){
            isVertical = true;
        }
        else{
            isVertical = false;
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose shot (or pause): ");
        System.out.println("xcoord (1-10): ");
        String inputx = scanner.nextLine();
        if (inputx.toLowerCase().equals("pause")){
            String[] args = {};
            Game.main(args);
            throw new PauseException("Game paused");
        }
        else{
            x = Integer.valueOf(inputx);
        }
        System.out.println("ycoord (1-10): ");
        String inputy = scanner.nextLine();
        if (inputy.toLowerCase().equals("pause")){
            String[] args = {};
            Game.main(args);
            throw new PauseException("Game paused");
        }
        else{
            y = Integer.valueOf(inputy);
        }
        try{
            coord = new Position(x,y);
        } catch(InvalidPositionException e){
            System.err.println("InvalidPositionException: " + e);
            Game.illegal = true;
        }
        return coord;
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
