package gui;

import entity.Member;
import fileio.MemberFileIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

abstract class BaseGUI extends JFrame {
    public void initUI(){}
}

public class MemberGUI extends BaseGUI {

    private JTextField idField, nameField, ageField, planField, searchField;
    private JTable table;
    private DefaultTableModel model;

    public MemberGUI() {
        initUI();
    }

    @Override
    public void initUI() {

        setTitle("Gym Management System");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(4, 2, 8, 8));
        form.setBorder(BorderFactory.createTitledBorder("Member Info"));

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        planField = new JTextField();

        form.add(new JLabel("ID (8 digits)"));
        form.add(idField);
        form.add(new JLabel("Name"));
        form.add(nameField);
        form.add(new JLabel("Age"));
        form.add(ageField);
        form.add(new JLabel("Plan"));
        form.add(planField);

        JPanel top = new JPanel(new BorderLayout());

        searchField = new JTextField();
        JButton searchBtn = new JButton("Search");

        JPanel searchPanel = new JPanel(new BorderLayout());
        JPanel searchBox = new JPanel(new BorderLayout());
        searchBox.add(searchField, BorderLayout.CENTER);
        searchBox.add(searchBtn, BorderLayout.WEST);
        searchPanel.add(searchBox, BorderLayout.CENTER);

        top.add(form, BorderLayout.CENTER);
        top.add(searchPanel, BorderLayout.SOUTH);

        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[] { "ID", "Name", "Age", "Plan" }, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel();

        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");
        JButton viewBtn = new JButton("View All");
        JButton clearBtn = new JButton("Clear");

        buttons.add(addBtn);
        buttons.add(updateBtn);
        buttons.add(deleteBtn);
        buttons.add(viewBtn);
        buttons.add(clearBtn);

        add(buttons, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> addMember());
        updateBtn.addActionListener(e -> updateMember());
        deleteBtn.addActionListener(e -> deleteMember());
        viewBtn.addActionListener(e -> viewAll());
        searchBtn.addActionListener(e -> search());
        clearBtn.addActionListener(e -> clear());

        table.getSelectionModel().addListSelectionListener(e -> loadSelected());

        try {
            MemberFileIO.createFileIfNotExists();
        } catch (Exception ignored) {
        }

        viewAll();
    }

    private boolean validateAll(boolean checkDuplicate) {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String age = ageField.getText().trim();
        String plan = planField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || age.isEmpty() || plan.isEmpty()) {
            showError("All fields are required!");
            return false;
        }

        if (!id.matches("\\d{8}")) {
            showError("ID must be exactly 8 digits!");
            return false;
        }

        try {
            Integer.parseInt(age);
        } catch (Exception e) {
            showError("Age must be a number!");
            return false;
        }

        if (name.contains(",") || plan.contains(",")) {
            showError("Comma not allowed!");
            return false;
        }

        if (checkDuplicate && MemberFileIO.idExists(id)) {
            showError("Duplicate ID!");
            return false;
        }

        return true;
    }

    private void addMember() {
        try {
            if (!validateAll(true))
                return;

            MemberFileIO.addMember(
                    new Member(idField.getText(), nameField.getText(), ageField.getText(), planField.getText()));

            showInfo("Added Successfully");
            clear();
            viewAll();

        } catch (IOException e) {
            showError(e.getMessage());
        }
    }

    private void updateMember() {
        try {
            if (!validateAll(false))
                return;

            MemberFileIO.updateMember(
                    new Member(idField.getText(), nameField.getText(), ageField.getText(), planField.getText()));

            showInfo("Updated");
            viewAll();

        } catch (IOException e) {
            showError(e.getMessage());
        }
    }

    private void deleteMember() {
        try {
            int confirm = JOptionPane.showConfirmDialog(this, "Delete?");
            if (confirm != JOptionPane.YES_OPTION)
                return;

            MemberFileIO.deleteMember(idField.getText());

            showInfo("Deleted");
            viewAll();

        } catch (IOException e) {
            showError(e.getMessage());
        }
    }

    private void viewAll() {
        refresh(MemberFileIO.getAllMembers());
    }

    private void search() {
        String key = searchField.getText();
        if (key.isEmpty()) {
            showError("Enter ID or Name");
            return;
        }
        refresh(MemberFileIO.searchMembers(key));
    }

    private void refresh(Object[][] data) {
        model.setRowCount(0);
        for (Object[] r : data)
            model.addRow(r);
    }

    private void loadSelected() {
        int r = table.getSelectedRow();
        if (r >= 0) {
            idField.setText(model.getValueAt(r, 0).toString());
            nameField.setText(model.getValueAt(r, 1).toString());
            ageField.setText(model.getValueAt(r, 2).toString());
            planField.setText(model.getValueAt(r, 3).toString());
        }
    }

    private void clear() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        planField.setText("");
        searchField.setText("");
        table.clearSelection();
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showInfo(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}