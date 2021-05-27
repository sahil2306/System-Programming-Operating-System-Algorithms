import java.io.*;
import java.util.ArrayList;

import java.util.Hashtable;

public class A2 {
    Hashtable<String, Integer> symaddr = new Hashtable<>();	//symbol
    ArrayList<Integer> litaddr = new ArrayList<>();			//literal
    ArrayList<String> doneSym = new ArrayList<>();
    Hashtable<String, Integer> registers = new Hashtable<>();	//registers
    Hashtable<String, Integer> conditional = new Hashtable<>();
//    ArrayList<Integer> pooltab = new ArrayList<>();
    int loc_cntr = 0;
    int key = 0;
//    int pooltab_pntr = 0;
    int littab_pntr = 0;
    Literals literalEntry;
    int firstLineNumber = 1;

    A2() {
        registers.put("AREG", 1);
        registers.put("BREG", 2);
        registers.put("CREG", 3);
        registers.put("DREG", 4);

        conditional.put("LT", 1);
        conditional.put("LE", 2);
        conditional.put("EQ", 3);
        conditional.put("GT", 4);
        conditional.put("GE", 5);
        conditional.put("ANY", 6);
    }

    void readTables() throws Exception {
        BufferedReader sym = new BufferedReader(new FileReader("C:\\Users\\Dell\\eclipse-workspace\\SPOS_A2\\src\\Symtab.txt"));
        BufferedReader lit = new BufferedReader(new FileReader("C:\\Users\\Dell\\eclipse-workspace\\SPOS_A2\\src\\Littab.txt"));
//        BufferedReader pool = new BufferedReader(new FileReader("Pooltab.txt"));
        String line = "";
        while ((line = sym.readLine()) != null) {
            String split[] = line.split("\\s+");
            symaddr.put(split[0], Integer.parseInt(split[1]));
        }
        while ((line = lit.readLine()) != null) {
            String split[] = line.split("\\s+");
            litaddr.add(Integer.parseInt(split[1]));
        }
//        while ((line = pool.readLine()) != null) {
//            pooltab.add(Integer.parseInt(line));
//        }
        sym.close();
        lit.close();
//        pool.close();
    }

    void execute() throws Exception {
        String line = "";
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Dell\\eclipse-workspace\\SPOS_A2\\src\\IC (1).txt"));
        BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\Dell\\eclipse-workspace\\SPOS_A2\\src\\MC.txt"));
        while ((line = in.readLine()) != null) {
            String words[] = line.split("\\s+");
            String arr[] = words[1].replace("(", "").replace(")", "").split("\\,");
            // System.out.println(words[0]);
            StringBuilder fileWrite = new StringBuilder();
            if (words[0].equals("---") || words[0].equals("000")) {
//                fileWrite.append("\t");
            		continue;
            } else
                fileWrite.append(words[0] + "\t");
            if (arr[0].equals("DL")) {
                if (arr[1].equals("1")) {	// 1=DS, 2=DC
                    fileWrite.append("+\t00\t00\t000");
                } else {
                    String op[] = words[2].replace("(", "").replace(")", "").split("\\,");
                    String val = String.format("%03d", Integer.parseInt(op[1]));
                    // System.out.println(op[1]);
                    fileWrite.append("+\t00\t00\t" + val + "");
                }
            } else if (arr[0].equals("IS")) {
                fileWrite.append("+\t"+String.format("%02d", Integer.parseInt(arr[1])));
                if (words.length == 2) {
                    fileWrite.append("\t00\t000");
                } else {
                    if (!words[2].contains(",")) {
                        // System.out.println(words[2]);
                        fileWrite.append("\t00\t" + symaddr.get(words[2]) + "");
                    } else {
                        String val[] = words[2].split(",", 2);
                        if (val[1].contains("(") && val[1].contains("L")) {		//LITERALS
                            String a[] = val[1].replace("(", "").replace(")", "").split(",");
                            fileWrite.append(
                                    "\t" + String.format("%02d",registers.get(val[0])) + "\t" + litaddr.get(Integer.parseInt(a[1]) - 1) + "");
                    } else {	//REGISTERS
                            if (registers.containsKey(val[0]))
                                fileWrite.append("\t" + String.format("%02d",registers.get(val[0])) + "\t" + symaddr.get(val[1]) + "");
                            else
                                fileWrite.append("\t" + String.format("%02d",conditional.get(val[0])) + "\t" + symaddr.get(val[1]) + "");

                        }
                    }

                }
            }
            else if(arr[0].equals("AD")){
                fileWrite.append(String.format("%02d", Integer.parseInt(arr[1])));
                if(arr[1].equals("1")){
                    String a[] = words[2].replace("(", "").replace(")", "").split("\\,");
                    fileWrite.append("\t00\t" + String.format("%03d", Integer.parseInt(a[1])));

                }
                else{
                    fileWrite.append("\t00\t000");

                }
            }
            out.write("" + fileWrite + "\n");
            System.out.println(fileWrite);
            out.flush();
        }
        in.close();
        out.close();
    }

    public static void main(String[] args) throws Exception {
        A2 obj = new A2();
        
        System.out.println("Machine Code:\n");
        obj.readTables();
        obj.execute();
    }
}
