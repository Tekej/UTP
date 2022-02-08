package zad1;


import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

class CountryTable{
    private String[] ss;
    private String countriesFileName;
    private DefaultTableModel tableModel;

    {
        tableModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int col) {
                switch (col) {
                    case 3:
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return ImageIcon.class;
                    case 3:
                        return Integer.class;
                    default:
                        return Object.class;
                }
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                Date date = new Date();
                    Scanner sc = null;
                    try {
                        sc = new Scanner(new File(countriesFileName));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    sc.nextLine();
                    while (sc.hasNextLine()) {
                        String[] s = sc.nextLine().split("\t");
                        if (s[1].equals(getValueAt(row, 1).toString())) {
                            if (s.length == 5) {
                                s[4] = date.toString();
                            }
                            Vector v = new Vector();
                            for (int i = 0; i < s.length; i++)
                                if (i == 0) {
                                    v.add(new ImageIcon(new ImageIcon("images/" + s[0] + ".gif").getImage().getScaledInstance((table.getColumnModel().getTotalColumnWidth() / 12), table.getRowHeight(), Image.SCALE_DEFAULT)));
                                } else {
                                    v.add(s[i]);
                                }
                            if (s.length < 5) v.add(date.toString());
                            dataVector.setElementAt(v, row);
                        }
                    }
                Vector rowVector = (Vector) dataVector.elementAt(row);
                rowVector.setElementAt(aValue, column);
                fireTableCellUpdated(row, column);
            }

            @Override
            public Object getValueAt(int row, int column) {
                Vector rowVector = (Vector) dataVector.elementAt(row);
                return rowVector.elementAt(column);
            }
        };
    }

    private JTable table = new JTable(tableModel);
    CountryTable(String countriesFileName) {
        this.countriesFileName=countriesFileName;
    }

    JTable create() throws FileNotFoundException {
        createRows(tableModel);
        JScrollPane scrollPane;
        scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        table.getColumnModel().getColumn(3).setCellRenderer(new CountryPopulationRenderer());
        table.getModel().addTableModelListener((TableModelEvent e) -> {
            try {
                Date date = new Date();
                Scanner sc = new Scanner(new File(countriesFileName));
                int count = 0;
                StringBuilder file = new StringBuilder(sc.nextLine() + "\n");
                while(sc.hasNextLine()){
                    String [] data = sc.nextLine().split("\t");
                    if(count == e.getFirstRow()){
                        if(data.length>=3){
                            data[3] = table.getModel().getValueAt(e.getFirstRow(),e.getColumn()).toString();
                        }
                    }
                    if(data.length<ss.length){
                        for (String aData : data) {
                            file.append(aData).append("\t");
                        }
                        if(count == e.getFirstRow()){
                            file.append(date.toString()).append("\n");
                        }else{
                            file.append("\n");
                        }
                    }else{
                        if(count == e.getFirstRow()){
                            data[data.length-1]=date.toString();
                        }
                        for (int i = 0; i < data.length; i++) {
                            if(i==data.length-1){
                                file.append(data[i]).append("\n");
                            }else{
                                file.append(data[i]).append("\t");
                            }
                        }
                    }
                    count++;
                }
                file = new StringBuilder(file.substring(0, file.length() - 1));
                BufferedWriter bw = new BufferedWriter(new FileWriter(countriesFileName,false));
                bw.write(file.toString());
                bw.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        return table;
    }


    private void createRows(DefaultTableModel tableModel) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(countriesFileName));
        ss = sc.nextLine().split("\t");
        for (String s1 : ss) {
            tableModel.addColumn(s1);
        }
        while(sc.hasNextLine()){
            String [] s = sc.nextLine().split("\t");
            Object [] obj = new Object[ss.length];
            for (int i = 0; i < s.length; i++) {
                if(i == 0){
                    obj[i]= new ImageIcon(new ImageIcon("images/"+s[0]+".gif").getImage().getScaledInstance((table.getColumnModel().getTotalColumnWidth()/12), table.getRowHeight(), Image.SCALE_DEFAULT));
                }else{
                    obj[i]=s[i];
                }
            }
            tableModel.insertRow(tableModel.getRowCount(),obj);
        }
    }
}
class CountryPopulationRenderer implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel l = new JLabel(value.toString());
        if (Long.parseLong( table.getModel().getValueAt(row ,3).toString()) > 20000000) {
            l.setForeground(Color.RED);
        }
        return l;
    }
}