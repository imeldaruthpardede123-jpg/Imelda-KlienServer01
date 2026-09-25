package cafeserver;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CafeServer extends JFrame {

    private JTextField txtNama;
    private JTextField txtNoMeja;
    private JTextField txtJumlah;
    private JTextField txtTotal;

    private JComboBox<String> comboMenu;

    private JButton btnHitung;
    private JButton btnSimpan;
    private JButton btnReset;

    private JTable tabel;
    private DefaultTableModel model;

    public CafeServer() {

        setTitle("CafeServer - Sistem Penjualan Cafe");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buatTampilan();
        buatTombol();
    }

    private void buatTampilan() {

        JPanel panelUtama = new JPanel();
        panelUtama.setLayout(new BorderLayout(10, 10));

        JLabel judul = new JLabel(
                "CAFE SERVER",
                SwingConstants.CENTER
        );

        judul.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        panelUtama.add(
                judul,
                BorderLayout.NORTH
        );

        JPanel panelForm = new JPanel(
                new GridLayout(4, 2, 8, 8)
        );

        panelForm.setBorder(
                BorderFactory.createTitledBorder(
                        "Data Pesanan"
                )
        );

        // Nama pelanggan
        panelForm.add(
                new JLabel("Nama Pelanggan")
        );

        txtNama = new JTextField();
        panelForm.add(txtNama);

        // Nomor meja
        panelForm.add(
                new JLabel("No. Meja")
        );

        txtNoMeja = new JTextField();
        panelForm.add(txtNoMeja);

        // Menu
        panelForm.add(
                new JLabel("Menu")
        );

        comboMenu = new JComboBox<>();

        comboMenu.addItem("Pilih Menu");
        comboMenu.addItem("Nasi Goreng");
        comboMenu.addItem("Mie Goreng");
        comboMenu.addItem("Ayam Geprek");
        comboMenu.addItem("Es Teh");
        comboMenu.addItem("Kopi");

        panelForm.add(comboMenu);

        // Jumlah
        panelForm.add(
                new JLabel("Jumlah")
        );

        txtJumlah = new JTextField();
        panelForm.add(txtJumlah);

        panelUtama.add(
                panelForm,
                BorderLayout.WEST
        );

        // Total
        JPanel panelTotal = new JPanel();

        panelTotal.add(
                new JLabel("Total Bayar:")
        );

        txtTotal = new JTextField(12);
        txtTotal.setEditable(false);

        panelTotal.add(txtTotal);

        panelUtama.add(
                panelTotal,
                BorderLayout.EAST
        );

        // Tabel
        model = new DefaultTableModel();

        model.addColumn("Nama");
        model.addColumn("No Meja");
        model.addColumn("Menu");
        model.addColumn("Jumlah");
        model.addColumn("Total");

        tabel = new JTable(model);

        JScrollPane scroll =
                new JScrollPane(tabel);

        scroll.setBorder(
                BorderFactory.createTitledBorder(
                        "Daftar Pesanan"
                )
        );

        panelUtama.add(
                scroll,
                BorderLayout.CENTER
        );

        // Tombol
        JPanel panelTombol =
                new JPanel();

        btnHitung =
                new JButton("HITUNG");

        btnSimpan =
                new JButton("SIMPAN");

        btnReset =
                new JButton("RESET");

        panelTombol.add(btnHitung);
        panelTombol.add(btnSimpan);
        panelTombol.add(btnReset);

        panelUtama.add(
                panelTombol,
                BorderLayout.SOUTH
        );

        add(panelUtama);
    }

    private void buatTombol() {

        btnHitung.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(
                    ActionEvent e) {

                hitungTotal();
            }
        });

        btnSimpan.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(
                    ActionEvent e) {

                simpanData();
            }
        });

        btnReset.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(
                    ActionEvent e) {

                resetForm();
            }
        });
    }

    private void hitungTotal() {

        try {

            int jumlah =
                    Integer.parseInt(
                            txtJumlah.getText()
                    );

            String menu =
                    comboMenu
                            .getSelectedItem()
                            .toString();

            int harga = 0;

            switch (menu) {

                case "Nasi Goreng":
                    harga = 15000;
                    break;

                case "Mie Goreng":
                    harga = 12000;
                    break;

                case "Ayam Geprek":
                    harga = 18000;
                    break;

                case "Es Teh":
                    harga = 5000;
                    break;

                case "Kopi":
                    harga = 8000;
                    break;

                default:

                    JOptionPane.showMessageDialog(
                            this,
                            "Silakan pilih menu!"
                    );

                    return;
            }

            int total =
                    harga * jumlah;

            txtTotal.setText(
                    String.valueOf(total)
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Jumlah harus berupa angka!"
            );
        }
    }

    private void simpanData() {

        if (txtNama.getText().isEmpty()
                || txtNoMeja.getText().isEmpty()
                || txtJumlah.getText().isEmpty()
                || txtTotal.getText().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Data belum lengkap!"
            );

            return;
        }

        model.addRow(
                new Object[]{

                    txtNama.getText(),

                    txtNoMeja.getText(),

                    comboMenu.getSelectedItem(),

                    txtJumlah.getText(),

                    txtTotal.getText()
                }
        );

        JOptionPane.showMessageDialog(
                this,
                "Pesanan berhasil disimpan!"
        );
    }

    private void resetForm() {

        txtNama.setText("");

        txtNoMeja.setText("");

        txtJumlah.setText("");

        txtTotal.setText("");

        comboMenu.setSelectedIndex(0);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                new Runnable() {

            @Override
            public void run() {

                new CafeServer()
                        .setVisible(true);
            }
        });
    }
}