import java.util.Arrays;
import java.util.Scanner;

public class RoundRobin {
    public void execute() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter number of Processes");
        int n = input.nextInt();

        Process[] process = new Process[n];

        System.out.println("Enter timer slab");
        int t = input.nextInt();

        for(int i=0;i<n;i++){
            System.out.println("P("+(i+1)+"):Enter Arrival Time and Burst timer");
            int at=input.nextInt();
            int bt = input.nextInt();
            process[i] = new Process("P"+(i+1),bt,at);
        }

        Arrays.sort(process,new SortByArrival());

        String Gnatt = "";

        int timer=0;
        int count=0;

        double avgWT=0,avgTAT=0;
        boolean check=false;

        System.out.println("\n\nPRNo\tBT\tAT\tCT\tTAT\tWT");
        System.out.println("============================================================================================");

        while (count<n){
            check = false;
            for(int i=0;i<n;i++){
                if(process[i].AT<=(timer) && process[i].remBT>0){
                    check=true;
                    int ptime = Math.min(t,process[i].remBT);

                    timer+=ptime;

                    Gnatt=Gnatt + (process[i].name) + "(" + timer + ") ";

                    process[i].remBT-=ptime;

                    if(process[i].remBT==0){
                        count++;
                        process[i].CT = timer;
                    }
                }
            }
            if(!check){
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

        /*while(true)
        {
            boolean done=true;
            for(int i=0;i<n;i++)
            {
                if(process[i].remBT>0 && process[i].AT<=timer)
                {
                    done=false;

                    if(process[i].remBT>t) //Ajun timer remaining aahe :v
                    {
                        timer=timer+t;
                        process[i].remBT=process[i].remBT-t;
                        System.out.println(i+" TIME "+timer);

                    }
                    else //process will finish execution
                    {

                        timer+=process[i].remBT;
                        System.out.println(i+" TIME "+timer);

                        process[i].remBT=0;
                        process[i].CT=timer;
                        process[i].TAT=process[i].CT-process[i].AT;
                        process[i].WT=process[i].TAT-process[i].BT;
                        avgWT+=process[i].WT;
                        avgTAT+=process[i].TAT;
                        process[i].display();
                    }
                }
				//else //no process is arrived currently
				//{
					//timer++;
					
				//}

            }
            if(done==true) //done executing all processes
            {
                break;
            }

        }*/
    }
}
