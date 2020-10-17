/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import communication.Conversion;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Adrian
 */
public class MainWindow extends javax.swing.JFrame implements SerialPortEventListener {

    public Queue<String> queue = new LinkedList<>();
    private SerialPort serialPort;
    private CommPortIdentifier portIdentifier;
    private InputStream inputStream;
    private OutputStream outputStream;
    private String namePortCOM;
    private int baudRateCOM;
    private final String appTitle;
    private boolean killThread;

    public MainWindow() {
        initComponents();
        namePortCOM = (String) comboBoxPorts.getSelectedItem();
        if (namePortCOM == null) {
            buttonOpen.setEnabled(false);
        }
        comboBoxBoudrate.setSelectedIndex(3);
        baudRateCOM = Integer.parseInt((String) comboBoxBoudrate.getSelectedItem());
        appTitle = this.getTitle();
        buttonClose.setEnabled(false);
        buttonSend.setEnabled(false);
    }

    private void startThread() {
        FSM fsm = new FSM();
        Thread thread = new Thread(fsm);
        thread.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        comboBoxPorts = new javax.swing.JComboBox();
        buttonRefresh = new javax.swing.JButton();
        buttonOpen = new javax.swing.JButton();
        buttonSend = new javax.swing.JButton();
        comboBoxBoudrate = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaReceiveData = new javax.swing.JTextArea();
        buttonClose = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaSendData = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ModemTerminal");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                MainWindow.this.windowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EVALKIT ST7580-1");
        jLabel1.setToolTipText("");

        comboBoxPorts.setModel(new DefaultComboBoxModel(portsList()));
        comboBoxPorts.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxPortsItemStateChanged(evt);
            }
        });

        buttonRefresh.setText("Refresh");
        buttonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshActionPerformed(evt);
            }
        });

        buttonOpen.setText("Open");
        buttonOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOpenActionPerformed(evt);
            }
        });

        buttonSend.setText("Send");
        buttonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSendActionPerformed(evt);
            }
        });

        comboBoxBoudrate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "9600", "19200", "38400", "57600" }));
        comboBoxBoudrate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxBoudrateItemStateChanged(evt);
            }
        });

        textAreaReceiveData.setColumns(20);
        textAreaReceiveData.setRows(5);
        jScrollPane1.setViewportView(textAreaReceiveData);

        buttonClose.setText("Close");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        textAreaSendData.setColumns(20);
        textAreaSendData.setRows(5);
        jScrollPane2.setViewportView(textAreaSendData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboBoxPorts, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxBoudrate, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonOpen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonClose)
                        .addGap(0, 369, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSend))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxPorts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRefresh)
                    .addComponent(buttonOpen)
                    .addComponent(comboBoxBoudrate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonClose))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonSend)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(224, Short.MAX_VALUE))
        );

        jLabel1.getAccessibleContext().setAccessibleName("jLabel1");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshActionPerformed

        comboBoxPorts.setModel(new DefaultComboBoxModel(portsList()));
        namePortCOM = (String) comboBoxPorts.getSelectedItem();

        if (namePortCOM != null) {
            buttonOpen.setEnabled(true);
        }
    }//GEN-LAST:event_buttonRefreshActionPerformed

    private void buttonOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOpenActionPerformed

        comboBoxPorts.setEnabled(false);
        comboBoxBoudrate.setEnabled(false);
        buttonRefresh.setEnabled(false);
        buttonOpen.setEnabled(false);
        buttonClose.setEnabled(true);
        buttonSend.setEnabled(true);
        openPort(namePortCOM, baudRateCOM, appTitle);
        System.out.println("port otwarty:    " + namePortCOM + "  "
                + baudRateCOM + "  " + SerialPort.DATABITS_8 + "  "
                + SerialPort.STOPBITS_1 + "  " + SerialPort.PARITY_NONE);
        textAreaSendData.grabFocus();
        killThread = false;
        startThread();
    }//GEN-LAST:event_buttonOpenActionPerformed

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        killThread = true;
        comboBoxPorts.setEnabled(true);
        comboBoxBoudrate.setEnabled(true);
        buttonRefresh.setEnabled(true);
        buttonClose.setEnabled(false);
        buttonSend.setEnabled(false);
        buttonOpen.setEnabled(true);
        textAreaSendData.setText("");
        textAreaReceiveData.setText("");
        closePort();
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void comboBoxPortsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxPortsItemStateChanged

        namePortCOM = (String) comboBoxPorts.getSelectedItem();
    }//GEN-LAST:event_comboBoxPortsItemStateChanged

    private void comboBoxBoudrateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxBoudrateItemStateChanged

        baudRateCOM = Integer.parseInt((String) comboBoxBoudrate.getSelectedItem());
    }//GEN-LAST:event_comboBoxBoudrateItemStateChanged

    private void buttonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSendActionPerformed

        String text = textAreaSendData.getText();
        text = text.replaceAll("\\s", "");

        if (Conversion.hexValidation(text)) {
            try {
                sendDataToPort(Conversion.stringToBytes(text));
            } catch (Exception ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Conversion.sendToBinaryFile(Conversion.stringToBytes(text));
            } catch (Exception ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            textAreaSendData.setText("");
        } else {
            System.out.println("To nie jest zapis hex!");
        }
        textAreaSendData.grabFocus();
    }//GEN-LAST:event_buttonSendActionPerformed

    private void windowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowClosing

        closePort();
    }//GEN-LAST:event_windowClosing

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonOpen;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JButton buttonSend;
    private javax.swing.JComboBox comboBoxBoudrate;
    private javax.swing.JComboBox comboBoxPorts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea textAreaReceiveData;
    private javax.swing.JTextArea textAreaSendData;
    // End of variables declaration//GEN-END:variables

    public String[] portsList() {
        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        ArrayList list = new ArrayList();
        while (ports.hasMoreElements()) {
            CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
            if (port.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                list.add(port.getName());
            }
        }
        String portArray[];
        portArray = (String[]) list.toArray(new String[0]);
        return portArray;
    }

    public void openPort(String getPort, int baudRate, String getTitle) {
        boolean stanportu = false;
        try {
            portIdentifier = CommPortIdentifier.getPortIdentifier(getPort);
        } catch (NoSuchPortException e) {
            System.out.println(e.toString());
        }
        if (portIdentifier.isCurrentlyOwned()) { // testowanie portu
            System.out.println("... Port " + portIdentifier.getName() + " jest zajęty - nie otwieram tego portu ...");
        } else { // port wolny mozna otwierac ...
            CommPort commPort = null;
            try {
                commPort = portIdentifier.open(getTitle, 2000); // tytul aplikacji, czas otwarcia aplikacji
            } catch (PortInUseException e) {
                System.out.println(" --- Nie mogę otworzyć portu " + portIdentifier.getName() + " !!!... --- ");
                System.out.println(e.toString());
            }
            serialPort = (SerialPort) commPort;
            try {
                inputStream = serialPort.getInputStream(); // inicjalizacja strumienia wejsciowego
                System.out.println("  ... inicjalizacja strumienia wejściowego");
                outputStream = serialPort.getOutputStream(); // inicjalizacja strumienia wyjsciowego
                System.out.println("  ... inicjalizacja strumienia wyjściowego");
            } catch (IOException e) {
                System.out.println(e.toString());
                stanportu = true;
            }
            if (stanportu) {
                closePort();
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Uwaga!... " + getPort + " ten port jest zajęty...");
            } else {
                // --- właczenie zdarzen portu RS (true) ---
                serialPort.notifyOnBreakInterrupt(false);
                serialPort.notifyOnFramingError(false);
                serialPort.notifyOnParityError(false);
                serialPort.notifyOnOverrunError(false);
                serialPort.notifyOnCarrierDetect(false);
                serialPort.notifyOnRingIndicator(false);
                serialPort.notifyOnDSR(false);
                serialPort.notifyOnCTS(false);
                serialPort.notifyOnOutputEmpty(false);
                serialPort.notifyOnDataAvailable(true); // odczyt portu
                // --- ustawienie lini RTS i DTR na obieranie ---
                serialPort.setDTR(true);
                serialPort.setRTS(false);
                try {
                    serialPort.setSerialPortParams(baudRate,
                            SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE);
                    serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
                } catch (UnsupportedCommOperationException e) {
                    System.out.println(e.toString());
                }
                try {
                    // inicjalizacja listenera do obslugi zdarzeń
                    serialPort.addEventListener((SerialPortEventListener) this);
                    System.out.println("  ... inicjalizacja listenera do obslugi zdarzeń na RS232");
                } catch (TooManyListenersException ex) {
                    System.out.println(ex.toString());
                }
                System.out.println("  ... port RS232 został otwarty ...");
            }
        }
    }

    // procedura zamykajaca port
    public void closePort() {
        killThread = true;
        if (inputStream != null) {
            try {
                inputStream.close(); // zamknięcie strumienia
                System.out.println("  ... zamknięcie strumienia wejsciowego");
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        if (outputStream != null) {
            try {
                outputStream.close(); // zamknięcie strumienia
                System.out.println("  ... zamknięcie strumienia wyjsciowego");
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        if (serialPort != null) {
            serialPort.removeEventListener(); // usunięcie listenera do obslugi zdarzeń
            System.out.println("  ... usunięcie listenera do obslugi zdarzeń na RS232");
            serialPort.close(); // zamknięcie portu
            System.out.println("  ... port RS232 został zamknięty ...");
        }
    }

    // procedura wysyłająca dane na port
    public void sendDataToPort(byte[] dane) throws Exception {
        try {
            serialPort.setRTS(true);
            serialPort.setDTR(true);
            sleep(10);
            outputStream.write(dane); // wysyla do portu dane
            serialPort.setRTS(false);
            serialPort.setDTR(true);
            outputStream.flush(); // czyszczenie strumienia wysylajacego dane
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    // procedura wysyłająca ACK na port
    public void sendAck() {
        try {
            byte[] data = Conversion.stringToBytes("06");
            outputStream.write(data); // wysyla do portu dane
            outputStream.flush(); // czyszczenie strumienia wysylajacego dane
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    // procedura wysyłająca NAK na port
    public void sendNak() {
        try {
            byte[] data = Conversion.stringToBytes("15");
            outputStream.write(data); // wysyla do portu dane
            outputStream.flush(); // czyszczenie strumienia wysylajacego dane
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    // procedura odbierająca dane z portu
    @Override
    public void serialEvent(SerialPortEvent arg0) {
        switch (arg0.getEventType()) {
            // --- odczyt zdarzeń odczytanych z portu ---
            case SerialPortEvent.BI:
                break;
            case SerialPortEvent.OE:
                break;
            case SerialPortEvent.FE:
                break;
            case SerialPortEvent.PE:
                break;
            case SerialPortEvent.CD:
                break;
            case SerialPortEvent.CTS:
                break;
            case SerialPortEvent.DSR:
                break;
            case SerialPortEvent.RI:
                break;
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.DATA_AVAILABLE:
                String receivedData = new String();
                try {
                    int nb;
                    // odczyt buforowany danych ze strunienia
                    do {
                        nb = inputStream.available(); // ilość bajtów odczytana ze strumienia
                        byte[] readBuffer = new byte[nb];
                        inputStream.read(readBuffer);
                        receivedData += Conversion.bytesToHex(readBuffer);
                        System.out.println("odebrane dane: " + receivedData);
                        nb = inputStream.available();
                    } while (nb > 0);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                // przekazanie odebranych danych do wątku dekodującego
                String[] characters = receivedData.split("");
                for (int i = 0; characters.length > 0; i += 2) {
                    String hex = characters[i] + characters[i + 1];
                    queue.offer(hex);
                }
                break;
            // --- odczyt zdarzeń odczytanych z portu ---
        }
    }

    public class FSM implements Runnable {

        @Override
        //@SuppressWarnings("WaitWhileNotSynced")
        public void run() {
            //System.out.println("watek startuje");
            String state = "WFB";
            String layer = new String();

            do {

                String data = new String();
                String lastChar = new String();
                Integer lengthData = 0;
                Integer sum = 0;
                String checksum1 = new String();
                String checksum2 = new String();
                String firstChar = new String();
                while (!queue.isEmpty()) {
                    switch (state) {
                        case "WFB":
                            firstChar = queue.poll();
                            if (firstChar.equals("06")) {
                                state = "WFB";
                                System.out.println("ACK");
                            } else if (firstChar.equals("15")) {
                                state = "WFB";
                                System.out.println("NAK");
                            } else if (firstChar.equals("02") || firstChar.equals("03") || firstChar.equals("3F")) {
                                lastChar = firstChar;
                                state = "WFL";
//                                System.out.println(firstChar);
//                                System.out.println("go to WFL");
//                                System.out.println("firstChar: " + firstChar);
                            }
                            break;
                        case "WFL":
                            firstChar = queue.poll();
                            //System.out.println("jestem w WFL");
                            if (lastChar.equals("3F")) {
                                //System.out.println(firstChar);
                                layer = readStatus(firstChar);
                                //System.out.println("layer: " + layer);
                                state = "WFB";
                            } else if (lastChar.equals("02") || lastChar.equals("03")) {
                                lengthData = Integer.parseInt(firstChar, 16);
                                if (lengthData > 255) {
                                    System.out.println("Długość danych wynosi wiecej niż 255!");
                                    waitMs(10);
                                    sendNak();
                                    state = "WFB";
                                    break;
                                }
                                sum += Integer.parseInt(firstChar, 16);
                                System.out.println("suma WFL:" + sum);
                                state = "WFCC";
                            }
                            break;
                        case "WFCC":
                            firstChar = queue.poll();
                            switch (layer) {
                                case "PHY":
                                    //czytaj cc dla PHY
                                    System.out.println("WFCC: PHP");
                                    break;
                                case "DL":
                                    //czytaj cc dla DL
                                    System.out.println("WFCC: DL");
                                    break;
                                case "SS":
                                    //czytaj cc dla SS
                                    System.out.println("WFCC: SS");
                                    break;
                                case "NC":
                                    //nie wiem co tutaj :D
                                    System.out.println("WFCC: NC");
                                    break;
                                default:
                                    System.out.println("Nie rozpoznano powłoki pracującego modemu.");
                                    break;
                            }
                            sum += Integer.parseInt(firstChar, 16);
                            System.out.println("suma WFCC: " + sum);
                            state = "RD";
                            break;
                        case "RD":
                            //System.out.println("RD");
                            System.out.println("length data: " + lengthData);
                            for (int i = 0; i < lengthData; i++) {
                                firstChar = queue.poll();
                                data += firstChar;
                                sum += Integer.parseInt(firstChar, 16);
                                //System.out.println("suma RD: " + sum);
                            }
                            System.out.println("dane: " + data);
                            System.out.println("sum: " + sum);
                            state = "WFCh1";
                            break;
                        case "WFCh1":
                            firstChar = queue.poll();
                            checksum1 = firstChar;
                            state = "WFCh2";
                            break;
                        case "WFCh2":
                            firstChar = queue.poll();
                            checksum2 = firstChar;
                            String checksum = checksum2 + checksum1;
                            int checksumInt = Integer.parseInt(checksum, 16);
                            if (checksumInt == sum) {
                                // ramka poprawna
                                waitMs(10);
                                sendAck();
                                System.out.println("poszedl ACK z WFCh2");
                                // wyświetl odebrane dane
                                System.out.println(data);
                            } else {
                                //ramka błędna
                                waitMs(10);
                                sendNak();
                            }
                            sum = 0;
                            state = "WFB";
                            break;
                        default:
                            System.out.println("Coś poszło nie tak #Wątek - metoda run()");
                            break;
                    }
                }
                //waitMs(200);
            } while (!killThread);
            //System.out.println("watek zdycha");
        }

        private int waitMs(int ms) {
            try {
                sleep(ms);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            return ms;
        }

    }

    public String readStatus(String data) {

        Integer[] status = new Integer[8];
        for (int i = 0; i < 8; i++) {
            status[i] = 0;
        }
        int intStatus = Integer.parseInt(data, 16);
        byte byteStatus = (byte) intStatus;
        String strBinStatus = Integer.toBinaryString(Conversion.unsignedToBytes(byteStatus));
        String[] strBinStatusTab = strBinStatus.split("");
        for (int i = 0; i < strBinStatusTab.length; i++) {
            status[(status.length - 1) - i] = Integer.parseInt(strBinStatusTab[i]);
        }

        if (status[0] == 0) {
            System.out.println("Configuration status: autoreconfiguration correctly occured");
        } else if (status[0] == 1) {
            System.out.println("Configuration status: autoreconfiguration occured with errors or at least one among MIB objects 00h (Modem Config), 01h (PHY Config), 02h (SS Key) hesn't changed its default value after boot");
        } else {
            System.out.println("Coś nie pykło #readStatus method# bitIndex 0");
        }

        if (status[1] == 0) {
            System.out.println("Transmission status: the ST7580 is not transmitting a power line frame");
        } else if (status[0] == 1) {
            System.out.println("Transmission status: the ST7580 is transmitting a power line frame");
        } else {
            System.out.println("Coś nie pykło #readStatus method# bitIndex 1");
        }

        if (status[2] == 0) {
            System.out.println("Reception status: the ST7580 is not receiving a power line frame");
        } else if (status[0] == 1) {
            System.out.println("Reception status: the ST7580 is receiving a power line frame");
        } else {
            System.out.println("Coś nie pykło #readStatus method# bitIndex 2");
        }

        int bitIndex34 = status[3] * 2 + status[4] * 1;
        String layer;
        if (bitIndex34 == 0) {
            System.out.println("Active layer: PHY layer");
            layer = "PHY";
        } else if (bitIndex34 == 1) {
            System.out.println("Active layer: DL layer");
            layer = "DL";
        } else if (bitIndex34 == 2) {
            System.out.println("Active layer: SS layer");
            layer = "SS";
        } else if (bitIndex34 == 3) {
            System.out.println("Active layer: ST7580 not configured");
            layer = "NC";
        } else {
            System.out.println("Coś nie pykło #readStatus method# bitIndex 3-4");
            layer = "0";
        }

        if (status[5] == 0) {
            System.out.println("Overcurrent flag: no overcurrent event on last transmission");
        } else if (status[0] == 1) {
            System.out.println("Overcurrent flag: last transmission generated at least one overcurrent event");
        } else {
            System.out.println("Coś nie pykło #readStatus method# bitIndex 5");
        }

        int bitIndex67 = status[6] * 2 + status[7] * 1;
        if (bitIndex67 == 0) {
            System.out.println("Extimated ST7580 temperature: T < 70 *C (typical)");
        } else if (bitIndex67 == 1) {
            System.out.println("Extimated ST7580 temperature: 70 *C < T < 100 *C (typical)");
        } else if (bitIndex67 == 2) {
            System.out.println("Extimated ST7580 temperature: 100 *C < T < 125 *C (typical)");
        } else if (bitIndex67 == 3) {
            System.out.println("Extimated ST7580 temperature: T > 125 *C (typical)");
        } else {
            System.out.println("Coś nie pykło #readStatus method# bitIndex 6-7");
        }
        return layer;

    }
}
