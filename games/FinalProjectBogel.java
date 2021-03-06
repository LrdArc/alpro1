   public class FinalProjectBogel {

 static private BufferedReader br = new BufferedReader (newInputStreamReader(System.in),1);
boolean timeUp = true;
boolean notValid = true;

Toolkit toolkit;
Timer timer;
sortLib sl = new sortLib();

public FinalProjectBogel() throws IOException
{
    toolkit = Toolkit.getDefaultToolkit();
    timer = new Timer();     //Creates new timer       

   // CREDITS
   System.out.println("");
   System.out.println("BOGEL ®");
   System.out.println("Created by George Ondi and Kirkland Clive Douglas");
   //Edited by Alan Kwok
   System.out.println("");
//________________________________________________________________________________________
// MAIN MENU
//________________________________________________________________________________________
   for (int ctrloop = 0; 0 < 1; ctrloop--)//Endless loop for main menu
   {
        System.out.println("");
        System.out.println("Enter one of the following numeric options to initiate the corresponding command.");
        System.out.println("");
        System.out.println("1  Commence Game");
        System.out.println("2  Rules");
        System.out.println("3  View Highscores");
        System.out.println("4  Exit");
        System.out.println("");
//________________________________________________________________________________________
// TIMER / GAMEBOARD
//________________________________________________________________________________________
        int userCmd = Keyboard.readInt();//Gets input from user for the main menu
        System.out.println("");

        if (userCmd==1)//Runs the game it self
        {
                timer.schedule(new RemindTask(),0,1*1000);  // start timer

                int score = 0;//Where your points are scored

                char table[][] = new char[10][10]; //Creates the array for the 10x10 grid
                int ctr = 0;
                char c;
                Random r = new Random();//for the random characters

                for (int x = 0; x < 10; x++)
                {
                    for (int y = 0; y < 10; y++)
                    {
                         table[x][y] = (char)(r.nextInt(26) + 'a');//creates random characters
                    }
                }   
                for (int x = 0; x < 10; x++)
                {
                    for (int y = 0; y < 10; y++)
                    {
                        System.out.print("|" + table[x][y] + "|");//creats the grid and the lines between the letters
                    }
                    System.out.println("");
                }
                System.out.println("Enter words that appear horizontally, vertically.");
                System.out.println("- Enter 0 to return to main menu.");
                String userInput = Keyboard.readString();//User input for the word search
               // System.out.println(userInput);
                String[] wordStorage = new String[50];//creats array for words you enter. this is where they are stored then later compared with.
                int size = 0;//for keeping track of array size
                boolean Flag = false;
//________________________________________________________________________________________
//  EXITING TO MAIN MENU OPTION / BUFF.READER / WORDSEARCH
//________________________________________________________________________________________
                while (Flag != true )
                {
                    if (userInput.equals("0"))//The following is initiated if userin. = 0
                    {
                        timer.cancel();//Cancels timer
                        timer = new Timer();//Makes or schedules a new timer
                        //HIGHSCORE FILES
                        notValid = false;
                        System.out.println("");
                        System.out.println("");
                        int[] numbers = new int[11];//creates array for storing highscore
                        BufferedReader lineIn2 = new BufferedReader(new FileReader("Highscore.txt"));//reads highscore file
                        String line2 = "";
                        //load array with file (for loop)
                        for (int ctr5 = 0; ctr5 < 11; ctr5++)
                        {
                            line2 = lineIn2.readLine();
                            numbers[ctr5] = Integer.parseInt(line2);
                        }
                        System.out.println("");
                        System.out.println("");
                        System.out.println("");
                        System.out.println("");
                        System.out.println("");
                        System.out.println("The previous highscore is: " + numbers[10]);
                        System.out.println("");
                        System.out.println("Your highscore is: " + score);

                        lineIn2.close();//closes file
                        PrintWriter lineOut2 = new PrintWriter(new FileWriter("Highscore.txt"));//writes to file
                        int Highscore = score;

                        numbers[0] = Highscore;
                        sl.quickSort(numbers, 0,10); //Sorts the high scores
                        System.out.println("");                         
                        System.out.println("The high scores are:");
                        for (int index = numbers.length-1; index >= 1; index--)
                        {
                            System.out.print (numbers[index] + " ");//Displays array of numbers. Not neccessry for game
                        }
                        for (int ctr99 = 0; ctr99 < 11; ctr99++)
                        {
                            lineOut2.println(numbers[ctr99]);//writes scores back to file
                        }
                        System.out.println("");
                        System.out.println();
                        lineOut2.close();//closes file one last time
                        System.out.println("The words you entered are:");

                        for (int disp = size-1; disp >= 0; disp--)
                        {
                            System.out.print(wordStorage[disp] + " ");
                        }
                        System.out.println("");

                        //notValid = true;
                        timeUp = false;
                        //Breaks back to main menu
                        break;//breaks back to main menu
                    }
                    else if (!userInput.equals("0") && !timeUp)//If userin. doesn't equal 0, following error is displayed
                    {
                            System.out.println("");
                            System.out.println("-------------------------Not a valid input----------------------------");
                            System.out.println("");
                    }
                    ctr = 0;
                    boolean answer = wordSearch(table, userInput);
                    //link the two together
                    BufferedReader lineIn = new BufferedReader(new FileReader("allWordsEn.txt"));//opens dictionary
                    int ctr2 = 0;
                    String line = lineIn.readLine();//reads dictionary
                    boolean match = false;
//________________________________________________________________________________________
//  SCORE SYSTEM / ERROR MESSAGES
//________________________________________________________________________________________
                        while(line != null && answer)
                        {
                            StringTokenizer words = new StringTokenizer(line);
                            String temp = words.nextToken();

                            if (userInput.equalsIgnoreCase(temp))//the following compares userinput with array of 
                                                                 //stored words, to see if they already exist or not
                            {
                                boolean ifwordexist = false;

                                for (int ctr9 = 0; ctr9 <= size; ctr9++)
                                {
                                    if (userInput.equals(wordStorage[ctr9]))
                                    {
                                        ifwordexist = true;
                                        System.out.println("You entered the word aleady");
                                        match = true;
                                        break;
                                    }
                                     //check if word is in the array.. if yes, ifwordexist= true
                                }

                                if (!ifwordexist)
                                {
                                    wordStorage[size] = userInput;  // store name into the array
                                    int temp999 = userInput.length();
                                    size++;     // keep track of logical size of the array
                                    score+=temp999; //Adds points to total score equal to length of word
                                    System.out.println("Match Dictionary! +" + temp999  + " Total Score: " + score); //displays current score
                                    match = true;
                                    break;
                                }
                            }
                            line = lineIn.readLine();
                            ctr2++;
                        }
                        lineIn.close(); //closes dictionary
                    //If word doesn't match the grid or dictionary, displays the following error message
                    if (timeUp)
                    {
                        if (!match)
                        {
                            System.out.println("Word not found in database. No point scored, try Again.");
                        }
                        //Displays the gird once again
                        for (int x = 0; x < 10; x++)
                        {
                            for (int y = 0; y < 10; y++)
                            {
                                System.out.print("|" + table[x][y] + "|");
                            }
                            System.out.println("");
                        }
                        System.out.println("Next word");
                    }

                    System.out.println("- Enter \"0\" to return to main menu.");
                    userInput = Keyboard.readString();
                }
           }
//________________________________________________________________________________________
// RULES
//________________________________________________________________________________________
        else if (userCmd==2)//displayes rules
        {
            System.out.println("Rules:");
            System.out.println("");
            System.out.println("- Search the 10 x 10 grid for words that are formed vertically and horizontally (left to right, top to bottom)");
            System.out.println("- When you have found a word (minimum 2 letter word), type the word into the program.");
            System.out.println("- The amount of points you score is determined by the length of the word you entered.");
            System.out.println("- The goal of the game is to score as many points before the time runs out.");
            System.out.println("- While playing the game you can enter \"0\" to return to the main menu.");
            System.out.println("- Good Luck and have fun!");
            System.out.println("");
        }
        else if (userCmd==3)//displayes high scores
        {
            int[] numbers = new int[11];
            BufferedReader lineIn2 = new BufferedReader(new FileReader("Highscore.txt"));
            String line2 = "";
            //load array with file (for loop)
            for (int ctr5 = 0; ctr5 < 11; ctr5++)
            {
                line2 = lineIn2.readLine();
                numbers[ctr5] = Integer.parseInt(line2);
            }
            System.out.println("");
            System.out.println("");
            System.out.println("The previous highscore is: " + numbers[10]);
            System.out.println("");
            lineIn2.close();
            PrintWriter lineOut2 = new PrintWriter(new FileWriter("Highscore.txt"));
            sl.quickSort(numbers, 0,10); //Sorts the high scores
            System.out.println("The high scores are:");
            for (int index = numbers.length-1; index >= 1; index--)
            {
                System.out.print (numbers[index] + " ");//Displays array of numbers. Not neccessry for game
            }

            for (int ctr99 = 0; ctr99 < 11; ctr99++)
            {
                lineOut2.println(numbers[ctr99]);
            }
            System.out.println("");
            System.out.println();
            lineOut2.close();
        }
        else if (userCmd==4)//ends the process
        {
            System.out.println("Bye!");
            System.exit(0);
        }
            else//displayes error message if input doesn't match 1,2,3, or 4.
            {
                System.out.println("");
                System.out.println("-------------------------Not a valid input----------------------------");
                System.out.println("");
                System.out.println("");
            }
   }
}
//________________________________________________________________________________________
// COUNTDOWN TIMER / ADJUST TIMER HERE
//________________________________________________________________________________________
class RemindTask extends TimerTask
{
    int numWarningBeeps = 59;//Set timer here (seconds)

        public void run() 
        {
            if (numWarningBeeps > 0) 
            {
                toolkit.beep();//Creats beeping noises
                numWarningBeeps--;
                timeUp = true;
            } 
            else 
            {
                toolkit.beep(); 
                timer.cancel();//cancels timer when it reaches 0
                System.out.println("");
                System.out.println("Time's up!");
                System.out.println("");
                System.out.println("- Enter \"0\" to continue.");
                timeUp = false;
                notValid = false;
                timer = new Timer();//creats or schedules new timer
                //System.exit(0);   //Stops the AWT thread (and everything else)
            }
        }
}    


   //____________________________________________________________________________
          // MAIN METHOD / RELATED TO TIMER
   //__________________________________________________________________________
 public static void main(String[] args) throws IOException
{
 new FinalProjectBogel();
}





        //____________________________________________________________________________
       // WORDSEARCH METHOD (Searches grid for words you enter to see if they match)
      //_______________________________________________________________________________

     public static boolean wordSearch (char[][] table, String search)
       {
           int  ctr = search.length();
           String temp = ""; 
           String hcraes = (search); // copy search into hcraes

           //Right to left, bottom to top
           int ls = search.length();  // length of initial string
            StringBuilder sb = new StringBuilder(); // temporary place to store growing string
            for(int ii=ls-1;ii>=0; ii--) {
                    sb.append(search.charAt(ii)); // build the string one character at a time
                }
            hcraes = sb.toString(); // convert to "regular" string

           // LEFT TO RIGHT / X-AXIS
           for (int row = 0; row < 10; row++) //Checks each row (x) one by one
           {
                   for (int a = 0; a <= (10 - ctr); a++)
                   {
                    StringBuilder s = new StringBuilder(10-ctr);//Does... something
                        for (int x = a; x <= (a+ctr-1); x++) //Checks every possibility in the row
                         {
                            s.append(table[row][x]);
                            temp = s.toString();
                            if (temp.equals(search) || temp.equals(hcraes))
                            {
                                return true;
                            }
                         }
                   }
           }
           // TOP TO BOTTOM / Y-AXIS
           for (int column = 0; column < 10; column++)
           {
                   for (int b = 0; b <= (10 - ctr); b++)
                   {
                    StringBuilder v = new StringBuilder(10-ctr);
                        for (int y = b; y <= (b+ctr-1); y++)//checks every possibility in grid
                         {
                            v.append(table[y][column]);
                            temp = v.toString();
                            if (temp.equals(search) || temp.equals(hcraes))
                            {
                                return true;
                            }
                         }
                   }
           }
            return false;//if word isn't in grid it returns as false, which then displays an error message
      }
}

