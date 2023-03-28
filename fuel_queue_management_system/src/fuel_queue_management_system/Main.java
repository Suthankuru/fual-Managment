package fuel_queue_management_system;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static FuelQueue [] Queue = new FuelQueue[31];
    public static passenger [] No_of_liter = new passenger[31];
    public static passenger [] Name1 = new passenger[31];
    public static passenger [] Name2 = new passenger[31];
    public static ArrayList<String> WaitingList = new ArrayList<>();
    public static String choice2 = "Y";
    static int Total_Fuel = 6600;
    static int RS = 430;

    private static String menu() {
        System.out.println("<----- WELCOME TO THE FUEL CENTER ----->");
        System.out.println("Enter '100' or 'VFQ' to View all Fuel Queues");
        System.out.println("Enter '101' or 'VEQ' to View all Empty Queues");
        System.out.println("Enter '102' or 'ACQ' to Add customer to a Queue ");
        System.out.println("Enter '103' or 'RCQ' to Remove a customer from a Queue ");
        System.out.println("Enter '104' or 'PCQ' to Remove a served customer");
        System.out.println("Enter '105' or 'VCS' View Customers Sorted in alphabetical order");
        System.out.println("Enter '106' or 'SPD' Store Program Data into file");
        System.out.println("Enter '107' or 'LPD' Load Program Data from file");
        System.out.println("Enter '108' or 'STK' View Remaining Fuel Stock");
        System.out.println("Enter '109' or 'AFS' Add Fuel Stock");
        System.out.println("Enter '110' or 'IFQ' What prize");
        System.out.println("Enter the code number ?  ");
        return input.next().toUpperCase();
    }

    public static void main(String[] args) {
        initialise();
        while (choice2.equals("Y")) {
            if (Total_Fuel >= 500) {
                String choice =  menu();
                switch (choice) {
                    case "100":
                    case "VFQ":
                        for (int x = 1; x < 31; x++) {
                            if (Queue[x].Que.equals("empty")) {
                                System.out.println("Queue " + x + " is empty");
                            } else {
                                System.out.println("---------------------------------------------");
                                System.out.println("Vehicle " + x + " is occupied by : " + Queue[x].Que);
                                System.out.println("First name of Vehicle owner : " + Name1[x].fName);
                                System.out.println("Surname of Vehicle owner : " + Name2[x].sName);
                                System.out.println("No of Liters : " + No_of_liter[x].literCount);
                                System.out.println("---------------------------------------------");
                            }
                        }
                        break;

                    case "101":
                    case "VEQ":
                        for (int x = 1; x < 31; x++) {
                            if (Queue[x].Que.equals("empty")) {
                                System.out.println("Queue " + x + " is empty");
                            }
                        }
                        break;

                    case "102":
                    case "ACQ":
                        int a = 0;
                        for (int i = 0; i < 31; i++) {
                            if (!(Queue[i].Que.equals("empty"))) {
                                a += 1;
                            }
                        }
                        if (a == 31) {
                            System.out.println("Dear Customer, all our Queue are currently full." +
                                    " \nYou will be added to our waiting list due to shortage of Queue. " +
                                    "\nSorry for the inconvenience caused. ");
                            System.out.println("Please enter your Vehicle number to add in the waiting list: ");
                            WaitingList.add(input.next().toUpperCase());
                        } else {
                            System.out.println("Enter Queue number (1-30) or 31 to stop:");
                            int VehicleNum = input.nextInt();
                            if (VehicleNum == 31) {
                                System.out.println("Sorry. You are out of 30");

                            } else if (Queue[VehicleNum].Que.equals("empty")) {
                                System.out.println("Enter Number of Vehicle " + VehicleNum + " :");
                                String Que = input.next().toUpperCase();
                                System.out.println("Enter first name of Vehicle owner: ");
                                String fName = input.next().toUpperCase();
                                Name1[VehicleNum].fName = fName;
                                System.out.println("Enter surname of of Vehicle owner: ");
                                String sName = input.next().toUpperCase();
                                Name2[VehicleNum].sName = sName;
                                System.out.println("Enter number of Liter you want:  ");
                                int no = input.nextInt();
                                No_of_liter[VehicleNum].literCount = no;
                                System.out.println("Thank You. Vehicle-Queue" + VehicleNum + " is occupied by " + Que);
                                Queue[VehicleNum] = new FuelQueue(Que, new passenger(no, fName, sName));

                            } else {

                                System.out.println("This Queue is already occupied by " + Queue[VehicleNum] +
                                        ".\n PLEASE CHOOSE ANOTHER QUEUE.");
                            }
                        }
                        for (String s : WaitingList) {
                            System.out.println(s);
                        }
                        break;

                    case "103":
                    case "RCQ":
                        System.out.println("Enter the Vehicle Number in which you want to delete a customer : ");
                        int VehicleNum = input.nextInt();
                        if (Queue[VehicleNum].Que.equals("empty")) {
                            System.out.println("The Queue is already empty");
                        } else {
                            a = 0;
                            for (int i = 0; i < 31; i++) {
                                if (!(Queue[i].Que.equals("empty"))) {
                                    a += 1;
                                }
                            }
                            Queue[VehicleNum] = new FuelQueue("empty", new passenger(0, " ", " "));
                            System.out.println("Deleted customer from Queue " + VehicleNum + " successfully");

                            if (a == 31 && !(WaitingList.isEmpty())) {
                                Queue[VehicleNum].Que = WaitingList.get(0);
                                WaitingList.remove(0);
                                System.out.println("You will be added to Queue " + VehicleNum + "shortly");
                                System.out.println("Enter first name of Vehicle owner: ");
                                String fName = input.next().toUpperCase();
                                Name1[VehicleNum].fName = fName;
                                System.out.println("Enter surname of Vehicle owner: ");
                                String sName = input.next().toUpperCase();
                                Name2[VehicleNum].sName = sName;
                                System.out.println("Enter number of Liters you want: ");
                                int no = input.nextInt();
                                No_of_liter[VehicleNum].literCount = no;
                                System.out.println("Thank You. Queue " + VehicleNum + " is occupied by " + Queue[VehicleNum].Que);
                                Name1[VehicleNum].fName = fName;
                                Name2[VehicleNum].sName = sName;
                                No_of_liter[VehicleNum].literCount = no;
                            }

                        }
                        break;

                    case "104":
                    case "PCQ":
                        int pos = 1;
                        System.out.println(No_of_liter[1].literCount);
                        Total_Fuel -= No_of_liter[1].literCount;
                        System.out.println("Total fuel count : " + Total_Fuel + "\nReduced liters : " + No_of_liter[1].literCount);
                        for (int i = 0; i < Queue.length - 1;  i++) {
                            Queue[i] = Queue[pos];
                            No_of_liter[i].literCount = No_of_liter[pos].literCount;
                            pos++;
                        }
                        break;

                    case "105":
                    case "VCS":
                        int count = 0;
                        for (int x = 0; x < 31; x++) {
                            if (!(Name1[x].fName.equals("empty"))) {
                                count += 1;
                            }
                        }
                        int c = 0;
                        String[] alphabet = new String[count];
                        for (int x = 1; x < 31; x++) {
                            if (!(Name1[x].fName.equals("empty"))) {
                                alphabet[c] = String.valueOf(Name1[x].fName);
                                c += 1;
                            }
                        }
                        boolean isSwapped = true;
                        while (isSwapped) {
                            isSwapped = false;
                            for (int i = 0; i < alphabet.length - 1; i++) {
                                if (alphabet[i].compareToIgnoreCase(alphabet[i + 1]) > 0) {
                                    String temp = alphabet[i + 1];
                                    alphabet[i + 1] = alphabet[i];
                                    alphabet[i] = temp;
                                    isSwapped = true;
                                }
                            }

                        }
                        for (String value : alphabet) {
                            System.out.println(value);
                        }
                        break;

                    case "106":
                    case "SPD":
                        try {
                            File myObj = new File("Queue.txt");
                            String newLine = System.getProperty("line.separator");
                            if (myObj.createNewFile()) {
                                System.out.println("File created: " + myObj.getName());
                            } else {
                                System.out.println("File already exists.");
                            }
                            FileWriter myWriter = new FileWriter("Queue.txt");
                            for (int x = 1; x < 31; x++) {
                                myWriter.write("---------------------------------------------"+ newLine);
                                myWriter.write("Number of Vehicle : " + Queue[x].Que + newLine);
                                myWriter.write("First name of Vehicle owner : " + Name1[x].fName + newLine);
                                myWriter.write("Surname of Vehicle owner : " + Name2[x].sName+ newLine);
                                myWriter.write("No of Liters : " + No_of_liter[x].literCount+ newLine);
                                myWriter.write("---------------------------------------------" + newLine);
                            }
                            myWriter.close();
                            System.out.println("Successfully wrote to the file.");
                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                        break;

                    case "107":
                    case "LPD":
                        try {
                            int line_count = 0;
                            File inputFile = new File("Queue.txt");
                            Scanner outputData = new Scanner(inputFile);
                            String file_line;
                            while (outputData.hasNext()) {
                                file_line = outputData.nextLine();
                                System.out.println(file_line);
                                line_count++;
                            }
                            outputData.close();
                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                        }
                        break;

                    case "108":
                    case "STK":
                        System.out.println("view remaining fuel stock: " + Total_Fuel);
                        break;

                    case "109":
                    case "AFS":
                        input.nextLine();
                        System.out.print("Would like to Fill the fuel stock (Y) or (N) : ");
                        String option = input.nextLine();
                        if(option.equals("y"))
                            Total_Fuel = 6600;
                        else if(option.equals("N"))
                            continue;
                        break;

                    case "110":
                    case "IFQ":
                        int balance = 6600 - Total_Fuel;
                        System.out.println("Remaining fuel stock: " + Total_Fuel);
                        System.out.println("Tacking fuel stock: " + balance);
                        System.out.println("Prize for one liter: " + RS);
                        System.out.println("Income for total fuel: " + balance*RS);
                        break;

                    default:
                        System.out.println("Invalid Choice");
                }
                do {
                    System.out.println("Do you want to choose another option from the menu?");
                    System.out.println("'Y' for Yes or 'N' for No");
                    choice2 = input.next().toUpperCase();
                    if (choice2.equals("N")) {
                        System.out.println("Thank You. You are about to exit the program");
                        break;
                    }
                } while (!(choice2.equals("Y")));

            } else {
                System.out.println("---------------------------------------------------");
                System.out.println("<-----!!!! Warning fuel less than 500l !!!!------->");
                System.out.println("<-------------Please add the Fuel----------------->");
                System.out.println("<-----------Enter code only 109 or AFS------------>");
                System.out.println("You added fuel before You cant see Rupees because--");
                System.out.println("----, It's new fuel stock------------------------> ");
                System.out.println("---------------------------------------------------");
                String choice =  menu();

                switch (choice) {
                    case "109":
                    case "AFS":
                        input.nextLine();
                        System.out.print("Would like to Fill the fuel stock (Y) or (N) : ");
                        String option = input.nextLine();
                        if(option.equals("y"))
                            Total_Fuel = 6600;
                        else if(option.equals("N"))
                            continue;
                        break;

                }
                do {
                    System.out.println("Do you want to choose another option from the menu?");
                    System.out.println("'Y' for Yes or 'N' for No");
                    choice2 = input.next().toUpperCase();
                    if (choice2.equals("N")) {
                        System.out.println("Thank You. You are about to exit the program");
                        break;
                    }
                } while (!(choice2.equals("Y")));

            }
        }
    }
    public static void initialise(){
        for (int x = 0; x < 31; x++) {
            Queue[x] = new FuelQueue("empty", new passenger(0," ", " "));
        }
        for (int i = 0; i < 31; i++) {
            Name1[i]=new passenger(0,"empty","");
            Name2[i]=new passenger(0,"","empty");
            No_of_liter[i]=new passenger(0,"","");
        }
    }
}
