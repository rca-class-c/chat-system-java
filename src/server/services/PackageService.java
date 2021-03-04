package server.services;

import server.models.Package;
import server.repositories.PackageRepository;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @authors:
 * - Loraine Irakoze
 * - Cyusa Keny
 *
 */

public class PackageService {
    private final PackageRepository packageRepository = new PackageRepository();

    public List<Package> getAllPackages() throws SQLException {
        return packageRepository.getAllPackages();
    }

    public Package getPackageInfoById(int package_id) throws SQLException {
        return packageRepository.getPackageInfo(package_id);
    }
    public Package savePackage(Package packages) throws SQLException {
        return packageRepository.savePackage(packages);
    }
    public boolean updatePackage(Package packages) throws SQLException {
        return packageRepository.updatePackage(packages);
    }
    public boolean deletePackage(int id) throws SQLException {
        return packageRepository.deletePackage(id);
    }
    public List<Package> searchPackage(String search) throws SQLException {
        return packageRepository.getPackageListSearch(search);
    }
}
