package composite;

import java.util.ArrayList;

public class Composite_Team extends AbstractComponent_PlayerElement {

    private ArrayList<AbstractComponent_PlayerElement> children = new ArrayList<AbstractComponent_PlayerElement>();
    private String teamName;

    public Composite_Team(String teamName){
        this.teamName = teamName;
    }

    public void add(AbstractComponent_PlayerElement abstractComponent_playerElement){
    	// 1 This method should add the given element to the children array.
    }
    
    public void remove(AbstractComponent_PlayerElement abstractComponent_PlayerElement){
    	// 2 This method should remove the given element from the children array.
    }

    public void Cry(){
    	
        System.out.println(teamName+" cry now!");
        
    	/* 3 Get each children component to cry. 
        Hint: You can loop the array of children */
    }
    
    public void SimulateInjury(){
        System.out.println(teamName+" simluate injury now!");
        // 4 Get each children component to simulate an injury. 
    }

    public void EnterField(){
        System.out.println(teamName+" enter the field now!");
        
        // 4 Get each children to enter to field.
    }

    public String getName(){
    	
    	//  This method should return its own name and the name of all its children
    	// Hint: You could use a StringBuilder
       
    }
}
