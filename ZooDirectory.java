/*
  Author: Helen Hua
  Date: June 20, 2021
  Summary:
    1. Allow user to store animals into a directory
    2. Allow user to access a full list of animals currently stored in the directory
    3. Allow user to access detailed information about an animal by entering its name
    4. Allow user to close the directory and warn them that all data will be lost
*/

import java.util.Scanner;
import java.util.ArrayList;

public class AnimalTester
{
  public static void main(String[] args)
  {
    // Initiate scanner and arraylist that will hold inputted animals by the user
    Scanner input = new Scanner(System.in);
    ArrayList<Animal> array = new ArrayList<Animal>();
    
    int status = 1;

    // Initiate default animals for land, water (fish) and sky (bird)
    Animal land = new Animal("name", "color", 0, 0, 0);
    Animal fish = new Fish("name", "color", 0, 0, 0);
    Animal bird = new Bird("name", "color", 0, 0);

    // Loops until status does not equal 1
    while(status == 1)
    {
      // Asks user what they want to do
      System.out.println("\nHello! What can I do for you? \n 1. Register a new animal \n 2. View current directory \n 3. See detailed info of an animal \n 4. Exit");
      int action = input.nextInt();

      // Asks user questions about the animal if they want to register an animal
      if(action ==1)
      {
        System.out.println();
        input.nextLine();

        System.out.println("What is the name of the animal?");
        String name = input.nextLine();
        
        System.out.println("What color is the animal?");
        String color = input.nextLine();

        System.out.println("How old is the animal?");
        int age = input.nextInt();

        System.out.println("How heavy is the animal in pounds?");
        int weight = input.nextInt();
        
        input.nextLine();
        System.out.println("Is it a land, water or sky animal?"); 
        String type = input.nextLine();
        
        // If user chooses sky, store a bird object into the array representing a sky animal
        if(type.length() == 3)
        {
          bird.replaceVariables(name, color, weight, age, 2, 3);
          array.add(bird);
        }
        
        // If user chooses land, ask user for more information then store an animal object into the array
        if(type.length() == 4)
        {
          System.out.println("How many legs does it have?");
          int legs = input.nextInt();
          land.replaceVariables(name, color, weight, age, legs, 3);

          array.add(land);
        }
        
        // If user chooses water, ask user for more information then store a fish object into the array 
        if(type.length() == 5)
        {
  
          System.out.print("Does it live in salt water (press 1) or fresh water (press 2)?");
          int saltvfresh = input.nextInt();
  
          fish.replaceVariables(name, color, weight, age, 0, saltvfresh);
          array.add(fish);
        }
      }

      // If the user wants to view the current directory, traverse the arraylist and print the names of all animals
      if(action == 2)
      {
        System.out.println("\nDirectory:");

        for (int i=0; i< array.size(); i++)
        {
          
          Animal temp = array.get(i);

          System.out.println((i+1) + ": " + temp.getName() );
        }
      }

      // If user wants to view details for one animal, traverse the array until the target animal's name matches the user's input then print out the information for that animal
      if(action == 3)
      {
        int counter = 1;

        input.nextLine();

        System.out.println("Enter the name of the animal: ");
        String animal_name = input.nextLine();

        for(int i=0; i< array.size(); i++)
        {
          Animal temp = array.get(i);
          
          if(temp.getName().equals(animal_name))
          {
            System.out.println("\nDetails:");
            System.out.println(temp);
            counter = 0;
            break;
          }
        }
        
        if (counter == 1)
        {
          System.out.println("Animal could not be found");
        }
      }

      input.nextLine();

      // If user wants to quit and close the directory, reconfirm the option as all data will be lost then continue looping or end code
      while(action == 4 && status == 1)
      {
          System.out.println("\nAre you sure you want to exit? All data will be lost: (yes or no)");
          
          String yesNo = input.nextLine();
          
          
          if (yesNo.equals("yes") || yesNo.equals("Yes") )
          {
              status = 0;
          }
          
          else if( yesNo.equals("no") || yesNo.equals("No"))
          {
              status = 1;
              action = 0;
          }
          else
          {
              System.out.println("Error: Unrecognized response \nHit enter to try again");
              input.nextLine();
          }
      }
    }
    
    // Closes scanner    
    input.close();
  }
}