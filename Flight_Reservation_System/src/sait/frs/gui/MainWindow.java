package sait.frs.gui;

import sait.frs.manager.FlightManager;
import sait.frs.manager.ReservationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainWindow extends JFrame {
    private static final String TAB_FLIGHTS = "flights";
    private static final String TAB_RESERVATIONS = "reservations";
    final int WINDOW_HEIGHT = 500;
    final int WINDOW_WIDTH = 575;

    private ReservationManager resManager;
    private FlightManager flyManager;
    private CardLayout cardLayout;

    private JPanel northPanel;
    private JPanel centerPanel;

    private JButton flightsButton;
    private JButton reservationsButton;

    private TabBase flightsTab;
    private TabBase reservationsTab;

    public MainWindow() {
        this.flyManager = new FlightManager();
        this.resManager = new ReservationManager();

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Flight Reservation Management System");
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        northPanel = createNorthPanel();
        add(northPanel, BorderLayout.NORTH);

        centerPanel = createCenterPanel();
        add(centerPanel, BorderLayout.CENTER);

    }

    private JPanel createNorthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel tabPanel = createTabPanel();
        panel.add(tabPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();
        cardLayout = new CardLayout();

        flightsTab = new FlightsTab(this.flyManager, this.resManager);
        reservationsTab = new ReservationsTab(this.resManager, this.flyManager);

        panel.setLayout(cardLayout);

        panel.add(flightsTab.getPanel(), TAB_FLIGHTS);
        panel.add(reservationsTab.getPanel(), TAB_RESERVATIONS);

        cardLayout.first(panel);

        return panel;
    }

    private JPanel createTabPanel() {
        JPanel tabPanel = new JPanel();

        tabPanel.setLayout(new GridLayout(1, 2));

        flightsButton = new JButton("Flights");
        reservationsButton = new JButton("Reservations");

        flightsButton.addActionListener(new TabButtonActionListener());
        reservationsButton.addActionListener(new TabButtonActionListener());

        tabPanel.add(flightsButton);
        tabPanel.add(reservationsButton);

        return tabPanel;
    }

    public void display() {
        setVisible(true);
    }

    /**
     * Inner action listener class that listens for a tab button to be clicked.
     *
     * @author Nick Hamnett, Mohamed
     * @version January 2, 2020
     */
    private class TabButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == flightsButton) {
                cardLayout.show(centerPanel, TAB_FLIGHTS);
            } else if (e.getSource() == reservationsButton) {
                cardLayout.show(centerPanel, TAB_RESERVATIONS);
            }
        }
    }
}
