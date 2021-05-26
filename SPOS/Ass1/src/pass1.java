
import support.InstanceTable;
import support.TableRow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class pass1 {

    private int locCounter;
    private int literalTable_pnt;
    private int poolTable_pnt;
    private int symbolIndex;
    private int literalIndex;

    private final LinkedHashMap<String, TableRow> symbolTable;
    private final ArrayList<TableRow> literalTable;
    private final ArrayList<Integer> poolTable;
    private final StringBuilder sb;

    public pass1() {
        this.locCounter = 0;
        this.literalTable_pnt = 0;
        this.poolTable_pnt = 0;
        this.symbolIndex = 0;
        this.literalIndex = 0;

        symbolTable = new LinkedHashMap<>();
        literalTable = new ArrayList<>();
        poolTable = new ArrayList<>();
        poolTable.add(0);
        sb = new StringBuilder();
    }

    public void parseFile(String s) throws IOException {
        String line;
        BufferedReader br = new BufferedReader(new FileReader(s));
        InstanceTable look = new InstanceTable();

        while((line = br.readLine()) != null){
            //Seperate words where spce
            String[] words = line.split("\\s+");

            //For Labels
            if(!words[0].isEmpty()) {

                if(symbolTable.containsKey(words[0])) {
                    symbolTable.put(words[0], new TableRow(words[0], locCounter, symbolTable.get(words[0]).getIndex()));
                }else {
                    symbolTable.put(words[0], new TableRow(words[0], locCounter, symbolIndex));
                }
                sb.append("(S,").append(symbolTable.get(words[0]).getIndex()).append(") ");
                //CHANGE
                symbolIndex++;
            }

            //For Commands
            if(!words[1].isEmpty()) {

                //Command : START
                if(words[1].equals("START")) {
                    locCounter = expr(words[2]);
                    sb.append(look.getCode(words[1]));
                    sb.append("(C,").append(locCounter).append(") ");
                }
                //Command : ORIGIN
                if(words[1].equals("ORIGIN")) {
                    locCounter = expr(words[2]);
                    sb.append(look.getCode(words[1]));
                    sb.append("(C,").append(locCounter).append(") ");
                }
                //Command : LTORG
                if(words[1].equals("LTORG")) {
                    int ptr = poolTable.get(poolTable_pnt);

                    for(int j = ptr; j<literalTable_pnt; j++) {
                        sb.append(look.getCode(words[1])).append(look.getCode("DC")).append("(C,").append(literalTable.get(j).getSymbol()).append(")");
                        if(j != literalTable_pnt-1) {
                            sb.append("\n");
                        }
                        literalTable.get(j).setAddress(locCounter);
                        ++locCounter;
                    }
                    poolTable_pnt++;
                    poolTable.add(literalTable_pnt);
                }

                //Command : EQU
                if(words[1].equals("EQU")) {
                    int loc=expr(words[2]);
                    //below If conditions are optional as no IC is generated for them
                    if(words[2].contains("+"))
                    {
                        String[] splits =words[2].split("\\+");
                        sb.append(look.getCode(words[1])).append("(S,").append(symbolTable.get(splits[0]).getIndex()).append(")+").append(splits[1]);
                    }
                    else if(words[2].contains("-"))
                    {
                        String[] splits =words[2].split("\\-");
                        sb.append(look.getCode(words[1])).append("(S,").append(symbolTable.get(splits[0]).getIndex()).append(")-").append(splits[1]);
                    }
                    else
                    {
                        sb.append(look.getCode(words[1])).append("(C,").append(words[2]);
                    }
                    if(symbolTable.containsKey(words[0]))
                        symbolTable.put(words[0], new TableRow(words[0],loc,symbolTable.get(words[0]).getIndex())) ;
                    else
                        symbolTable.put(words[0], new TableRow(words[0],loc,++symbolIndex));
                    ++locCounter;
                }

                //Command : DS/DC
                if(words[1].equals("DS")) {
                    sb.append(look.getCode(words[1])).append("(C,").append(words[2]).append(") ");
                    locCounter = locCounter + Integer.parseInt(words[2]);
                }
                if(words[1].equals("DC")) {
                    ++locCounter;
                    int constant = Integer.parseInt(words[2].replace("'",""));
                    sb.append(look.getCode(words[1])).append("(C,").append(constant).append(") ");
                }

                //Command belongs to : IS
                if(look.getType(words[1]).equals("IS")) {
                    sb.append(look.getCode(words[1]));
                    int j = 2;
                    while(j < words.length) {
                        words[j]=words[j].replace(",", "");
                        if(look.getType(words[j]).equals("RG")) {
                            sb.append(look.getCode(words[j]));
                        }
                        else {
                            if(words[j].contains("=")) {
                                words[j] = words[j].replace("=","").replace("'","");
                                literalTable.add(new TableRow(words[j],-1,++literalIndex));
                                ++literalTable_pnt;
                                sb.append("(L,").append(literalIndex).append(") ");
                            }else if(symbolTable.containsKey(words[j])) {
                                int idx=symbolTable.get(words[j]).getIndex();
                                sb.append("(S,").append(idx).append(") ");
                            }
                            else {
                                symbolTable.put(words[j],new TableRow(words[j],-1,++symbolIndex));
                                int idx=symbolTable.get(words[j]).getIndex();
                                sb.append("(S,").append(idx).append(") ");
                            }
                        }
                        j++;
                    }
                    ++locCounter;
                }

                //Command : END
                if(words[1].equals("END")) {
                    int ptr = poolTable.get(poolTable_pnt);
                    if(ptr != literalTable_pnt) {
                        for(int j = ptr; j<literalTable_pnt; j++) {
                            sb.append(look.getCode("LTORG")).append(look.getCode("DC")).append("(C,").append(literalTable.get(j).getSymbol()).append(")\n");
                            literalTable.get(j).setAddress(locCounter);
                            ++locCounter;
                        }
                        poolTable_pnt++;
                        poolTable.add(literalTable_pnt);
                    }
                    sb.append(look.getCode(words[1]));
                }
                sb.append("\n");
            }

        }
    }

    public String getIC() {
        return sb.toString();
    }

    public String getSymbolTable() {
        StringBuilder temp = new StringBuilder();
        temp.append("\n-------------- SYMBOL TABLE ---------------\n\n");
        temp.append("Index\tSymbol\tAddress\n");
        for(Map.Entry<String,TableRow> mapElement : symbolTable.entrySet()) {
            TableRow tableRow = mapElement.getValue();
            String symbol = tableRow.getSymbol();
            int address = tableRow.getAddress();
            int index = tableRow.getIndex();
            temp.append(index).append("\t\t").append(symbol).append("\t\t").append(address).append("\n");
        }
        temp.append("\n-------------------------------------------\n");
        return temp.toString();
    }

    public String getLiteralTable() {
        StringBuilder temp = new StringBuilder();
        temp.append("\n-------------- LITERAL TABLE --------------\n\n");
        temp.append("Index\tSymbol\tAddress\n");
        for(TableRow tableRow : literalTable) {
            String symbol = tableRow.getSymbol();
            int address = tableRow.getAddress();
            int index = tableRow.getIndex();
            temp.append(index).append("\t\t").append(symbol).append("\t\t").append(address).append("\n");
        }
        temp.append("\n-------------------------------------------\n");
        return temp.toString();
    }

    public String getPoolTable() {
        StringBuilder temp = new StringBuilder();
        temp.append("\n-------------- POOL TABLE ---------------\n\n");
        temp.append("Index\n");
        for(Integer i : poolTable) {
            temp.append(i).append("\n");
        }
        temp.append("\n-----------------------------------------\n");
        return temp.toString();
    }

    private int expr(String word) {
        //For Commands like ORIGIN+3
        int x;
        if(word.contains("+")){
            String[] str = word.split("\\+");
            x = symbolTable.get(str[0]).getAddress() + Integer.parseInt(str[1]);
        }
        else if(word.contains("-")){
            String[] str = word.split("\\-");
            x = symbolTable.get(str[0]).getAddress() - Integer.parseInt(str[1]);
        }
        else{
            x = Integer.parseInt(word);
        }
        return x;
    }
}
