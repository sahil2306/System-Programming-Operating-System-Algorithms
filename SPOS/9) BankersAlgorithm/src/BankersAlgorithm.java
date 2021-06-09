import java.util.Scanner;

public class BankersAlgorithm {

    static Scanner inp = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Enter Number of Resources:");
        int m = inp.nextInt();
        System.out.println("Enter Number of Processes:");
        int n = inp.nextInt();




        int Allocation[][]=new int[n][m];   //Already Allocated Resources input
        int MaxReq[][]=new int[n][m];   // Maximum Requirement of a process input
        int TotalRes[]=new int[m];  // Total Available input

        int AvailableRes[]=new int[m];

        int NeededRes[][]=new int[n][m];
        boolean Finished[] = new boolean[n];

        StringBuilder sb = new StringBuilder();
        int count=n;

        System.out.println("Enter Total Availability of Resources: ");
        System.out.println("----------------------------");
        for(int i=0;i<m;i++)
        {
            System.out.println("Resource " + (i+1) + " : ");
            TotalRes[i] = inp.nextInt();
        }


        System.out.println("Enter Allocated Resources: ");
        System.out.println("----------------------------");
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<m;j++) {
                System.out.println("Enter the amount of R" +(j+1) + " allocated to P" + (i+1));
                Allocation[i][j] = inp.nextInt();
            }
        }

        System.out.println("Enter Maximum Requirement: ");
        System.out.println("----------------------------");

        for(int i = 0;i<n;i++) {
            for(int j = 0;j<m;j++) {
                System.out.println("Enter the amount of R" +(j+1) + " maximum requirement to P" + (i+1));
                MaxReq[i][j] = inp.nextInt();
            }
        }

        for (int j = 0; j < m; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += Allocation[i][j];
            }
            AvailableRes[j] = TotalRes[j] - sum;
        }



        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                NeededRes[i][j] = MaxReq[i][j] - Allocation[i][j];
            }
        }

        while(count>0) {

            boolean safeOrUnsafe = false;

            for (int i = 0; i < n; i++) {
                boolean safe = true;
                for (int j = 0; j < m; j++) {
                    if ((NeededRes[i][j] > AvailableRes[j]) && !Finished[i]) {
                        safe = false;
                    }
                }

                if (safe && !Finished[i]) {
                    count--;
                    safeOrUnsafe = true;
                    Finished[i]=true;
                    sb.append("P").append(i+1).append("\n");
                    for (int j = 0; j < m; j++) {
                        AvailableRes[j] += Allocation[i][j];
                        Allocation[i][j] = 0;
                        MaxReq[i][j] = 0;
                    }
                }
            }

            if(!safeOrUnsafe){
                //DEADLOCK
                System.out.println("Deadlock Found");
                break;

            }

        }

        System.out.println("Processes : ");
        System.out.println(sb.toString());
    }


}
