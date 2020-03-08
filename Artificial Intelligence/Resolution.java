import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;

public class Resolution {


	public void method(){


		System.out.println("Enter path for Knowledge Base file");

		Scanner input = new Scanner(System.in);

		String path=input.next();
		String KBline;
		File KB;


		try{


		KB = new File(path);

		Scanner fileinput = new Scanner(KB);
		ArrayList<CNFSubClause>ReSubClauses = new ArrayList<CNFSubClause>();

		CNFSubClause subClause = new CNFSubClause();

			KBline = fileinput.nextLine();
		     String temp;
			 String [] subC = KBline.split("\\^"); //xorizoume ta ^
			 String [] subL;
             ArrayList <String> lit = new ArrayList<String>(); //literal

             CNFClause Base = new CNFClause();

		        for (int i = 0; i < subC.length; i++) { //xorizei kathe sub clause kai kovei tis parentheseis
		            subC[i] = subC[i].trim();
		            subC[i] = subC[i].substring(1, subC[i].length() - 1);
		            subC[i] = subC[i].trim();
					//System.out.println(subC[i]);
		        }


		        for(int i=0;i<subC.length;i++){

		        	subL = subC[i].split("V"); //pairnoume kathe literal xoria diladi xorizoume kai sta V

		        	CNFSubClause l = new CNFSubClause();
		        	for(int j=0;j<subL.length;j++){
		        		subL[j]=subL[j].trim();
		        		temp=subL[j];

		        		if(temp.toCharArray()[0]=='!'){  //dimiourgw antikeimeno literal ki an einai arnisi pernaw true
						//i getLiterals mou epistrefei ena hashset me literals
		        		 l.getLiterals().add(new Literal(temp.substring(1),true));

		        		}else{
		        			l.getLiterals().add(new Literal(temp,false));
		        		}
		        		
		        	}

		        	ReSubClauses.add(i,l); //ta apothikeuw se mia arraylist subclause i opoia periexei literals eite se morfi p12 i !p12

		        }

		        Literal literal_to_prove;
		        for ( int i =0; i < ReSubClauses.size(); i++){
		             Base.getSubclauses().add(ReSubClauses.get(i)); //dimiourgw ena clause me ola ta subclauses
		        }
		        String liter;
		        System.out.println("Enter the literal we want to prove: ");
		        liter=input.next();

		        if(liter.toCharArray()[0]=='!'){
		        	literal_to_prove = new Literal(liter.substring(1),true);
		        }else{
		        	literal_to_prove = new Literal(liter,false);
		        }


		        boolean result = PL_Resolution(Base,literal_to_prove);

		        literal_to_prove.print();
		        System.out.println("is " + result);


	}catch(IOException e){
	   e.printStackTrace();
	}



	}

	public static boolean PL_Resolution(CNFClause KB, Literal a)
    {
        //We create a CNFClause that contains all the clauses of our Knowledge Base
        CNFClause clauses = new CNFClause();
        clauses.getSubclauses().addAll(KB.getSubclauses());

        //...plus a clause containing the negation of the literal we want to prove
        Literal aCopy = new Literal(a.getName(), !a.getNeg());
        CNFSubClause aClause = new CNFSubClause();
        aClause.getLiterals().add(aCopy);
        clauses.getSubclauses().add(aClause);

        System.out.println("We want to prove...");
        a.print();

        boolean stop = false;
        int step = 1;
        //We will try resolution till either we reach a contradiction or cannot produce any new clauses
        while(!stop)
        {
            Vector<CNFSubClause> newsubclauses = new Vector<CNFSubClause>();
            Vector<CNFSubClause> subclauses = clauses.getSubclauses();

            System.out.println("Step:" + step);
            step++;
            //For every pair of clauses Ci, Cj...
            for(int i = 0; i < subclauses.size(); i++)
            {
                CNFSubClause Ci = subclauses.get(i); //pairnei kathe sub caluse px edw to prwto subclause  notp21 ki b11

                for(int j = i+1; j < subclauses.size(); j++)
                {
                    CNFSubClause Cj = subclauses.get(j); //gia kathe ena apo t alla treia px p12 b11 p21

                    //...we try to apply resolution and we collect any new clauses
					//to apotelesma tha periexei ola ta literals ton dio subclause
					//ektos apo to zeugari pou to ena tha einai arnisi tou allou
                    Vector<CNFSubClause> new_subclauses_for_ci_cj = CNFSubClause.resolution(Ci, Cj);
					// px ektos apo to p21 kai to not p21 ara to b11 p12
	        //We check the new subclauses...
                    for(int k = 0; k < new_subclauses_for_ci_cj.size(); k++)
                    {
                        CNFSubClause newsubclause = new_subclauses_for_ci_cj.get(k);

                        //...and if an empty subclause has been generated we have reached contradiction; and the literal has been proved
                        if(newsubclause.isEmpty())
                        {
                            System.out.println("----------------------------------");
                            System.out.println("Resolution between");
                            Ci.print();
                            System.out.println("and");
                            Cj.print();
                            System.out.println("produced:");
                            System.out.println("Empty subclause!!!");
                            System.out.println("----------------------------------");
                            return true;
                        }

                        //All clauses produced that don't exist already are added
                        if(!newsubclauses.contains(newsubclause) && !clauses.contains(newsubclause))
                        {
                            System.out.println("----------------------------------");
                            System.out.println("Resolution between");
                             Ci.print();
                            System.out.println("and");
                             Cj.print();
                            System.out.println("produced:");
                            newsubclause.print();
                            newsubclauses.add(newsubclause);
                            System.out.println("----------------------------------");
                        }
                    }
                }
            }

            boolean newClauseFound = false;

            //Check if any new clauses were produced in this loop
            for(int i = 0; i < newsubclauses.size(); i++)
            {
                if(!clauses.contains(newsubclauses.get(i)))
                {
                    clauses.getSubclauses().addAll(newsubclauses);
                    newClauseFound = true;
                }
            }
            //If not then Knowledge Base does not logically infer the literal we wanted to prove
            if(!newClauseFound)
            {
                System.out.println("Not found new clauses");
                stop = true;
            }
        }
        return false;
    }



}
