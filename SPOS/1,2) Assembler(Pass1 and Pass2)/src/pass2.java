import support.TableRow;
import support.TableRow;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class pass2 {
    private final ArrayList<TableRow> symbolTable;
    private final ArrayList<TableRow> literalTable;
    private final ArrayList<Integer> poolTable;
    private final StringBuilder sb;

    public pass2() throws IOException {
        symbolTable = new ArrayList<>();
        literalTable = new ArrayList<>();
        poolTable = new ArrayList<>();
        sb = new StringBuilder();

        String line;

        // Symbol Table Input

        BufferedReader br = new BufferedReader(new FileReader("Symboltable.txt"));
        while((line=br.readLine())!=null) {
            String[] parts = line.split("\\s+");
            symbolTable.add(new TableRow(parts[1],Integer.parseInt(parts[2]),Integer.parseInt(parts[0])));
        }

        // Literal Table Input

        br = new BufferedReader(new FileReader("Literaltable.txt"));
        while((line=br.readLine())!=null) {
            String[] parts = line.split("\\s+");
            literalTable.add(new TableRow(parts[1],Integer.parseInt(parts[2]),Integer.parseInt(parts[0])));
        }

    }

    public String getMachineCode() throws IOException {

        String line;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader("output.txt"));
        while((line = br.readLine()) != null) {
            String[] words = line.split("\\s+");
            if(words[0].contains("AD") || words[0].equals("(DL,01)")){
                sb.append("____ __ __ ____");
            }
            else if(words.length==1){
                //If length of code is 1
                sb.append(getCode(words[0])).append(" 00 0000");
            }
            else if(words.length==2){
                //If length of code is 2
                if(words[0].contains("IS")){

                    int i=Integer.parseInt(getCode(words[1]));

                    //Literal or Symbol(or Label) or Constant
                    sb.append(getCode(words[0]));
                    if(words[1].contains("S")){
                        sb.append(" 00 ");
                        sb.append(symbolTable.get(i).getAddress());
                    }
                    else if(words[1].contains("L")){
                        sb.append(" 00 ");
                        sb.append(literalTable.get(i).getAddress());
                    }
                    else if(words[1].contains("C")){
                        sb.append(" 00 ");
                        sb.append(getCode(words[1]));
                    }
                    else if(words[1].contains("RG")){
                        //Register
                        sb.append(getCode(words[1]));
                        sb.append(" 0000 ");
                    }
                }
                else if(words[0].equals("(DL,02)")) {
                    sb.append("00 00 ").append(getCode(words[1]));
                }
            }
            else if(words.length==3){
                //If length of code is 3
                //IS
                sb.append(getCode(words[0]));

                //RG or CC
                sb.append(" ");
                sb.append(getCode(words[1]));

                //Literal or Symbol(or Label) or Constant
                sb.append(" ");
                int i=Integer.parseInt(getCode(words[2]));
                if(words[2].contains("S")){
                    sb.append(symbolTable.get(i).getAddress());
                }
                else if(words[2].contains("L")){
                    sb.append(literalTable.get(i).getAddress());
                }
                else if(words[2].contains("C")){
                    sb.append(getCode(words[1]));
                }
            }
            sb.append("\n");
        }

        //Save in a New File
        BufferedWriter out = new BufferedWriter(new FileWriter("Machine Code.txt"));
        out.write(sb.toString());
        out.close();
        return sb.toString();
    }

    private String getCode(String s) {
        //Return Code from intermediate output
        String[] parts = s.split(",");
        parts[1] = parts[1].replace(")","");
        return parts[1];
    }

}
