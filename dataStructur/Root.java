
/**
 * Person
 * 
 * @author mostafa ahmadi(9701923)
 */

/**one Root in whole project */
public class Root{
    Person person;
    /*private no arg constructor because we have one object from root */
    private Root(){

    }
    private static Root rootObj = null; //this object of root
    
    public static Root getObject(){
        if(rootObj == null)
            rootObj = new Root();
        return rootObj;
    }

    public void setPerson(Person person){
        this.person = person;
    }
    public Person GetPerson(){
        return this.person;
    }
    
}
