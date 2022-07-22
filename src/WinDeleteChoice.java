import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;

public class WinDeleteChoice extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSearch;
	JRadioButton rbName;
	JRadioButton rbTel;
	JRadioButton rbHakhun;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinDeleteChoice dialog = new WinDeleteChoice();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinDeleteChoice() {
		setTitle("\uD68C\uC6D0 \uD0C8\uD1F4");
		setBounds(100, 100, 183, 213);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			rbName = new JRadioButton("\uC774\uB984");
			buttonGroup.add(rbName);
			rbName.setBounds(8, 30, 78, 23);
			contentPanel.add(rbName);
		}
		{
			rbTel = new JRadioButton("\uC804\uD654\uBC88\uD638");
			buttonGroup.add(rbTel);
			rbTel.setBounds(8, 55, 78, 23);
			contentPanel.add(rbTel);
		}
		{
			rbHakhun = new JRadioButton("\uD559\uBC88");
			buttonGroup.add(rbHakhun);
			rbHakhun.setBounds(8, 80, 78, 23);
			contentPanel.add(rbHakhun);
		}
		{
			txtSearch = new JTextField();
			txtSearch.setBounds(8, 109, 116, 21);
			contentPanel.add(txtSearch);
			txtSearch.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String transferData = "";
						if(rbName.isSelected())
							transferData = "n" + txtSearch.getText();
						else if(rbTel.isSelected()) {
							String temp = txtSearch.getText();
							temp = temp.replaceAll("-","");
							transferData = "t" + txtSearch.getText();
						}
						else
							transferData = "h" + txtSearch.getText();;
					
							WinDelete winDelete;
							try {
								winDelete = new WinDelete(transferData);
								winDelete.setModal(true);
								winDelete.setVisible(true);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
