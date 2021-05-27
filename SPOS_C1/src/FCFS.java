
import java.util.*;
import java.util.Map.Entry;




public class FCFS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer n; // no of processes
		TreeMap<Integer,Integer> map=new TreeMap<Integer,Integer>(); 
		TreeMap<Integer,String> mapname=new TreeMap<Integer,String>(); 
		
		
		
	    Scanner myObj = new Scanner(System.in);  // Create a Scanner object

	    System.out.println("Enter the number of processes for FCFS");
	    n=myObj.nextInt();
	    
	    int[] arrival=new int[n+1];
		int[] burst=new int[n+1];
		int[] turnarround=new int[n+1];
		int[] finish=new int[n+1];
		int[] waiting=new int[n+1];
	    
	    Integer burstin,arrivalin; //burst time and arrival time of each process
	    System.out.println("Enter burst and arrival time for each process");
		for(int i=1;i<=n;i++) {
			burstin=myObj.nextInt();
			arrivalin=myObj.nextInt();
			map.put(arrivalin, burstin);
			mapname.put(arrivalin, "P"+i);
			arrival[i]=arrivalin;
			burst[i]=burstin;
			mapname.put(arrivalin, "P"+i);
		}
		Integer finishTime=0;
		Integer i=1;
		System.out.println("Burst Time\t\tArrival Time\t\tTurn Around Time\t\t\tWaiting Time");
		for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
			if(i==1) {
				finishTime=entry.getValue()+entry.getKey();
				finish[i]=finishTime;
				turnarround[i]=finishTime-entry.getKey();
				waiting[i]=turnarround[i]-entry.getValue();
				System.out.println(entry.getValue()+"\t\t\t\t"+entry.getKey()+"\t\t\t\t"+turnarround[i]+"\t\t\t\t"+waiting[i]);
				i++;
			}
			else {
				if(finish[i-1]>=entry.getKey()) {
					finishTime+=entry.getValue();
					finish[i]=finishTime;
					turnarround[i]=finishTime-entry.getKey();
					waiting[i]=turnarround[i]-entry.getValue();
					System.out.println(entry.getValue()+"\t\t\t\t"+entry.getKey()+"\t\t\t\t"+turnarround[i]+"\t\t\t\t"+waiting[i]);
					i++;
				}
				else {
					finishTime=entry.getValue()+entry.getKey();
					finish[i]=finishTime;
					turnarround[i]=finishTime-entry.getKey();
					waiting[i]=turnarround[i]-entry.getValue();
					System.out.println(entry.getValue()+"\t\t\t\t"+entry.getKey()+"\t\t\t\t"+turnarround[i]+"\t\t\t\t"+waiting[i]);
					i++;
				}
			}
			
		}
		Float avgturn=(float) 0,avgwait=(float) 0;
		for(i=1;i<=n;i++) {
			avgturn+=turnarround[i];
			avgwait+=waiting[i];
			
		}
		System.out.println("\n\nAverage TurnAround Time is:"+avgturn/n);
		System.out.println("Average Waiting Time is:"+avgwait/n);
		
		System.out.println("\nThe gantt chart of given processes for FCFS is:");
		for(Map.Entry<Integer,String> entry : mapname.entrySet()) {
			System.out.print(entry.getValue()+" ");
		}
		
		
	   
	}

}
