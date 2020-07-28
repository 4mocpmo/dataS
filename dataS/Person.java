import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


/**
 * Person
 *
 * @author mostafa ahmadi(9701923)
 */
public class Person {
    private String  fName;
    private String  lName;
    private Integer nationalId;
    private String  birthPlace;
    private String  birthDate;
    private String  spuseName;
    private Gender  gender;/*male or female */
    private Status  status;/*alive or dead */
    private LinkedList<Person> children; /*This list represents each person's children and can be null*/
    private Person  spouse;
    private Person parent;

    /** no arg constructor */
    public Person(){

    }
    /** constructor */
    public Person(String fName , String lName , Integer  nationalId , String  birthPlace){
        this.fName = fName;
        this.lName = lName;
        this.nationalId = nationalId;
        this.birthPlace = birthPlace;
    }

    public String getFName() {
        return this.fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return this.lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public Integer getNationalId() {
        return this.nationalId;
    }

    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    public String getBirthPlace() {
        return this.birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(int day , int month , int year){
        if(day > 0 && day < 32 && month<13 && month > 0 && year < 2021){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(day).append("/").append(month).append("/").append(year);
            this.birthDate = stringBuilder.toString();
        }
        else throw new IllegalArgumentException("IllegalArgument");
    }

    public String getSpuseName() {
        return this.spuseName;
    }

    public void setSpuseName(String spuseName) {
        this.spuseName = spuseName;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Person getSpouse() {
        return this.spouse;
    }

    public void setSpouse(Person spouse) {
       this.spouse = spouse;
       this.spuseName = spouse.getFName() + " " + spouse.getLName();
    }


    public LinkedList<Person> getChildren() {
        if(this.children == null){
            this.children = new LinkedList<>();
            return this.children;
        }
        return this.children;

    }

    public void setChildren(LinkedList<Person> children) {
        this.children = children;
    }

    public Person getParent() {
        return this.parent;
    }

    public void setParent(Person father) {
        this.parent = father;
    }
    public List<Person> addChildrens(Person person){
        this.getChildren().add(person);
        return this.getChildren();
    }
    public String toString() {
        String s =  "-> [" +
            "fName='" + getFName() + "'" +
            ", lName='" + getLName() + "'" +
            ", nationalId='" + getNationalId() + "'" +
            ", birthPlace='" + getBirthPlace() + "'" +
            ", birthDay='" + getBirthDate() + "'";
            if(this.getParent() == null){
                s += ", parentID='" + "null" + "'";
            }
            else{
                s += ", parentName='" + getParent().getFName() + " " +getParent().getLName() +"'" ;
            }
            s += ", spouse='" + getSpuseName() + "'";
            s +=  ", gender='" + getGender() + "'" +
            ", status='" + getStatus() + "'" +
            "]";
            return s;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(fName, person.fName) && Objects.equals(lName, person.lName) && Objects.equals(nationalId, person.nationalId) && Objects.equals(birthPlace, person.birthPlace) && Objects.equals(birthDate, person.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fName, lName, nationalId, birthPlace, birthDate);
    }
}
/** this enum choose gender for any person */
enum Gender{
    MALE,
    FEMALE
}
/** this enum choose alive or dead status for any person */
enum Status{
    A_LIVE,
    DEAD
}
