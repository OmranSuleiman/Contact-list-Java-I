
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.io.PrintWriter;

public class DataFileManager {
    // Detta är en klass med array av objekt av typen Person
    private Person[] person;
    private int counter;
    private int AllPerson;


    // Detta klass läser från filen Person.txt och lagrar data i array av objekt av typen Person
    public DataFileManager() {
        AllPerson = 1000;
        counter = 0;
        person = new Person[AllPerson];
        readDataFromFile("C:\\Users\\omran\\IdeaProjects\\Contactlist.java\\src\\person.txt");
    }


    // skapa en funktion för att skriva dta till filen
    public void writeDataOnFile(String fileName) {
        // Läs igenom alla objekt i array och skriv över de i filen rad och rad

        try {
            PrintWriter writer = new PrintWriter("C:\\Users\\omran\\IdeaProjects\\Contactlist.java\\src\\person.txt");
            for (int i = 0; i < counter; i++) {
                writer.println(encryptData(person[i].toString()));
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUNDED");
        }
    }

    // Läs data från filen och lagra de i array av objekt av typen Person
    public void readDataFromFile(String filename) {
        try {
            File file = new File(filename);

            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                line = decryptData(line);
                String[] data = line.split(",");

                person[counter] = new Person(data[0], data[1], data[2], Integer.parseInt(data[3]),
                        new Adress(data[4], data[5], data[6]));
                counter++;
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUNDED");
        }
    }


    // skapa en funktion för att kryptera den passerade strängen och returnera den krypterade strängen med shift-chiffer och Caesar-chiffer
    public String encryptData(String str) {
        String encrypted = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c = (char) (c + 3);
                if (c > 'z') {
                    c = (char) (c - 'z' + 'a' - 1);
                }
                encrypted += c;
            } else if (c >= 'A' && c <= 'Z') {
                c = (char) (c + 3);
                if (c > 'Z') {
                    c = (char) (c - 'Z' + 'A' - 1);
                }
                encrypted += c;
            } else {
                encrypted += c;
            }
        }
        return encrypted;
    }


    // skapa en funktion för att kryptera den passerade strängen och returnera den krypterade strängen med shift-chiffer och Caesar-chiffer
    public String decryptData(String str) {
        String decrypted = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c = (char) (c - 3);
                if (c < 'a') {
                    c = (char) (c + 'z' - 'a' + 1);
                }
                decrypted += c;
            } else if (c >= 'A' && c <= 'Z') {
                c = (char) (c - 3);
                if (c < 'A') {
                    c = (char) (c + 'Z' - 'A' + 1);
                }
                decrypted += c;
            } else {
                decrypted += c;
            }
        }
        return decrypted;
    }

    // sortera data med namn
    public void sortDataByName() {
        for (int i = 0; i < counter - 1; i++) {
            for (int j = 0; j < counter - i - 1; j++) {
                if (person[j].getName().compareTo(person[j + 1].getName()) > 0) {
                    Person temp = person[j];
                    person[j] = person[j + 1];
                    person[j + 1] = temp;
                }
            }
        }
    }

    // sortera personer med deras signatur eller visa felmeddelandet om personen inte hittas
    public void searchDataBySignature(String signature) {
        boolean found = false;
        for (int i = 0; i < counter; i++) {
            if (person[i].getSignature().equals(signature)) {
                person[i].DisplayPerson();
                found = true;
            }
        }
        if (!found) {
            System.out.println("PERSON NOT FOUNDED");
        }
    }

    // radera personen med signatur annars visa felmeddelandet om personen inte hittas
    public void removeDataBySignature(String signature) {
        boolean found = false;
        for (int i = 0; i < counter; i++) {
            if (person[i].getSignature().equals(signature)) {
                person[i] = person[counter - 1];
                counter--;
                found = true;
            }
        }
        if (!found) {
            System.out.println("PERSON NOT FOUNDED");
        }
    }

    // sortera personer med deras signatur
    public void sortDataBySignature() {
        for (int i = 0; i < counter - 1; i++) {
            for (int j = 0; j < counter - i - 1; j++) {
                if (person[j].getSignature().compareTo(person[j + 1].getSignature()) > 0) {
                    Person temp = person[j];
                    person[j] = person[j + 1];
                    person[j + 1] = temp;
                }
            }
        }
    }

    // Sortera personer utifrån deras längd. Kortaste längs upp
    public void sortDataByLength() {
        for (int i = 0; i < counter - 1; i++) {
            for (int j = 0; j < counter - i - 1; j++) {
                if (person[j].getLength() > person[j + 1].getLength()) {
                    Person temp = person[j];
                    person[j] = person[j + 1];
                    person[j + 1] = temp;

                }
            }
        }
    }



    // sortera personer slumpmässigt ordning
    public void sortDataRandomly() {
        Random rand = new Random();
        for (int i = 0; i < counter; i++) {
            int randomIndexToSwap = rand.nextInt(counter);
            Person temp = person[randomIndexToSwap];
            person[randomIndexToSwap] = person[i];
            person[i] = temp;
        }
    }

    // Detta är meny för att användaren ska välja mellan olika alternative
    public int menuOptions() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("1. ADD THE PERSON");
        System.out.println("2. SHOW ALL THE PERSONS ON THE SCREEN");
        System.out.println("3. SEARCH FOR THE PERSON IN THE LIST");
        System.out.println("4. DELETE THE PERSON FROM THE LIST");
        System.out.println("5. SORT THE PERSONS BY THEIR NAMES");
        System.out.println("6. SORT THE PERSONS BY THEIR SIGNATURE");
        System.out.println("7. SORT THE PERSONS BY THEIR LENGTH");
        System.out.println("8. SHOW THE LIST IN RANDOMLY ORDER");
        System.out.println("9. SAVE THE LIST IN A TEXT FILE. LET THE USER TO ENTER THE FILE NAME");
        System.out.println("10. READ THE LIST FROM THE FILE. LET THE USER TO ENTER THE FILE NAME");
        System.out.println("11. FINISH");
        // read user input from keyboard
        System.out.println("ENTER YOUR OPTIONS: ");
        System.out.println();
        int choice = 0;
        if (sc.hasNextLine()) {

            String input = sc.nextLine();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("INVALID INPUT DATA. ALLOWED CHARACTER ARE INTEGERS BETWEEN  1 AND 11");
                return menuOptions();
            }
        }
        // validera input
        while (choice < 1 || choice > 11) {
            System.out.println("INVALID INPUT DATA. ALLOWED CHARACTER INTEGERS BETWEEN  1 AND 11");
            return menuOptions();
        }
        // sc.close();
        return choice;

    }

    void addPerson() {
        person[counter] = new Person();
        Scanner sc = new Scanner(System.in);

        person[counter].input(sc);
        counter++;
        System.out.println("PERSON ADDED SUCCESSFULLY");
        //return;
    }

    void displayAllDataOnScreen() {
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "SIGNATURE",
                "FIRST NAME", "LAST NAME",
                "LENGTH [CM]", "MAILINGADRESS", "POSTALCODE", "POSTALADRESS");
        for (int i = 0; i < counter; i++) {

            person[i].DisplayPerson();
        }
    }

    public void menu() {
        int choice = 0;
        while (choice != 11) {
            choice = menuOptions();
            if (choice == 1) {
                addPerson();
            } else if (choice == 2) {
                displayAllDataOnScreen();
            } else if (choice == 3) {
                Scanner sc = new Scanner(System.in);
                System.out.println("ENTER THE SIGNATURE TO SEARCH A PERSON: ");
                String signature = sc.nextLine();
                searchDataBySignature(signature);

            } else if (choice == 4) {
                Scanner sc = new Scanner(System.in);
                System.out.println("ENTER THE SIGNATURE TO REMOVE THE PERSON: ");
                String signature = sc.nextLine();
                System.out.println("PERSON HAS REMOVED");

                removeDataBySignature(signature);
            } else if (choice == 5) {
                sortDataByName();
            } else if (choice == 6) {
                sortDataBySignature();
            } else if (choice == 7) {
                sortDataByLength();
            } else if (choice == 8) {
                sortDataRandomly();
            } else if (choice == 9) {
                Scanner sc = new Scanner(System.in);
                System.out.println("GIVE THE FILE NAME TO SAVE THE LIST: ");
                String fileName = sc.nextLine();
                System.out.println("FILE SAVED");

                writeDataOnFile(fileName);

            } else if (choice == 10) {
                Scanner sc = new Scanner(System.in);
                System.out.println("GIVE THE FILE NAME TO READ FROM: ");
                String fileName = sc.nextLine();
                readDataFromFile(fileName);
                System.out.println("FILE READ");
            } else if (choice == 11) {
                System.out.println("GOODBYE! AND THANK YOU FOR USING THIS SYSTEM");
                System.exit(0);
            }
        }
    }
}
