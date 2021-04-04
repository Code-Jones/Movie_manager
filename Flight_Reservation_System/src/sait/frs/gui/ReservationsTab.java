package sait.frs.gui;

import sait.frs.exception.InvalidCitizenshipException;
import sait.frs.exception.InvalidNameException;
import sait.frs.manager.FlightManager;
import sait.frs.manager.ReservationManager;
import sait.frs.problemdomain.Reservation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;


public class ReservationsTab extends TabBase {

    JTextField outputCodeBox;
    JTextField outputFlightBox;
    JTextField outputAirlineBox;
    JTextField outputCostBox;
    JTextField inputCodeBox;
    JTextField inputAirlineBox;
    JTextField inputNameBox;
    JTextField outputNameBox;
    JTextField outputCitizenBox;
    JComboBox<String> statusBox;
    private FlightManager flyManager;
    private ReservationManager resManager;
    private JList<Reservation> reservationList;
    private DefaultListModel<Reservation> reservationModel;


    public ReservationsTab(ReservationManager resManager, FlightManager flyManager) {
        this.resManager = resManager;
        this.flyManager = flyManager;
        panel.setLayout(new BorderLayout());

        JPanel northPanel = createNorthPanel();
        panel.add(northPanel, BorderLayout.NORTH);

        JPanel centerPanel = createCenterPanel();
        panel.add(centerPanel, BorderLayout.CENTER);

        JPanel eastPanel = createEastPanel();
        panel.add(eastPanel, BorderLayout.EAST);

        JPanel southPanel = createSouthPanel();
        panel.add(southPanel, BorderLayout.SOUTH);
    }

    private JPanel createNorthPanel() {
        JPanel panel = new JPanel();

        JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
        title.setFont(new Font("serif", Font.PLAIN, 29));
        panel.add(title);

        return panel;
    }

    private JPanel createSouthPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Search", SwingConstants.CENTER);
        title.setFont(new Font("serif", Font.PLAIN, 29));
        JButton findFlights = new JButton("Find Reservations");
        try {
            reservationModel.clear();
            findFlights.addActionListener(new FindReservationsListener());
        } catch (Exception e) {
            System.out.println("Input fields are empty");
        }


        panel.add(title, BorderLayout.NORTH);
        panel.add(innerSouthPanel(), BorderLayout.CENTER);
        panel.add(findFlights, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel innerSouthPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        JLabel codeLabel = new JLabel("Code: ");
        JLabel airlineLabel = new JLabel("Airline: ");
        JLabel nameLabel = new JLabel("Name: ");

        inputCodeBox = new JTextField(10);
        inputAirlineBox = new JTextField(10);
        inputNameBox = new JTextField(10);

        constraint.gridx = 0;
        constraint.gridy = 0;
        panel.add(codeLabel, constraint);
        constraint.gridy = 1;
        panel.add(airlineLabel, constraint);
        constraint.gridy = 2;
        panel.add(nameLabel, constraint);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 1;
        constraint.gridx = 1;
        constraint.gridy = 0;
        panel.add(inputCodeBox, constraint);
        constraint.gridy = 1;
        panel.add(inputAirlineBox, constraint);
        constraint.gridy = 2;
        panel.add(inputNameBox, constraint);
        return panel;
    }

    private JPanel createEastPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Reserve", SwingConstants.CENTER);

        title.setFont(new Font("serif", Font.PLAIN, 29));


        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new UpdateButtonListener());

        panel.add(title, BorderLayout.PAGE_START);
        panel.add(innerInputPanel(), BorderLayout.EAST);
        panel.add(updateButton, BorderLayout.PAGE_END);
        return panel;
    }

    private JPanel innerInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints l = new GridBagConstraints();


        JLabel codeBoxLabel = new JLabel("Code: ");
        outputCodeBox = new JTextField(10);
        outputCodeBox.setEditable(false);

        JLabel flightBoxLabel = new JLabel("Flight: ");
        outputFlightBox = new JTextField(10);
        outputFlightBox.setEditable(false);

        JLabel airlineBoxLabel = new JLabel("Airline: ");
        outputAirlineBox = new JTextField(10);
        outputAirlineBox.setEditable(false);

        JLabel costBoxLabel = new JLabel("Cost: ");
        outputCostBox = new JTextField(10);
        outputCostBox.setEditable(false);

        JLabel nameBoxLabel = new JLabel("Name: ");
        outputNameBox = new JTextField(10);

        JLabel citizenBoxLabel = new JLabel("Citizenship: ");
        outputCitizenBox = new JTextField(10);

        JLabel statusBoxLabel = new JLabel("Status: ");
        String[] statusList = {"Active", "Not Active"};

        statusBox = new JComboBox<>(statusList);


        l.gridy = 0;
        panel.add(codeBoxLabel, l);
        l.gridy = 1;
        panel.add(flightBoxLabel, l);
        l.gridy = 2;
        panel.add(airlineBoxLabel, l);
        l.gridy = 3;
        panel.add(costBoxLabel, l);
        l.gridy = 4;
        panel.add(nameBoxLabel, l);
        l.gridy = 5;
        panel.add(citizenBoxLabel, l);
        l.gridy = 6;
        panel.add(statusBoxLabel, l);
//		____________________________

        l.fill = GridBagConstraints.HORIZONTAL;
        l.gridy = 0;
        l.gridx = 1;
        panel.add(outputCodeBox, l);
        l.gridy = 1;
        panel.add(outputFlightBox, l);
        l.gridy = 2;
        panel.add(outputAirlineBox, l);
        l.gridy = 3;
        panel.add(outputCostBox, l);
        l.gridy = 4;
        panel.add(outputNameBox, l);
        l.gridy = 5;
        panel.add(outputCitizenBox, l);
        l.gridy = 6;
        panel.add(statusBox, l);
        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        reservationModel = new DefaultListModel<>();
        reservationList = new JList<>(reservationModel);

        reservationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(this.reservationList);

        reservationList.addListSelectionListener(new ReservationsTab.MyListSelectionListener());

        panel.add(scrollPane);

        return panel;
    }

    private class FindReservationsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(reservationList.getSelectedIndex());
            System.out.println("Find button pressed");
//            try {
//                reservationModel.clear();
//            } catch (Exception ex) {
//                System.out.println("nope");
//            }
//            if (reservationList.getSelectedIndex() == -1) {
//                reservationModel.clear();
//            }
            if (resManager.findReservationByCode(inputCodeBox.getText()) != null) {
                System.out.println(reservationModel.size());
                reservationModel.insertElementAt(resManager.findReservationByCode(inputCodeBox.getText()), reservationModel.size());
            } else {
                ArrayList<Reservation> tempResList = resManager.findReservations(inputCodeBox.getText(), inputAirlineBox.getText(), inputNameBox.getText());
                for (Reservation reservation : tempResList) {
                    System.out.println(reservationModel.size());
                    reservationModel.insertElementAt(reservation, reservationModel.size());
                }
            }
            inputCodeBox.setText(null);
            inputAirlineBox.setText(null);
            inputNameBox.setText(null);
        }
    } // done

    private class UpdateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Update button pressed");

            Reservation selected = reservationModel.get(reservationList.getSelectedIndex());
            if ((!selected.getName().equals(outputNameBox.getText()) && resManager.getReservations().contains(selected))) {
                try {
                    reservationModel.get(reservationList.getSelectedIndex()).setName(outputNameBox.getText());
                } catch (InvalidNameException ex) {
                    ex.printStackTrace();
                }
            }
            if ((!selected.getCitizenship().equals(outputCitizenBox.getText()))) {
                try {
                    reservationModel.get(reservationList.getSelectedIndex()).setCitizenship(outputCitizenBox.getText());
                } catch (InvalidCitizenshipException ex) {
                    ex.printStackTrace();
                }
            }
            if (Objects.equals(statusBox.getSelectedItem(), "Active")) {
                reservationModel.get(reservationList.getSelectedIndex()).setActive(true);
                JOptionPane.showMessageDialog(null, "Reservation Updated. Your Reservation is Active");
            } else {
                reservationModel.get(reservationList.getSelectedIndex()).setActive(false);
                JOptionPane.showMessageDialog(null, "Reservation Updated. Your Reservation is Not Active");
            }
        }
    } // done

    private class MyListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            Reservation selected = reservationModel.get(reservationList.getSelectedIndex());
            System.out.println(selected.getName());
            outputCodeBox.setText(selected.getCode());
            outputFlightBox.setText(selected.getFlight().getCode());
            outputAirlineBox.setText(selected.getFlight().getAirline());
            outputCostBox.setText(String.valueOf(selected.getFlight().getCostPerSeat()));
            outputNameBox.setText(selected.getName());
            outputCitizenBox.setText(selected.getCitizenship());
        } // done
    } // done
}
