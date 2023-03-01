package GiaoDien;

public class StartScreen extends javax.swing.JFrame {

	public StartScreen() {
		initComponents();
	}

	private void initComponents() {

		StartScreen_jPanel = new javax.swing.JPanel();
		StartScreen_Quit = new javax.swing.JButton();
		StartScreen_Quit.setBounds(410, 530, 150, 60);
		StartScreen_Start = new javax.swing.JButton();
		StartScreen_Start.setBounds(230, 530, 150, 60);
		StartScreen_Tutorial = new javax.swing.JButton();
		StartScreen_Tutorial.setBounds(30, 530, 170, 60);
		BackgroundImage = new javax.swing.JLabel();
		BackgroundImage.setBounds(0, -49, 580, 620);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Trò chơi Mã đi tuần");
		setResizable(false);
		getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		StartScreen_Quit.setFont(new java.awt.Font("Arial", 1, 24));
		StartScreen_Quit.setText("QUIT");
		StartScreen_Quit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				StartScreen_QuitActionPerformed(evt);
			}
		});
		StartScreen_jPanel.setLayout(null);
		StartScreen_jPanel.add(StartScreen_Quit);

		StartScreen_Start.setFont(new java.awt.Font("Arial", 1, 24)); 
		StartScreen_Start.setText("START");
		StartScreen_Start.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				StartScreen_StartActionPerformed(evt);
			}
		});
		StartScreen_jPanel.add(StartScreen_Start);

		StartScreen_Tutorial.setFont(new java.awt.Font("Arial", 1, 24));
		StartScreen_Tutorial.setText("TUTORIAL");
		StartScreen_Tutorial.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				StartScreen_TutorialActionPerformed(evt);
			}
		});
		StartScreen_jPanel.add(StartScreen_Tutorial);

		BackgroundImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ficture/knight.png")));
		StartScreen_jPanel.add(BackgroundImage);

		getContentPane().add(StartScreen_jPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 620));

		pack();
	}
	private void StartScreen_QuitActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	private void StartScreen_StartActionPerformed(java.awt.event.ActionEvent evt) {
		Mode m = new Mode();
		m.setVisible(true);
	}

	private void StartScreen_TutorialActionPerformed(java.awt.event.ActionEvent evt) {
		Tutorial t = new Tutorial();
		t.setVisible(true);
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
			java.util.logging.Logger.getLogger(StartScreen.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(StartScreen.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(StartScreen.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(StartScreen.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new StartScreen().setVisible(true);
			}
		});
	}

	private javax.swing.JLabel BackgroundImage;
	private javax.swing.JButton StartScreen_Start;
	private javax.swing.JButton StartScreen_Tutorial;
	private javax.swing.JButton StartScreen_Quit;
	private javax.swing.JPanel StartScreen_jPanel;
}
