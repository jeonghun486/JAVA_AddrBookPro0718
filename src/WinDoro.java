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
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinDoro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JList list;
	String sDoro = "";
	public String getAddress() {
		return sDoro;
	}
	
	public WinDoro(String strDoro) {
		setTitle("도로명(" + strDoro + ") 검색 결과");
		setBounds(100, 100, 423, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 383, 241);
		contentPanel.add(scrollPane);
		
		list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sDoro = list.getSelectedValue().toString();
				setVisible(false);
			}
		});
		scrollPane.setViewportView(list);
		
		try {
			ShowDetailDoro(strDoro);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}


	private void ShowDetailDoro(String strDoro) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = 
				DriverManager.getConnection("jdbc:mysql://localhost:3306/hanbitdb",
				"root","12345"); // JDBC 연결
		Statement stmt = conn.createStatement();
		
		String sql = "select * from addresstbl where doro = '";
		sql = sql + strDoro + "'";
		ResultSet rs = stmt.executeQuery(sql);
		Vector<String> v = new Vector<String>();
		while(rs.next()) {
			String strSi = rs.getString("Si");
			String strGu = rs.getString("Gu");
			String strDong = rs.getString("Dong");
			//System.out.println(strSi + " " + strGu + " " + strDong);
			v.add(strSi + " " + strGu + " " + strDong);
		}
		list.setListData(v);
	}
}
