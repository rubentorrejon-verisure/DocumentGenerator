import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DocumentWindow extends JFrame {

    private final JComboBox<CountryItem> countryCombo;
    private final JComboBox<String> documentCombo;
    private final JTextArea outputArea;

    public DocumentWindow(String title) {
        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("Generador de documentos", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        inputPanel.add(new JLabel("Selecciona país:"));

        countryCombo = new JComboBox<>(new CountryItem[] {
            new CountryItem("ES", "Spain"),
            new CountryItem("FR", "France"),
            new CountryItem("IT", "Italy"),
            new CountryItem("AR", "Argentina")
        });
        countryCombo.addActionListener(e -> updateDocuments());
        inputPanel.add(countryCombo);

        inputPanel.add(new JLabel("Selecciona documento:"));
        documentCombo = new JComboBox<>();
        updateDocuments();
        inputPanel.add(documentCombo);

        JButton generateButton = new JButton("Generar documento");
        generateButton.addActionListener(new GenerateAction());
        inputPanel.add(generateButton);

        add(inputPanel, BorderLayout.CENTER);

        outputArea = new JTextArea(5, 40);
        outputArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        setSize(560, 280);
        setLocationRelativeTo(null);
    }

    private void updateDocuments() {
        CountryItem selected = (CountryItem) countryCombo.getSelectedItem();
        if (selected == null) {
            return;
        }

        String[] documents = DocumentGenerator.getAvailableDocuments(selected.code);
        documentCombo.removeAllItems();
        for (String doc : documents) {
            documentCombo.addItem(doc);
        }
    }

    private void generateDocument() {
        CountryItem selected = (CountryItem) countryCombo.getSelectedItem();
        if (selected == null) {
            return;
        }

        String countryCode = selected.code;
        String documentType = (String) documentCombo.getSelectedItem();
        if (documentType == null) {
            return;
        }

        String document = DocumentGenerator.generateDocument(countryCode, documentType.toLowerCase());
        String countryName = DocumentGenerator.getCountryName(countryCode);
        outputArea.setText(documentType + " para " + countryName + " generado: " + document);
    }

    public static void display() {
        SwingUtilities.invokeLater(() -> {
            DocumentWindow window = new DocumentWindow("Generador de Documentos");
            window.setVisible(true);
        });
    }

    private static class CountryItem {
        private final String code;
        private final String name;

        CountryItem(String code, String name) {
            this.code = code;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private class GenerateAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            generateDocument();
        }
    }
}
