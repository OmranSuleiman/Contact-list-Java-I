import java.util.Scanner;

//  Förnamn  (Sträng)
//  efternamn (Sträng)
//  Signatur (Sträng) (se nere för hur signatur är implementerad)
//  Length i cm (integer)
//  Address

public class Person {
    private String fName;
    private String lName;
    private String signature;
    private int length;
    private Adress adress;


    // skapa en static integer variabel för att ha koll på hur många objekt som skapades
    private static int count = 0;

    public Person(String fName, String lName, String signature, int length, Adress adress) {
        this.fName = fName;
        this.lName = lName;
        this.signature = signature;
        this.length = length;
        this.adress = adress;
        count++;

    }


    // Default konstruktören
    public Person() {
        this.fName = "";
        this.lName = "";
        this.signature = "";
        this.length = 0;
        this.adress = new Adress();
    }

    public String getName() {
        return fName + " " + lName;

    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getSignature() {
        return signature;
    }

    public int getLength() {
        return length;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

     public void DisplayPerson() {
         // skapa kolum för varje utdata och skriv data till skärmen
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", signature,
                fName, lName, length,
                adress.getMailingAdress(), adress.getZipNumber(), adress.getPostalAdress());

    }

    public String toString() {
        return fName + "," + lName + "," + signature + "," + length + "," + adress.toString();
    }


    // ta emot input från användaren genom skanner objekt som skickas till metoden och gör det standard överbelastningsmetoden
    public void input(Scanner input) {
        System.out.println("ENTER THE FIRST NAME: ");
        fName = input.nextLine();
        System.out.println("ENTER THE LAST NAME: ");
        lName = input.nextLine();
        System.out.println("ENTER THE LENGTH IN (CM): ");
        String inputStr = input.nextLine();
        boolean found = true;
        while (found) {


            try {
                length = Integer.parseInt(inputStr);
                found = false;

            } catch (NumberFormatException e) {
                System.out.println("INVALID INPUT DATA. LENGTH MUST BE BETWEEN 0 AND 9:");
                System.out.println("ENTER THE LENGTH IN (CM): ");
                inputStr = input.nextLine();

            }
        }



        adress.input(input);
        signature = createUnikSignature(fName, lName, count);
    }

    private String createUnikSignature(String firstName, String lastName, int serialNumber) {

        // kontrollera om längden av förnamn är mindre än 3 strängar annars lägg till x i slutet
        while (firstName.length() < 3) {
            firstName += "zz";
        }

        // kontrollera om längden av efternamn är mindre än 3 strängar annars lägg till x i slutet
        while (lastName.length() < 3) {
            lastName += "zz";
        }

        // skapa signaturen med hjälp av de 3 första bokstäver för förnamn och de 3 bokstäver av efternamn och lägg till seral nummer till slutet
        signature = firstName.substring(0, 3) + lastName.substring(0, 3) + serialNumber;
        return signature;
    }

}
