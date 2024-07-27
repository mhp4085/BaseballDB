import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.ScrollPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField value1;
	private JTextField value2;
	private JTextField value3;
	private JTextField value4;
	private JTextField value5;
	private JTable output;
	private DefaultTableModel model;
	private JTextField loRange2;
	private JTextField hiRange2;
	private JTextField loRange3;
	private JTextField hiRange3;
	private JTextField loRange4;
	private JTextField hiRange4;
	private JTextField loRange5;
	private JTextField hiRange5;
	private JTextField loRange1;
	private JTextField hiRange1;
	
	public static DBC data;
	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		data = new DBC();
		data.readDataBase();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application frame = new Application();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	
	// turns resultSet into a form that can be put into the jtable
    private static List<List<String>> resultsArrayList(ResultSet resultSet) throws SQLException {
        List<List<String>> data = new ArrayList<>();

        int columnCount = resultSet.getMetaData().getColumnCount();

        while (resultSet.next()) {
            List<String> rowData = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                rowData.add(resultSet.getString(i));
            }
            data.add(rowData);
        }

        return data;
    }
    
    public String checkNum (String s) {
    	if (s.matches("^[0-9]+$")) return s;
    	else return "\"" + s + "\"";
    }

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Application() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 662);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Table");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(33, 46, 96, 31);
		getContentPane().add(lblNewLabel);
		
		ArrayList<String> cols = data.getCols(data.getResultSet());
		
		JComboBox<String> filter1 = new JComboBox();
		filter1.setBounds(89, 177, 132, 22);
		getContentPane().add(filter1);
		
		JComboBox<String> filter2 = new JComboBox();
		filter2.setBounds(89, 202, 132, 22);
		getContentPane().add(filter2);
		
		JComboBox<String> filter3 = new JComboBox();
		filter3.setBounds(89, 227, 132, 22);
		getContentPane().add(filter3);
		
		JComboBox<String> filter4 = new JComboBox();
		filter4.setBounds(89, 252, 132, 22);
		getContentPane().add(filter4);
		
		JComboBox<String> filter5 = new JComboBox();
		filter5.setBounds(89, 277, 132, 22);
		getContentPane().add(filter5);
		
		JComboBox tableSelect = new JComboBox();
		tableSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			        ArrayList<String> cols;
			        if (tableSelect.getSelectedItem().equals("batting")) {
			            try {
			            	data.setResultSet(data.getStatement().executeQuery("select * from baseball.batting;"));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        } else {
			            try {
			            	data.setResultSet(data.getStatement().executeQuery("select * from baseball.fielding;"));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        }

					try {
						cols = data.getCols(data.getResultSet());

				        filter1.removeAllItems();
				        filter2.removeAllItems();
				        filter3.removeAllItems();
				        filter4.removeAllItems();
				        filter5.removeAllItems();
				        for (int i = 0; i < cols.size(); i++) {
				            filter1.addItem(cols.get(i));
				            filter2.addItem(cols.get(i));
				            filter3.addItem(cols.get(i));
				            filter4.addItem(cols.get(i));
				            filter5.addItem(cols.get(i));
				        }
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			}
		});
		tableSelect.setBounds(139, 50, 145, 22);
		getContentPane().add(tableSelect);
		tableSelect.addItem("batting");
		tableSelect.addItem("fielding");
		
		
		JLabel lblNewLabel_1 = new JLabel("Filter 1");
		lblNewLabel_1.setBounds(33, 181, 49, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Filter 2");
		lblNewLabel_1_1.setBounds(33, 206, 49, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Filter 3");
		lblNewLabel_1_2.setBounds(33, 231, 49, 14);
		getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Filter 4");
		lblNewLabel_1_3.setBounds(33, 256, 49, 14);
		getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Filter 5");
		lblNewLabel_1_4.setBounds(33, 281, 49, 14);
		getContentPane().add(lblNewLabel_1_4);
		

		
		for (int i = 0; i < cols.size(); i++) {
			filter1.addItem(cols.get(i));
			filter2.addItem(cols.get(i));
			filter3.addItem(cols.get(i));
			filter4.addItem(cols.get(i));
			filter5.addItem(cols.get(i));
		}
		
		JLabel lblNewLabel_2 = new JLabel("with value");
		lblNewLabel_2.setBounds(257, 181, 76, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("with value");
		lblNewLabel_2_1.setBounds(257, 206, 76, 14);
		getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("with value");
		lblNewLabel_2_2.setBounds(257, 231, 76, 14);
		getContentPane().add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("with value");
		lblNewLabel_2_3.setBounds(257, 256, 76, 14);
		getContentPane().add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("with value");
		lblNewLabel_2_4.setBounds(257, 281, 76, 14);
		getContentPane().add(lblNewLabel_2_4);
		
		value1 = new JTextField();
		value1.setBounds(378, 178, 96, 20);
		getContentPane().add(value1);
		value1.setColumns(10);
		
		value2 = new JTextField();
		value2.setColumns(10);
		value2.setBounds(378, 203, 96, 20);
		getContentPane().add(value2);
		
		value3 = new JTextField();
		value3.setColumns(10);
		value3.setBounds(378, 228, 96, 20);
		getContentPane().add(value3);
		
		value4 = new JTextField();
		value4.setColumns(10);
		value4.setBounds(378, 253, 96, 20);
		getContentPane().add(value4);
		
		value5 = new JTextField();
		value5.setColumns(10);
		value5.setBounds(378, 278, 96, 20);
		getContentPane().add(value5);
		
		JLabel lblNewLabel_3 = new JLabel("or in range");
		lblNewLabel_3.setBounds(552, 181, 84, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("or in range");
		lblNewLabel_3_1.setBounds(552, 206, 84, 14);
		getContentPane().add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("or in range");
		lblNewLabel_3_2.setBounds(552, 231, 84, 14);
		getContentPane().add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("or in range");
		lblNewLabel_3_3.setBounds(552, 256, 84, 14);
		getContentPane().add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3_4 = new JLabel("or in range");
		lblNewLabel_3_4.setBounds(552, 281, 84, 14);
		getContentPane().add(lblNewLabel_3_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("[                                   ,                                   ]");
		lblNewLabel_4_1.setBounds(688, 206, 246, 14);
		getContentPane().add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("[                                   ,                                   ]");
		lblNewLabel_4_2.setBounds(688, 231, 246, 14);
		getContentPane().add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4_3 = new JLabel("[                                   ,                                   ]");
		lblNewLabel_4_3.setBounds(688, 256, 246, 14);
		getContentPane().add(lblNewLabel_4_3);
		
		JLabel lblNewLabel_4_4 = new JLabel("[                                   ,                                   ]");
		lblNewLabel_4_4.setBounds(688, 281, 246, 14);
		getContentPane().add(lblNewLabel_4_4);
		
		JButton queryBtn = new JButton("Query");
		queryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userQuery = "SELECT * FROM " + "baseball." + tableSelect.getSelectedItem().toString();
				if (value1.getText().isEmpty() && value5.getText().isEmpty() &&
						value2.getText().isEmpty() && value4.getText().isEmpty() &&
						value3.getText().isEmpty() && loRange1.getText().isEmpty()
						&& hiRange1.getText().isEmpty() && loRange2.getText().isEmpty()
						&& hiRange2.getText().isEmpty() && loRange3.getText().isEmpty()
						&& hiRange3.getText().isEmpty() && loRange4.getText().isEmpty()
						&& hiRange4.getText().isEmpty() && loRange5.getText().isEmpty()
						&& hiRange5.getText().isEmpty() ) {
					try {
						data.readDataBase();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					userQuery += " WHERE ";
					int itemCt = 0;
					if (!filter1.getSelectedItem().toString().isEmpty() && !value1.getText().isEmpty()) {
						if (itemCt >= 1) userQuery += " AND ";
						userQuery += filter1.getSelectedItem().toString() + " = " + checkNum(value1.getText());
						++itemCt;
					} else if (!filter1.getSelectedItem().toString().isEmpty() && !loRange1.getText().isEmpty() && !hiRange1.getText().isEmpty()) {
						if (itemCt >= 1) userQuery += " AND ";
						userQuery += filter1.getSelectedItem().toString() + " >= " + checkNum(loRange1.getText()) + " AND " +
								filter1.getSelectedItem().toString()+ " <= " + checkNum(hiRange1.getText());
					} else if (!filter2.getSelectedItem().toString().isEmpty() && !value2.getText().isEmpty()) {
						if (itemCt >= 1) userQuery += " AND "; 
						userQuery += filter2.getSelectedItem().toString() + " = " + checkNum(value2.getText());
						++itemCt;
					} else if (!filter2.getSelectedItem().toString().isEmpty() && !loRange2.getText().isEmpty() && !hiRange2.getText().isEmpty()) {
						if (itemCt >= 1) userQuery += " AND ";
						userQuery += filter2.getSelectedItem().toString() + " >= " + checkNum(loRange2.getText()) + " AND " +
								filter2.getSelectedItem().toString()+ " <= " + checkNum(hiRange2.getText());
					} else if (!filter3.getSelectedItem().toString().isEmpty() && !value3.getText().isEmpty()) {
						if (itemCt >= 1) userQuery += " AND ";
						userQuery += filter3.getSelectedItem().toString() + " = " + checkNum(value3.getText());
						++itemCt;
					} else if (!filter3.getSelectedItem().toString().isEmpty() && !loRange3.getText().isEmpty() && !hiRange3.getText().isEmpty()) {
						if (itemCt >= 1) userQuery += " AND ";
						userQuery += filter3.getSelectedItem().toString() + " >= " + checkNum(loRange3.getText()) + " AND " +
								filter3.getSelectedItem().toString()+ " <= " + checkNum(hiRange3.getText());
					} else if (!filter4.getSelectedItem().toString().isEmpty() && !value4.getText().isEmpty()) {
						if (itemCt >= 1) userQuery += " AND ";
						userQuery += filter4.getSelectedItem().toString() + " = " + checkNum(value4.getText());
						++itemCt;
					} else if (!filter4.getSelectedItem().toString().isEmpty() && !loRange4.getText().isEmpty() && !hiRange4.getText().isEmpty()) {
						if (itemCt >= 1) userQuery += " AND ";
						userQuery += filter4.getSelectedItem().toString() + " >= " + checkNum(loRange4.getText()) + " AND " +
								filter4.getSelectedItem().toString()+ " <= " + checkNum(hiRange4.getText());
					} else if (!filter5.getSelectedItem().toString().isEmpty() && !value5.getText().isEmpty()) {
						if (itemCt >= 1) userQuery += " AND ";
						userQuery += filter5.getSelectedItem().toString() + " = " + checkNum(value5.getText());
						++itemCt;
					} else if (!filter5.getSelectedItem().toString().isEmpty() && !loRange5.getText().isEmpty() && !hiRange5.getText().isEmpty()) {
						if (itemCt >= 1) userQuery += " AND ";
						userQuery += filter5.getSelectedItem().toString() + " >= " + checkNum(loRange5.getText()) + " AND " +
								filter5.getSelectedItem().toString()+ " <= " + checkNum(hiRange5.getText());
					}
					
				}
				userQuery += " ORDER BY playerID, yearID, teamID;";

				System.out.println(userQuery);
				try {
					data.setResultSet(data.getStatement().executeQuery(userQuery));
//					while (data.getResultSet().next()) {
//						System.out.println(data.getResultSet().getInt("yearID"));
//					}
					model = new DefaultTableModel();
					model.setColumnIdentifiers(data.getCols(data.getResultSet()).toArray());
					List<List<String>> resList = resultsArrayList(data.getResultSet());
					for (List<String> row : resList) {
						model.addRow(row.toArray());
					}
					output.setModel(model);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
						
		queryBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		queryBtn.setBounds(705, 41, 185, 37);
		getContentPane().add(queryBtn);
		
		JLabel lblNewLabel_5 = new JLabel("Analysis");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(33, 336, 49, 14);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Select the best");
		lblNewLabel_6.setBounds(125, 336, 96, 14);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("in position");
		lblNewLabel_7.setBounds(378, 336, 76, 14);
		getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("on team");
		lblNewLabel_8.setBounds(642, 336, 49, 14);
		getContentPane().add(lblNewLabel_8);
		
		JComboBox selectBest = new JComboBox();
		selectBest.setBounds(218, 332, 132, 22);
		getContentPane().add(selectBest);
		selectBest.addItem("batter (AVG)");
		selectBest.addItem("batter (RBI)");
		selectBest.addItem("batter (SLG)");
		
		JComboBox selectPos = new JComboBox();
		selectPos.setBounds(476, 332, 104, 22);
		getContentPane().add(selectPos);
		selectPos.addItem("");
		selectPos.addItem("P");
		selectPos.addItem("C");
		selectPos.addItem("1B");
		selectPos.addItem("2B");
		selectPos.addItem("3B");
		selectPos.addItem("LF");
		selectPos.addItem("CF");
		selectPos.addItem("RF");
		selectPos.addItem("SS");
		
		JComboBox selectTeam = new JComboBox();
		selectTeam.setBounds(720, 332, 104, 22);
		getContentPane().add(selectTeam);
		 String[] teams = {"",
		 "ARI", // Arizona Diamondbacks
         "ATL", // Atlanta Braves
         "BAL", // Baltimore Orioles
         "BOS", // Boston Red Sox
         "CWS", // Chicago White Sox
         "CHC", // Chicago Cubs
         "CIN", // Cincinnati Reds
         "CLE", // Cleveland Guardians
         "COL", // Colorado Rockies
         "DET", // Detroit Tigers
         "HOU", // Houston Astros
         "KC",  // Kansas City Royals
         "LAA", // Los Angeles Angels
         "LAD", // Los Angeles Dodgers
         "MIA", // Miami Marlins
         "MIL", // Milwaukee Brewers
         "MIN", // Minnesota Twins
         "NYY", // New York Yankees
         "NYM", // New York Mets
         "OAK", // Oakland Athletics
         "PHI", // Philadelphia Phillies
         "PIT", // Pittsburgh Pirates
         "SD",  // San Diego Padres
         "SF",  // San Francisco Giants
         "SEA", // Seattle Mariners
         "STL", // St. Louis Cardinals
         "TB",  // Tampa Bay Rays
         "TEX", // Texas Rangers
         "TOR", // Toronto Blue Jays
         "WSH"  // Washington Nationals
		 };
		 for (String t : teams)
			 selectTeam.addItem(t);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 375, 1151, 224);
		getContentPane().add(scrollPane);
		
		output = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		scrollPane.setViewportView(output);
		
		loRange2 = new JTextField();
		loRange2.setColumns(10);
		loRange2.setBounds(709, 203, 71, 20);
		getContentPane().add(loRange2);
		
		hiRange2 = new JTextField();
		hiRange2.setColumns(10);
		hiRange2.setBounds(819, 203, 71, 20);
		getContentPane().add(hiRange2);
		
		loRange3 = new JTextField();
		loRange3.setColumns(10);
		loRange3.setBounds(709, 228, 71, 20);
		getContentPane().add(loRange3);
		
		hiRange3 = new JTextField();
		hiRange3.setColumns(10);
		hiRange3.setBounds(819, 228, 71, 20);
		getContentPane().add(hiRange3);
		
		loRange4 = new JTextField();
		loRange4.setColumns(10);
		loRange4.setBounds(709, 253, 71, 20);
		getContentPane().add(loRange4);
		
		hiRange4 = new JTextField();
		hiRange4.setColumns(10);
		hiRange4.setBounds(819, 253, 71, 20);
		getContentPane().add(hiRange4);
		
		loRange5 = new JTextField();
		loRange5.setColumns(10);
		loRange5.setBounds(709, 278, 71, 20);
		getContentPane().add(loRange5);
		
		hiRange5 = new JTextField();
		hiRange5.setColumns(10);
		hiRange5.setBounds(819, 278, 71, 20);
		getContentPane().add(hiRange5);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("[                                   ,                                   ]");
		lblNewLabel_4_1_1.setBounds(688, 181, 246, 14);
		getContentPane().add(lblNewLabel_4_1_1);
		
		loRange1 = new JTextField();
		loRange1.setColumns(10);
		loRange1.setBounds(709, 178, 71, 20);
		getContentPane().add(loRange1);
		
		hiRange1 = new JTextField();
		hiRange1.setColumns(10);
		hiRange1.setBounds(819, 178, 71, 20);
		getContentPane().add(hiRange1);
		
		JButton createTableBtn = new JButton("Create Table");
		createTableBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (selectBest.getSelectedItem().toString().equals("batter (AVG)")) {
		        	model.setRowCount(0);
		        	int queryCount = 0;
		            try {
		            	String userQuery = "";
		            	userQuery += "SELECT baseball.batting.playerID, " +
		                        "GROUP_CONCAT(DISTINCT " +
		                        "CASE " +
		                        "  WHEN baseball.fielding.POS = 'C' THEN 'CATCHER' " +
		                        "  WHEN baseball.fielding.POS = 'CF' THEN 'CENTERFIELD' " +
		                        "  ELSE baseball.fielding.POS " +
		                        "END " +
		                        " ORDER BY baseball.fielding.POS) AS Positions, " +
		                        "MAX(baseball.batting.yearID) AS yearID, " +
		                        "MAX(baseball.batting.stint) AS stint, " +
		                        "MAX(baseball.batting.teamID) AS teamID, " +
		                        "MAX(baseball.batting.lgID) AS lgID, " +
		                        "MAX(baseball.batting.G) AS G, " +
		                        "MAX(baseball.batting.AB) AS AB, " +
		                        "MAX(baseball.batting.R) AS R, " +
		                        "MAX(baseball.batting.H) AS H, " +
		                        "MAX(baseball.batting.`2B`) AS `2B`, " +
		                        "MAX(baseball.batting.`3B`) AS `3B`, " +
		                        "MAX(baseball.batting.HR) AS HR, " +
		                        "MAX(baseball.batting.RBI) AS RBI, " +
		                        "ROUND ((MAX(baseball.batting.H) + MAX(baseball.batting.`2B`) + 2 * MAX(baseball.batting.`3B`) + 3 * MAX(baseball.batting.HR)) / NULLIF(MAX(baseball.batting.AB), 0), 3) AS SLG, " +
		                        "ROUND ((MAX(baseball.batting.H) / NULLIF(MAX(baseball.batting.AB), 0)), 3) AS bAVG " +
		                        "FROM baseball.fielding " +
		                        "INNER JOIN baseball.batting " +
		                        "ON baseball.fielding.playerID = baseball.batting.playerID ";
		            	if (!selectPos.getSelectedItem().toString().isEmpty()) {
		            		userQuery += " WHERE POS LIKE CONCAT('%', '" + selectPos.getSelectedItem().toString() + "', '%') ";
		            		++queryCount;
		            	}
		            	if (!selectTeam.getSelectedItem().toString().isEmpty()) {
		            		if (queryCount >= 1) userQuery += " AND ";
		            		else userQuery += " WHERE ";
		            		userQuery += "batting.teamID = '" + selectTeam.getSelectedItem().toString() + "'";
		            	}
		            	userQuery += " GROUP BY baseball.batting.playerID " +
                        " ORDER BY bAVG DESC;";
						data.setResultSet(data.getStatement().executeQuery(userQuery));
						model.setColumnIdentifiers(data.getCols(data.getResultSet()).toArray());
						List<List<String>> resList = resultsArrayList(data.getResultSet());
						for (List<String> row : resList) {
							model.addRow(row.toArray());
						}
						output.setModel(model);
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            }
		        } else if(selectBest.getSelectedItem().toString().equals("batter (RBI)")) {
		        	model.setRowCount(0);
		        	int queryCount = 0;
		            try {
		            	String userQuery = "";
		            	userQuery += userQuery += "SELECT baseball.batting.playerID, " +
		                        "GROUP_CONCAT(DISTINCT " +
		                        "CASE " +
		                        "  WHEN baseball.fielding.POS = 'C' THEN 'CATCHER' " +
		                        "  WHEN baseball.fielding.POS = 'CF' THEN 'CENTERFIELD' " +
		                        "  ELSE baseball.fielding.POS " +
		                        "END " +
		                        " ORDER BY baseball.fielding.POS) AS Positions, " +
		                        "MAX(baseball.batting.yearID) AS yearID, " +
		                        "MAX(baseball.batting.stint) AS stint, " +
		                        "MAX(baseball.batting.teamID) AS teamID, " +
		                        "MAX(baseball.batting.lgID) AS lgID, " +
		                        "MAX(baseball.batting.G) AS G, " +
		                        "MAX(baseball.batting.AB) AS AB, " +
		                        "MAX(baseball.batting.R) AS R, " +
		                        "MAX(baseball.batting.H) AS H, " +
		                        "MAX(baseball.batting.`2B`) AS `2B`, " +
		                        "MAX(baseball.batting.`3B`) AS `3B`, " +
		                        "MAX(baseball.batting.HR) AS HR, " +
		                        "MAX(baseball.batting.RBI) AS RBI, " +
		                        "ROUND ((MAX(baseball.batting.H) + MAX(baseball.batting.`2B`) + 2 * MAX(baseball.batting.`3B`) + 3 * MAX(baseball.batting.HR)) / NULLIF(MAX(baseball.batting.AB), 0), 3) AS SLG, " +
		                        "ROUND ((MAX(baseball.batting.H) / NULLIF(MAX(baseball.batting.AB), 0)), 3) AS bAVG " +
		                        "FROM baseball.fielding " +
		                        "INNER JOIN baseball.batting " +
		                        "ON baseball.fielding.playerID = baseball.batting.playerID ";
		            	if (!selectPos.getSelectedItem().toString().isEmpty()) {
		            		userQuery += " WHERE POS LIKE CONCAT('%', '" + selectPos.getSelectedItem().toString() + "', '%') ";
		            		++queryCount;
		            	}
		            	if (!selectTeam.getSelectedItem().toString().isEmpty()) {
		            		
		            		if (queryCount >= 1) userQuery += " AND ";
		            		else userQuery += " WHERE ";
		            		userQuery += "batting.teamID = '" + selectTeam.getSelectedItem().toString() + "'";
		            	}
		            	userQuery += " GROUP BY baseball.batting.playerID " +
                        " ORDER BY RBI DESC;";
						data.setResultSet(data.getStatement().executeQuery(userQuery));
						model.setColumnIdentifiers(data.getCols(data.getResultSet()).toArray());
						List<List<String>> resList = resultsArrayList(data.getResultSet());
						for (List<String> row : resList) {
							model.addRow(row.toArray());
						}
						output.setModel(model);
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            }
		        } else if (selectBest.getSelectedItem().toString().equals("batter (SLG)")) {
		        	model.setRowCount(0);
		        	int queryCount = 0;
		            try {
		            	String userQuery = "";
		            	userQuery += "SELECT baseball.batting.playerID, " +
		                        "GROUP_CONCAT(DISTINCT " +
		                        "CASE " +
		                        "  WHEN baseball.fielding.POS = 'C' THEN 'CATCHER' " +
		                        "  WHEN baseball.fielding.POS = 'CF' THEN 'CENTERFIELD' " +
		                        "  ELSE baseball.fielding.POS " +
		                        "END " +
		                        " ORDER BY baseball.fielding.POS) AS Positions, " +
		                        "MAX(baseball.batting.yearID) AS yearID, " +
		                        "MAX(baseball.batting.stint) AS stint, " +
		                        "MAX(baseball.batting.teamID) AS teamID, " +
		                        "MAX(baseball.batting.lgID) AS lgID, " +
		                        "MAX(baseball.batting.G) AS G, " +
		                        "MAX(baseball.batting.AB) AS AB, " +
		                        "MAX(baseball.batting.R) AS R, " +
		                        "MAX(baseball.batting.H) AS H, " +
		                        "MAX(baseball.batting.`2B`) AS `2B`, " +
		                        "MAX(baseball.batting.`3B`) AS `3B`, " +
		                        "MAX(baseball.batting.HR) AS HR, " +
		                        "MAX(baseball.batting.RBI) AS RBI, " +
		                        "ROUND ((MAX(baseball.batting.H) + MAX(baseball.batting.`2B`) + 2 * MAX(baseball.batting.`3B`) + 3 * MAX(baseball.batting.HR)) / NULLIF(MAX(baseball.batting.AB), 0), 3) AS SLG, " +
		                        "ROUND ((MAX(baseball.batting.H) / NULLIF(MAX(baseball.batting.AB), 0)), 3) AS bAVG " +
		                        "FROM baseball.fielding " +
		                        "INNER JOIN baseball.batting " +
		                        "ON baseball.fielding.playerID = baseball.batting.playerID ";
		            	if (!selectPos.getSelectedItem().toString().isEmpty()) {
		            		userQuery += " WHERE POS LIKE CONCAT('%', '" + selectPos.getSelectedItem().toString() + "', '%') ";
		            		++queryCount;
		            	}
		            	if (!selectTeam.getSelectedItem().toString().isEmpty()) {
		            		if (queryCount >= 1) userQuery += " AND ";
		            		else userQuery += " WHERE ";
		            		userQuery += "batting.teamID = '" + selectTeam.getSelectedItem().toString() + "'";
		            	}
		            	userQuery += " GROUP BY baseball.batting.playerID " +
		                        " ORDER BY SLG DESC;";
						data.setResultSet(data.getStatement().executeQuery(userQuery));
						model.setColumnIdentifiers(data.getCols(data.getResultSet()).toArray());
						List<List<String>> resList = resultsArrayList(data.getResultSet());
						for (List<String> row : resList) {
							model.addRow(row.toArray());
						}
						output.setModel(model);
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            }
		        }
		    }
		});

		createTableBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		createTableBtn.setBounds(899, 323, 185, 37);
		getContentPane().add(createTableBtn);
	}
}
