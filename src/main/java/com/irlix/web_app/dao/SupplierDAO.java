package com.irlix.web_app.dao;

import com.irlix.web_app.model.Supplier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SupplierDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/confectionery_factory";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "5255742";

    private static final Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Supplier> getSuppliers() {
        List<Supplier> suppliersList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Supplier";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Supplier supplier = new Supplier();

                supplier.setId(resultSet.getInt("id"));
                supplier.setName(resultSet.getString("name"));
                supplier.setAddress(resultSet.getString("address"));

                suppliersList.add(supplier);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return suppliersList;
    }

    public Supplier showSupplier(int id) {
        Supplier supplier = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM supplier WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            supplier = new Supplier();
            supplier.setId(resultSet.getInt("id"));
            supplier.setName(resultSet.getString("name"));
            supplier.setAddress(resultSet.getString("address"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplier;
    }

    public void addSupplier(Supplier supplier) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Supplier(name, address) VALUES (?, ?)");
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateSupplier(int id, Supplier updateSupplier) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE supplier SET name=?, address=? WHERE id=?");
            preparedStatement.setString(1, updateSupplier.getName());
            preparedStatement.setString(2, updateSupplier.getAddress());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSupplier(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM supplier WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
