import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import wordSeek.GameBoard;

public class WordGame {

    private char[][] letters;
    GameBoard gb;

    public static void main(String[] args) {
        WordGame wg = new WordGame();
        wg.play();

    }

    public WordGame() {
        letters = readLettersFromFile();
        gb = new GameBoard(letters);

    }

    private void play() {
        Scanner s = new Scanner(System.in);
        String word;

        do {
            System.out.println("Enter word to find: ");
            word = s.next();

            // reset all highlighted tiles
            gb.reset();

            search(word);

        } while (!word.equals("QUIT"));

        gb.dispose();
    }

    // Nothing to be done above
    // Complete all the methods below

    private char[][] readLettersFromFile() {
        // From the data in the file Letters.txt determine the size (number of
        // rows and number of columns) for the letters array

        int rowCount = 0;
        int colCount = 0;
        char c;

        File file = new File("resources/Places.txt");

        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
            } 
        catch (FileNotFoundException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        ArrayList<String> data = new ArrayList<String>();

        while(fileScanner.hasNextLine())
        {
            String line = fileScanner.nextLine();
            data.add(line);
        }

        fileScanner.close();
        rowCount = data.size();
        colCount = data.get(0).trim().length()/2+1;

        // Instantiate a two dimensional array of characters of the appropriate
        // size

        letters = new char [rowCount][colCount];

        // Populate the array with the letters in Letters.txt
        for (int i = 0; i < rowCount; i++) {
            String line = data.get(i);
            int pos = 0;
            for (int j = 0; j < colCount; j++) {
                letters[i][j] = line.charAt(pos);
                pos += 2;
            }

        }

        // return the array

        return letters;

    }

    private void search(String word) {
        System.out.println("Searching for " + word);

        //Call the other search methods below as needed
        searchIterativeEast(word);
        searchIterativeWest(word);
        searchIterativeSouth(word);
        searchIterativeNorth(word);
    }

    //The following four methods must employ ITERATION to search the game board
    private boolean searchIterativeEast(String word) {
        int k = 0;

        for (int i = 0; i < letters.length; i++) 
        {
            for (int j = 0; j < letters[i].length; j++) {
                if (word.charAt(k) == letters[i][j]) {
                    k++;
                } 
                else {
                    k = 0;
                }
                if (k == word.length()) {
                    for (int col = j - k + 1; col <= j; col++) {
                        gb.highlight(i, col);
                    }

                    return true;
                }
            }
        }

        return false;
    }

    private boolean searchIterativeWest(String word) {

            String reversedWord = "";
            for (int i = word.length() - 1; i != -1; i--)
            {
                reversedWord += word.charAt(i);
            }

            int k = 0;

            for (int i = 0; i < letters.length; i++) 
            {
                for (int j = 0; j < letters[i].length; j++) {
                    if (reversedWord.charAt(k) == letters[i][j]) {
                        k++;
                    } 
                    else {
                        k = 0;
                    }
                    if (k == reversedWord.length()) {
                        for (int col = j - k + 1; col <= j; col++) {
                            gb.highlight(i, col);
                        }

                        return true;
                    }
                }
            }

            return false;

    }

    private boolean searchIterativeSouth(String word) {
        int k = 0;
        int store = letters[0].length;

        for (int j = 0; j < letters[store].length; j++)
        {
            for (int i = 0; i < letters.length; i++)
            {
                if (word.charAt(k) == letters[i][j])
                {
                    k++;
                }
                else
                {
                    k = 0;
                }
                if (k == word.length())
                {
                    for(int row = i-k+1 ; row <= i; row++)
                    {
                        gb.highlight(row, j);
                    }
                    return true;
                }
            }
        }

        return false;
    }

    private boolean searchIterativeNorth(String word) {

        String reversedWord = "";
        for (int i = word.length() - 1; i != -1; i--)
        {
            reversedWord += word.charAt(i);
        }

        int k = 0;
        int store = 0;

        for(int i = 0; i < letters.length; i++)
        {
            store = letters[i].length;
        }

        for (int j = 0; j < letters[store].length; j++)
        {
            for (int i = 0; i < letters.length; i++)
            {
                if (reversedWord.charAt(k) == letters[i][j])
                {
                    k++;
                }
                else
                {
                    k = 0;
                }
                if (k == reversedWord.length())
                {
                    for(int row = i-k+1 ; row <= i; row++)
                    {
                        gb.highlight(row, j);
                    }
                    return true;
                }
            }
        }

        return false;
    }
}
