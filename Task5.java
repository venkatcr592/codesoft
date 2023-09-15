import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nPhone Number: " + phoneNumber + "\nEmail Address: " + emailAddress + "\n";
    }
}

class AddressBook {
    private List<Contact> contacts = new ArrayList<>();
    private String fileName = "contacts.txt";

    public AddressBook() {
        loadContactsFromFile();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        saveContactsToFile();
    }

    public void removeContact(String name) {
        contacts.removeIf(contact -> contact.getName().equalsIgnoreCase(name));
        saveContactsToFile();
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public void displayAllContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    private void loadContactsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    contacts.add(new Contact(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveContactsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Contact contact : contacts) {
                writer.write(contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmailAddress());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class AddressBookSystem {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Address Book System Menu:");
            System.out.println("1. Add a Contact");
            System.out.println("2. Remove a Contact");
            System.out.println("3. Search for a Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String emailAddress = scanner.nextLine();
                    addressBook.addContact(new Contact(name, phoneNumber, emailAddress));
                    break;
                case 2:
                    System.out.print("Enter name to remove: ");
                    String nameToRemove = scanner.nextLine();
                    addressBook.removeContact(nameToRemove);
                    break;
                case 3:
                    System.out.print("Enter name to search: ");
                    String nameToSearch = scanner.nextLine();
                    Contact foundContact = addressBook.searchContact(nameToSearch);
                    if (foundContact != null) {
                        System.out.println("Contact found:\n" + foundContact);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 4:
                    addressBook.displayAllContacts();
                    break;
                case 5:
                    System.out.println("Exiting Address Book System.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
