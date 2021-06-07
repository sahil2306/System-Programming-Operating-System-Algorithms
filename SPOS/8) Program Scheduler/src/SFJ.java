import java.util.Arrays;
import java.util.Scanner;

public class SFJ {
    public void execute(){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter number of Processes");
        int n = input.nextInt();

        Process[] process = new Process[n];

        System.out.println("Enter time slab");
        int t = input.nextInt();

        for(int i=0;i<n;i++){
            System.out.println("P("+(i+1)+"):Enter Arrival Time and Burst time");
            int at=input.nextInt();
            int bt = input.nextInt();
            process[i] = new Process("P"+(i+1),bt,at);
        }

        String Gnatt = "";

        int timer=0;
        int min=Integer.MAX_VALUE;
        int count=0,shortest=0;

        int sum=0;
        double avgWT=0,avgTAT=0;
        boolean check=false;

        System.out.println("\n\nPRNo\tBT\tAT\tCT\tTAT\tWT");
        System.out.println("============================================================================================");


        while(count<n){
            for(int i=0;i<n;i++)
            {

                if(process[i].AT<=timer && (process[i].remBT<min && process[i].remBT>0))
                {
                    shortest=i;

                    min=process[i].remBT;
                    check=true;
                }
            }

            if(check){

                int ptime = Math.min(t,process[shortest].remBT);
                timer+=ptime;

                Gnatt=Gnatt + (process[shortest].name) + "(" + timer + ") ";

                process[shortest].remBT-=ptime;
                min=process[shortest].remBT;

                if(min==0){

                    process[shortest].CT = timer;
                    count++;

                }
                //timer++;
                check=false;
                min=Integer.MAX_VALUE;
            }
            else{
                timer++;
            }
        }

        for(int i=0;i<n;i++){

            process[i].TAT=process[i].CT-process[i].AT;
            process[i].WT=process[i].TAT-process[i].BT;

            avgTAT+=process[i].TAT;
            avgWT+=process[i].WT;

            System.out.println(process[i].name+"\t"+process[i].BT+"\t"+process[i].AT+"\t"+process[i].CT+"\t"+process[i].TAT+"\t"+process[i].WT);

        }

        System.out.println();
        System.out.println("Gnatt Chart : "+Gnatt);
        avgTAT=(double)avgTAT/n;
        avgWT=(double)avgWT/n;
        System.out.println("Average Waiting Time : "+avgWT);
        System.out.println("Average TAT : "+avgTAT);
    }
}
