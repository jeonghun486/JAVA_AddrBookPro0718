import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinMain extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinMain dialog = new WinMain();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinMain() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(WinMain.class.getResource("/images/AddressBook.png")));
		setTitle("동창회 주소록");
		setBounds(100, 100, 279, 299);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton btnMemberInsert = new JButton("");
		btnMemberInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinMemberInsert winMemberInsert = new WinMemberInsert();
				winMemberInsert.setModal(true);
				winMemberInsert.setVisible(true);
			}
		});
		btnMemberInsert.setIcon(new ImageIcon(WinMain.class.getResource("/images/MemberInsert.png")));
		contentPanel.add(btnMemberInsert);
		
		JButton btnMemberUpdate = new JButton("");
		btnMemberUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinUpdateChoice winUpdateChoice = new WinUpdateChoice();
				winUpdateChoice.setModal(true);
				winUpdateChoice.setVisible(true);
			}
		});
		btnMemberUpdate.setIcon(new ImageIcon(WinMain.class.getResource("/images/MemberUpdate.png")));
		contentPanel.add(btnMemberUpdate);
		
		JButton btnMemberDelete = new JButton("");
		btnMemberDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinDeleteChoice winDeleteChoice = new WinDeleteChoice();
				winDeleteChoice.setModal(true);
				winDeleteChoice.setVisible(true);
			}
		});
		btnMemberDelete.setIcon(new ImageIcon(WinMain.class.getResource("/images/MemberDelete.png")));
		contentPanel.add(btnMemberDelete);
		
		JButton btnMemberAllShow = new JButton("");
		btnMemberAllShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinMemberAllShow winMemberAllShow;
				
				try {
					winMemberAllShow = new WinMemberAllShow();
					winMemberAllShow.setModal(true);
					winMemberAllShow.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnMemberAllShow.setIcon(new ImageIcon(WinMain.class.getResource("/images/MemberShow.png")));
		contentPanel.add(btnMemberAllShow);
	}

}
