import java.util.Arrays;
import java.util.Scanner;

public class PriorityNonPreemptive {

    public void execute(){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter number of Processes");
        int n = input.nextInt();

        Process[] process = new Process[n];

        for(int i=0;i<n;i++){
            System.out.println("P("+(i+1)+"):Enter Burst time and Priority");
            int bt=input.nextInt();
            int priority = input.nextInt();
            int at=0;
            process[i] = new Process("P"+(i+1),at,bt,priority);
        }

        Arrays.sort(process, new SortByPriority());

        int sum=0;
        double avgTAT = 0;
        double avgWT = 0;

        System.out.println("\n\nPRNo\tBT\tAT\tCT\tTAT\tWT\tPR");
        System.out.println("============================================================================================");

        for(int i=0;i<n;i++){
            sum+=process[i].BT;
            process[i].CT = sum;

            process[i].TAT=process[i].CT-process[i].AT;
            process[i].WT=process[i].TAT-process[i].BT;

            avgTAT+=process[i].TAT;
            avgWT+=process[i].WT;

            process[i].display();
        }

        avgTAT=(double)avgTAT/n;
        avgWT=(double)avgWT/n;
        System.out.println();
        System.out.println("Average Waiting Time : "+avgWT);
        System.out.println("Average TAT : "+avgTAT);
    }
}
