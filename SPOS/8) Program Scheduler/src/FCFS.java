import java.util.Arrays;
import java.util.Scanner;

public class FCFS {

    private Scanner input;

    public void  execute()
    {
        input = new Scanner(System.in);

        //--------FCFS
        System.out.println("Enter Number of Processes:");
        int n=input.nextInt();
        Process[] process=new Process[n];

        //Accept Input
        for(int i=0;i<n;i++)
        {
            System.out.println("P("+(i+1)+"):Enter Arrival time & Burst time");
            int at=input.nextInt();
            int bt=input.nextInt();
            process[i] = new Process("P"+(i+1), bt, at);
        }

        //Sorting processes according to Arrival Time //No need if you take AT=0 or in ascending order
        Arrays.sort(process,new SortByArrival());

        int sum=0;
        double avgWT=0,avgTAT=0;
        System.out.println("\n\nPRNo\tBT\tAT\tCT\tTAT\tWT");
        System.out.println("============================================================================================");
        for(int i=0;i<n;i++)
        {
            sum+=process[i].BT;
            process[i].CT = sum;


            process[i].TAT=process[i].CT-process[i].AT;
            process[i].WT=process[i].TAT-process[i].BT;

            avgWT=avgWT+process[i].WT;
            avgTAT=avgTAT+process[i].TAT;

            System.out.println(process[i].name+"\t"+process[i].BT+"\t"+process[i].AT+"\t"+process[i].CT+"\t"+process[i].TAT+"\t"+process[i].WT);
        }
        avgTAT=(double)avgTAT/n;
        avgWT=(double)avgWT/n;
        System.out.println();
        System.out.println("Average Waiting Time : "+avgWT);
        System.out.println("Average TAT : "+avgTAT);
    }

}