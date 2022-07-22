import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class WinMemberAllShow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;


	/**
	 * Create the dialog.
	 * @throws Exception 
	 */
	public WinMemberAllShow() throws Exception {
		setTitle("��ü ȸ�� ����");
		setBounds(100, 100, 701, 639);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 661, 493);
		contentPanel.add(scrollPane);
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn =  	
				DriverManager.getConnection("jdbc:mysql://localhost:3306/hanbitDB",
				"root","12345"); // JDBC ����
		Statement stmt = conn.createStatement();
		String sql = "select * from addressBookTBL";
		ResultSet rs = stmt.executeQuery(sql);
		
		Vector <String> columnNames = new Vector<String>();
		columnNames.add("��ȣ");
		columnNames.add("�̸�");
		columnNames.add("��ȭ��ȣ");
		columnNames.add("�ּ�");
		columnNames.add("ȸ���");
		columnNames.add("�й�");
		columnNames.add("��ȥ");
		columnNames.add("����");
		columnNames.add("���");
		columnNames.add("�׸����ϰ��");
		
		Vector records = new Vector();
		while(rs.next()) {
			Vector<String> cols = new Vector<String>();
			for(int i=1;i<=10;i++)
				cols.add(rs.getString(i));			
			records.add(cols);
		}
		conn.close();
		stmt.close();
		rs.close();
		
		DefaultTableModel dtm = new DefaultTableModel(records, columnNames);
		
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		
				
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		JMenuItem mnuDetail = new JMenuItem("ȸ�� �ڼ��� ����");
		popupMenu.add(mnuDetail);
		
		mnuDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idx = table.getValueAt(table.getSelectedRow(), 0).toString();				
				WinMemberDetail winMemberDetail;
				try {
					winMemberDetail = new WinMemberDetail(idx);
					winMemberDetail.setModal(true);
					winMemberDetail.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JMenuItem mnuUpdate = new JMenuItem("ȸ�� ����");
		popupMenu.add(mnuUpdate);
		mnuUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idx = table.getValueAt(table.getSelectedRow(), 0).toString();				
				WinMemberUpdate winMemberUpdate;
				try {
					winMemberUpdate = new WinMemberUpdate(idx);
					winMemberUpdate.setModal(true);
					winMemberUpdate.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JMenuItem mnuDelete = new JMenuItem("ȸ�� ����");
		popupMenu.add(mnuDelete);
		mnuDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idx = table.getValueAt(table.getSelectedRow(), 0).toString();				
				WinMemberDelete winMemberDelete;
				try {
					winMemberDelete = new WinMemberDelete(idx);
					winMemberDelete.setModal(true);
					winMemberDelete.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JMenuItem mnuInsert = new JMenuItem("ȸ�� �߰�");
		popupMenu.add(mnuInsert);
		mnuInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idx = table.getValueAt(table.getSelectedRow(), 0).toString();				
				WinMemberInsert winMemberInsert;
				try {
					winMemberInsert = new WinMemberInsert();
					winMemberInsert.setModal(true);
					winMemberInsert.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int column = table.columnAtPoint(e.getPoint());
				if(!table.isRowSelected(row))
					table.changeSelection(row, column, false, false);
				
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
