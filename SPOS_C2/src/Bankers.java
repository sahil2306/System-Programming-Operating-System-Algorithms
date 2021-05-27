import java.util.*;

public class Bankers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc=new Scanner(System.in);
		Integer n,m; //no of processes and resources
		System.out.print("Enter number of processes and resources:");
		n=sc.nextInt();
		m=sc.nextInt();
		
		Integer allocation[][]=new Integer[n][m];
		Integer resource[]=new Integer[m];
		Integer maxneed[][]=new Integer[n][m];
		Integer remaining[][]=new Integer[n][m];
		Integer available[]=new Integer[m];
		Integer totalalloc[]=new Integer[m];
		Integer finish[]=new Integer[n];
		Integer sum=0;
		Vector<String>ans=new Vector<String>();
		
		for(int i=0;i<n;i++) {
			finish[i]=0;
		}
		
		System.out.println("Enter the no. of instances for each resource");
		for(int i=0;i<m;i++) {
			resource[i]=sc.nextInt();
		}
		System.out.println("Enter the number of allocated resources to each process");
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				allocation[i][j]=sc.nextInt();
			}
		}
		System.out.println("Enter the maximum needs of each process for every resource");
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				maxneed[i][j]=sc.nextInt();
			}
		}
		
		//calculation of total allocated matrix
		for(int i=0;i<m;i++) {
			sum=0;
			for(int j=0;j<n;j++) {
				sum+=allocation[j][i];
			}
			totalalloc[i]=sum;
			available[i]=resource[i]-totalalloc[i];
		}
		
		//calculation of remaining matrix
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				remaining[i][j]=maxneed[i][j]-allocation[i][j];
			}
		}
		
		//calculating whether the situation is safe or unsafe
		Integer flag=-1,tempsum=0,count=n;
		for(int i=0;i<n;i++) {
			if(remaining[i][0]<=available[0] && remaining[i][1]<=available[1] && remaining[i][2]<=available[2]) {
				flag=1;
				break;
			}
			
		}
		if(flag==-1) {
			System.out.println("The given situation is unsafe");
			return;
		}
		else {
			
			for(int i=0,cnt=0;cnt<n && tempsum<=count;) {
				if(finish[i]==0 && remaining[i][0]<=available[0] && remaining[i][1]<=available[1] && remaining[i][2]<=available[2]) {
					System.out.println("Work array after "+cnt+" iterations");
					for(int j=0;j<m;j++) {
						available[j]=available[j]+allocation[i][j];
						System.out.print(available[j]+" ");
					}
					System.out.println();
					count-=1;
					tempsum=0;
					finish[i]=1;
					System.out.println("Finish array after "+cnt+" iterations");
					for(int j=0;j<n;j++) {
						System.out.print(finish[j]+" ");
					}
					System.out.println();
					cnt++;
//					System.out.print("P"+(i+1)+" ");
					ans.add("P"+(i+1));
					i=(i+1)%n;
				}
				else {
					System.out.println("Finish array after "+cnt+" iterations");
					for(int j=0;j<n;j++) {
						System.out.print(finish[j]+" ");
					}
					System.out.println();
					i=(i+1)%n;
					tempsum++;
					
				}
				
			}
			if(count==0) {
				System.out.println("\nThe given situation is safe\n");
				System.out.println("\nThe safe sequence is");
				for(int i=0;i<ans.size();i++)
					System.out.print(ans.get(i)+" ");
			}
			else
				System.out.println("\nThe given situation is unsafe");
		}

	}

}


//Enter number of processes and resources:5 4
//Enter the no. of instances for each resource
//3 14 12 12
//Enter the number of allocated resources to each process
//0 0 1 2
//1 0 0 0
//1 3 5 4 
//0 6 3 2
//0 0 1 4
//Enter the maximum needs of each process for every resource
//0 0 1 2
//1 7 5 0
//2 3 5 6
//0 6 5 2
//0 6 5 6

