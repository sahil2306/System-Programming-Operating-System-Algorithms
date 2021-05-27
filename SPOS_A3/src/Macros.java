import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Macros {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub		 
		 boolean flag=false;
		 String[] arr,arr1;
		 int mdt_ptr=1,kpdtp=101;
		 FileWriter obj=null;
		 String PNTAB_Address="";
		 Scanner PNTAB_Reader=null,myReader=null;
		 Vector<String> v=new Vector<>();
		 
		 File myObj=null;
		 
		 try {
//		      myObj = new File("C:\\Users\\Dell\\Downloads\\input1 (1).txt");
		      myObj = new File("C:\\Users\\Dell\\eclipse-workspace\\SPOS_A3\\src\\MACRO.txt");
		      FileWriter MNT = new FileWriter("C:\\Users\\Dell\\eclipse-workspace\\SPOS_A3\\src\\MNT.txt");
		      FileWriter MDT = new FileWriter("C:\\Users\\Dell\\eclipse-workspace\\SPOS_A3\\src\\MDT.txt");
		      FileWriter KPDTAB = new FileWriter("C:\\Users\\Dell\\eclipse-workspace\\SPOS_A3\\src\\KPDTAB.txt");
		      
		      
		      String first="NAME	PP	KP	MDT	KPDTP\n";
		      MNT.write(first);
		      myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		    	  String data = myReader.nextLine();
//		    	  System.out.println(data);
		    	  
		    	  
		    	  
		    	  if(data.equals("MACRO")) {
		    		 flag=true;
		    		 data = myReader.nextLine();
		    		 arr=data.split("\t");
		    		 String t="";
		    		 t+=arr[0];
		    		 obj = new FileWriter("C:\\Users\\Dell\\eclipse-workspace\\SPOS_A3\\src\\PNTAB of "+t+".txt");
		    		 PNTAB_Address="C:\\Users\\Dell\\eclipse-workspace\\SPOS_A3\\src/PNTAB of "+t+".txt";
		    		 v.add(PNTAB_Address);
		    		 arr1=arr[1].split(", ");
		    		 int pp=0,kp=0;
		    		 int towritekpdtp=kpdtp;
		    		 for(String i : arr1) {
		    			 if(i.contains("=")) {
		    				 kp++;
		    				 kpdtp++;
		    				 int ind=i.indexOf('=');
		    				 String r=i.substring(0,ind)+"	"+i.substring(ind+1)+"\n";
		    				 KPDTAB.write(r);
		    				 obj.write(i.substring(0,ind)+"\n");
		    			 }
		    			 else {
		    				 int ind=i.indexOf(',');
		    				 if(ind==-1) {
		    					 obj.write(i+"\n");
		    				 }else {
		    					 obj.write(i.substring(0,ind)+"\n");
		    				 }
		    				 pp++;
		    			 }
		    		 }
		    		 t+="	"+pp+"	"+kp+"	"+mdt_ptr+"	"+towritekpdtp+"\n";
		    		 MNT.write(t);
		    		 continue;
		    	  }
		    	  obj.close();
		    	  
		    	  if(flag) {
		    		  File PNTAB = new File(PNTAB_Address);
		    		  arr = data.split("\t");
		    		  String t =mdt_ptr+"	"+arr[0];
		    		  
		    		  for(int i=1;i<arr.length;i++) {
		    			  if(arr[i].contains(",")) {
		    				  arr[i]=arr[i].substring(0,arr[i].length()-1);
		    			  }
		    			  PNTAB_Reader =new Scanner(PNTAB);
		    			  int ind_of_i=1;
		    			  boolean f=true;
		    			  while(PNTAB_Reader.hasNextLine()) {
		    				  String e=PNTAB_Reader.nextLine();
		    				  if(arr[i].equals(e)) {
		    					  t+="	(P,"+ind_of_i+")";
		    					  f=false;
		    					  break;
		    				  }else {
		    					  ind_of_i++;
		    				  }
		    			  }
		    			  if(f) {
		    				  t+="	"+arr[i];
		    			  }
		    		  }
		    		  MDT.write(t+"\n");
		    		  
		    		  mdt_ptr++;
		    	  }
		    	  if(data.equals("MEND")) {
		    		  flag=false;
		    	  }  
		      }
		      myReader.close();
		      MNT.close();
		      KPDTAB.close();
		      MDT.close();
		
		    		  
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		 
		 
		 	System.out.println("MNT\n");
		 	
		 	myObj=new File("C:\\Users\\Dell\\eclipse-workspace\\SPOS_A3\\src\\MNT.txt");
		 	myReader = new Scanner(myObj);
		 	while (myReader.hasNextLine()) {
		    	  String data = myReader.nextLine();
		    	  System.out.println(data);
		 	}
		 		
		 	System.out.println("\n"+"MDT"+"\n");
		 	
		 	myObj=new File("C:\\Users\\Dell\\eclipse-workspace\\SPOS_A3\\src\\MDT.txt");
		 	myReader = new Scanner(myObj);
		 	while (myReader.hasNextLine()) {
		    	  String data = myReader.nextLine();
		    	  System.out.println(data);
		 	}
		 	
		 	
		 	System.out.println("\n"+"KPDTAB"+"\n");
		 	
		 	myObj=new File("C:\\Users\\Dell\\eclipse-workspace\\SPOS_A3\\src\\KPDTAB.txt");
		 	myReader = new Scanner(myObj);
		 	while (myReader.hasNextLine()) {
		    	  String data = myReader.nextLine();
		    	  System.out.println(data);
		 	}
		 	
		 	
		 	for(String i : v) {
		 		int ind=i.indexOf('/');
		 		String for_=i.substring(ind+1,i.length()-4);
		 		System.out.println("\n"+for_+"\n");
		 		myObj=new File(i);
			 	myReader = new Scanner(myObj);
			 	while (myReader.hasNextLine()) {
			    	  String data = myReader.nextLine();
			    	  System.out.println(data);
			 	}
		 	} 
		 	
		 	
		 }

}