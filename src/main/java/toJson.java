import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class toJson {

    public static void main(String[] args) throws SQLException {
        String connectionUrl =
                "jdbc:sqlserver://35.197.128.168:1433;"
                        + "DatabaseName=trial;"
                        + "user=sqlserver;"
                        + "password=admin;"
                        + "trustServerCertificate=True;";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT TOP 10 * FROM trial.dbo.demo";
            resultSet = statement.executeQuery(selectSql);
//            JSONArray result = new JSONArray();
//
//            while(resultSet.next()){
//                ResultSetMetaData md = resultSet.getMetaData();
//                int numCols = md.getColumnCount();
//                List<String> colNames = IntStream.range(0, numCols)
//                        .mapToObj(i -> {
//                            try {
//                                return md.getColumnName(i + 1);
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                                return "?";
//                            }
//                        })
//                        .collect(Collectors.toList());
//
//
//                JSONObject row = new JSONObject();
//                ResultSet finalResultSet = resultSet;
//                colNames.forEach(cn -> {
//                    try {
//                        row.put(cn, finalResultSet.getObject(cn));
//                    } catch (JSONException | SQLException e) {
//                        e.printStackTrace();
//                        row.put(cn, e.toString());
//                    }
//                });
//                result.put(row);
//            }
//
//            System.out.println(result.toString());

            ResultSetMetaData md = resultSet.getMetaData();
            int numCols = md.getColumnCount();
            List<String> colNames = IntStream.range(0, numCols)
                    .mapToObj(i -> {
                        try {
                            return md.getColumnName(i + 1);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            return "?";
                        }
                    })
                    .collect(Collectors.toList());

            JSONArray result = new JSONArray();

            while (resultSet.next()) {
                JSONObject row = new JSONObject();
                ResultSet finalResultSet = resultSet;
                colNames.forEach(cn -> {
                    try {
                        row.put(cn, finalResultSet.getObject(cn));
                    } catch (JSONException | SQLException e) {
                        e.printStackTrace();
                        row.put(cn, e.toString());
                    }
                });
                result.put(row);
            }

            System.out.println(result.toString());

            // Print results from select statement
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
//            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

//        ResultSetMetaData md = resultSet.getMetaData();
//        int numCols = md.getColumnCount();
//        List<String> colNames = IntStream.range(0, numCols)
//                .mapToObj(i -> {
//                    try {
//                        return md.getColumnName(i + 1);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                        return "?";
//                    }
//                })
//                .collect(Collectors.toList());
//
//        JSONArray result = new JSONArray();
//        while (resultSet.next()) {
//            JSONObject row = new JSONObject();
//            colNames.forEach(cn -> {
//                try {
//                    row.put(cn, resultSet.getObject(cn));
//                } catch (JSONException | SQLException e) {
//                    e.printStackTrace();
//                }
//            });
//            result.put(row);
//        }
//
//        System.out.println(result.toString());
    }
}
