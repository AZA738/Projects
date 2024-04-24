import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;




public class Main {
    // importing the list of words and adding it to a list
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("wordList.txt");
        Scanner s = new Scanner(f);

        // placing all strings from list into an array
        ArrayList<String> wordList = new ArrayList<String>();
        while(s.hasNextLine() == true){
            wordList.add(s.nextLine().toUpperCase());
        }




        // creating the word to be guessed and removing it from the list so it's not chosen again
        String word = wordList.get((int) (Math.random() * wordList.size()));
        wordList.remove(word);
        System.out.println(word);
        String yesNo = "Y";




        // total win counter
        int wins = 0;


        // taking in an input from the user and comparing it to the word to be guessed
        // while player response = y (meaning continue to play), play
        do {
            System.out.println("Welcome to Wordle! FYI green = right spot, yellow = wrong spot, red = wrong letter." +
                    " Type in all caps and good luck!");

            // number of guesses
            int count = 1;

            while (count <= 6) {
                System.out.println("Take your " + count + "/6 guess!");
                count++;


                // taking in guess as guessedWord
                Scanner stringScanner = new Scanner(System.in);
                String guessedWord = stringScanner.next();
                guessedWord= guessedWord.toUpperCase();

                // if player guesses word, they win
                if (guessedWord.equalsIgnoreCase(word)) {
                    wins++;
                    System.out.print("YOU WIN! So far you've won " + wins + " times. Another Round? (Y/N) ");
                    word = wordList.get((int) (Math.random() * wordList.size()));
                    yesNo = stringScanner.next();
                    break;
                }

                // if more than six guesses have been made, the user loses and has the option to play again or not
                if (count > 6) {
                    System.out.print("Game Over! The word was " + word + ". So far, you've won " +
                            "" + wins + " times. Try Again? (Y/N) ");
                    yesNo = stringScanner.next();
                    break;
                }

                // checking if more than or less than 5 letters is inputted
                if (guessedWord.length() > 5 || guessedWord.length() < 5) {
                    System.out.println("Guess a 5 letter word please.");
                    count--;
                }

                // checking if inputted word is a word in the list
                else if(!wordList.contains(guessedWord)){
                    System.out.println("Guess a real word please.");
                    count--;
                }


                else {

                    // coloring the letters, correct letter and position = green, correct letter = yellow,
                    //  wrong = red, and comparing the words
                    for (int i = 0; i < word.length(); i++) {
                        if (guessedWord.charAt(i) == word.charAt(i)) {
                            // then make letter green
                            System.out.print("\u001B[32m" + guessedWord.charAt(i));
                        } else if (word.indexOf(guessedWord.charAt(i)) != -1) {
                            // then make letter yellow
                            System.out.print("\u001B[33m" + guessedWord.charAt(i));
                        } else {
                            // then make letter red
                            System.out.print("\u001B[31m" + guessedWord.charAt(i));
                        }
                    }
                    System.out.println();
                }
            }
            }
            while (yesNo.equalsIgnoreCase("Y")) ;
            System.out.println("Ok, bye.");
    }
}