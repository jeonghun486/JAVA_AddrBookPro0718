import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Toolkit;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class WinMemberInsert extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdx;
	private JTextField txtName;
	private JTextField txtTel2;
	private JTextField txtTel3;
	private JLabel lblCompany;
	private JTextField txtAddress;
	private JTextField txtCompany;
	private JLabel lblPic;
	private JLabel lblYear;
	private JComboBox cbYear;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JTextField txtBirth;
	JComboBox cbTel1;
	JRadioButton rbMarriage1;
	JRadioButton rbMarriage2;
	JCheckBox ckBirthType;
	String path = "";
	
	public WinMemberInsert() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(WinMemberInsert.class.getResource("/images/MemberInsert.png")));
		setTitle("동창 회원가입");
		setBounds(100, 100, 431, 366);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblIdx = new JLabel("일련번호:");
			lblIdx.setBounds(12, 10, 57, 15);
			contentPanel.add(lblIdx);
		}
		
		txtIdx = new JTextField();
		txtIdx.setEnabled(false);
		txtIdx.setBounds(81, 7, 116, 21);
		contentPanel.add(txtIdx);
		txtIdx.setColumns(10);
		
		JLabel lblName = new JLabel("이름:");
		lblName.setBounds(12, 38, 57, 15);
		contentPanel.add(lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(81, 35, 116, 21);
		contentPanel.add(txtName);
		
		JLabel lblTel = new JLabel("전화번호:");
		lblTel.setBounds(12, 148, 57, 15);
		contentPanel.add(lblTel);
		
		JLabel lblAddress = new JLabel("주소:");
		lblAddress.setBounds(12, 123, 57, 15);
		contentPanel.add(lblAddress);
		
		txtTel2 = new JTextField();
		txtTel2.setColumns(10);
		txtTel2.setBounds(157, 146, 105, 23);
		contentPanel.add(txtTel2);
		
		txtTel3 = new JTextField();
		txtTel3.setColumns(10);
		txtTel3.setBounds(274, 146, 105, 23);
		contentPanel.add(txtTel3);
		
		cbTel1 = new JComboBox();
		cbTel1.setModel(new DefaultComboBoxModel(new String[] {"010", "02", "032", "031", "055"}));
		cbTel1.setBounds(81, 146, 57, 23);
		contentPanel.add(cbTel1);
		
		lblCompany = new JLabel("회사명:");
		lblCompany.setBounds(12, 178, 57, 15);
		contentPanel.add(lblCompany);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(81, 120, 298, 21);
		contentPanel.add(txtAddress);
		
		txtCompany = new JTextField();
		txtCompany.setColumns(10);
		txtCompany.setBounds(81, 175, 116, 21);
		contentPanel.add(txtCompany);
		
		lblPic = new JLabel("");
		lblPic.setIcon(new ImageIcon(WinMemberInsert.class.getResource("/images/MemberInsert.png")));
		lblPic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("그림 파일", "png", "jpg", "gif");
					
					chooser.setFileFilter(filter);
					chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					int ret = chooser.showOpenDialog(null);
					if(ret == JFileChooser.APPROVE_OPTION) {
						// 그림을 100*100으로 변환해서 레이블에 보이시오.
						path = chooser.getSelectedFile().toString();
						System.out.println(path);
						ImageIcon image = new ImageIcon(path);
						Image pic = image.getImage();
						pic = pic.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
						ImageIcon imageConv = new ImageIcon(pic);
						lblPic.setIcon(imageConv);
						// Insert 시 문제 생김 \ ==> 없어짐
						path = path.replaceAll("\\\\", "\\\\\\\\");
					}
				}
			}
		});
		lblPic.setToolTipText("\uB354\uBE14\uD074\uB9AD\uD574\uC11C \uC0AC\uC9C4 \uC120\uD0DD");
		lblPic.setBackground(Color.YELLOW);
		lblPic.setOpaque(true);
		lblPic.setBounds(209, 7, 100, 100);
		contentPanel.add(lblPic);
		
		lblYear = new JLabel("입학년도:");
		lblYear.setBounds(12, 206, 57, 15);
		contentPanel.add(lblYear);
		
		cbYear = new JComboBox();
		cbYear.setBounds(81, 202, 116, 23);
		contentPanel.add(cbYear);
		
		rbMarriage2 = new JRadioButton("기혼");
		buttonGroup.add(rbMarriage2);
		rbMarriage2.setBounds(71, 237, 57, 23);
		contentPanel.add(rbMarriage2);
		
		rbMarriage1 = new JRadioButton("미혼");
		buttonGroup.add(rbMarriage1);
		rbMarriage1.setSelected(true);
		rbMarriage1.setBounds(12, 237, 57, 23);
		contentPanel.add(rbMarriage1);
		
		JLabel lblBirth = new JLabel("생일:");
		lblBirth.setBounds(209, 206, 34, 15);
		contentPanel.add(lblBirth);
		
		ckBirthType = new JCheckBox("음력(기본값: 양력):");
		ckBirthType.setBounds(255, 229, 141, 23);
		contentPanel.add(ckBirthType);
		
		JButton btnBirth = new JButton("...");// 생일 선택
		btnBirth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinCalendar winCalendar = new WinCalendar();
				winCalendar.setModal(true);
				winCalendar.setVisible(true);
				txtBirth.setText(winCalendar.getDate());
			}
		});
		btnBirth.setBounds(372, 202, 34, 23);
		contentPanel.add(btnBirth);
		
		txtBirth = new JTextField();
		txtBirth.setColumns(10);
		txtBirth.setBounds(255, 202, 105, 21);
		contentPanel.add(txtBirth);
		
		JButton btnAddressSearch = new JButton("주소검색");
		btnAddressSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strDoro = JOptionPane.showInputDialog("도로명을 입력하시오");
				WinDoro winDoro = new WinDoro(strDoro);
				winDoro.setModal(true);
				winDoro.setVisible(true);
				txtAddress.setText(winDoro.getAddress());
			}
		});
		btnAddressSearch.setBounds(321, 87, 85, 23);
		contentPanel.add(btnAddressSearch);
		
		JButton btnInsert = new JButton("회원등록");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					InsertMember();
					Clear();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnInsert.setBounds(146, 270, 97, 47);
		contentPanel.add(btnInsert);
		
		Calendar now = Calendar.getInstance();
		int curYear = now.get(Calendar.YEAR);
		for(int year = 1900; year<= curYear; year++) 
			cbYear.addItem(year);
			cbYear.setSelectedItem(curYear);
			
			JLabel lblNewLabel = new JLabel("-");
			lblNewLabel.setBounds(146, 150, 11, 15);
			contentPanel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("-");
			lblNewLabel_1.setBounds(265, 150, 11, 15);
			contentPanel.add(lblNewLabel_1);
		
	}
	protected void Clear() {
		txtName.setText("");
		txtAddress.setText("");
		txtTel2.setText("");
		txtTel3.setText("");
		txtCompany.setText("");
		txtBirth.setText("");
		rbMarriage1.setSelected(true);
		ckBirthType.setSelected(false);
		txtName.requestFocus();
	}


	protected void InsertMember() throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = 
				DriverManager.getConnection("jdbc:mysql://localhost:3306/hanbitdb",
				"root","12345"); // JDBC 연결
		Statement stmt = conn.createStatement();
		String sName = txtName.getText();
		String sPath = path;
		String sAddress = txtAddress.getText();
		String sTel = cbTel1.getSelectedItem().toString() + "-" + txtTel2.getText() + "-" + txtTel3.getText();
		String sCompany = txtCompany.getText();
		String sYear = cbYear.getSelectedItem().toString();
		String sBirth = txtBirth.getText();
		int bMarriage;
		if(rbMarriage1.isSelected())
			bMarriage = 0; // 미혼
		else
			bMarriage = 1; // 기혼
		
		int type;
		if(ckBirthType.isSelected())
			type = 1; // 음력
		else
			type = 0; // 양력
		
//		System.out.println(sName);
//		System.out.println(sAddress);
//		System.out.println(sTel);
//		System.out.println(sCompany);
//		System.out.println(sYear);
//		System.out.println(sBirth);
//		System.out.println(bMarriage);
//		System.out.println(type);
		
		
		String sql = "insert into addressbooktbl values(null,'" + sName + "','" + sPath + "','";
		sql = sql + sAddress + "','" + sTel + "','" + sCompany + "'," + sYear + ",";
		sql = sql + bMarriage + ",'" + sBirth + "'," + type + ")";
		System.out.println(sql);
		
		if(stmt.executeUpdate(sql)>0)
			System.out.println("회원 추가 완료!!!");
		
		conn.close();
		stmt.close();
	}
	
		
}
