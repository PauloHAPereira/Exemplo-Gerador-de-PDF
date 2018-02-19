package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import control.TelaPrincipalControl;
import model.FolderManagement;

public class Tela_Principal {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtAge;
	private TelaPrincipalControl screenControl = new TelaPrincipalControl();
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JLabel lblStatus = new JLabel("");
	private JScrollPane scrollPane = new JScrollPane();
	private JPanel panelDelet = new JPanel();
	private JRadioButton rdbtnM = new JRadioButton("M");
	private JRadioButton rdbtnF = new JRadioButton("F");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FolderManagement fM = new FolderManagement();
					fM.verifyFolder();
					Tela_Principal window = new Tela_Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tela_Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 507, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//========================================================================== JPanels ==================================
		JPanel panelRegister = new JPanel();
		panelRegister.setBounds(12, 41, 464, 129);
		panelRegister.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(panelRegister);
		panelRegister.setLayout(null);
		
		panelDelet.setBounds(12, 183, 464, 86);
		panelDelet.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(panelDelet);
		panelDelet.setLayout(null);
		
		//========================================================================== JTextField ===============================
		txtName = new JTextField();
		txtName.setBounds(83, 39, 369, 22);
		panelRegister.add(txtName);
		txtName.setColumns(10);
		
		txtAge = new JTextField();
		txtAge.setBounds(67, 77, 92, 22);
		panelRegister.add(txtAge);
		txtAge.setColumns(10);
		
		//==========================================================================  JRadioButtons  ==========================
		rdbtnM.setSelected(true);
		rdbtnM.setBounds(218, 76, 39, 25);
		panelRegister.add(rdbtnM);
		rdbtnM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnF.setSelected(false);
			}
		});
		
		rdbtnF.setBounds(264, 76, 35, 25);
		panelRegister.add(rdbtnF);
		rdbtnF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnM.setSelected(false);
			}
		});
		
		//==========================================================================  JLabels  ================================
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 35, 59, 25);
		panelRegister.add(lblName);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblSex = new JLabel("Sex:");
		lblSex.setBounds(171, 73, 39, 25);
		panelRegister.add(lblSex);
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(14, 73, 41, 25);
		panelRegister.add(lblAge);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblRegisterNewStudent = new JLabel("Register new student");
		lblRegisterNewStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterNewStudent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRegisterNewStudent.setBounds(127, 13, 195, 25);
		panelRegister.add(lblRegisterNewStudent);
		
		JLabel lblStudent = new JLabel("Student:");
		lblStudent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStudent.setBounds(12, 47, 92, 16);
		panelDelet.add(lblStudent);
		
		JLabel lblGeneration = new JLabel("Exemple generation pdf");
		lblGeneration.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeneration.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblGeneration.setBounds(12, 0, 464, 44);
		frame.getContentPane().add(lblGeneration);
		
		JLabel lblStatusName = new JLabel("Status:");
		lblStatusName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStatusName.setBounds(12, 304, 83, 25);
		frame.getContentPane().add(lblStatusName);
		
		JLabel lblDeleteStudent = new JLabel("Delete student");
		lblDeleteStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteStudent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDeleteStudent.setBounds(129, 13, 194, 22);
		panelDelet.add(lblDeleteStudent);
		
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStatus.setBounds(76, 304, 400, 25);
		frame.getContentPane().add(lblStatus);
		
		//==========================================================================  JCombobox  ==============================
		//update The combobox first time
		comboBox = screenControl.updateComboBox(comboBox);
		comboBox.setBounds(100, 44, 183, 22);
		panelDelet.add(comboBox);
		
		//==========================================================================  JScrollPane =============================
		scrollPane.setBounds(12, 361, 464, 171);
		frame.getContentPane().add(scrollPane);
		//Update the JScrollPanel (first time)
		scrollPane.setViewportView(screenControl.updateJTable());
		
		//==========================================================================  JButtons  ===============================
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(339, 83, 113, 33);
		panelRegister.add(btnRegister);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				try{
					int age = Integer.parseInt(txtAge.getText());
					boolean sex = true;
					if(rdbtnM.isSelected()){
						sex = true;
					}else if (rdbtnF.isSelected()){
						sex = false;
					}
					//register the new student and set the status
					lblStatus.setForeground(Color.black);
					lblStatus.setText(screenControl.registerButton(name, age, sex));
					//update the comboBox
			 		comboBox = screenControl.updateComboBox(comboBox);
					//update the JScrollPanel
					scrollPane.setViewportView(screenControl.updateJTable());
				}catch (NumberFormatException e1) {
					lblStatus.setForeground(Color.red);
					lblStatus.setText("Invalid entry in field 'Age'");
				}
			}
		});
	
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(339, 36, 113, 33);
		panelDelet.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//obtain the select item in the combobox
				String quem = (String) comboBox.getSelectedItem();
				//delet the select student and update the Status
				lblStatus.setText(screenControl.deletButton(quem));
				//update the JScrollPanel
				scrollPane.setViewportView(screenControl.updateJTable());
				//update the combobox
				comboBox = screenControl.updateComboBox(comboBox);
				FolderManagement fl = new FolderManagement();
				fl.verifyFolder();
			}
		});
		
		JButton btnGeneratePdf = new JButton("Generate PDF");
		btnGeneratePdf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGeneratePdf.setBounds(156, 545, 180, 25);
		frame.getContentPane().add(btnGeneratePdf);
		btnGeneratePdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				screenControl.generatePdfButton();
			}
		});
		
	}
}
