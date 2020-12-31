/**
 * Class: Cart
 * Programmer: Usman Minhas, Jay Cordeiro, Sarada Sai Turaga
 * Program: Jay's NoFrills
 * Date: January 22, 2019
 * Description: Contains cart items and prices
 */
package code;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.ArrayList;


public class Cart extends Inventory{
    
    //Feilds 
    protected ArrayList <String> cartItems = new ArrayList<>();
    protected ArrayList <String> cartPrices = new ArrayList<>();
    protected ArrayList <String> cartAmount = new ArrayList<>();
    
    
    /**
     * Constructor
     * @throws IOException 
     */
    public Cart() throws IOException{
        super();
    }
    
    /**
     * inventorySize - returns the size of the inventory
     * @return - size of inventory
     */
    public int inventorySize(){
        return items.size();
    }
    
    /**
     * addCart - Adds item to cart with price and quantity
     * @param itemnum - Contains index of item
     * @param amount -  Contains quantity desired
     * @return - Returns whether there is enough stock or not
     */
    public boolean addCart(int itemnum, String amount ){
        
        boolean overstock = false;
        
        //Checks to see if there is enough stock
        if(Integer.parseInt(stock.get(itemnum - 1)) == 0){
            System.err.println("\nOUT OF STOCK!\n");
            overstock = false;
        }
        else if(Integer.parseInt(amount) > 
                Integer.parseInt(stock.get(itemnum - 1))){
            System.err.println("Sorry we don't have that many in stock");
            System.err.println("We have " + stock.get(itemnum - 1) + " of " +
                    items.get(itemnum - 1));
            overstock = true;
        }
        else{
            //Adds prices and item name to carts array lists
            cartItems.add(items.get(itemnum - 1));
            cartPrices.add(prices.get(itemnum - 1));
            cartAmount.add(amount);
            int garb = (Integer.parseInt(stock.get(itemnum - 1)) - 
                    Integer.parseInt(amount));
            stock.set((itemnum - 1), Integer.toString(garb));
            overstock = false;
        }
        
        return overstock;
    }//end of AddCart
    
    /**
     * @method - removeCart(allows the customer to remove items 
     * from their cart)
     */
    public void removeCart(){
        
        Scanner scanN = new Scanner(System.in);   
        
        int itemNum = 0, i = 0;
        boolean item =  false;
    
        viewCart();
        
        //Asks user to select which item to remove
        do{//Error traps selection
            System.out.println("Which item would you like to remove?");
            itemNum = scanN.nextInt();
            
            if(cartItems.size() < itemNum || itemNum < 0){
                System.err.println("INVALID ENTRY!");
            }
        }while(cartItems.size() < itemNum || itemNum < 0);
        
        while(item != true){//Adds stock back to the inventory
            if(cartItems.get(itemNum - 1).equalsIgnoreCase(items.get(i))){
                //Adds inventory back to stock from cart
                int garb = (Integer.parseInt(stock.get(i)) + 
                            Integer.parseInt(cartAmount.get(itemNum - 1)));
                stock.set((i), Integer.toString(garb));
                item = true;
            }
            else{
                i++;
            }
        }
            
        //Removes that item from all array lists
        cartItems.remove(itemNum - 1);
        cartPrices.remove(itemNum - 1);
        cartAmount.remove(itemNum - 1);
    }//end of Remove Cart
    /**
     * @method - viewAccount(allows customer to see their account) 
     * @param username
     * @throws IOException 
     */
    public void viewAccount(String username) throws IOException {
        // Open the file 
        File myfile = new File("H:\\" + username + ".txt");
        
        Scanner inputFile = new Scanner(myfile);

            while(inputFile.hasNext()){
                
                // Read the data from the file
                String line = inputFile.nextLine();
                
                System.out.println(line);
            }//end of while.hasNext()
            
        // Close the file
        inputFile.close();
        
    }//end of ViewAccount 
    /**
     * @method - viewCart(displays cart)
     */
    public void viewCart(){
        String format = "%-40s%s%n";//For indentation
        System.out.printf(format, "Items", "Number");
        
        if(cartItems.size() >= 1){//Error traps displaying empty cart
            System.out.println("*********************************************");
            for (int i = 0; i < cartItems.size(); i++){
                System.out.print((i + 1) + ". ");
                System.out.printf(format, cartItems.get(i), cartAmount.get(i));
            }//end of for loop
            
            System.out.println("*********************************************");
        }
        else{
            System.err.println("NOTHING IN CART!");
        }
        
        
    }//end of viewAccount method
    
    /**
     * @method – Checks out user and displays receipt
     * @Param - String username
     */
    public void checkOut(String username) throws IOException{
        
        //Variables
        double  total = 0;
        String format = "%-40s%s%n";//For indenation
        DecimalFormat round = new DecimalFormat ("0.00");
        
        //Checks to ensure cart is not empty
        if (cartItems.size() >= 1){
            //Calculates total
            for(int i = 0; i < cartItems.size();i++)
            {
                total += (Double.parseDouble(cartPrices.get(i))) * 
                        (Double.parseDouble(cartAmount.get(i)));
            }//end of for loop 

            //Create Scanner 
            Scanner scanS = new Scanner(System.in);
            String userAns;

            //Asks if they want a receipt
            do{//Error traps selection
                System.out.print("Do you want the Receipt: ");
                userAns = scanS.nextLine();
                if(!(userAns.equalsIgnoreCase("Yes")) && 
                        !(userAns.equalsIgnoreCase("No"))){
                    System.err.println("INVALID ENTRY!");
                }
            }while(!(userAns.equalsIgnoreCase("Yes")) && 
                    !(userAns.equalsIgnoreCase("No")));

            //Prints out reciept
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~RECEIPT~~~~~~~"
                    + "~~~~~~~~~~~~~~~");
            for (int i = 0; i < cartItems.size(); i++){
                        System.out.printf(format, cartItems.get(i), 
                        round.format((Double.parseDouble(cartPrices.get(i))) * 
                        (Double.parseDouble(cartAmount.get(i))))); 
                    }//end of for loop

            System.out.println("Total: " + round.format(total));

            //Saves receipt in file
            if(userAns.equalsIgnoreCase("Yes")){

                FileWriter fwriter = new FileWriter("H:\\" + username +
                        "Receipts.txt", false) ;
                PrintWriter outFile = new PrintWriter(fwriter); 

                    for (int i = 0; i < cartItems.size(); i++){
                        outFile.println(cartItems.get(i) + ";" + 
                        round.format((Double.parseDouble(cartPrices.get(i))) * 
                        (Double.parseDouble(cartAmount.get(i))))); 
                    }//end of for loop

                    outFile.println("Total;" + total);
                    outFile.close();

                }//end of if statement method 
            System.out.println("Thank you for Using our Program!");
            System.exit(0);
        }
        else{
            System.err.println("Nothing in cart!");
        }
    }//end of Cart 
    
     /**
     * Search - Finds name of item being searched for
     * @param s - Passes name of item being searched for
     */
    public void search(String s){
        int first = 0; //The first index 
        int last = items.size() - 1; //The last index 
        int middle; //The midpoint of search 
        String amount = null;
        boolean found = false, overstock = false; //flag 
        String choice;
        
        DecimalFormat round = new DecimalFormat("0.00");        
        Scanner scanS = new Scanner(System.in);
        Scanner scanN = new Scanner(System.in);

        //Sorts array to allow easy searching
        alphabetical();
        
        //Checks entire inventory for item
        while (!found && first <= last) 
        { 
            middle = (first + last) / 2;//Finds middle
            
            //Checks to see if middle is the item ore before/after
            if (items.get(middle).equalsIgnoreCase(s)) 
            { 
                //Prints out item if found
                found = true;
                System.out.println("    ITEM\t\t\t\tPRICE");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                        + "~~~~~~~");
                System.out.println((middle + 1) + ". " + items.get(middle) +
                    "\t\t\t\t$"
                    + round.format(Double.parseDouble((prices.get(middle)))));
                
                do{//Do while to error trap yes or no
                    System.out.println("Would you like to buy this item?"
                            + "(Yes/No)");
                    choice = scanS.nextLine();
                    if(!(choice.equalsIgnoreCase("Yes")) &&
                            !(choice.equalsIgnoreCase("No"))){
                        System.err.println("NVALID ENTRY!");
                    }
                }while(!(choice.equalsIgnoreCase("Yes")) && 
                        !(choice.equalsIgnoreCase("No")));
                
                if(choice.equalsIgnoreCase("Yes")){
                    do{//Do while to error trap overstock
                        do{//Do while to error trap negative numbers
                            System.out.println("How many would you like?");
                            amount = scanS.nextLine();
                            if(Integer.parseInt(amount) < 1){
                                System.err.println("INVALID ENTRY");
                            }
                        }while(Integer.parseInt(amount) < 1);

                        //Passes index and amount to addCart
                        overstock = addCart((middle + 1), amount);

                        if(overstock == true){
                            System.err.println("INVALID ENTRY");
                        }

                    }while (overstock == true);
                }
            } 
                
            else if (items.get(middle).compareTo(s) > 0) 
            { 
                last = middle - 1;  
            } 
                
            else if (items.get(middle).compareTo(s) < 0) 
            { 
                first = middle + 1; 
            } 
        }
        
        //Displays error message if not found
        if (found == false){
            System.out.println("Sorry! We do not have this item.");
        }
    }//End of method
    
    /**
     * @method – viewLastOrder  - Displays last order
     * @param username – Contains username which is the text file name
     * @throws IOException 
     */
    public void viewLastOrder(String username) throws IOException{
        
        //Opens file and declares scanner
        File myFile = new File("H:\\" + username+ "Receipts.txt");
        Scanner readFile = new Scanner(myFile);
        String format = "%-40s%s%n";//For indentation
        DecimalFormat round = new DecimalFormat("0.00"); 
         
        System.out.println("*********************************************");
        //Prints out last order from file
        System.out.printf(format, "Item", "Price");
        while(readFile.hasNext()){
            String line = readFile.nextLine();
            String tokens[] = line.split(";");
            System.out.printf(format, tokens[0], 
                    round.format(Double.parseDouble(tokens[1])));
        }
        
        System.out.println("*********************************************");
    }//end of viewLastOrder
    
}//end of Cart class 
