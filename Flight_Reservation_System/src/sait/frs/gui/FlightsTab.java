package sait.frs.gui;

import sait.frs.exception.*;
import sait.frs.manager.FlightManager;
import sait.frs.manager.ReservationManager;
import sait.frs.problemdomain.Flight;
import sait.frs.problemdomain.Reservation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FlightsTab extends TabBase {

    JScrollPane scrollPane;
    JComboBox<String> comboFromBox;
    JComboBox<String> comboToBox;
    JComboBox<String> comboDayBox;
    JTextField flightBox;
    JTextField airlineBox;
    JTextField dayBox;
    JTextField timeBox;
    JTextField costBox;
    JTextField nameBox;
    JTextField citizenBox;
    private FlightManager flyManager;
    private ReservationManager resManager;
    private JList<Flight> flightsList;
    private DefaultListModel<Flight> flightsModel;


    public FlightsTab(FlightManager flyManager, ReservationManager resManager) {
        this.flyManager = flyManager;
        this.resManager = resManager;
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

    private JPanel createSouthPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Flight Finder", SwingConstants.CENTER);
        title.setFont(new Font("serif", Font.PLAIN, 29));
        JButton findFlights = new JButton("Find Flights");
        findFlights.addActionListener(new FindFlightsListener());

        panel.add(title, BorderLayout.NORTH);
        panel.add(innerSouthPanel(), BorderLayout.CENTER);
        panel.add(findFlights, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel innerSouthPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        JLabel fromLabel = new JLabel("From: ");
        JLabel toLabel = new JLabel("To: ");
        JLabel dayLabel = new JLabel("Day: ");

        ArrayList<String> airportList = this.flyManager.getAirports();
        String[] airportArray = airportList.toArray(new String[0]);

        comboFromBox = new JComboBox<>(airportArray);
        comboToBox = new JComboBox<>(airportArray);

        String[] dayList = {FlightManager.WEEKDAY_ANY, FlightManager.WEEKDAY_MONDAY, FlightManager.WEEKDAY_TUESDAY, FlightManager.WEEKDAY_WEDNESDAY, FlightManager.WEEKDAY_THURSDAY, FlightManager.WEEKDAY_FRIDAY, FlightManager.WEEKDAY_SATURDAY, FlightManager.WEEKDAY_SUNDAY};

        comboDayBox = new JComboBox<>(dayList);

        constraint.gridx = 0;
        constraint.gridy = 0;
        panel.add(fromLabel, constraint);
        constraint.gridy = 1;
        panel.add(toLabel, constraint);
        constraint.gridy = 2;
        panel.add(dayLabel, constraint);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 1;
        constraint.gridx = 1;
        constraint.gridy = 0;
        panel.add(comboFromBox, constraint);
        constraint.gridy = 1;
        panel.add(comboToBox, constraint);
        constraint.gridy = 2;
        panel.add(comboDayBox, constraint);
        return panel;
    }

    private JPanel createNorthPanel() {
        JPanel panel = new JPanel();

        JLabel title = new JLabel("Flights", SwingConstants.CENTER);
        title.setFont(new Font("serif", Font.PLAIN, 29));
        panel.add(title);

        return panel;
    }

    private JPanel createEastPanel() {
        JPanel panel = new JPanel(new BorderLayout());


        JLabel title = new JLabel("Reserve", SwingConstants.CENTER);
        title.setFont(new Font("serif", Font.PLAIN, 29));


        JButton reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(new ReserveButtonListener());

        panel.add(title, BorderLayout.PAGE_START);
        panel.add(innerInputPanel(), BorderLayout.EAST);
        panel.add(reserveButton, BorderLayout.PAGE_END);
        return panel;
    }

    private JPanel innerInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints l = new GridBagConstraints();


        JLabel flightBoxLabel = new JLabel("Flight: ");
        flightBox = new JTextField(10);
        flightBox.setEditable(false);

        JLabel airlineBoxLabel = new JLabel("Airline: ");
        airlineBox = new JTextField(10);
        airlineBox.setEditable(false);

        JLabel dayBoxLabel = new JLabel("day: ");
        dayBox = new JTextField(10);
        dayBox.setEditable(false);

        JLabel timeBoxLabel = new JLabel("Time: ");
        timeBox = new JTextField(10);
        timeBox.setEditable(false);

        JLabel costBoxLabel = new JLabel("Cost: ");
        costBox = new JTextField(10);
        costBox.setEditable(false);

        JLabel nameBoxLabel = new JLabel("Name: ");
        nameBox = new JTextField(10);

        JLabel citizenBoxLabel = new JLabel("Citizenship: ");
        citizenBox = new JTextField(10);

        l.gridy = 0;
        panel.add(flightBoxLabel, l);
        l.gridy = 1;
        panel.add(airlineBoxLabel, l);
        l.gridy = 2;
        panel.add(dayBoxLabel, l);
        l.gridy = 3;
        panel.add(timeBoxLabel, l);
        l.gridy = 4;
        panel.add(costBoxLabel, l);
        l.gridy = 5;
        panel.add(nameBoxLabel, l);
        l.gridy = 6;
        panel.add(citizenBoxLabel, l);
//		____________________________

        l.fill = GridBagConstraints.HORIZONTAL;
        l.gridy = 0;
        l.gridx = 1;
        l.weightx = 1;
        panel.add(flightBox, l);
        l.gridy = 1;
        panel.add(airlineBox, l);
        l.gridy = 2;
        panel.add(dayBox, l);
        l.gridy = 3;
        panel.add(timeBox, l);
        l.gridy = 4;
        panel.add(costBox, l);
        l.gridy = 5;
        panel.add(nameBox, l);
        l.gridy = 6;
        panel.add(citizenBox, l);
        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BorderLayout());

        flightsModel = new DefaultListModel<>();
        flightsList = new JList<>(flightsModel);

        // User can only select one item at a time.
        flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Wrap JList in JScrollPane so it is scrollable.
        scrollPane = new JScrollPane(this.flightsList);

        flightsList.addListSelectionListener(new MyListSelectionListener());

        panel.add(scrollPane);

        return panel;
    }

    private class FindFlightsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("find button pressed");
            flightsModel.clear();
            flightsList.clearSelection();

            ArrayList<Flight> tempList = flyManager.findFlights((String) comboFromBox.getSelectedItem(), (String) comboToBox.getSelectedItem(), (String) comboDayBox.getSelectedItem());
            for (int i = 0; i < tempList.size(); i++) {
                flightsModel.insertElementAt(tempList.get(i), i);
            }
        }
    }

    private class ReserveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("reserve button pressed");
            Flight selected = flightsModel.get(flightsList.getSelectedIndex());
            Flight flight = new Flight(selected.getCode(), selected.getAirline(), (String) comboFromBox.getSelectedItem(), (String) comboToBox.getSelectedItem(), (String) comboDayBox.getSelectedItem(), selected.getTime(), selected.getSeats(), selected.getCostPerSeat());
            try {
                Reservation res = resManager.makeReservation(flight, nameBox.getText(), citizenBox.getText());
                JOptionPane.showMessageDialog(null, "Reservation Created. Your code is " + res);
                System.out.println(res);
            } catch (NullFlightException | InvalidNameException | InvalidCitizenshipException | NoMoreSeatsException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class MyListSelectionListener implements ListSelectionListener {
        /**
         * Called when user selects an item in the JList.
         */
        @Override
        public void valueChanged(ListSelectionEvent e) {
            Flight selected = flightsModel.get(flightsList.getSelectedIndex());
            flightBox.setText(selected.getCode());
            airlineBox.setText(selected.getAirline());
            dayBox.setText(selected.getWeekday());
            timeBox.setText(selected.getTime());
            costBox.setText(String.valueOf(selected.getCostPerSeat()));
        }
    }
}