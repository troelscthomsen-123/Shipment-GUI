package sample;


import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import static java.sql.DriverManager.getConnection;

public class Model {
    Connection connection = null;
    String url;
    Statement statement = null;
    PreparedStatement prepStat = null;
    ResultSet resSet = null;

    Model(String url) {
        this.url = url;
    }

    public void connect() throws SQLException {
        connection = getConnection(url);
    }

    public void close() throws SQLException {
        if (connection != null)
            connection.close();
    }

    public void creatingStatement() throws SQLException {
        this.statement = connection.createStatement();
    }

    public ArrayList<String> habourListNames() {
        ArrayList<String> names = new ArrayList<String>();
        String sql = "select h1.name as habour from habour t inner join habour h1 on t.id = h1.id;";
        try {
            resSet = statement.executeQuery(sql);
            while (resSet != null && resSet.next()) {
                String name = resSet.getString(1);
                names.add(name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        resSet = null;
        return names;
    }
    //The Sql search that collect data for my GUI
    public void preparedStatementPrintShippingInfo() {
        String sql = "Select h1.name as fromport, h2.name as toport, v.name as vessel, Sum(f.containers) as flow, v.capacity from transport t inner join vessel v on t.vessel = v.id inner join habour h1 on t.fromhabour = h1.id inner join habour h2 on t.tohabour = h2.id left outer join flow f on t.id = f.transport where h1.name = ? and h2.name =? and ?<v.capacity;";
        try {
            prepStat = connection.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //The output  to put in the GUI textarea
    public ArrayList<ShippingData> FindShippingData(String fromport, String toport, String capacityCount) {
        ArrayList<ShippingData> shippingdatas = new ArrayList<>();
        try {
            prepStat.setString(1, fromport);
            prepStat.setString(2, toport);
            prepStat.setString(3, capacityCount);
            resSet = prepStat.executeQuery();

            while (resSet != null && resSet.next()) {
                shippingdatas.add(new ShippingData(resSet.getString(1), resSet.getString(2), resSet.getString(3), resSet.getInt(4),resSet.getString(5)));
                System.out.println("Fromport name: " + resSet.getString(1));
                System.out.println("Toport name: " + resSet.getString(2));
                System.out.println("Vessel name: " + resSet.getString(3));
                System.out.println("Containers: " + resSet.getString(4));
                System.out.println("Vessel capacity: " + resSet.getString(5));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return shippingdatas;

    }
}
