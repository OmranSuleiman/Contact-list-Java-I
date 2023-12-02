import java.util.Scanner;

//skapa en adressklass

public class Adress {
    private String mailingAdress;
    private String zipNumber;
    private String postalAdress;

    public Adress(String mailingAdress, String zipNumber, String postalAdress) {
        this.mailingAdress = mailingAdress;
        this.zipNumber = zipNumber;
        this.postalAdress = postalAdress;
    }

    //skapa en default konstruktorn
    public Adress() {
        this.mailingAdress = "";
        this.zipNumber = "";
        this.postalAdress = "";
    }

    public String getMailingAdress() {
        return mailingAdress;
    }

    public String getZipNumber() {
        return zipNumber;
    }

    public String getPostalAdress() {
        return postalAdress;
    }

    public void setMailingAdress(String mailingAdress) {
        this.mailingAdress = mailingAdress;
    }

    public void setZipNumber(String zipNumber) {
        this.zipNumber = zipNumber;
    }

    public void setPostalAdress(String postalAdress) {
        this.postalAdress = postalAdress;
    }

    public String displayAdress() {
        return "Mailing Adress: " + mailingAdress + "\nPostal Code: " + zipNumber + "\nPostal Adress: " + postalAdress
                + "\n";
    }


    //få input från användaren genom skannerobjektet som skickas till metoden och gör den till standaröverbelastningsmetod
    public void input(Scanner input) {
        System.out.println("ENTER THE MAILING ADRESS: ");
        mailingAdress = input.nextLine();
        System.out.println("ENTER THE POSTAL CODE: ");
        zipNumber = input.nextLine();
        System.out.println("ENTER THE POSTAL ADRESS: ");
        postalAdress = input.nextLine();
    }

    public String toString() {
        return mailingAdress + "," + zipNumber + "," + postalAdress;
    }

}