package dev.delphington.dao;

import dev.delphington.config.Config;
import dev.delphington.model.Person;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class PersonDAO {


    private final JdbcTemplate jdbcTemplate;


    //
//
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) throws SQLException {
        this.jdbcTemplate = jdbcTemplate;
    }


//    private static Connection connection;
//
//    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            connection = DriverManager.getConnection(Config.URL_STUDY, Config.USER, Config.PASSWORD);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    public List<Person> index() {
//        System.out.println("===================================");
//        List<Person> p = new ArrayList<>();
//        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person")) {
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                int year = resultSet.getInt("year");
//                p.add(new Person(id, name, year));
//            }
//
//
//        } catch (SQLException e) {
//
//            throw new RuntimeException(e);
//        }
//
//
//        return p;


        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());

    }
}
