import java.util.Scanner;
import java.lang.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FOL_FC {

 public void method(){


	Scanner input = new Scanner(System.in);

	File KB;
	String path;
	String line;

	System.out.println("Enter file of Knowledge base");
	path=input.nextLine();
	ArrayList<String> KB_fol = new ArrayList<String>();

	String a;

	System.out.println("Enter question to prove?");
	a=input.nextLine();

	try{

		KB= new File(path);
		Scanner reader = new Scanner(KB);

		while(reader.hasNextLine()){
			line=reader.nextLine();
			KB_fol.add(line);
		}
		boolean res = fol_FC_Ask(KB_fol,a);
	   System.out.println(a+" is " +res);

	}catch(IOException e){
		e.printStackTrace();
	}
}
public static boolean fol_FC_Ask(ArrayList<String> KB,String a){

	ArrayList<String> facts = new ArrayList<String>(); //ta gegonota
	ArrayList<String> def_cl = new ArrayList<String>(); //idiotites sells hostile criminal etc
	ArrayList<String> new_list = new ArrayList<String>();
	ArrayList<String> cop_def_cl = new ArrayList<String>();
	boolean flag = false;

	for(int i=0;i<KB.size();i++){

		if(KB.get(i).contains("=>")){
			def_cl.add(KB.get(i));
			cop_def_cl.add(KB.get(i));

		}else{
			facts.add(KB.get(i));
		}
	}
		ArrayList<String> unified = new_vars(def_cl,facts);
		String p;
		for(int i=0;i<unified.size();i++){
			if ( !facts.contains(p = unified.get(i).split("=>")[1])){
				facts.add(p);
      
			}
		}

		facts.removeAll(Arrays.asList(null,""));

		unified = new_vars(def_cl , facts); //ksanakalw tin vars me ta nea simperasmata
		for ( int i =0; i<facts.size(); i++){
			if ( facts.get(i).equals(a)){
				flag = true;
			}
		}
	return flag;
}

  public static ArrayList<String> new_vars(ArrayList<String> old_clause,ArrayList<String> facts ){
      ArrayList<String> dif_feat = new ArrayList<String>();
      ArrayList<String> res = new ArrayList<String>();
	  String line;
	  boolean appears;
	  String sub="";
	  int pos=0;

	  for(int i=0;i<facts.size() -1;i++){
		  line=facts.get(i);
		  for(int j=0;j<line.length();j++){

			  if(line.toCharArray()[j]=='('){
				pos=j;
				break;
			  }
		  }
		  sub=line.substring(0, pos);

		  if (!dif_feat.contains(sub)){
			  dif_feat.add(sub);    //exoume apothikeusei oles tis idiotits px crminal sells hostile ktl
		  }
	  }
      String str="";
      String s;
	  for ( int p =0; p<dif_feat.size(); p++){
		  for(int l=0;l<old_clause.size();l++){
			  if ( old_clause.get(l).contains(dif_feat.get(p))){
				  s=dif_feat.get(p)+"(";
				  str=getBetweenStrings(old_clause.get(l),s,")"); //i metavliti pou tha allaxthei
				  String st1 =getBetweenStrings(facts.get(p), "(", ")"); //i nea metavliti
				  if ( str.contains(",")){ //otan exei parapanw apo dio orismata
					  String[] sp = str.split(",");
					  String[] sp1 = st1.split(",");

					for ( int k=0; k<sp.length; k++){
						old_clause.set(l, old_clause.get(l).replaceAll(sp[k], sp1[k])); //replace tin metavliti me tin nea
            }
				  }else{
				  old_clause.set(l, old_clause.get(l).replaceAll(str, st1));}
				  if(!res.contains(old_clause.get(l))){
					  res.add(old_clause.get(l));
				  }

			  }
		  }
	  }
        return res;}

  public static String getBetweenStrings(String text,String textFrom,String textTo){
	  String result="";
	  result=text.substring(text.indexOf(textFrom)+textFrom.length(),text.length());
	  result=result.substring(0,result.indexOf(textTo));
	  return result;

  }




}
