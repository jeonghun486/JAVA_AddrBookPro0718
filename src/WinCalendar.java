import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WinCalendar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMonth;
	JPanel panel_cal;
	private JLabel lblYear;
	private JTextField txtYear;
	private JButton btnNextMonth;
	private JButton btnPreviousMonth;
	private JButton btnNextYear;
	private JButton btnPreviousYear;
	String strDate = "9999-9-9";
	
	public String getDate() {
		return strDate;
	}
	
	int curYear;
	int curMonth;
	int curDay;	
	String selectDay;
	
	/**
	 * Create the dialog.
	 */
	public WinCalendar() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				strDate="선텍안함";
			}
		});
		setTitle("Calendar");
		setBounds(100, 100, 590, 395);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				btnPreviousYear = new JButton("<<");
				btnPreviousYear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int year = Integer.parseInt(txtYear.getText());
						int month = Integer.parseInt(txtMonth.getText());
						year--;
						txtYear.setText(Integer.toString(year));
						
						ViewCalendar();
					}
				});
				panel.add(btnPreviousYear);
			}
			{
				btnPreviousMonth = new JButton("<");
				btnPreviousMonth.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {						
						int year = Integer.parseInt(txtYear.getText());
						int month = Integer.parseInt(txtMonth.getText());
						month--;
						if(month==0) {
							year--;
							month=12;
						}
						txtYear.setText(Integer.toString(year));
						txtMonth.setText(Integer.toString(month));
						
						ViewCalendar();
					}
				});
				panel.add(btnPreviousMonth);
			}
			{
				lblYear = new JLabel("연도:");
				panel.add(lblYear);
			}
			{
				txtYear = new JTextField();
				txtYear.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(txtYear);
				txtYear.setColumns(10);
			}
			{
				JLabel lblMonth = new JLabel("월:");
				panel.add(lblMonth);
			}
			{
				txtMonth = new JTextField();
				txtMonth.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode()==KeyEvent.VK_ENTER) {
							ViewCalendar();
						}
					}
				});
				txtMonth.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(txtMonth);
				txtMonth.setColumns(10);
			}
			{
				btnNextMonth = new JButton(">");
				btnNextMonth.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {		
						int year = Integer.parseInt(txtYear.getText());
						int month = Integer.parseInt(txtMonth.getText());
						month++;
						if(month==13) {
							year++;
							month=1;
						}
						txtYear.setText(Integer.toString(year));
						txtMonth.setText(Integer.toString(month));
						
						ViewCalendar();
					}
				});
				panel.add(btnNextMonth);
			}
			{
				btnNextYear = new JButton(">>");
				btnNextYear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int year = Integer.parseInt(txtYear.getText());						
						year++;
						txtYear.setText(Integer.toString(year));
						
						ViewCalendar();
					}
				});
				panel.add(btnNextYear);
			}
		}
		{
			panel_cal = new JPanel();
			contentPanel.add(panel_cal, BorderLayout.CENTER);
			panel_cal.setLayout(new GridLayout(0, 7, 0, 0));
		}
		
		Calendar now = Calendar.getInstance();
		curYear = now.get(Calendar.YEAR);
		curMonth = now.get(Calendar.MONTH) + 1;
		curDay = now.get(Calendar.DAY_OF_MONTH);
		
		
		txtYear.setText(Integer.toString(curYear));
		txtMonth.setText(Integer.toString(curMonth));
		
		ViewCalendar();		
	}

	protected void ViewCalendar() {
		int[] Month = {31,28,31,30,31,30,31,31,30,31,30,31};
		String[] Yoil = {"일","월","화","수","목","금","토"};							
		int year = Integer.parseInt(txtYear.getText());
		int month = Integer.parseInt(txtMonth.getText());
		// 생성된 버튼 삭제
		Component [] componentList = panel_cal.getComponents();
		for(Component c: componentList) {
			if(c instanceof JButton)
				panel_cal.remove(c);
		}
		panel_cal.revalidate();
		panel_cal.repaint();
		
		// 버튼 생성
		for(int i=0;i<7;i++) {
			JButton btn = new JButton(Yoil[i]);
			if(i==0)
				btn.setForeground(Color.RED);
			else if(i==6)
				btn.setForeground(Color.BLUE);
			
			panel_cal.add(btn);
			panel_cal.revalidate();
		}
		// 1922년 1월의 시작은 일요일
		int sum=0;	
		
		// 직전 연도까지의 합을 구합니다.
		for(int y=1922 ; y<year ; y++) {
			if(y%4 == 0 && y%100 != 0 || y%400 == 0) //윤년인가?
				sum = sum + 366;
			else
				sum = sum + 365;
		}
			
		// 직전 달까지의 합을 구합니다.
		for(int i=0;i<month-1;i++)
			if(i==1 && year%4 == 0 && year%100 != 0 || year%400 == 0)
				sum = sum + ++Month[i];
			else
				sum = sum + Month[i];
		
		int start = 0;
		start = (sum + start) % 7;  // 0 : 1922년 1월의 시작위치
		
		int idx=0;
		for(int i=1;i<=start;i++){
			JButton btn = new JButton("");
			panel_cal.add(btn);
			panel_cal.revalidate();
			idx++;
		}		
		int lastDay = Month[month-1];
		// 2월이고 윤년이면 29일로 변경
		if(month==2 && year%4 == 0 && year%100 != 0 || year%400 == 0)
			lastDay++;
		
		for(int i=1 ; i <= lastDay ; i++) {
			idx = idx % 7;
			JButton btn = new JButton(Integer.toString(i));
			if(year == curYear && month == curMonth && i==curDay)
				btn.setBackground(Color.RED);
			if(idx==0)  // 일요일
				btn.setForeground(Color.RED);
			else if(idx==6) // 토요일
				btn.setForeground(Color.BLUE);
			panel_cal.add(btn);
			panel_cal.revalidate();
			idx++;
			
			btn.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {					
					// TODO Auto-generated method stub
					JButton btn1 = (JButton)e.getSource();
					btn1.setBackground(Color.YELLOW);
					
					String year = txtYear.getText();
					String month = txtMonth.getText();
					String day = btn1.getText();
					strDate = year + "-" + month + "-" + day;
//					selectDay = btn1.getText();	
					setVisible(false);
				}
			});
		}		
		txtMonth.setSelectionStart(0);
		txtMonth.setSelectionEnd(txtMonth.getText().length());
	}



}
