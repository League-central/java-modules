package composite;

//This is our interface, which define the methods to be implemented 
// by both the leaf (players) and the composite (teams)

abstract class AbstractComponent_PlayerElement {

    public void add(AbstractComponent_PlayerElement AbstractComponent_PlayerElement){
        throw new UnsupportedOperationException();
    }
    public void remove(AbstractComponent_PlayerElement AbstractComponent_PlayerElement){
        throw new UnsupportedOperationException();
    }
    public  AbstractComponent_PlayerElement getChild(int i){
        throw new UnsupportedOperationException();
    }

    public String getName(){
        throw new UnsupportedOperationException();
    }
    public void Shoot(){
        throw new UnsupportedOperationException();
    }
    public void Cry(){
        throw new UnsupportedOperationException();
    }
    public void SimulateInjury(){
        throw new UnsupportedOperationException();
    }
    public void EnterField(){
        throw new UnsupportedOperationException();
    }
}
