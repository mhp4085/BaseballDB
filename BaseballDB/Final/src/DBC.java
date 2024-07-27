import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBC {
	
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    public String userQuery = null;

	public void readDataBase() throws Exception {
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			//*******************************************************************
			//*******************************************************************
			String username = "root";//change this to your MySQL server user name
			String password = "root";//change this to your MySQL server password
			//*******************************************************************
			//*******************************************************************
			
			connect = DriverManager.getConnection("jdbc:mysql://localhost/baseball?"+ "user="+username+"&password="+password);
			
			//this creates a statement object which is the thing that is actually sent to the MySQL database
			//MySQL does not process Strings, or arrays or anything else, it only processes Statement objects
			//So here we are preparing the statement that will eventually store the SQL query that we want to
			//send to the DB
			statement = connect.createStatement(); 
			
			
			resultSet = statement.executeQuery("select * from baseball.batting;");
			writeData(resultSet); //custom method call. We will write this.
			getCols(resultSet);
			
			
		}//end try
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}//end catch
	}//end readDataBase
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public Connection getConnect() {
		return connect;
	}


	public void setConnect(Connection connect) {
		this.connect = connect;
	}


	public Statement getStatement() {
		return statement;
	}


	public void setStatement(Statement statement) {
		this.statement = statement;
	}


	public ResultSet getResultSet() {
		return resultSet;
	}


	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}


	public ArrayList<String> getCols(ResultSet resultSet) throws SQLException {
		System.out.println("*******************************************************************");
		System.out.println("the columns in the table are: ");
		ArrayList<String> md = new ArrayList<String>();
		for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
			md.add(resultSet.getMetaData().getColumnName(i));
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }//end for
		return md;
	}//end writeMetaData
	
	private void writeData(ResultSet resultSet) throws SQLException{
		while(resultSet.next()) {
			String id = resultSet.getString("playerID");
		 	System.out.println(" player id: "+ id);
		}//end while
	}//end writeResultSet
	
	private void close() {
		try {
			if(resultSet != null) {
				resultSet.close();
			}//end if
			if(resultSet != null) {
				resultSet.close();
			}//end if
			if(resultSet != null) {
				resultSet.close();
			}//end if
			
		}//end try
		catch(Exception e) {
			e.printStackTrace();
		}//end catch
	}//end close
	
	public static void main(String[] args) throws Exception {
		System.out.println("starting program");
		DBC simpleConnaction = new DBC();
		System.out.println("initiating connection");
		simpleConnaction.readDataBase();
        
        System.out.println("program terminated");

	}

}
