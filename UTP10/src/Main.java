import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Main {

    public static void main(String[] args) {
        List<Integer> countOfThreads = new ArrayList<>();
        MyJFrame mainF = new MyJFrame("Tasks List");
        JTextArea outputArea = new JTextArea();
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        JScrollPane scroll = new JScrollPane();
        DefaultListModel<Task> listModel = new DefaultListModel<Task>();
        JList<Task> tasksList = new JList<Task>(listModel);
        Button buttonNewThread = new Button("New Thread");
        Button buttonStopAllThread = new Button("Stop All Thread");
        Button buttonSuspendThread = new Button("Suspend Thread");
        Button buttonCountinueThread = new Button("Countinue Thread");

        TitledBorder border;

        tasksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Scroll
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setViewportView(tasksList);

        //Buttons
        buttonSuspendThread.setEnabled(false);
        buttonCountinueThread.setEnabled(false);
        buttonStopAllThread.setEnabled(false);

        //List Listener - clicklable buttons & output changer
        tasksList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = ((JList<String>) e.getSource()).getSelectedIndex();
                if (selectedIndex > -1) {
                    buttonSuspendThread.setEnabled(true);
                    buttonCountinueThread.setEnabled(true);
                    outputArea.setText(listModel.get(selectedIndex).data);
                    listModel.get(selectedIndex).setPropertyChangeListener(new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent e) {
                            outputArea.setText((String) e.getNewValue());
                        }
                    });
                    if (listModel.get(selectedIndex).resultFuture.isDone()) {
                        buttonStopAllThread.setEnabled(false);
                        bottom.repaint();
                    }
                }
            }
        });
        //Button Listeners
        buttonSuspendThread.addActionListener(e -> {
            int index = tasksList.getSelectedIndex();
            if(index>-1){
                if(listModel.get(index).taskStatus().equals("Running"))listModel.get(index).Suspend();
            }
        });
        buttonCountinueThread.addActionListener(e -> {
            int index = tasksList.getSelectedIndex();
            if(index>-1){
                try {
                    if(listModel.get(index).taskStatus().equals("Suspend"))listModel.get(index).Countinue();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });
        buttonNewThread.addActionListener(e -> {
            try {
                buttonStopAllThread.setEnabled(true);
                if (countOfThreads.size() == 0) {
                    countOfThreads.add(1);
                } else {
                    countOfThreads.add(countOfThreads.get(countOfThreads.size() - 1) + 1);
                }
                new Task(countOfThreads.get(countOfThreads.size() - 1) * 1000, listModel).taskStart();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        buttonStopAllThread.addActionListener(e -> {
            for (int i = 0; i < listModel.size(); i++) {
                if (!listModel.get(i).taskStatus().equals("Finished")&&!listModel.get(i).taskStatus().equals("Interrupted")) listModel.get(i).taskStop();
            }
            buttonStopAllThread.setEnabled(false);
            buttonSuspendThread.setEnabled(false);
            buttonCountinueThread.setEnabled(false);
        });


        //Top Jpanel - Threds List scroll
        top.add(buttonNewThread);
        top.add(buttonStopAllThread);
        top.add(buttonSuspendThread);
        top.add(buttonCountinueThread);
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.add(scroll);
        border = new TitledBorder("");
        top.setBorder(border);

        //Bot Jpanel - output Area
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().add(outputArea);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.add(scrollPane);
        border = new TitledBorder("Output area");
        bottom.setBorder(border);

        //Main Frame
        mainF.setLayout(new BorderLayout());
        mainF.add(top, BorderLayout.NORTH);
        mainF.add(bottom, BorderLayout.CENTER);
        mainF.pack();
        mainF.setLocationRelativeTo(null);
        mainF.setVisible(true);
    }
}