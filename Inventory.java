/**
 * Class: Inventory
 * Programmer: Usman Minhas, Jay Cordeiro, Sarada Sai Turaga
 * Program: Jay's NoFrills
 * Date: January 22, 2019
 * Description: This class contains an array with all the inventory and each
 * items prices
 */
package code;

//Imports
import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class Inventory {
    
    //Created arraylists that will hold the inventory details with prices
    protected ArrayList <String> items = new ArrayList<>();
    protected ArrayList <String> stock = new ArrayList<>();
    protected ArrayList <String> prices = new ArrayList<>();
    
    /**
     * Inventory - Reads file and put price, stock and item name in an 
     * arraylist
     * @throws IOException 
     */
    public Inventory() throws IOException{
        
        //Opens file and scanner to read it
        File invent = new File("H:\\Inventory.txt");
        Scanner readFile = new Scanner(invent);
        
        //Decalre random numbers
        Random amount = new Random();
        
        //Varibales for counter and stock
        int i = 0, g;
        
        //Loop reads the entire inventory file
        while(readFile.hasNext()){
            String line = readFile.nextLine();
            
            //Splits the item name and price using a colon
            String [] tokens = line.split(":");
            
            //Assigns [rice and name to arraylist
            items.add(tokens[0]);
            prices.add(tokens[1]);
            
            //Assigns a rnadom number to the stock
            g = amount.nextInt(100) ;
            stock.add(Integer.toString(g));
            
            i++;
        }
        
    }//End of constructor
    
    /**
     * Alphabetical - Sorts items by alphabet
     */
    public void alphabetical(){
        
        //Declare vairbales to hold minimums
        String minValueI, minValueP, minValueS;
        int minIndex;
        
        for (int i = 0; i < items.size(); i++) 
        { 
            //Keeps track of the item name and its corresponding price/stock
            minValueI = items.get(i); 
            minValueP = prices.get(i); 
            minValueS = stock.get(i); 
            minIndex = i; 
            
            //Moves through the array until a lower item name is found
            for (int j = i + 1; j < items.size(); j++) 
            { 
                if (items.get(j).compareToIgnoreCase(minValueI) < 0) 
                { 
                    
                    //Stores the new lowest value discovered
                    minValueI = items.get(j);
                    minValueP = prices.get(j); 
                    minValueS = stock.get(j); 
                    minIndex = j; 
                } 
            }      
            
            //After checking the entire array the first name is switched 
            items.set(minIndex, items.get(i));
            items.set(i, minValueI);
            prices.set(minIndex, prices.get(i));
            prices.set(i, minValueP);
            stock.set(minIndex, stock.get(i));
            stock.set(i, minValueS);
        }  
    }//End of method
    
    /**
     * PriceL2H - Sort from lowest to highest price
     */
    public void priceL2H(){
        
        //Declare vairbales to hold minimums
        double minValueP;
        int minIndex;
        String minValueI, minValueS;
        
        for (int i = 0; i < prices.size(); i++) 
        { 
            
            //Keeps track of the item price and its corresponding name/stock
            minValueI = items.get(i); 
            //Parse to double for comparison
            minValueP = Double.parseDouble(prices.get(i));
            minValueS = stock.get(i); 
            minIndex = i; 
            
            //Moves through the array until a lower item price is found
            for (int j = i + 1; j < prices.size(); j++) 
            { 
                if (Double.parseDouble(prices.get(j)) < minValueP) 
                { 
                    
                    //Stores the new lowest value discovered
                    minValueI = items.get(j);
                    minValueP = Double.parseDouble(prices.get(j)); 
                    minValueS = stock.get(j); 
                    minIndex = j; 
                } 
            }      
            
            //After checking the entire array the first name is switched
            items.set(minIndex, items.get(i));
            items.set(i, minValueI);
            prices.set(minIndex, prices.get(i));
            prices.set(i, Double.toString(minValueP));
            stock.set(minIndex, stock.get(i));
            stock.set(i, minValueS);
        }  
    }//End of method
    
    /**
     * PriceH2L - Sort from price high to low
     */
    public void priceH2L(){
        
        //Declare vairbales to hold minimums
        double minValueP;
        int minIndex;
        String minValueI, minValueS;
        
        for (int i = 0; i < prices.size(); i++) 
        { 
            
            //Keeps track of the item price and its corresponding name/stock
            minValueI = items.get(i); 
            //Parse to double for comparison
            minValueP = Double.parseDouble(prices.get(i));
            minValueS = stock.get(i); 
            minIndex = i; 
            
            //Moves through the array until a lower item price is found
            for (int j = i + 1; j < prices.size(); j++) 
            { 
                if (Double.parseDouble(prices.get(j)) > minValueP) 
                { 
                    
                    //Stores the new lowest value discovered
                    minValueI = items.get(j);
                    minValueP = Double.parseDouble(prices.get(j)); 
                    minValueS = stock.get(j); 
                    minIndex = j; 
                } 
            }      
            
            //After checking the entire array the first name is switched
            items.set(minIndex, items.get(i));
            items.set(i, minValueI);
            prices.set(minIndex, prices.get(i));
            prices.set(i, Double.toString(minValueP));
            stock.set(minIndex, stock.get(i));
            stock.set(i, minValueS);
        }  
    }//End of method
    
    /**
     * Display - Prints out the inventory with prices
     */
    public void display(){
        
        //Decimal format to display cents
        DecimalFormat round = new DecimalFormat("0.00");
        
        //Allows print out to be formatted to the left
        String format = "%-40s%s%n";
        
        //Headers
        System.out.println("    ITEM\t\t\t\tPRICE");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                + "~~~~");
        
        //Loop to print out the entire inventory with prices
        for(int i = 0; i < items.size(); i++){
            
            if (i < 9){
                System.out.print((i + 1) + ".  ");
            }
            else{
                System.out.print((i + 1) + ". ");
            }
            System.out.printf(format,  items.get(i), 
                    round.format(Double.parseDouble(prices.get(i))));
        }

    }//End of method
}//End of class

