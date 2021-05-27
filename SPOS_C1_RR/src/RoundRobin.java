import java.util.*;

class arrivalSort implements Comparator<RoundRobin> 
{ 
    public int compare(RoundRobin a, RoundRobin b) 
    { 
        return a.arrival - b.arrival; 
    } 
} 

public class RoundRobin {
	 static Scanner sc=new Scanner(System.in);
	 int arrival,burst,index,f;
	 int time;
	
	 RoundRobin(int a,int b,int c){
		arrival=a;
		burst=b;
		index=c;
		f=0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int  n,Quantum;
		System.out.println("Enter the number of processes for RR:");
		n=sc.nextInt();
		System.out.println("Enter Quantum Time for RR:");
		Quantum=sc.nextInt();
	
		ArrayList<RoundRobin> arr=new ArrayList<>();
		ArrayList<RoundRobin> arr1=new ArrayList<>();
		
		Queue<RoundRobin> q = new LinkedList<RoundRobin>();
		Map<Integer,Integer> ans=new HashMap<>();
		
	
		Integer[] turn= new Integer[n];
		Integer[] wait= new Integer[n];
		
		System.out.println("Enter Arrival and Burst time for Round Robin");
		
		for(int i=0;i<n;i++) {
			
			int a,b;
			a=sc.nextInt();
			b=sc.nextInt();
			arr.add(new RoundRobin(a,b,i));
			arr1.add(new RoundRobin(a,b,i));
			
		}
		Collections.sort(arr,new arrivalSort());
		Vector<String> orderexe=new Vector<String>();
		q.add(arr.get(0));
		arr.get(0).f=1;
		int t=0;
		while(!q.isEmpty()) {
			RoundRobin ele=q.peek();
			q.remove();
			orderexe.add("P"+(ele.index+1));
			t+=Math.min(Quantum,ele.burst);

			ele.burst=Math.max(0,ele.burst-Quantum);
			ele.f=0;
			
			int j=0;
			
			while(j<n && t>=arr.get(j).arrival) {
				if(arr.get(j).index!=ele.index && arr.get(j).f==0 && arr.get(j).burst!=0) {
					q.add(arr.get(j));
					arr.get(j).f=1;
				}
				j++;
			}

			if(ele.burst==0) {
				ans.put(ele.index+1,t);

			}else {	
				ele.f=1;
				q.add(ele);
			}

		}
		for(int i=0;i<n;i++) {
			turn[i]=ans.get(i+1)-arr1.get(i).arrival;
		}
		for(int i=0;i<n;i++) {
			wait[i]=turn[i]-arr1.get(i).burst;
		}
		
		System.out.println("Process\t  Burst Time\tArrival Time\tTurn Around Time\tWaiting Time");


		float sum=0,sum1=0;
		
		for(int i=0;i<n;i++) {
			sum+=turn[i];
			sum1+=wait[i];
			System.out.println("P"+(i+1)+"\t\t"+arr1.get(i).burst+"\t\t"+arr1.get(i).arrival+"\t\t"+turn[arr1.get(i).index]+"\t\t"+wait[arr1.get(i).index]);
		}
		
		System.out.println("\nAverage of Turn Around Time="+sum/n);
		System.out.println("Average of Waiting Time="+sum1/n);
		
		System.out.println("\nThe Gantt chart of processes for RR is:");
		
		for(int i=0;i<orderexe.size();i++) {
			if(i!=orderexe.size()-1){
				System.out.print(orderexe.get(i)+" ");
			}else {
				System.out.print(orderexe.get(i));
			}
		}
		
	}

}