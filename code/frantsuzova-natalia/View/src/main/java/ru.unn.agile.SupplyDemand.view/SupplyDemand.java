package ru.unn.agile.SupplyDemand.view;

import ru.unn.agile.SupplyDemand.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class SupplyDemand {

    private JPanel mainPanel;
    private JButton btnCalc;
    private ViewModel viewModel;

    private JTextField q1;
    private JTextField q2;
    private JTextField I1;
    private JTextField I2;
    private JTextField txtResult;
    private JLabel lbStatus;

    private SupplyDemand() { }

    private SupplyDemand(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        loadListOfOperations();

        btnCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                SupplyDemand.this.viewModel.calculate();
                backBind();
            }
        });

        cbOperation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bind();
                SupplyDemand.this.viewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        };
        txtZ1Re.addKeyListener(keyListener);
        txtZ1Im.addKeyListener(keyListener);
        txtZ2Re.addKeyListener(keyListener);
        txtZ2Im.addKeyListener(keyListener);
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("SupplyDemand");

        frame.setContentPane(new SupplyDemand(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    private void bind() {
        viewModel.setQ1(q1.getText());
        viewModel.setQ2(q2.getText());
        viewModel.setI1(I1.getText());
        viewModel.setI2(I2.getText());

        viewModel.setOperation((ViewModel.Operation) cbOperation.getSelectedItem());
    }

    private void backBind() {
        btnCalc.setEnabled(viewModel.isCalculateButtonEnabled());

        txtResult.setText(viewModel.getResult());
        lbStatus.setText(viewModel.getStatus());
    }
}
