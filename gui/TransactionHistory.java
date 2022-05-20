package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import assets.ColorPalette;
import assets.FontFamily;
import components.Button;
import components.Frame;
import components.HeadingLabel4;
import components.Panel;
import components.Table;
import components.TextField;
import database.TransactionFormQuery;
import model.TransactionHistoryDetail;
import model.TransactionHistoryHeader;
import model.User;

public class TransactionHistory implements ActionListener, ListSelectionListener {
//Data
	private User user;
	private Object[] transactionHistoryHeaderHeader = new String[]{"Transasction ID", "Transaction Date", "Pick Up Date"};
	private Object[] transactionHistoryDetailHeader = new String[]{"Cake Name", "Cake Size", "Cake Shape", "Cake Price", "Quantity", "Subtotal"};
	private TransactionFormQuery query = new TransactionFormQuery();	
//	Component
	private Frame frame = new Frame("CakeLAnd");
	private HeadingLabel4 lbTitle = new HeadingLabel4("CakeLAnd");
	private HeadingLabel4 lbTransactionHistory = new HeadingLabel4("Transaction History", ColorPalette.navy);
	private JLabel lbSelectedId = new JLabel("Selected ID");
	private JLabel lbTotal = new JLabel("Total");
	private TextField txtSelectedId = new TextField();
	private TextField txtTotal = new TextField();
	private Panel pnlLbTitle = new Panel();
	private Panel pnlLbTransactionHistory = new Panel();
	private Panel pnlDummy1 = new Panel();
	private Panel pnlDummy2 = new Panel();
	private Panel pnlDummy3 = new Panel();
	private Panel pnlDummy4 = new Panel();
	private Button btnBackToMainMenu = new Button("Back to Main Menu", FontFamily.btnFont2, true);
	private Table tblTransactionHistoryHeader = new Table(transactionHistoryHeaderHeader);
	private Table tblTransactionHistoryDetail = new Table(transactionHistoryDetailHeader);
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public TransactionHistory(User user) {
		this.user = user;
		pnlLbTitle.add(lbTitle);
		pnlLbTransactionHistory.add(lbTransactionHistory);
		frame.setLayout(new GridBagLayout());
		
		setGridBagLayout();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		frame.add(pnlLbTitle, gbc);
		
		gbc.gridy = 1;
		frame.add(pnlLbTransactionHistory, gbc);
		
		gbc.gridy = 2;
		gbc.gridheight = 6;
		gbc.weighty = 1;
		gbc.insets = new Insets(0,10,10,10);
		tblTransactionHistoryHeader.getTable().getSelectionModel().addListSelectionListener(this);
		initTblTransactionHistoryHeader();
		frame.add(tblTransactionHistoryHeader, gbc);
		
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weighty = 0.1;
		frame.add(lbSelectedId, gbc);
		
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		frame.add(txtSelectedId, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 5;
		gbc.gridheight = 6;
		gbc.weighty = 1;
		frame.add(tblTransactionHistoryDetail, gbc);
		
		gbc.gridy = 15;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weighty = 0.1;
		frame.add(lbTotal, gbc);
		
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		frame.add(txtTotal, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 16;
		gbc.gridwidth = 1;
		gbc.weighty = 0;
		gbc.insets = new Insets(0,10,10,0);
		frame.add(pnlDummy1, gbc);
		
		gbc.gridx = 1;
		gbc.insets = new Insets(0,0,10,0);
		frame.add(pnlDummy2, gbc);
		
		gbc.gridx = 2;
		frame.add(pnlDummy3, gbc);
		
		gbc.gridx = 3;
		frame.add(pnlDummy4, gbc);
		
		gbc.gridx = 4;
		gbc.insets = new Insets(0,0,10,10);
		btnBackToMainMenu.addActionListener(this);
		frame.add(btnBackToMainMenu, gbc);
		
		frame.Window();
		
	}
	
	private void setGridBagLayout() {
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,10,0,10);
	}
	
	private void initTblTransactionHistoryHeader() {
		tblTransactionHistoryHeader.eraseTable();
		Vector<TransactionHistoryHeader> transactionHistoryHeaders = query.getTransactionHeaderList(user.getUserID());
		for (TransactionHistoryHeader t : transactionHistoryHeaders) {
			Object transactionHistoryHeader[] = new Object[]{
				t.getTransactionId(),
				t.getTransactionDate(),
				t.getPickupDate(),
			};
			tblTransactionHistoryHeader.addTable(transactionHistoryHeader);
		}
	}
	
	private void initTblTransactionHistoryDetail(String transactionId) {
		tblTransactionHistoryDetail.eraseTable();
		Vector<TransactionHistoryDetail> transactionHistoryDetails = query.getTransactionDetailList(transactionId);
		for (TransactionHistoryDetail t : transactionHistoryDetails) {
			Object transactionHistoryDetail[] = new Object[]{
				t.getCakeName(),
				t.getCakeSize(),
				t.getCakeShape(),
				t.getCakePrice(),
				t.getQuantity(),
				t.getSubtotal(),
			};
			tblTransactionHistoryDetail.addTable(transactionHistoryDetail);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBackToMainMenu){
			 frame.setVisible(false);
             new MainForm(user);
		} 
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		if (!e.getValueIsAdjusting()) {
			if (e.getSource() == tblTransactionHistoryHeader.getTable().getSelectionModel()) {
				int selectRowIdx = lsm.getMinSelectionIndex();
				String transactionId = tblTransactionHistoryHeader.getTable().getValueAt(selectRowIdx, 0).toString();
				int index = 0;
				int total = 0;
				txtSelectedId.setText(transactionId);
				initTblTransactionHistoryDetail(transactionId);
				System.out.println();
				while(index++ < tblTransactionHistoryDetail.getTable().getRowCount()) {
					total += (Integer)tblTransactionHistoryDetail.getTable().getValueAt(selectRowIdx, 5);
				}
				txtTotal.setText("Rp " + Integer.toString(total));
			}
		}
    }
	

}
