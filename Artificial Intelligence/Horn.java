import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Horn {

	public void method(){

		System.out.println("Enter file of Knowledge base: ");
		String path;
		String q;
		Scanner input = new Scanner(System.in);
		path=input.nextLine();

		System.out.println("Enter literal to prove: ");
		q=input.nextLine();

		File KB_file;


		try{

			KB_file=new File(path);

			Scanner reader = new Scanner(KB_file);
		    String line;
			ArrayList <String> KB = new ArrayList<String>();

			while(reader.hasNextLine()){
				line=reader.nextLine();
				KB.add(line.trim());
			}

		   boolean t = pl_fc_entails(KB,q);

		   System.out.println(q+ " is " +t);



		}catch(IOException e){
			e.printStackTrace();
		}


	}


	public static boolean pl_fc_entails(ArrayList<String> KB, String q){

		ArrayList <Integer> count = new ArrayList<Integer>(); // number of premises
		ArrayList <String>  agenda = new ArrayList <String>(); //facts
		ArrayList <Boolean> inferred = new ArrayList<Boolean>(); //an ta exw simperanei i oxi
		ArrayList <String> li_to_prove = new ArrayList<String>();
	    ArrayList <String> str_inferred = new ArrayList<String>();
		ArrayList <String> hornclauses = new ArrayList<String>();
		String line;
		String p;
		String k;

		for(int i=0;i<KB.size();i++){
			int num=0; // number of "^" found in each line
			if(!(line=KB.get(i)).contains("=>")){
				agenda.add(KB.get(i));

				if(!str_inferred.contains(KB.get(i))){
					str_inferred.add(KB.get(i));
					inferred.add(true);
				}

			}else{
				li_to_prove.add((k=KB.get(i).split("=>")[1].trim()));
				hornclauses.add((k=KB.get(i).split("=>")[0].trim()));

				if(!str_inferred.contains(k)){
					str_inferred.add(k);
					inferred.add(false);
				}

				for(char c :line.toCharArray()){
					if(c=='^') num++;
				}
				count.add(num+1);

			}
		}
		int pos=0;
		while(!agenda.isEmpty()){
			p=agenda.remove(0);

			for(int i=0;i<str_inferred.size();i++){
				if(str_inferred.get(i)==p) pos=i;
			}

			if(inferred.get(pos)==false){
				inferred.set(pos,true);

			}

			for (int i=0; i<hornclauses.size(); i++){
				if (hornclauses.get(i).contains(p)){
					count.set(i,count.get(i)-1);

					if (count.get(i)==0){ //pirodotisi kanona
						inferred.set(i,true);

						if (li_to_prove.get(i).equals(q)) {

							return true;
						}

						agenda.add(li_to_prove.get(i));
						break;
					}
				}
			}
		}
		return false;

	}










}
