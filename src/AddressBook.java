import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
    HashMap<String, Object> contactDetailsList = new HashMap<>();

    public List readContactDetail(boolean isEdit) {
        List<String> list = new ArrayList<>();
        System.out.println("Enter Contact Details");
        System.out.println("----------------------");
        Scanner in = new Scanner(System.in);
        if (isEdit == false) {
            System.out.print("Enter First Name: ");
            list.add(in.next());
        }
        System.out.print("Enter Last Name: ");
        list.add(in.next());
        System.out.print("Enter Address: ");
        list.add(in.next());
        System.out.print("Enter City: ");
        list.add(in.next());
        System.out.print("Enter State: ");
        list.add(in.next());
        System.out.print("Enter Zip: ");
        list.add(in.next());
        System.out.print("Enter Phone number: ");
        list.add(in.next());
        System.out.print("Enter Email Id: ");
        list.add(in.next());
        return list;
    }

    public void addContactDetail() {
        List listContactDetails = readContactDetail(false);
        PersonContact objPersonContact = new PersonContact();
        objPersonContact.addPersonContact(listContactDetails);
        contactDetailsList.put((String) listContactDetails.get(0), objPersonContact);
    }

    public void editContactDetail(String firstName) {
        if (contactDetailsList.containsKey(firstName)) {
            PersonContact newPersonContact = (PersonContact) contactDetailsList.get(firstName);
            List editedPersonDetail = readContactDetail(true);
            newPersonContact.editPersonContactDetails(editedPersonDetail);
        } else {
            System.out.println("First name doesn't exist");
        }
    }

    public void displayContactDetails() {
        for (String key : contactDetailsList.keySet()) {
            PersonContact newPersonContact = (PersonContact) contactDetailsList.get(key);
            System.out.println("--------------------------");
            newPersonContact.displayPersonContactDetails();
            System.out.println("--------------------------");
        }
    }
}
