package client.views.components;

import utils.CommonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TableView {
    private static final String HORIZONTAL_ID = "-";
    private String vertical_id;
    private String join_id;
    private String[] headers;
    private List<String[]> rows = new ArrayList();
    private boolean rightAlign;

    public TableView() {
    }

    public void commandLineTable() {
        this.setShowVerticalLines(false);
    }

    public void setRightAlign(boolean rightAlign) {
        this.rightAlign = rightAlign;
    }

    public void setShowVerticalLines(boolean showVerticalLines) {
        this.vertical_id = showVerticalLines ? "|" : "";
        this.join_id = showVerticalLines ? "+" : " ";
    }

    public void setHeaders(String... headers) {
        this.headers = headers;
    }

    public void addRow(String... cells) {
        this.rows.add(cells);
    }

    public void print() {
        int[] maxWidths = this.headers != null ? Arrays.stream(this.headers).mapToInt(String::length).toArray() : null;
        Iterator var2 = this.rows.iterator();

        String[] cells;
        while(var2.hasNext()) {
            cells = (String[])var2.next();
            if (maxWidths == null) {
                maxWidths = new int[cells.length];
            }

            if (cells.length != maxWidths.length) {
                throw new IllegalArgumentException("header and cells should be equal");
            }

            for(int i = 0; i < cells.length; ++i) {
                maxWidths[i] = Math.max(maxWidths[i], cells[i].length());
            }
        }

        if (this.headers != null) {
            CommonUtil.addTabs(10, false);
            this.printLine(maxWidths);
            CommonUtil.addTabs(10, false);
            this.printRow(this.headers, maxWidths);
            CommonUtil.addTabs(10, false);
            this.printLine(maxWidths);
        }

        var2 = this.rows.iterator();

        while(var2.hasNext()) {
            cells = (String[])var2.next();
            CommonUtil.addTabs(10, false);
            this.printRow(cells, maxWidths);
        }

        if (this.headers != null) {
            CommonUtil.addTabs(10, false);
            assert maxWidths != null;
            this.printLine(maxWidths);
        }

    }

    private void printLine(int[] columnWidths) {
        for(int i = 0; i < columnWidths.length; ++i) {
            String line = String.join("", Collections.nCopies(columnWidths[i] + this.vertical_id.length() + 1, "-"));
            String var10001 = this.join_id;
            System.out.print(var10001 + line + (i == columnWidths.length - 1 ? this.join_id : ""));
        }

        System.out.println();
    }

    private void printRow(String[] cells, int[] maxWidths) {
        for(int i = 0; i < cells.length; ++i) {
            String cell = cells[i];
            String verStrTemp = i == cells.length - 1 ? this.vertical_id : "";
            if (i == 0) {
                if (this.rightAlign) {
                    System.out.printf("%s %" + maxWidths[i] + "s %s", this.vertical_id, cell, verStrTemp);
                } else {
                    System.out.printf("%s %-" + maxWidths[i] + "s %s", this.vertical_id, cell, verStrTemp);
                }
            } else if (this.rightAlign) {
                System.out.printf("%s %" + maxWidths[i] + "s %s", this.vertical_id, cell, verStrTemp);
            } else {
                System.out.printf("%s %-" + maxWidths[i] + "s %s", this.vertical_id, cell, verStrTemp);
            }
        }

        System.out.println();
    }
}
