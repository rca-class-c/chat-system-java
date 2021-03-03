package server.repositories;

import server.config.PostegresConfig;
import server.models.Package;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PackageRepository {

    private List<Package> packageList= new ArrayList<>();

    public Package savePackage(Package packages) throws SQLException {
        Connection connection = PostegresConfig.getConnection();
        String query = "INSERT INTO package (package_name, period, price) VALUES(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, packages.getPackageName());
        statement.setInt(2, packages.getPeriod());
        statement.setFloat(3, packages.getPrice());

        statement.executeUpdate();

        connection.close();
        return packages;
    }

    public List<Package> getAllPackages() throws SQLException{

        Connection connection= PostegresConfig.getConnection();
        String query = "SELECT * from package";
        Statement statement = connection.createStatement();
        ResultSet resultSet= statement.executeQuery(query);

        while(resultSet.next()){
            int id = resultSet.getInt("package_id");
            String name = resultSet.getString("package_name");
            int period = resultSet.getInt("period");
            float price = resultSet.getFloat("price");

            Package packages = new Package(id,name,period,price);
            packageList.add(packages);
        }

        resultSet.close();
        statement.close();
        connection.close();

        return null;
    }

    public Package getPackageInfo(int package_id) throws SQLException{

        Connection connection= PostegresConfig.getConnection();
        String query = "SELECT * from package where package_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,package_id);

        ResultSet result = statement.executeQuery(query);

        if(result.next()){

            int id = result.getInt(1);
            String packageName = result.getString(2);
            int period = result.getInt(3);
            float price = result.getFloat(4);

            return new Package(id, packageName, period, price);
        }
        statement.close();
        connection.close();

        return null;
    }

    public boolean updatePackage(Package packages, int id) throws SQLException {
        Connection connection = PostegresConfig.getConnection();
        String query = "UPDATE package SET packageName = ? ,period=?, price=? WHERE id=? ";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, packages.getPackageName());
        statement.setInt(2, packages.getPeriod());
        statement.setFloat(3, packages.getPrice());
        statement.setInt(4, id);
        boolean rowUpdated = statement.executeUpdate(query)>0;
        connection.close();

        return rowUpdated;
    }

    public boolean deletePackage(int package_id) throws SQLException {

        Connection connection = PostegresConfig.getConnection();
        String query = "DELETE FROM package WHERE packageId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, package_id);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        connection.close();

        return rowDeleted;
    }
}