import java.util.*;

public class AddressBook {
    ArrayList<PersonDetails> listContactDetails = new ArrayList<>();
    Scanner in = new Scanner(System.in);
    PersonDetails objPersonContact=null;


    public PersonDetails readContactDetail() {
        objPersonContact = new PersonDetails();
        System.out.println("Enter Contact Details");
        System.out.println("----------------------");
        System.out.print("Enter First Name: ");
        objPersonContact.setFirstName(in.next());
        System.out.print("Enter Last Name: ");
        objPersonContact.setLastName(in.next());
        System.out.print("Enter Address: ");
        objPersonContact.setAddress(in.next());
        System.out.print("Enter City: ");
        objPersonContact.setCity(in.next());
        System.out.print("Enter State: ");
        objPersonContact.setState(in.next());
        System.out.print("Enter Zip: ");
        objPersonContact.setZip(in.next());
        System.out.print("Enter Phone number: ");
        objPersonContact.setPhone(in.next());
        System.out.print("Enter Email Id: ");
        objPersonContact.setEmail(in.next());
        return objPersonContact;
    }

    public void addContactDetail() {
        boolean flag = false;
        PersonDetails personDetails = readContactDetail();
        for (PersonDetails objPerson : listContactDetails) {
            if (objPerson.getFirstName().equals(personDetails.getFirstName())) {
                flag = true;
            }
        }
        if (!flag) {
            listContactDetails.add(personDetails);
            storePersonByCity((String) personDetails.getCity(), personDetails);//call store person details by city name
            storePersonByState((String) personDetails.getState(), personDetails);//call store person details by state name
        } else {
            System.out.println("First Name already exist..");
        }
    }

    public void storePersonByCity(String cityName, PersonDetails personObject) {
        ArrayList<PersonDetails> personDetailsArray;
        if (Main.dictCity.containsKey(cityName)) {
            personDetailsArray = (ArrayList<PersonDetails>) Main.dictCity.get(cityName);
        } else {
            personDetailsArray = new ArrayList<>();
        }
        personDetailsArray.add(personObject);
        Main.dictCity.put(cityName, personDetailsArray);
    }

    public void storePersonByState(String stateName, PersonDetails personObject) {
        ArrayList<PersonDetails> personDetailsArray;
        if (Main.dictState.containsKey(stateName)) {
            personDetailsArray = (ArrayList<PersonDetails>) Main.dictState.get(stateName);
        } else {
            personDetailsArray = new ArrayList<>();
        }
        personDetailsArray.add(personObject);
        Main.dictState.put(stateName, personDetailsArray);
    }

    public void editContactDetail(String firstName) {
        if (listContactDetails.isEmpty()) {
            System.out.println("------NO RECORDS------");
            return;
        }

        boolean flag = false;
        PersonDetails newPersonContact = null;
        for (PersonDetails objPerson : listContactDetails) {
            if (objPerson.getFirstName().equals(firstName)) {
                newPersonContact = objPerson;
                flag = true;
                break;
            }
        }

        if (flag) {
            int choice;
            do {
                System.out.println("---------------------------");
                System.out.println("What you want to edit");
                System.out.println("1. First Name\n2. Last Name\n3. Address\n4. City\n5. State\n6. Zip\n7. Phone Number\n8. Email Id\n0. Cancel\n");
                System.out.print("Enter choice: ");
                Scanner in = new Scanner(System.in);
                choice = in.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Old: " + newPersonContact.getFirstName());
                        System.out.print("New: ");
                        String newFirstName = in.next();
                        newPersonContact.setFirstName(newFirstName);
                        break;

                    case 2:
                        System.out.println("Old: " + newPersonContact.getLastName());
                        System.out.print("New: ");
                        String newLastName = in.next();
                        newPersonContact.setLastName(newLastName);
                        break;
                    case 3:
                        System.out.println("Old: " + newPersonContact.getAddress());
                        System.out.print("New: ");
                        String newAddress = in.next();
                        newPersonContact.setAddress(newAddress);
                        break;
                    case 4:
                        System.out.println("Old: " + newPersonContact.getCity());
                        System.out.print("New: ");
                        String newCity = in.next();
                        newPersonContact.setCity(newCity);
                        break;
                    case 5:
                        System.out.println("Old: " + newPersonContact.getState());
                        System.out.print("New: ");
                        String newState = in.next();
                        newPersonContact.setState(newState);
                        break;
                    case 6:
                        System.out.println("Old: " + newPersonContact.getZip());
                        System.out.print("New: ");
                        String newZip = in.next();
                        newPersonContact.setZip(newZip);
                        break;
                    case 7:
                        System.out.println("Old: " + newPersonContact.getPhone());
                        System.out.print("New: ");
                        String newPhone = in.next();
                        newPersonContact.setPhone(newPhone);
                        break;
                    case 8:
                        System.out.println("Old: " + newPersonContact.getEmail());
                        System.out.print("New: ");
                        String newEmail = in.next();
                        newPersonContact.setEmail(newEmail);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("invalid option....");
                        break;
                }
            } while (choice != 0);


        } else {
            System.out.println("First name doesn't exist");
        }
    }

    public void deleteContactDetail(String firstName) {
        boolean flag = false;
        if (listContactDetails.isEmpty()) {
            System.out.println("------NO RECORDS------");
            return;
        }
        for (PersonDetails objPerson : listContactDetails) {
            if (objPerson.getFirstName().equals(firstName)) {
                listContactDetails.remove(objPerson);
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.println("Record Deleted..");
        } else {
            System.out.println("First name doesn't exist");
        }
    }

    public void displayContactDetails() {
        if (listContactDetails.isEmpty()) {//check list are empty or not
            System.out.println("------NO RECORDS------");
            return;
        }
        System.out.println("do want to sort contacts on specific details");
        System.out.println("1. Name \t 2. City \t 3. State \t 4. Zip");
        int ch = in.nextInt();
        switch (ch) {
            case 2:
                listContactDetails.stream().sorted(Comparator.comparing(PersonDetails::getCity)).forEach(System.out::println);
                break;
            case 3:
                listContactDetails.stream().sorted(Comparator.comparing(PersonDetails::getState)).forEach(System.out::println);
                break;
            case 4:
                listContactDetails.stream().sorted(Comparator.comparing(PersonDetails::getZip)).forEach(System.out::println);
                break;
            default:
                listContactDetails.stream().sorted(Comparator.comparing(PersonDetails::getFirstName)).forEach(System.out::println);
                break;
        }
    }
}