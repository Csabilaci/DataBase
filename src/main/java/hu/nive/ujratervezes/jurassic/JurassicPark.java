package hu.nive.ujratervezes.jurassic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {
        return getResult("SELECT breed FROM dinosaur WHERE expected < actual ORDER BY breed");
    }

    public List<String> getResult(String SQL) {
        List<String> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            if (resultSet.next()) return Collections.singletonList(resultSet.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
