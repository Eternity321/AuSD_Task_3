package ru.vsu.cs.Akimushkin;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

public class FormQueue extends JFrame {
    private JTable tableInput;
    private JTable tableOutput;
    private JButton buttonAdd;
    private JButton buttonSolve;
    private JPanel panelQueue;
    private JSpinner spinnerElement;
    private JButton buttonLoad;
    private JTable tableResult;

    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;

    FormQueue () {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Нахождение размера, минимального и максимального элементов очереди");
        this.setContentPane(panelQueue);

        util.JTableUtils.initJTableForArray(tableInput, 40, true, true, true, true);
        util.JTableUtils.initJTableForArray(tableOutput, 40, true, true, true, true);
        util.JTableUtils.initJTableForArray(tableResult, 40, true, true, true, true);
        tableInput.setRowHeight(25);
        tableOutput.setRowHeight(25);
        tableResult.setRowHeight(25);


        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        util.JTableUtils.writeArrayToJTable(tableInput, new int[] {});

        int width = 800;
        int height = 300;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
        this.setPreferredSize(new Dimension(width, height));

        spinnerElement.setValue(7);

        this.pack();


        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int element = (int) spinnerElement.getValue();
                try {
                    int[] arr = util.JTableUtils.readIntArrayFromJTable(tableInput);
                    assert arr != null;
                    int[] updatedArr = new int[arr.length + 1];
                    for (int i = 0; i < arr.length; i++) {
                        updatedArr[i] = arr[i];
                    }
                    updatedArr[updatedArr.length - 1] = element;
                    util.JTableUtils.writeArrayToJTable(tableInput, updatedArr);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });

        buttonSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int[] arr = util.JTableUtils.readIntArrayFromJTable(tableInput);

                    MyQueueGeneric queue;
                    UIManager.put("OptionPane.yesButtonText", "Собственной реализацией");
                    UIManager.put("OptionPane.noButtonText", "Используя Java Collections");

                    int a = JOptionPane.showConfirmDialog(FormQueue.this, "Выберите метод решения");


                    switch (a) {
                        case 1:
                            queue = new JavaQueue();
                            break;
                        default:
                            queue = new MyQueue();
                            break;
                    }

                    assert arr != null;
                    for (int i : arr) {
                        queue.pushHead(i);
                    } // создание массива и переделывание в очередь

                    ArrayList<Integer> result = queue.sizeMinMax();
                    int[] resultArr = new int[] {result.get(0), result.get(1), result.get(2)}; //получение размера, мин и макс
                    util.JTableUtils.writeArrayToJTable(tableResult, resultArr);

                    for (int i = 0; i < arr.length; i++) {
                        arr[i] = queue.popTail();
                    }
                    util.JTableUtils.writeArrayToJTable(tableOutput, arr); //вывод очереди после изменений (проверка)
                } catch (ParseException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });


        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelQueue) == JFileChooser.APPROVE_OPTION) {
                        int[] arr = util.ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                        util.JTableUtils.writeArrayToJTable(tableInput, arr);
                    }
                } catch (Exception ex) {
                    util.SwingUtils.showErrorMessageBox(ex);
                }
            }
        });
    }
}
