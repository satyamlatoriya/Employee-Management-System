package com.Avnish_Sanket.EMS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class Employee {


	private JFrame frame;
	private JTable table;
	private JTextField textField1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	Connection connection=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	DefaultTableModel model= new DefaultTableModel();
	protected String sql;

	
	public void UpdateTable()
	{
		connection = EmployeeData.connectDB();
		
		if(connection != null) {
			
		}
		try
		{
			
			String sql = null ;
			pst=connection.prepareStatement(sql);
			rs = pst.executeQuery();
			Object[] columnData = new Object[4];
			
			while(rs.next())
			{
				columnData [0] = rs.getString("EmpID");
				columnData [1] = rs.getString("firstName");
				columnData [2] = rs.getString("surName");
				columnData [3] = rs.getString("Age");
				
				model.addRow(columnData);
				
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee window = new Employee();
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
	
	public Employee() {
		initialize();
		
		connection = EmployeeData.connectDB();
		Object col[] = {"EmpID","firstName","surName","Age"};
		model.setColumnIdentifiers(col);
		
	}
	
		
		
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(176, 196, 222));
		frame.setBounds(0, 0, 850, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			private JLabel lblAge;
			private JLabel lblSurName;
			private JLabel lblFirstName;
			private JLabel lblEmpID;
			
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row =table.getSelectedRow();
					String click=table.getModel().getValueAt(row,0).toString();
					pst=connection.prepareStatement(click);
					rs = pst.executeQuery();
					if(rs.next()) {
						String add1=rs.getString("EmpID");
						
						lblEmpID.setText(add1);
						String add2=rs.getString("firstName");
					
						lblFirstName.setText(add2);
						String add3=rs.getString("surName");
						
						lblSurName.setText(add3);
						String add4=rs.getString("Age");
					
						lblAge.setText(add4);
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}

			
			}	
		});
		scrollPane.setBounds(363, 50, 443, 359);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"EmpID", "firstName", "surName", "Age"
			}
		));

		try {
			Connection connection = EmployeeData.connectDB();
			String sql="select * from Employee";
			Statement st=connection.createStatement();

			
			rs = st.executeQuery(sql);

			DefaultTableModel model= (DefaultTableModel) table.getModel();
			while(rs.next()) {
				System.out.print(rs.getString(1));
				model.addRow(new Object[] {
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						
				});
			}

		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null,e1);
			
		}
	
		table.setFont(new Font("Tahoma", Font.PLAIN, 10));
		table.setBackground(new Color(204, 255, 204));
		scrollPane.setViewportView(table);
		textField1 = new JTextField();
		textField1.setBounds(117, 181, 148, 19);
		frame.getContentPane().add(textField1);
		textField1.setBackground(Color.WHITE);
		textField1.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(117, 217, 148, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(117, 258, 148, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(117, 304, 148, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
	   JTextField textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_4.setColumns(10);
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				try {
					String sql="insert into Employee(EmpID,firstName,surName,Age) values (?,?,?,?)";
					pst=connection.prepareStatement(sql);
					
					pst.setString(1,textField1.getText()) ;
					pst.setString(2,textField_1.getText());
					pst.setString(3,textField_2.getText());
					pst.setString(4,textField_3.getText());
					
					pst.execute();

					pst.close();
					JOptionPane.showMessageDialog(null,"Added");
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null,e1);
					
				}
				DefaultTableModel model= (DefaultTableModel) table.getModel();
				
				model.addRow(new Object[] {
						textField1.getText() ,
						textField_1.getText(),
						textField_2.getText(),
						textField_3.getText(),
						
				});
				if(table.getSelectedRow()==-1) {
					if(table.getRowCount()==0) {
						JOptionPane.showMessageDialog(null,"Updated Successfully..","Employee data",JOptionPane.OK_OPTION);
					}
				}
					
			}
		});
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAdd.setBounds(10, 446, 101, 29);
		frame.getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				try
				{
				String value1 =	textField1.getText();
				String value2 =	textField_1.getText();
				String value3 =   textField_2.getText();
				String value4 =	textField_3.getText();
			    String sql="update Employee set firstName='"+value2+"',surName='"+value3+"',Age='"+value4+"' where EmpID='"+value1+"'";
			    
			    pst=connection.prepareStatement(sql);
			    
				pst.execute();
				
				JOptionPane.showMessageDialog(null,"Updated");
				
					
				String sql1="select * from Employee";
				Statement st=connection.createStatement();
				pst.setString(4,textField_3.getText());
				
				rs = st.executeQuery(sql1);
		
				DefaultTableModel model= (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				while(rs.next()) {
					
					model.addRow(new Object[] {
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							
					});
				}

					
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
					
				}
		}
			
	});
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUpdate.setBounds(147, 446, 104, 29);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				int p =JOptionPane.showConfirmDialog(null, "Do you really want to Delete","Delete",JOptionPane.YES_NO_OPTION);
				
				if(p==0) {
					String sql="delete from Employee where EmpID=?";
				
				
				try {
					pst=connection.prepareStatement(sql);
					pst.setString(1, textField1.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null, "Deleted Successfully");
					

				
								
					
					}
					
				
						
				catch(Exception ev)
				{
					JOptionPane.showMessageDialog(null, ev);
					
				}
				}
			}
			});
	
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelete.setBounds(286, 446, 101, 29);
		frame.getContentPane().add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(Color.WHITE);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame,"confirm if you want to exit",
						"Employee Management System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
			System.exit(0);
		}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExit.setBounds(419, 446, 104, 29);
		frame.getContentPane().add(btnExit);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.WHITE);
		separator.setBounds(20, 419, 816, 4);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 43, 816, 4);
		frame.getContentPane().add(separator_1);
		
		JLabel lblEmpID = new JLabel("EmpID");
		lblEmpID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmpID.setBounds(10, 184, 77, 16);
		frame.getContentPane().add(lblEmpID);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFirstName.setBounds(10, 217, 97, 19);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblSurName = new JLabel("Sur Name");
		lblSurName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSurName.setBounds(10, 261, 77, 16);
		frame.getContentPane().add(lblSurName);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAge.setBounds(20, 302, 57, 18);
		frame.getContentPane().add(lblAge);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 499, 816, 4);
		frame.getContentPane().add(separator_2);
		
	}
			}
			
				
			
			
		
		
		
		
	
			



