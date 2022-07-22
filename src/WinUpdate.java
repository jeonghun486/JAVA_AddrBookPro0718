import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinUpdate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	
	public WinUpdate(String data) throws Exception {
		setTitle("수정할 레코드 선택");
		setBounds(100, 100, 393, 330);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 353, 241);
		contentPanel.add(scrollPane);
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = 
				DriverManager.getConnection("jdbc:mysql://localhost:3306/hanbitdb",
				"root","12345"); // JDBC 연결
		Statement stmt = conn.createStatement();
		char type = data.charAt(0);
		String sql="";
		if(type=='n')
			sql = "select * from addressbookTBL where name = '" + data.substring(1) + "'";
		else if(type=='t')
			sql = "select * from addressbookTBL where tel = '" + data.substring(1) + "'";
		else
			sql = "select * from addressbookTBL where year = '" + data.substring(1) + "'";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		Vector <String> colNames = new Vector<String>();
		colNames.add("일련번호");
		colNames.add("이름");
		colNames.add("전화번호");
		colNames.add("학번");
		
		Vector records = new Vector();
		
		while(rs.next()) {
			Vector<String> column = new Vector<String>();
			column.add(rs.getString("idx"));
			column.add(rs.getString("name"));
			column.add(rs.getString("tel"));
			column.add(rs.getString("year"));
			
			records.add(column);
		}
		
		conn.close();
		stmt.close();
		rs.close();
		
		DefaultTableModel dtm = new DefaultTableModel(records,colNames);
		
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		
		JButton btnSelect = new JButton("자세히 보기...");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idx = table.getValueAt(table.getSelectedRow() , 0).toString();
				WinMemberUpdate winmemberUpdate = new WinMemberUpdate(idx);
				winmemberUpdate.setModal(true);
				winmemberUpdate.setVisible(true);
			}
		});
		btnSelect.setBounds(139, 261, 97, 23);
		contentPanel.add(btnSelect);
	}
}
