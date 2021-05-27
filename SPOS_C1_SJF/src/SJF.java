import java.util.Scanner;
import java.util.Vector;


public class SJF {
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println ("Enter no of process:");
        int n = sc.nextInt();
        int pid[] = new int[n]; //process id
        int at[] = new int[n];	//arrival time
        int bt[] = new int[n];	//burst time
        int ct[] = new int[n];	//completion time
        int ta[] = new int[n];	//turn around time
        int wt[] = new int[n];	//waiting time
        int f[] = new int[n];	//flag

        Vector<Integer> vec=new Vector<Integer>();
        int st=0, tot=0;
        float avgwt=0, avgta=0;

        System.out.println("Enter arrival and burst time for each process(SJF):");
        for(int i=0;i<n;i++)
        {
//            System.out.println ("enter process " + (i+1) + " arrival time:");
            at[i] = sc.nextInt();
//            System.out.println ("enter process " + (i+1) + " brust time:");
            bt[i] = sc.nextInt();
            pid[i] = i+1;
            f[i] = 0;
        }


        while(true)
        {
            int c=n, min = 999999;

            if (tot == n)
                break;

            for (int i=0; i<n; i++)
            {

                if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min))
                {
                    min=bt[i];
                    c=i;
                }
            }
            if (c==n) {
            	st++;
            	
            }
            else
            {
                ct[c]=st+bt[c];
                st+=bt[c];
                ta[c]=ct[c]-at[c];
                wt[c]=ta[c]-bt[c];
                f[c]=1;
                pid[tot] = c + 1;
                tot++;
//                vec.add(pid[c]);
            }
        }

        System.out.println("\nPid\t\tArrival\t\tBurst\t\tTurn\t\tWaiting");
        for(int i=0;i<n;i++)
        {
            avgwt+= wt[i];
            avgta+= ta[i];
            System.out.println(i+1+"\t\t"+at[i]+"\t\t"+bt[i]+"\t\t"+ta[i]+"\t\t"+wt[i]);
        }
        System.out.println ("\nAverage turn around time is "+ (float)(avgta/n));
        System.out.println ("Average waiting time is "+ (float)(avgwt/n));
        
        
    }
}