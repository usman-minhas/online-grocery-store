/**
 * Project: Culminating
 * Programmer: Usman Minhas
 * Program: Jay's NoFrills
 * Date: January 22, 2019
 * Description: This program mimics an online grocery store
 */

package code;

//Imports
import java.io.*;
import java.util.Scanner;

public class Code {

    public static void main(String[] args) throws IOException{
        
        //Declare All Variable, Scanners 
        Scanner scanS = new Scanner(System.in);
        Scanner scanN = new Scanner(System.in);
        String filename, username = null;
        int option, sortChoice;
        
        //Create user cart
        Cart cart = new Cart();
        
        //Welcome message
        System.out.println("Welcome to Jay's NoFrills!");
        
        //Print statement to allow user to know what their options are 
        do {//Error traps selection
                System.out.println("\n1. Sign up");
                System.out.println("2. Log in");
                option = scanN.nextInt();
                
                if(option != 1 && option != 2) {
                    System.err.println("You have entered an incorrect option!");
                }
        }while(option != 1 && option != 2);
        

        /**
        *Switch statement - to allow the user to choose between 
        *log in or sign in 
        **/
        switch (option) {
            //if user chooses 1 as their choice 
            case 1: {
                //delcaring variables 
                String password, email, name , securityQuestion;

                //Ask user to input data required to make an account
                //and then further collect the data 
                System.out.print("Enter Full Name: ");
                name = scanS.nextLine();

                System.out.print("Please enter your email: ");
                email = scanS.nextLine();

                System.out.print("Please enter your username: ");
                username = scanS.nextLine();
                
                System.out.println("Security question.....");
                System.out.print("What is your favorite type of food: ");
                securityQuestion = scanS.nextLine();

                System.out.print("Please enter a password: ");
                password = scanS.nextLine();

                //Calls upon the login method for the user  
                FileWriter fWriter = new FileWriter("H:\\" + 
                        username + ".txt", false);
                PrintWriter accounts = new PrintWriter(fWriter);
                accounts.println(password);
                accounts.println(securityQuestion);
                accounts.close();
                System.out.println("Please prepare to login with your "
                        + "account...");
                login();
                break;
            }//end of case 1 

            case 2: {
                username = login();
                break;
                
            }//end of case 2
            
        }//end of swtich statement
            
        //ask user what they want to do
        do{
            do {//Displays menu and error traps selection
                System.out.println("What operation would you like to perform?"
                        + "\n(Please type in the option number of your choice)"
                        + "\n 1) Purchase Items" 
                        + "\n 2) Remove Items"
                        + "\n 3) View Cart"
                        + "\n 4) View Account"
                        + "\n 5) Checkout"
                        + "\n 6) View Last Order"
                        + "\n 7) Exit");
        
                //get input
                option = scanN.nextInt();
            
                    if(option < 0 || option > 7) {
                    System.err.println("You have entered an incorrect option!");
                }
            } while(option < 0 || option > 7);//End of do while
        
        
            //Switch for the options when they are logged in
            switch (option) {
                case 1: {//Case for menu options
                    do{//Error traps sort choice
                        System.out.println("How would you like to sort it?");
                        System.out.println("1. Alphabetically\n2. Price "
                                + "(Low to High)"
                                + "\n3. Price (High to Low)\n4. Search");
                        sortChoice = scanN.nextInt();
                        switch (sortChoice) {//Case for sorting method
                            case 1:{
                                cart.alphabetical();
                                cart.display();
                                buy(cart);
                                break;
                            }
                            case 2:{
                                cart.priceL2H();
                                cart.display();
                                buy(cart);
                                break;
                            }
                            case 3:{
                                cart.priceH2L();
                                cart.display();
                                buy(cart);
                                break;
                            }
                            case 4:{
                                //Asks them what item they want
                                String item;
                                System.out.println("Enter Item Name:");
                                item = scanS.nextLine();
                                cart.search(item);
                                break;
                            }
                            default:{
                                System.err.println("INVALID ENTRY");
                                break;
                            }
                        }//End of switch
                    }while(sortChoice < 0 || sortChoice > 4);//End of do while
                    break;
                }
                case 2: {
                    cart.removeCart();
                    break;
                }
                case 3: {
                    cart.viewCart();
                    break;
                }
                case 4: {
                    cart.viewAccount(username);
                    break;
                }
                case 5: {
                    cart.checkOut(username);
                    break;
                }
                case 6: {
                    cart.viewLastOrder(username);
                    break;
                }
                case 7: {
                    System.out.println("Thank's For Shopping");
                    System.exit(0);
                    break;
                }
            }//End of switch
        }while (option != 7);//End of do while
      
    
    }//end of main
    
    /***************************************************************
     * login - Allows the user to login as well as reset password
     * @return - Returns the username
     * @throws IOException 
     **************************************************************/
    public static String login() throws IOException{
                    
        //Declare Vairables and Scanner
        String checkUS, checkPass, newPass, securityQuestion, answer, password,
                name, email;
        Scanner scanS = new Scanner(System.in);
        File file;

        //ask user to enter their username 
        do{//Do while to error trap
            System.out.print("Enter Username: ");
            checkUS = scanS.nextLine();
            file = new File("H:\\" + checkUS + ".txt");
            
            if(!file.exists()){//Checks if users file exists
                System.err.println("Incorrect Username!");
            }
        }while(!file.exists());

        //ask user to enter their password 
        System.out.print("Enter Password: ");
        checkPass = scanS.nextLine();

        //Decalre scanner
        Scanner myFile = new Scanner(file);
        
        //Read file
        String line = myFile.nextLine();
        String lineTwo = myFile.nextLine();
        
        //Store as password and answer
        password = line;
        answer = lineTwo;
        
        /**
        *Check the password and username
        *if the password is the same and username is the same. 
        **/
       if (password.equalsIgnoreCase(checkPass)) {
           System.out.println("Welcome!!");
                    } //else if the password is the same and username is diff. 
        else if (!password.equalsIgnoreCase(checkPass)) {

        //allow the user to enter in their password 
        do {//Runs loop while password is wrong
            //display kind message and allow them to re - enter
            System.out.println("Sorry your password was"
            + " wrong.");
            System.out.println("Re - Enter your Password.");
            System.out.print("Password: ");
            checkPass = scanS.nextLine();
            
            if (password.equalsIgnoreCase(checkPass)) {
                System.out.println("Welcome!! ");
                            }
            
            else {
                
                String ans = null;
                
                do{//Error traps selection
                    //ask if they want to reset the username
                    System.out.print("Do you want to reset the password: ");
                    ans = scanS.nextLine();
                    
                    //Error message display
                    if(!ans.equalsIgnoreCase("Yes") && 
                            !ans.equalsIgnoreCase("No")){
                        System.err.println("INVALID ENTRY");
                    }
                }while(!ans.equalsIgnoreCase("Yes") && 
                        !ans.equalsIgnoreCase("No"));
                
                //If the user wants to reset their password 
                if (ans.equalsIgnoreCase("Yes")) {
                    //Asks for details
                    System.out.print("Enter Full Name: ");
                    name = scanS.nextLine();

                    System.out.print("Please enter your email: ");
                    email = scanS.nextLine();

                    do{//Error traps wrong username
                        System.out.print("Enter Username: ");
                        checkUS = scanS.nextLine();
                        file = new File("H:\\" + checkUS + ".txt");
                        
                        if(!file.exists()) {
                                System.err.println("Incorrect Username!");
                        }
                    }while(!file.exists());

                    do {//Error traps secuirity question answer
                        System.out.println("Security question.....");
                        System.out.print("What is your favorite type of"
                                + " food: ");
                        securityQuestion = scanS.nextLine();
                        if(!securityQuestion.equalsIgnoreCase(answer)) {
                            System.err.println("Wrong Security Question!");
                            System.exit(0);
                        }
                    }while(securityQuestion.equals(answer));
                    
                    //If statement for if the security question is correct
                    if(securityQuestion.equalsIgnoreCase(answer)) {
                           
                        //allow them to re enter their new password 
                        System.out.println("Enter new Password: ");
                        newPass = scanS.nextLine();
                                  
                        //open users file and add newPass into it. 
                        FileWriter fwriter = new FileWriter(checkUS + ".txt",
                                false);
                        PrintWriter accounts = new PrintWriter(fwriter);
                        accounts.println(newPass);
                        accounts.println(securityQuestion);
                        accounts.close();
                    }
                    break;
                }   
                else {
                    System.out.println("Thank you for using our program!");
                    System.exit(0);
                }
            }//end of else
                                          
            } while (!password.equalsIgnoreCase(checkPass));
                        
            //print out a welcome sign for welcoming user into program
            System.out.println("Welcome!!!");
         
        }//end of else if
                    
       return checkUS;
    }//end of method
    
    public static void buy(Cart cart){
        //Variables
        int itemnum;
        String amount;
        boolean overstock =  false;
        
        //Scanners
        Scanner scanN = new Scanner(System.in);
        Scanner scanS = new Scanner(System.in);
        
        do{//Error traps incorrect choice
            
            System.out.println("Which item would you like?");
            itemnum = scanN.nextInt();
            
            if(cart.inventorySize() < (itemnum)){
                System.err.println("INVALID ENTRY");
            }
            
        }while(cart.inventorySize() < (itemnum));
        
        do{//Error traps overstock
            do{//Error traps negative input
                System.out.println("How many would you like?");
                amount = scanS.nextLine();
                if(Integer.parseInt(amount) < 1){
                    System.err.println("INVALID ENTRY");
                }
            }while(Integer.parseInt(amount) < 1);
            
            overstock = cart.addCart(itemnum, amount);
            
            if(overstock == true){
                System.err.println("INVALID ENTRY");
            }
            
        }while (overstock == true);
        
        
    }//End of buy method
    
}//End of program



