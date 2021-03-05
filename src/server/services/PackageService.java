package server.services;

/**
 *
 * @authors:
 * - Loraine Irakoze
 * - Cyusa Keny
 *
 */

public class PackageService {
    private final server.repositories.PackageRepository packageRepository = new server.repositories.PackageRepository();

    public java.util.List<server.models.Package> getAllPackages() throws java.sql.SQLException {
        return packageRepository.getAllPackages();
    }

    public server.models.Package getPackageInfoById(int package_id) throws java.sql.SQLException {
        return packageRepository.getPackageInfo(package_id);
    }
    public server.models.Package savePackage(server.models.Package packages) throws java.sql.SQLException {
        return packageRepository.savePackage(packages);
    }
    public boolean updatePackage(server.models.Package packages) throws java.sql.SQLException {
        return packageRepository.updatePackage(packages);
    }
    public boolean deletePackage(int id) throws java.sql.SQLException {
        return packageRepository.deletePackage(id);
    }
    public java.util.List<server.models.Package> searchPackage(String search) throws java.sql.SQLException {
        return packageRepository.getPackageListSearch(search);
    }
}
