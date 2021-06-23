package composite;

public class Leaf_Player extends AbstractComponent_PlayerElement{

	// 1 Create a private attribute "name", which will hold the name of the player or the team
	
	// A Leaf_Player is initialized with its name as parameter
    public Leaf_Player(String name){
    	
    	// 2 assign the name from the constructor to the class
        
    }
    
    // 3 Implements the method getName. It should return the player name.
    
    
    public void Shoot(){
        System.out.println("\t"+name+" shooted!");
    }
    
    // 4 Implements the methods Cry, SimulateInjury, EnterField. You can take the method Shoot here above as example

}
