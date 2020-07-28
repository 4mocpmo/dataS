import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
  * Main
  *
  * @aouter mostafa-ahmadi(9701923)
  */
 public class Main {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menu();
        int item = scanner.nextInt();
        while (item != 0) {
            switch (item) {
                case 1: // Add one person
                    addPerson(scanner);                                  //calling addPerson function
                    break;
                case 2: // Search for a person and edit this person's information based on national Id
                    System.out.println("\u001B[42m" + "search & edit" + "\u001B[40m");
                    System.out.println("Enter national id : ");
                    int nationalId = scanner.nextInt();
                    searchAndEdit(nationalId , scanner);                     //calling searchAndEdit function
                    break;
                case 3: // Delete one person based on national id
                    System.out.println("Enter national id : ");
                    int nationalId1 = scanner.nextInt();
                    deletePerson(nationalId1);                      // calling delete function
                    break;
                case 4:
                    System.out.println("\u001B[42m" + "show information" + "\u001B[40m");
                    System.out.println("Enter national id : ");
                    int nationalId2 = scanner.nextInt();
                    Person person = search(nationalId2, Root.getObject().GetPerson());     //searches person with search function
                    if(person == null){
                        System.out.println("\u001B[31m" + "this natinal id does not exist" + "\u001B[37m" + "\n __________________________");
                    }else{
                        System.out.println(person);  // show person info
                    }
                    break;
                case 5 : // Prints informatin about all person
                    System.out.println("\u001B[42m" + "show all person" + "\u001B[40m");
                    if(Root.getObject().GetPerson() != null ){
                        List<Person> list  = subTree(Root.getObject().GetPerson());
                        System.out.println(Root.getObject().GetPerson());
                        for (Person per : list) {
                            System.out.println(per);
                        }
                    }
                    else
                        System.out.println("\u001B[31m" + "don't exist any person" + "\u001B[37m ");
                    break;
                case 6 : //Prints all children's of any person(with national id)
                    System.out.println("\u001B[42m" + "shows all cildren" + "\u001B[40m");
                    System.out.println("Enter national id : ");
                    int nationalId3 = scanner.nextInt();
                    Person p = search(nationalId3, Root.getObject().GetPerson());
                    if(p == null)
                        System.out.println("\u001B[31m" + "this natinal id does not exist" + "\u001B[37m" + "\n __________________________");
                    else{
                        List<Person> list = p.getChildren();
                        if(list.size() == 0){
                            System.out.println("\u001B[31m" + "this person does not have any children's" + "\u001B[37m" + "\n __________________________");
                        }
                        else{
                            for (Person pers : list) {
                                System.out.println(pers);
                            }
                        }
                    }
                    break;
                case 7 : //Prints all people who are alive
                    if(Root.getObject().GetPerson() != null){
                        if(Root.getObject().GetPerson().getStatus() == Status.DEAD){
                            System.out.println(Root.getObject().GetPerson());
                        }
                        List <Person> list1 = subTree(Root.getObject().GetPerson()); // use of subTree function and get all subTree
                        for (Person per : list1) {
                            if(per.getStatus() == Status.DEAD){
                                System.out.println(per);
                            }
                        }
                    }
                    else{
                        System.out.println("does not exist any peson");
                    }
                    break;
                case 8 : //Prints any father that have more children's
                    if(Root.getObject().GetPerson() != null){
                        List<Person> list3 = new LinkedList<Person>();
                        list3.add(Root.getObject().GetPerson());
                        list3.addAll(subTree(Root.getObject().GetPerson())); // fill my list from all subTree of root
                        Person person_more_children = Root.getObject().GetPerson();
                        if(list3.size() == 1 ){
                            System.out.println("\u001B[33m" + "only exsit root" + "\u001B[37m" );
                        }
                        else{
                            for (Person person2 : list3) {
                                if(person2.getChildren().size() > person_more_children.getChildren().size() ){
                                    person_more_children = person2;
                                }
                            }
                            System.out.println(person_more_children);  //Prints any father that have more children's
                            System.out.println("number of children : " + person_more_children.getChildren().size());
                        }
                    }
                    else{
                        System.out.println("does not exist any peson");
                    }
                    break;
                case 0 : System.exit(0);
                default:                                            //for  give  any wrong argument from user
                    System.out.println("\u001B[31m" + "enter the correct option" + "\u001B[37m" );
            }
            menu();
            item = scanner.nextInt();
        }
        scanner.close();
    }

    /** this method show main menu */
    public static void menu() {
        System.out.println(" ______________________________________________________________________________________");
        System.out.println("| 1  ->    Add one person                                                              |");
        System.out.println("| 2  ->    Search for a person and edit this person's information based on national Id |");
        System.out.println("| 3  ->    Delete one person based on national id                                      |");
        System.out.println("| 4  ->    Prints information about each person individually                           |");
        System.out.println("| 5  ->    Prints informatin about all person                                          |");
        System.out.println("| 6  -> " + "\u001B[33m" + "(*)" + "\u001B[37m"+ "Prints all children's of any person(with national id)                       |");
        System.out.println("| 7  -> " + "\u001B[33m" + "(*)" + "\u001B[37m" + "Prints all people who are dead                                              |");
        System.out.println("| 8  -> " + "\u001B[33m" + "(*)" + "\u001B[37m" + "Prints any father that have more children's                                 |");
        System.out.println("| 0  -> " + "\u001B[31m" + "(-)exit " + "\u001B[37m                                                                       |");
        System.out.println("|______________________________________________________________________________________|");
        System.out.print(":");
    }
    public static void addPerson(Scanner scanner){
        Root root = Root.getObject();
        if (root.GetPerson() == null) { // first person
            System.out.println("\u001B[42m" + "This is first person and Root of tree" + "\u001B[40m");
            System.out.println("Enter first name(without space):");
            String fName = scanner.next();
            System.out.println("Enter last name(without space)");
            String lName = scanner.next();
            System.out.println("Enter national id : ");
            int nationalId = scanner.nextInt();
            System.out.println("\u001B[33m" + "__________________________" + "\u001B[37m");
            System.out.println("Enter birth Date:\n Day(1_31):");
            int day = scanner.nextInt();
            System.out.println("Month(1_12):");
            int month = scanner.nextInt();
            System.out.println("Year(<2021):");
            int year = scanner.nextInt();
            System.out.println("Enter birth place : ");
            String birthPlace = scanner.next();
            System.out.println("\u001B[33m" + "__________________________" + "\u001B[37m");
            System.out.println("(m) for male & (f) for female  (default = male):");
            char gender = scanner.next().charAt(0);
            System.out.println("(l) for alive & (d) for dead   (default = aLive): ");
            char status = scanner.next().charAt(0);
            Person person = new Person(fName, lName, nationalId, birthPlace);
            if (gender == 'f')
                person.setGender(Gender.FEMALE);
            else
                person.setGender(Gender.MALE);
            if (status == 'd')
                person.setStatus(Status.DEAD);
            else
                person.setStatus(Status.A_LIVE);
            person.setBirthDate(day, month, year);
            root.setPerson(person);
        } else {                                                        // child
            System.out.println("\u001B[42m" + "Add children" + "\u001B[40m");
            System.out.println("Enter the parent national id : ");
            int nationalId1 = scanner.nextInt();
            Person p = search(nationalId1, root.GetPerson());
            if (p == null) {
                System.out.println("\u001B[31m" + "this natinal id does not exist" + "\u001B[37m"
                        + "\n __________________________");
            }else {
                if(p.getSpuseName() == null){
                    System.out.println("\u001B[42m" + "spouse informatin for parent" + "\u001B[40m");
                    System.out.println("\u001B[33m" + "__________________________" + "\u001B[37m");
                    System.out.println("Enter national id for spouse(parent)");
                    int spouseId = scanner.nextInt();
                    Person flag1 = search(spouseId ,Root.getObject().GetPerson());
                    if(flag1 != null && ((flag1.getGender() == Gender.MALE && p.getGender() == Gender.FEMALE) ||  (flag1.getGender() == Gender.FEMALE && p.getGender() == Gender.MALE))){
                        p.setSpouse(flag1);
                        flag1.setSpouse(p);
                    }
                    else{
                        System.out.println("Enter first name for spouse(parent) ");
                        String spouseFName = scanner.next();
                        System.out.println("Enter last name for spouse(parent) ");
                        String spouseLName = scanner.next();
                        p.setSpuseName(spouseFName + " " + spouseLName);
                    }
                }
                System.out.println("\u001B[42m" + "child informatin" + "\u001B[40m");
                System.out.println("\u001B[33m" + "__________________________" + "\u001B[37m");
                System.out.println("Enter first name(without space):");
                String fName = scanner.next();
                System.out.println("Enter last name(without space)");
                String lName = scanner.next();
                System.out.println("Enter national id : ");
                int nationalId = scanner.nextInt();
                while( null != search(nationalId, Root.getObject().GetPerson())){  // if this id dons not already exist continue
                    System.out.println("\u001B[31m" + "this id already exist"+ "\u001B[37m");
                    System.out.println("1 -> try again");
                    System.out.println("\u001B[31m" + "0 -> cancel"+ "\u001B[37m");
                    int flag = scanner.nextInt();
                    if(flag == 0 ){
                        return;
                    }
                    System.out.println("Enter national id : ");
                    nationalId = scanner.nextInt();
                }
                System.out.println("\u001B[33m" + "__________________________" + "\u001B[37m");
                System.out.println("Enter birth Date:\n Day(1_31):");
                int day = scanner.nextInt();
                System.out.println("Month(1_12):");
                int month = scanner.nextInt();
                System.out.println("Year(<2021):");
                int year = scanner.nextInt();
                System.out.println("Enter birth place : ");
                String birthPlace = scanner.next();
                System.out.println("(m) for male & (f) for female  (default = male):");
                char gender = scanner.next().charAt(0);
                System.out.println("(l) for alive & (d) for dead   (default = aLive): ");
                char status = scanner.next().charAt(0);
                Person person = new Person(fName, lName, nationalId, birthPlace);
                if (gender == 'f')
                    person.setGender(Gender.FEMALE);
                else
                    person.setGender(Gender.MALE);
                if (status == 'd')
                    person.setStatus(Status.DEAD);
                else
                    person.setStatus(Status.A_LIVE);
                person.setBirthDate(day, month, year);
                person.setParent(p);
                p.addChildrens(person);
            }
        }
    }

   

    /**
     * this function searches person and edit this person info
     * @param nationalId
     */
    public static void searchAndEdit(int nationalId , Scanner scanner){
        Root root = Root.getObject();
        Person p = search(nationalId, root.GetPerson());
        if (p == null) {
            System.out.println("\u001B[31m" + "this natinal id does not exist" + "\u001B[37m"
                    + "\n __________________________");
        } else{
            System.out.println(p);
            System.out.println("\u001B[33m" + "__________________________________" + "\u001B[37m");
            System.out.println("1 -> edit first name             "+"\u001B[33m" + "|"+ "\u001B[37m");
            System.out.println("2 -> edit last  name             "+"\u001B[33m" + "|"+ "\u001B[37m");
            System.out.println("3 -> edit national id            "+"\u001B[33m" + "|"+ "\u001B[37m");
            System.out.println("4 -> edit birth date             "+"\u001B[33m" + "|"+ "\u001B[37m");
            System.out.println("5 -> edit birth place            "+"\u001B[33m" + "|"+ "\u001B[37m");
            System.out.println("6 -> edit status                 "+"\u001B[33m" + "|"+ "\u001B[37m");
            System.out.println("0 -> " + "\u001B[31m" + "done                        " + "\u001B[40m"+"\u001B[33m" + "|"+ "\u001B[37m");
            System.out.println("\u001B[33m" + "_________________________________|" + "\u001B[37m");
            int flag = scanner.nextInt();
            while (flag != 0) {
                switch (flag) {
                    case 1: // edit first name
                        System.out.println("Enter new first name(without space):");
                        String fName = scanner.next();
                        p.setFName(fName);
                        if(p.getSpouse() != null){
                            p.getSpouse().setSpouse(p);
                        }
                        break;
                    case 2: // edit last name
                        System.out.println("Enter new last name(without space):");
                        String lName = scanner.next();
                        p.setLName(lName);
                        if(p.getSpouse() != null){
                            p.getSpouse().setSpouse(p);
                        }

                        break;
                    case 3: // edit national id
                        System.out.println("Enter new national id : ");
                        int nationalId1 = scanner.nextInt();
                        Person p1 = search(nationalId1, root.GetPerson());
                        if (p1 != null) {
                            System.out.println("\u001B[31m" + "this natinal id already exist"
                                    + "\u001B[37m" + "\n __________________________");
                            break;
                        } else {
                            p.setNationalId(nationalId1);
                            // for (Person i : p.getChildren()) {
                            //     i.getParent().setNationalId(nationalId1);
                            // }
                        }
                        break;
                    case 4: // edit birth date
                        System.out.println("Enter birth new Date:\n Day(1_31):");
                        int day = scanner.nextInt();
                        System.out.println("Month(1_12):");
                        int month = scanner.nextInt();
                        System.out.println("Year(<2021):");
                        int year = scanner.nextInt();
                        p.setBirthDate(day, month, year);
                        break;
                    case 5: // edit birth place
                        System.out.println("Enter new birth place : ");
                        String birthPlace = scanner.next();
                        p.setBirthPlace(birthPlace);
                        break;
                    case 6: // edit status
                        System.out.println("(l) for alive & (d) for dead   (default = aLive): ");
                        char status = scanner.next().charAt(0);
                        if (status == 'd')
                            p.setStatus(Status.DEAD);
                        else
                            p.setStatus(Status.A_LIVE);
                        break;
                    case 0: // after edit done
                        flag = 0;
                        break;

                    default: // when user enter any character that dont related this menu
                        System.out.println("\u001B[31m" + "!!Incorrect arg" + "\u001B[37m");
                        break;
                }
                if (flag != 0) {// show menu again
                    System.out.println("\u001B[33m" + "__________________________________" + "\u001B[37m");
                    System.out.println("1 -> edit first name             "+"\u001B[33m" + "|"+ "\u001B[37m");
                    System.out.println("2 -> edit last  name             "+"\u001B[33m" + "|"+ "\u001B[37m");
                    System.out.println("3 -> edit national id            "+"\u001B[33m" + "|"+ "\u001B[37m");
                    System.out.println("4 -> edit birth date             "+"\u001B[33m" + "|"+ "\u001B[37m");
                    System.out.println("5 -> edit birth place            "+"\u001B[33m" + "|"+ "\u001B[37m");
                    System.out.println("6 -> edit status                 "+"\u001B[33m" + "|"+ "\u001B[37m");
                    System.out.println("0 -> " + "\u001B[42m" + "done                        " + "\u001B[40m"+"\u001B[33m" + "|"+ "\u001B[37m");
                    System.out.println("\u001B[33m" + "_________________________________|" + "\u001B[37m");
                    flag = scanner.nextInt();
                }
            }
        }
    }
    /**
     * this function searches person and delete it and all sub tree of person
     * @param nationalId
     */
    public static void deletePerson(int nationalId){
        Root root = Root.getObject();
        Person p1 = search(nationalId, root.GetPerson());
        if (p1 == null) {
            System.out.println("\u001B[31m" + "this natinal id does not exist" + "\u001B[37m" + "\n __________________________");
        } else {
            p1.getChildren().clear();
            if(p1.getNationalId() == root.GetPerson().getNationalId()){
                root.setPerson(null);
            }
            else {
                List<Person> list = p1.getParent().getChildren();
                for(Person i : list){
                    if(i.getNationalId() == p1.getNationalId()){
                        // p1.getFather().getChildren().set(p1.getFather().getChildren().indexOf(i), null);
                        p1.getParent().getChildren().remove(p1.getParent().getChildren().indexOf(i));
                        i = null;
                    }
                }
                p1 = null;
            }
        }
    }

    /** this method with get one person , return all of sub tree from that */
    public static List<Person> subTree(Person person) {
        // LinkedList<Person> persons = new LinkedList<>();
        List<Person> list = new CopyOnWriteArrayList<Person>();
        list.addAll(person.getChildren());
        if(list != null){
            for (Person p : list){
                if(subTree(p) != null)
                    list.addAll(subTree(p));
                else continue;
            }
        }
        return list;
    }
    
    
     /**
     * this method search for all of sub Tree from person with national id and
     * @param person -> root.getPerson
     * @param nationalId
     * @return finded person
     */
    public static Person search(int nationalID, Person person) {
        if(person == null ){
            return null;
        }
        else{
            if(person.getNationalId() == nationalID)
                return person;
            LinkedList<Person> childrens = new LinkedList<>();
            childrens = person.getChildren();
            if (childrens !=  null ) {
                for (Person p : childrens) {
                    if (p.getNationalId() == nationalID) {
                        return p;
                    }
                    Person flag = (search(nationalID , p));
                    if(flag == null ){
                      continue;
                    }
                    else
                      return flag;
                }
            }
            return null;
        }
    }
 }