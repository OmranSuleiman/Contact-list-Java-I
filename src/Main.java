public class Main {

    //   Menyn som visar på terminalen
    //  skapa en menu system där användaren kan följa utifrån alternative som finns
    //  1. Lägg till person
    //  2. Lisa alla kontakter som finns
    //  3. Sök efter en person
    //  4. Radera en person från listan
    //  5. Sortera med namn
    //  6. Sortera med signatur
    //  7. Sortera med längd
    //  8. Slumpmässigt ordning
    //  9. lagra listan i text filen. Ge möjlighet till användaren att mata in fil namn.
    //  10. Läs lista från text filen. Ge möjlighet till användaren att mata in fil namn.
    //  11. Avsluta
    //  Detta menyn kommer visas hela tiden tills användaren väljer avsluta programmet manuellt.

    public static void main(String[] args) {

        DataFileManager dataFM = new DataFileManager();
        dataFM.menu();
    }

}
