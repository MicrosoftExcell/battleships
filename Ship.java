import java.util.ArrayList;

public class Ship implements ShipInterface
{
    private int size;
    private boolean sunk;
    private ArrayList<ShipStatus> squares = new ArrayList<ShipStatus>();

    public Ship(int size)
    {
        this.size = size;
        sunk = false;
        for (int i=0;i<size;i++){
            squares.add(ShipStatus.INTACT);
        }
    }

    public int getSize(){
        return size;
    }
    
    public boolean isSunk(){
        return sunk;
    }
    
    public void shoot(int offset) throws InvalidPositionException{
        if(offset<0 || offset>=size){
            throw new InvalidPositionException("Not a ship");
        }
        squares.set(offset,ShipStatus.HIT);
        if (squares.contains(ShipStatus.INTACT) == false){
            for (int i=0;i<squares.size();i++){
                squares.set(i,ShipStatus.SUNK);
            }
            sunk = true;
        }
    }
    
    public ShipStatus getStatus(int offset) throws InvalidPositionException{
        if(offset<0 || offset>=size){
            throw new InvalidPositionException("Not a ship");
        }   
        ShipStatus status = squares.get(offset);
        return status;
    }
}
