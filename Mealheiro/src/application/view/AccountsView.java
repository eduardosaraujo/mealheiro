package application.view;

import application.model.AccountType;
import application.model.UserList;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ed
 */
public class AccountsView extends JPanel implements Observer {

    private UserList db;

    private DefaultTableModel assetModel;
    private DefaultTableModel expenseModel;
    private DefaultTableModel revenueModel;
    private DefaultTableModel liabilityModel;

    /**
     * Creates new form AccountsView
     */
    public AccountsView() {
        // tables data placeholder
        Object[][] data = {};

        // asset table headers
        String[] tableColumnNames = {"Name", "Account number", "Current balance", "Is active?", "Action"};

        // asset table model
        assetModel = new DefaultTableModel(data, tableColumnNames);

        // expense table model
        expenseModel = new DefaultTableModel(data, tableColumnNames);

        // revenue table model
        revenueModel = new DefaultTableModel(data, tableColumnNames);

        // liability table model
        liabilityModel = new DefaultTableModel(data, tableColumnNames);

        initComponents();

    }

    /**
     *
     * @param db UserList
     */
    public void setModel(UserList db) {
        this.db = db;
        db.addObserver(this);
        this.update(db, null);
    }

    /**
     *
     * @param el EventListener
     */
    public void setController(EventListener el) {
        bTransactionSubmit.addActionListener((ActionListener) el);
    }

    /**
     *
     * @param o Observable
     * @param arg Object
     */
    public void update(Observable o, Object arg) {
        // if user is logged in, populate table
        if (db.getLoggedInUser() != null) {

            System.out.println("Account view: updated");
            // clear the table first
            assetModel.setRowCount(0);
            expenseModel.setRowCount(0);
            revenueModel.setRowCount(0);
            liabilityModel.setRowCount(0);

            // foreach account in users
            db.getLoggedInUser().getAccounts().forEach(acc -> {
                // switch account AccountType
                switch (acc.getAccountType()) {
                    case ASSET ->
                        assetModel.addRow(new Object[]{acc.getName(), acc.getAccountNumber(), "€" + acc.getBalance(), acc.getActive(), ""});

                    case EXPENSE ->
                        expenseModel.addRow(new Object[]{acc.getName(), acc.getAccountNumber(), "€" + acc.getBalance(), acc.getActive(), ""});

                    case REVENUE ->
                        revenueModel.addRow(new Object[]{acc.getName(), acc.getAccountNumber(), "€" + acc.getBalance(), acc.getActive(), ""});

                    case LIABILITY ->
                        liabilityModel.addRow(new Object[]{acc.getName(), acc.getAccountNumber(), "€" + acc.getBalance(), acc.getActive(), ""});
                }
            });
        }
    }

    /**
     *
     * @return AccountType at - Return account type if exists, if not return
     * null
     */
    public AccountType getCbAccountsType() {
        AccountType at = null;
        switch (cbAccountsType.getSelectedItem().toString()) {
            case "Asset" -> {
                return at = AccountType.ASSET;
            }
            case "Expense" -> {
                return at = AccountType.EXPENSE;
            }
            case "Revenue" -> {
                return at = AccountType.REVENUE;
            }
            case "Liability" -> {
                return at = AccountType.LIABILITY;
            }
        }
        return at;
    }

    /**
     *
     * @return String balance - Return text field input or return "0" if no
     * input is provided
     */
    public String getFtfAccountsBalance() {
        if (!ftfAccountsBalance.getText().isEmpty() || !ftfAccountsBalance.getText().isBlank()) {
            return ftfAccountsBalance.getText();
        }
        return "0";
    }

    /**
     *
     * @return String bic - Return text field input
     */
    public String getTfAccountsBic() {
        return tfAccountsBic.getText();
    }

    /**
     *
     * @return String account iban - Return text field input
     */
    public String getTfAccountsIban() {
        return tfAccountsIban.getText();
    }

    /**
     *
     * @return account name - Return text field input
     */
    public String getTfAccountsName() {
        return tfAccountsName.getText();
    }

    /**
     *
     * @return String account number - Return text field input
     */
    public String getTfAccountsNumber() {
        return tfAccountsNumber.getText();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        tpAccounts = new javax.swing.JTabbedPane();
        AssetPanel = new javax.swing.JPanel();
        jScrollPaneAsset = new javax.swing.JScrollPane();
        tableAsset = new javax.swing.JTable();
        ExpensePanel = new javax.swing.JPanel();
        jScrollPaneExpense = new javax.swing.JScrollPane();
        tableExpense = new javax.swing.JTable();
        RevenuePanel = new javax.swing.JPanel();
        jScrollPaneRevenue = new javax.swing.JScrollPane();
        tableRevenue = new javax.swing.JTable();
        newAccountPanel = new javax.swing.JPanel();
        lblAccountsName = new javax.swing.JLabel();
        tfAccountsName = new javax.swing.JTextField();
        lblAccountsNumber = new javax.swing.JLabel();
        tfAccountsNumber = new javax.swing.JTextField();
        tfAccountsIban = new javax.swing.JTextField();
        lblAccountsIban = new javax.swing.JLabel();
        tfAccountsBic = new javax.swing.JTextField();
        lblAccountsBic = new javax.swing.JLabel();
        lblAccountsBalance = new javax.swing.JLabel();
        lblAccountsType = new javax.swing.JLabel();
        cbAccountsType = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        bTransactionSubmit = new javax.swing.JButton();
        ftfAccountsBalance = new javax.swing.JFormattedTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 32767));

        setName("Accounts"); // NOI18N

        tableAsset.setModel(assetModel);
        jScrollPaneAsset.setViewportView(tableAsset);

        javax.swing.GroupLayout AssetPanelLayout = new javax.swing.GroupLayout(AssetPanel);
        AssetPanel.setLayout(AssetPanelLayout);
        AssetPanelLayout.setHorizontalGroup(
            AssetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneAsset, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
        );
        AssetPanelLayout.setVerticalGroup(
            AssetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneAsset, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
        );

        tpAccounts.addTab("Asset", AssetPanel);

        tableExpense.setModel(expenseModel);
        jScrollPaneExpense.setViewportView(tableExpense);

        javax.swing.GroupLayout ExpensePanelLayout = new javax.swing.GroupLayout(ExpensePanel);
        ExpensePanel.setLayout(ExpensePanelLayout);
        ExpensePanelLayout.setHorizontalGroup(
            ExpensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneExpense, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
        );
        ExpensePanelLayout.setVerticalGroup(
            ExpensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneExpense, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
        );

        tpAccounts.addTab("Expense", ExpensePanel);

        tableRevenue.setModel(revenueModel);
        jScrollPaneRevenue.setViewportView(tableRevenue);

        javax.swing.GroupLayout RevenuePanelLayout = new javax.swing.GroupLayout(RevenuePanel);
        RevenuePanel.setLayout(RevenuePanelLayout);
        RevenuePanelLayout.setHorizontalGroup(
            RevenuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneRevenue, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
        );
        RevenuePanelLayout.setVerticalGroup(
            RevenuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneRevenue, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
        );

        tpAccounts.addTab("Revenue", RevenuePanel);

        jSplitPane1.setRightComponent(tpAccounts);

        newAccountPanel.setPreferredSize(new java.awt.Dimension(180, 499));

        lblAccountsName.setText("Name");

        lblAccountsNumber.setText("Account number");

        lblAccountsIban.setText("IBAN");

        lblAccountsBic.setText("BIC");

        lblAccountsBalance.setText("Opening balance");

        lblAccountsType.setText("Account type");

        cbAccountsType.setModel(new javax.swing.DefaultComboBoxModel<>(AccountType.getAccountTypes()));

        jLabel7.setText("€");

        bTransactionSubmit.setText("Submit");

        ftfAccountsBalance.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.00"))));
        ftfAccountsBalance.setToolTipText("Please enter a number.");

        javax.swing.GroupLayout newAccountPanelLayout = new javax.swing.GroupLayout(newAccountPanel);
        newAccountPanel.setLayout(newAccountPanelLayout);
        newAccountPanelLayout.setHorizontalGroup(
            newAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newAccountPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(newAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newAccountPanelLayout.createSequentialGroup()
                        .addComponent(ftfAccountsBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(newAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bTransactionSubmit)
                        .addGroup(newAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblAccountsName)
                            .addComponent(tfAccountsName, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(lblAccountsNumber)
                            .addComponent(tfAccountsNumber)
                            .addComponent(lblAccountsIban)
                            .addComponent(tfAccountsIban)
                            .addComponent(lblAccountsBic)
                            .addComponent(tfAccountsBic)
                            .addComponent(lblAccountsBalance)
                            .addComponent(lblAccountsType)
                            .addComponent(cbAccountsType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
            .addGroup(newAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newAccountPanelLayout.createSequentialGroup()
                    .addGap(0, 170, Short.MAX_VALUE)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(newAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newAccountPanelLayout.createSequentialGroup()
                    .addGap(0, 150, Short.MAX_VALUE)
                    .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        newAccountPanelLayout.setVerticalGroup(
            newAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newAccountPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAccountsName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfAccountsName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblAccountsBalance)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(ftfAccountsBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblAccountsType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbAccountsType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblAccountsIban)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfAccountsIban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblAccountsBic)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfAccountsBic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblAccountsNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfAccountsNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bTransactionSubmit)
                .addContainerGap(115, Short.MAX_VALUE))
            .addGroup(newAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newAccountPanelLayout.createSequentialGroup()
                    .addContainerGap(249, Short.MAX_VALUE)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(250, Short.MAX_VALUE)))
            .addGroup(newAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newAccountPanelLayout.createSequentialGroup()
                    .addContainerGap(249, Short.MAX_VALUE)
                    .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(250, Short.MAX_VALUE)))
        );

        jSplitPane1.setLeftComponent(newAccountPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jSplitPane1)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AssetPanel;
    private javax.swing.JPanel ExpensePanel;
    private javax.swing.JPanel RevenuePanel;
    private javax.swing.JButton bTransactionSubmit;
    private javax.swing.JComboBox<String> cbAccountsType;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JFormattedTextField ftfAccountsBalance;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPaneAsset;
    private javax.swing.JScrollPane jScrollPaneExpense;
    private javax.swing.JScrollPane jScrollPaneRevenue;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblAccountsBalance;
    private javax.swing.JLabel lblAccountsBic;
    private javax.swing.JLabel lblAccountsIban;
    private javax.swing.JLabel lblAccountsName;
    private javax.swing.JLabel lblAccountsNumber;
    private javax.swing.JLabel lblAccountsType;
    private javax.swing.JPanel newAccountPanel;
    private javax.swing.JTable tableAsset;
    private javax.swing.JTable tableExpense;
    private javax.swing.JTable tableRevenue;
    private javax.swing.JTextField tfAccountsBic;
    private javax.swing.JTextField tfAccountsIban;
    private javax.swing.JTextField tfAccountsName;
    private javax.swing.JTextField tfAccountsNumber;
    private javax.swing.JTabbedPane tpAccounts;
    // End of variables declaration//GEN-END:variables
}
