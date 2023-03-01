
package GiaoDien;

import Code.AppWindow;
import Code.PlayersTour;

import javax.swing.JFrame;


public class Mode extends javax.swing.JFrame {

	
	public Mode() {
		setResizable(false);
		initComponents();
	}

	private void initComponents() {

		Mode_jPanel = new javax.swing.JPanel();
		Mode_AutoPlay = new javax.swing.JButton();
		Mode_AutoPlay.setBounds(280, 540, 210, 60);
		Mode_AutoPlay.setBackground(getForeground());
		Mode_HumanPlay = new javax.swing.JButton();
		Mode_HumanPlay.setBounds(40, 540, 210, 60);
		Background = new javax.swing.JLabel();
		Background.setBounds(0, -49, 580, 620);
		setTitle("Trò chơi Mã đi tuần");
		getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		Mode_AutoPlay.setFont(new java.awt.Font("Arial", 1, 24)); 
		Mode_AutoPlay.setText("AUTO PLAY");
		Mode_AutoPlay.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Mode_AutoPlayActionPerformed(evt);
			}
		});
		Mode_jPanel.setLayout(null);
		Mode_jPanel.add(Mode_AutoPlay);

		Mode_HumanPlay.setFont(new java.awt.Font("Arial", 1, 24));
		Mode_HumanPlay.setText("HUMAN PLAY");
		Mode_HumanPlay.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Mode_HumanPlayActionPerformed(evt);
			}
		});
		Mode_jPanel.add(Mode_HumanPlay);

		Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ficture/knight.png"))); 
		Mode_jPanel.add(Background);

		getContentPane().add(Mode_jPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 620));

		pack();
	}

	private void Mode_AutoPlayActionPerformed(java.awt.event.ActionEvent evt) {
		AppWindow a = new AppWindow();
		a.setVisible(true);
	}

	private void Mode_HumanPlayActionPerformed(java.awt.event.ActionEvent evt) {
		PlayersTour b = new PlayersTour();
		b.setVisible(true);
	}

	public static void main(String args[]) {
		
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Mode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Mode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Mode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Mode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Mode().setVisible(true);
			}
		});
	}

	private javax.swing.JButton Mode_HumanPlay;
	private javax.swing.JButton Mode_AutoPlay;
	private javax.swing.JLabel Background;
	private javax.swing.JPanel Mode_jPanel;

}
