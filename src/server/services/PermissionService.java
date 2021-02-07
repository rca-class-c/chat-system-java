package server.services;

import server.models.Permission;
import server.repositories.PermissionRepository;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @description this is a class that presents Service object perform all of the access to the database
 * or to the repository
 * @author Gahamanyi Yvette
 * @date 4-2-2021
 *
 * */

public class PermissionService {
    private final PermissionRepository permissionRepository=new PermissionRepository();

    public Permission getPermission(int id) throws SQLException {
        return permissionRepository.getPermission(id);
    }

    public List<Permission> getAllPermission() throws SQLException {
        return permissionRepository.getAllPermission();
    }

    public boolean createPermission(Permission permission) throws SQLException {
        return permissionRepository.createPermission(permission);
    }

    public boolean deletePermission(Permission permission) throws SQLException {
       return permissionRepository.deletePermission(permission);
    }

    public boolean updatePermission(Permission permission) throws SQLException {
       return permissionRepository.updatePermission(permission);
    }
}
