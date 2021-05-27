import java.util.Scanner;

public class Priority
{

    int burstTime[];
    int priority[];
    int arrivalTime[];
    String[] processId;
    Scanner sc = new Scanner(System.in);
    int n;

    void getProcessData() 
    {
        System.out.println("Enter the number of Process for Scheduling:");
        int inputNumberOfProcess = sc.nextInt();
        n = inputNumberOfProcess;
        burstTime = new int[n];
        priority = new int[n];
        arrivalTime = new int[n];
        processId = new String[n];
        String st = "P";
        System.out.println("Enter the burst, arrival time and priority for each process");
        for (int i = 0; i < n; i++) 
        {
            processId[i] = st.concat(Integer.toString(i));
            burstTime[i] = sc.nextInt();
            arrivalTime[i] = sc.nextInt();
            priority[i] = sc.nextInt();
        }
    }

    void sortArrivalAndPriority(int[] at, int[] bt, int[] prt, String[] pid) 
    {

        int temp;
        String stemp;
        for (int i = 0; i < n; i++) 
        {

            for (int j = 0; j < n - i - 1; j++) 
            {
                if (at[j] > at[j + 1]) 
                {
                    //swapping arrival time
                    temp = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp;

                    //swapping burst time
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;

                    //swapping priority
                    temp = prt[j];
                    prt[j] = prt[j + 1];
                    prt[j + 1] = temp;

                    //swapping process identity
                    stemp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = stemp;

                }
                
                if (at[j] == at[j + 1]) 
                {
                    if (prt[j] > prt[j + 1]) 
                    {
                        
                        temp = at[j];
                        at[j] = at[j + 1];
                        at[j + 1] = temp;

                       
                        temp = bt[j];
                        bt[j] = bt[j + 1];
                        bt[j + 1] = temp;

                        
                        temp = prt[j];
                        prt[j] = prt[j + 1];
                        prt[j + 1] = temp;

                        
                        stemp = pid[j];
                        pid[j] = pid[j + 1];
                        pid[j + 1] = stemp;

                    }
                }
            }

        }
    }

    void priorityNonPreemptive() 
    {
        int finishTime[] = new int[n];
        int bt[] = burstTime.clone();
        int at[] = arrivalTime.clone();
        int prt[] = priority.clone();
        String pid[] = processId.clone();
        int waitingTime[] = new int[n];
        int turnAroundTime[] = new int[n];

        sortArrivalAndPriority(at, bt, prt, pid);

       
        finishTime[0] = at[0] + bt[0];
        turnAroundTime[0] = finishTime[0] - at[0];
        waitingTime[0] = turnAroundTime[0] - bt[0];

        for (int i = 1; i < n; i++) 
        {
            finishTime[i] = bt[i] + finishTime[i - 1];
            turnAroundTime[i] = finishTime[i] - at[i];
            waitingTime[i] = turnAroundTime[i] - bt[i];
        }
        float sum = 0;
        for (int n : waitingTime) 
        {
            sum += n;
        }
        float avgWaiting = sum / n;

        sum = 0;
        for (int n : turnAroundTime) 
        {
            sum += n;
        }
        float avgTurnAround = sum / n;

        
        System.out.println("PId\tBurstTime\tArrivalTime\tPriority\tTurnAroundTime\tWaitingTime");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i]+"\t\t"+ bt[i]+"\t\t"+ at[i]+"\t\t"+ prt[i]+"\t\t"+turnAroundTime[i]+"\t\t"+waitingTime[i]);
        }

        System.out.println("\n\nAverage of Turn Around time is:"+ avgTurnAround);
        System.out.println("Average of Waiting time is:"+ avgWaiting);
        System.out.println("\nThe Gantt chart of the given processes for Priority job scheduling is:\n");
        for (int i = 0; i < n; i++) {
            System.out.print(pid[i]+" ");
        }
    }

    public static void main(String[] args) 
    {
        Priority obj = new Priority();
        obj.getProcessData();
        obj.priorityNonPreemptive();
    }
}
