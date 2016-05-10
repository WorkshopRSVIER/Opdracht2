        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

//maak automatisch een nieuw connection en statement object aan en reference dit mbv de class variabelen connection en statement.

    public void createCS(String SQLStatement) throws SQLException, ClassNotFoundException{
        connection = DriverManager.getConnection(URL,USER,PASSWORD);
        statement = connection.prepareStatement(SQLStatement);
    }

//sluit automatisch alle openstaande connecties

    public void closeCS(){
        {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {}
            }   
        }
    }