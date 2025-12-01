import java.util.Scanner;
public class practice_lab6 {
    public static void main(String[] args) {
        Scanner kb=new Scanner(System.in);
        int choice=readMenuChoice(kb);
        if (choice==1){
            choice=
        }

    }

    public static void printMenu(){
    System.out.println("1) deposit");
    System.out.println("2) withdraw");
    System.out.println("check balance");
     System.out.println("exit");
    }

    public static readMenuChoice(Scanner kb){
        int choice=0;
        while(choice < 1 || > 4){
            if (kb.hasNextInt()){
                choice=kb.nextInt();
            }
            else {
                choice=kb.nextLine();
            }
            if (choice < 1 || > 4){
                System.out.println("Invalid input.")
            }
        }
            return choice;
            }

    public 
        }
 
