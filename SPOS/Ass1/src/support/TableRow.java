package support;

public class TableRow {

    //Symbol Table, Literal Table
    private final String symbol;
    private int address;
    private final int index;

    public TableRow(String symbol, int address, int index) {
        this.symbol = symbol;
        this.address = address;
        this.index = index;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getAddress() {
        return address;
    }
    public void setAddress(int address) {
        this.address = address;
    }

    public int getIndex() {
        return index;
    }


}
