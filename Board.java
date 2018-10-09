import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Board implements BoardInterface
{
    private ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<Position>> positions = new ArrayList<ArrayList<Position>>();
    private String line;
    private String show;
    private int x;
    private int y;
    private int size;
    private int count;
    private ShipStatus status;
    
    public Board()
    {
        for (int i=0;i<10;i++){
            ArrayList<String> row = new ArrayList<String>();
            table.add(row);
            for (int j=0;j<10;j++){
                table.get(i).add("N");
            }
        }
    }
    
    public void placeShip(ShipInterface ship, Position position, boolean isVertical) throws InvalidPositionException, ShipOverlapException{
        size = ship.getSize();
        x = position.getX() -1;
        y = position.getY() -1;
        ArrayList<Position> new_ship = new ArrayList<Position>();
        positions.add(new_ship);
        if (isVertical == true){
            if (y+size<11){
                for (int i=0;i<size;i++){
                    if (table.get(y+i).get(x)=="N"){
                        table.get(y+i).set(x,"I");
                        Position create = new Position(x+1,y+i+1);
                        positions.get(positions.size()-1).add(create);
                    }
                    else{
                        for (int j=1;j<i+1;j++){
                            table.get(y+i-j).set(x,"N");
                            positions.remove(positions.size()-1);
                        }
                        throw new ShipOverlapException("Already ship on one square");
                    }
                }   
            }
            else {
                throw new InvalidPositionException("Ship can't be placed here");
            }
        }
        else{
            if (x+size<11){
                for (int i=0;i<size;i++){
                    if (table.get(y).get(x+i)=="N"){
                        table.get(y).set(x+i,"I");
                        Position create = new Position(x+i+1,y+1);
                        positions.get(positions.size()-1).add(create);
                    }
                    else{
                        for (int j=1;j<i+1;j++){
                            table.get(y).set(x+i-j,"N");
                            positions.remove(positions.size()-1);
                        }
                        throw new ShipOverlapException("Already ship on one square");
                    }
                }  
            }
            else {
                throw new InvalidPositionException("Ship can't be placed here");
            }   
        }
        toString();
    }
    
    public void shoot(Position position) throws InvalidPositionException{
        x = position.getX();
        y = position.getY();
        count = 0;
        if(x<1 || x>10){
            throw new InvalidPositionException("horizontal position not on board");
        }
        else if(y<1 || y>10){
            throw new InvalidPositionException("vertical position not on board");
        }
        if (table.get(y-1).get(x-1)=="I"){
            table.get(y-1).set(x-1,"H");
            Position compare = new Position(x,y);
            for (ArrayList<Position> list: positions){
                for (Position coord: list){
                    if (coord.getX() == compare.getX() && coord.getY() == compare.getY()){
                        for (int i=0;i<list.size();i++){
                            x = list.get(i).getX()-1;
                            y = list.get(i).getY()-1;
                            if (table.get(y).get(x) == "H"){
                                count+=1;
                            }
                        }
                        if (count == list.size()){
                            for (int j=0;j<list.size();j++){
                                x = list.get(j).getX()-1;
                                y = list.get(j).getY()-1;
                                table.get(y).set(x,"S");
                            }
                        }
                    }
                }
            }        
        } else if (table.get(y-1).get(x-1)=="N"){
            table.get(y-1).set(x-1,"M");
        }
        for (ArrayList<String> row:table){
            System.out.println(row);
        }
    }
        
    public ShipStatus getStatus(Position position) throws InvalidPositionException{
        x = position.getX() -1;
        y = position.getY() -1;
        if(x<0 || x>9){
            throw new InvalidPositionException("horizontal position not on board");
        }
        else if(y<0 || y>9){
            throw new InvalidPositionException("vertical position not on board");
        }
        if (table.get(y).get(x).equals("S")){
            status = ShipStatus.SUNK;
            return status;
        }
        else if (table.get(y).get(x).equals("I")){
            status = ShipStatus.INTACT;
            return status;
        }
        else if (table.get(y).get(x).equals("H")){
            status = ShipStatus.HIT;
            return status;
        }
        else{
            status = ShipStatus.NONE;
            return status;
        }
    }
    
    public boolean allSunk(){
        for (ArrayList<String> row: table){
            if (row.contains("I") == true){
                return false;
            }
        }
        return true;
    }
    
    public String toString(){
        show = "";
        for (ArrayList<String> row: table){
            line = "";
            for (String letter: row){
                line = line + letter;
            }
            show = show + line + "\n";
        }
        System.out.println(show);
        return show;
    }
    
    public BoardInterface clone(){
        Board new_board = new Board();
        new_board.table = table;
        new_board.positions = positions;
        return new_board;
    }
}
