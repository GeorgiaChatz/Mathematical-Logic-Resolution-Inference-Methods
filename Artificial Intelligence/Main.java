
import java.util.Scanner;



public class Main {

    public static void main(String[] args) 
    {
        
    	int argsNum=args.length;
    	int choice;
    	boolean flag=true;
    	
    	while(flag){

    	if(argsNum==0){
    	
    		Scanner input = new Scanner(System.in);
    	
    		//--------Menu----------
    	
    		System.out.println("Press 1 for PL-Resolution");
    		System.out.println("Press 2 for FC-Horn");
    		System.out.println("Press 3 for FOL-FC");
    		System.out.println("Press 0 to exit");
    	
    		choice = input.nextInt();
    		
    	}else {

    		if(args[0].equals("PL")){
    			choice=1;
    		}else if(args[0]=="FC"){
    			choice=2;
    		}else if(args[0]=="FOL-FC"){
    			choice=3;
    		}else{
    			choice=0;
    		}
    		
    	}
    	
    	if(choice==1){
    		Resolution res = new Resolution();
    		res.method();
    		
    	}else if(choice==2){
    		Horn horn = new Horn();
    		horn.method();
    		
    	}else if(choice==3){
    		FOL_FC fol = new FOL_FC();
    		fol.method();
    		
    	}else if(choice ==0){
    		System.out.println("End of program");
    		flag=false;
    	}
    	else{
    		System.out.println("Wrong choice");
    	}
    	
       System.out.println("\n");	
    	
    }
  }
}

