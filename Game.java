import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;

public class Game implements GameInterface
{
    private static PlayerInterface player1;
    private static PlayerInterface player2;
    private static BoardInterface board1;
    private static BoardInterface board2;
    private int turn;
    private ShotStatus shot;
    private PlayerInterface victor;
    private static boolean playing;
    public static boolean illegal;
    private ShipStatus status;
    private Position position;
    private static Game game;
    private static String name1;
    private static String name2;
    private static String type1;
    private static String type2;
    private static int placed;
    private Position place;
    private static boolean set;
    
    public Game(PlayerInterface player1,PlayerInterface player2)
    {
        this.player1 = player1;
        this.player2 = player2;
        turn = 1;
        playing = true;
        victor = null;
        illegal = false;
        placed = 0;
        board1 = new Board();
        board2 = new Board();
        set = false;
    }
    
    public static void main(String[] args){
        System.out.println("MENU: ");
        System.out.println("1: Set the players");
        System.out.println("2: Load game");
        System.out.println("3: Continue game");
        System.out.println("4: Save game");
        System.out.println("5: New game");
        System.out.println("6: Exit program");
        System.out.println("Enter choice (1-6): ");
        Scanner scanner = new Scanner(System.in);
        int choice = Integer.valueOf(scanner.nextLine());
        if (choice == 1){
            System.out.println("Player1: human (h) or computer (c): ");
            String player = scanner.nextLine();
            System.out.println("Player1 name: ");
            name1 = scanner.nextLine();
            if (player.equals("c")){
                player1 = new ComputerPlayer(name1);
                type1 = "c";
            }
            else{
                player1 = new HumanConsolePlayer(name1);
                type1 = "h";
            }
            System.out.println("Player2: human (h) or computer (c): ");
            player = scanner.nextLine();
            System.out.println("Player2 name: ");
            name2 = scanner.nextLine();
            if (player.equals("h")){
                player2 = new HumanConsolePlayer(name2);
                type2 = "h";
            }
            else{
                player2 = new ComputerPlayer(name2);
                type2 = "c";
            }
            game = new Game(player1,player2);
            set = true;
            main(args);
        } else if (choice == 2){
            System.out.println("file name: ");
            String fname = scanner.nextLine();
            player1 = new HumanConsolePlayer("");
            player2 = new ComputerPlayer("");
            game = new Game(player1,player2);
            try{
                game.loadGame(fname);
            } catch (IOException e){
                System.err.println("Problem with loading from file");
            }
            main(args);
        } else if (choice == 3){
            if (placed < 10){
                playing = false;
            } else {  
                playing = true;
            }
            game.play();
        } else if (choice == 4){
            System.out.println("file name: ");
            String fname = scanner.nextLine();
            try{
                game.saveGame(fname);
            } catch (IOException e){
                System.err.println("Problem with saving to file");
            }
            main(args);
        } else if (choice == 5){
            if (set == false){
                name1 = "A";
                name2 = "B";
                player1 = new HumanConsolePlayer(name1);
                type1 = "h";
                player2 = new ComputerPlayer(name2);
                type2 = "c";
            }
            System.out.println("N = No ship, I = Intact, H = Hit, S = Sunk, M = Miss");
            game = new Game(player1,player2);
            playing = false;
            game.play();
        } else {
            System.exit(0);
        }
    }
    
    public PlayerInterface play(){
        if (playing == false){
            ShipInterface ship1 = new Ship(5);
            ShipInterface ship2 = new Ship(4);
            ShipInterface ship3 = new Ship(3);
            ShipInterface ship4 = new Ship(3);
            ShipInterface ship5 = new Ship(2);
            ShipInterface ship6 = new Ship(5);
            ShipInterface ship7 = new Ship(4);
            ShipInterface ship8 = new Ship(3);
            ShipInterface ship9 = new Ship(3);
            ShipInterface ship10 = new Ship(2);
            try{
                board1.toString();
                if (placed == 0){
                    player1.choosePlacement(ship1,board1);
                    if (illegal == true){
                        victor = player2;
                        System.out.println(player2.toString()+" won");
                        return victor;
                    }
                    placed += 1;
                }
                if (placed == 1){
                    player1.choosePlacement(ship2,board1);
                    if (illegal == true){
                        victor = player2;
                        System.out.println(player2.toString()+" won");
                        return victor;
                    }
                    placed += 1;
                }
                if (placed == 2){
                    player1.choosePlacement(ship3,board1);
                    if (illegal == true){
                        victor = player2;
                        System.out.println(player2.toString()+" won");
                        return victor;
                    }
                    placed += 1;
                }
                if (placed == 3){
                    player1.choosePlacement(ship4,board1);
                    if (illegal == true){
                        victor = player2;
                        System.out.println(player2.toString()+" won");
                        return victor;
                    }
                    placed += 1;
                }
                if (placed == 4){
                    player1.choosePlacement(ship5,board1);
                    if (illegal == true){
                        victor = player2;
                        System.out.println(player2.toString()+" won");
                        return victor;
                    }
                    placed += 1;
                }
                if (placed == 5){
                    player2.choosePlacement(ship6,board2);
                    if (illegal == true){
                        victor = player1;
                        System.out.println(player1.toString()+" won");
                        return victor;
                    }
                    placed += 1;
                }
                if (placed == 6){
                    player2.choosePlacement(ship7,board2);
                    if (illegal == true){
                        victor = player1;
                        System.out.println(player1.toString()+" won");
                        return victor;
                    }
                    placed += 1;
                }
                if (placed == 7){
                    player2.choosePlacement(ship8,board2);
                    if (illegal == true){
                        victor = player1;
                        System.out.println(player1.toString()+" won");
                        return victor;
                    }
                    placed += 1;
                }
                if (placed == 8){
                    player2.choosePlacement(ship9,board2);
                    if (illegal == true){
                        victor = player1;
                        System.out.println(player1.toString()+" won");
                        return victor;
                    }
                    placed += 1;
                }
                if (placed == 9){
                    player2.choosePlacement(ship10,board2);
                    if (illegal == true){
                        victor = player1;
                        System.out.println(player1.toString()+" won");
                        return victor;
                    }
                    placed += 1;
                }
            } catch (PauseException e){
                System.err.println("Game paused");
                return null;
            }
        }
        playing = true;;
        while (playing == true){
            if (turn == 1){
                try{
                    position = player1.chooseShot();
                    System.out.println(name1+"'s board: ");
                    board2.shoot(position);
                    status = board2.getStatus(position);
                } catch (PauseException e){
                    System.err.println("Game paused");
                    String[] args = {};
                    main(args);
                    return null;
                } catch (InvalidPositionException e){
                    victor = player2;
                    System.out.println(player2.toString()+" won");
                    return victor;
                }
                if (status.equals(ShipStatus.NONE)){
                    shot = ShotStatus.MISS;
                } else if (status.equals(ShipStatus.INTACT) || status.equals(ShipStatus.HIT)){
                    shot = ShotStatus.HIT;
                } else if (status.equals(ShipStatus.SUNK)){
                    shot = ShotStatus.SUNK;
                    for (int i=0;i<10;i++){
                        for (int j=0;j<10;j++){
                            try{
                                Position check = new Position(j+1,i+1);
                                if (board2.getStatus(check).equals(ShipStatus.SUNK)){
                                    player1.shotResult(check,shot);
                                }
                            } catch (InvalidPositionException e){
                                System.err.println("InvalidPositionException: " + e);
                                Game.illegal = true;
                            }
                        }
                    }
                }
                System.out.println(name2+"'s board: ");
                player1.shotResult(position,shot);
                player2.opponentShot(position);
                if (board2.allSunk() == true){
                    victor = player1;
                    System.out.println(player1.toString()+" won");
                    return victor;
                } else {
                    turn = 2;
                }
            } else if (turn == 2){
                try{
                    position = player2.chooseShot();
                    System.out.println(name2+"'s board: ");
                    board1.shoot(position);
                    status = board1.getStatus(position);
                } catch (PauseException e){
                    System.err.println("Game paused");
                    String[] args = {};
                    main(args);
                    return null;
                } catch (InvalidPositionException e){
                    victor = player1;
                    System.out.println(player1.toString()+" won");
                    return victor;
                }
                if (status.equals(ShipStatus.NONE)){
                    shot = ShotStatus.MISS;
                } else if (status.equals(ShipStatus.INTACT) || status.equals(ShipStatus.HIT)){
                    shot = ShotStatus.HIT;
                } else if (status.equals(ShipStatus.SUNK)){
                    shot = ShotStatus.SUNK;
                    for (int i=0;i<10;i++){
                        for (int j=0;j<10;j++){
                            try{
                                Position check = new Position(j+1,i+1);
                                if (board1.getStatus(check).equals(ShipStatus.SUNK)){
                                    player2.shotResult(check,shot);
                                }
                            } catch (InvalidPositionException e){
                                System.err.println("InvalidPositionException: " + e);
                                Game.illegal = true;
                            }
                        }
                    }
                }
                System.out.println(name1+"'s board: ");
                player2.shotResult(position,shot);
                player1.opponentShot(position);
                if (board1.allSunk() == true){
                    victor = player2;
                    System.out.println(player2.toString()+" won");
                    return victor;
                } else {
                    turn = 1;
                }
            }
        }
        return victor;
    }
    
    public void saveGame(String filename) throws IOException{
        try{
            FileWriter f = new FileWriter(filename);
            f.write(String.valueOf(turn));
            f.write(";");
            f.write(String.valueOf(placed));
            f.write(";");
            f.write(name1);
            f.write(";");
            f.write(type1);
            f.write(";");
            f.write(board1.toString());
            f.write(";");
            f.write(name2);
            f.write(";");
            f.write(type2);
            f.write(";");
            f.write(board2.toString());
            f.write(";");
            f.close();
        }
        catch(IOException e){
            System.err.println("IOException " + e);
        }
    }
    
    public void loadGame(String filename) throws IOException{
        try{
            Scanner sc = new Scanner(new File(filename));
            sc.useDelimiter(";");
            int new_turn = sc.nextInt();
            int new_placed = sc.nextInt();
            name1 = sc.next();
            type1 = sc.next();
            String table1 = sc.next();
            table1 = table1.replace("\n","");
            name2 = sc.next();
            type2 = sc.next();
            String table2 = sc.next();
            table2 = table2.replace("\n","");
            if (type1.equals("h")){
                player1 = new HumanConsolePlayer(name1);
            } else {
                player1 = new ComputerPlayer(name1);
            }
            if (type2.equals("h")){
                player2 = new HumanConsolePlayer(name2);
            } else {
                player2 = new ComputerPlayer(name2);
            }
            game = new Game(player1,player2);
            turn = new_turn;
            placed = new_placed;
            for (int i=0;i<10;i++){
                for (int j=0;j<10;j++){
                    int num = (i*10)+j;
                    String letter = String.valueOf(table1.charAt(num));
                    if (letter.equals("H") || letter.equals("S") || letter.equals("I")){
                        try{
                            place = new Position(j+1,i+1);
                            Ship ship = new Ship(1);
                            board1.placeShip(ship,place,false);
                            if (letter.equals("H") || letter.equals("S")){
                                board1.shoot(place);
                                player2.shotResult(place,ShotStatus.SUNK);
                            }
                        } catch (InvalidPositionException e){
                            System.err.println("InvalidPositionException: " + place);
                        } catch (ShipOverlapException e){
                            System.err.println("ShipOverlapException: " + e);
                        }
                    } else if (letter.equals("M")){
                        try{
                            place = new Position(j+1,i+1);
                            board1.shoot(place);
                            player2.shotResult(place,ShotStatus.MISS);
                        } catch (InvalidPositionException e){
                            System.err.println("InvalidPositionException: " + place);
                        }
                    }
                }
            }
            for (int i=0;i<10;i++){
                for (int j=0;j<10;j++){
                    int num = (i*10)+j;
                    String letter = String.valueOf(table2.charAt(num));
                    if (letter.equals("H") || letter.equals("S") || letter.equals("I")){
                        try{
                            place = new Position(j+1,i+1);
                            Ship ship = new Ship(1);
                            board2.placeShip(ship,place,false);
                            if (letter.equals("H") || letter.equals("S")){
                                board2.shoot(place);
                                player1.shotResult(place,ShotStatus.SUNK);
                            }
                        } catch (InvalidPositionException e){
                            System.err.println("InvalidPositionException: " + place);
                        } catch (ShipOverlapException e){
                            System.err.println("ShipOverlapException: " + e);
                        }
                    } else if (letter.equals("M")){
                        try{
                            place = new Position(j+1,i+1);
                            board2.shoot(place);
                            player1.shotResult(place,ShotStatus.MISS);
                        } catch (InvalidPositionException e){
                            System.err.println("InvalidPositionException: " + place);
                        }
                    }
                }
            }
            if (placed <10){
                playing = false;
            } else {
                playing = true;
            }
            game.play();
        } catch (IOException e){
            System.err.println("IOException " + e);
        }
    }
}
