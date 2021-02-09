package server.services;
import server.models.categories;
import server.repositories.categoryRepository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;

public class categoryServices {
private final categoryRepository categoryRepository=new categoryRepository();

public categories Save(categories categories) throws SQLException {
    return categoryRepository.saveCategory(categories);
}
public int UpdateCategory(categories categories, int id) throws SQLException {
    return categoryRepository.updateCategory(categories,id);
}
public LinkedList<categories> getAllCategories() throws SQLException {
    return categoryRepository.getAllCategories();
}
public categories getCategoryById(int id) throws SQLException {
    return  categoryRepository.getCategory(id);
}
public  int deleteCategory(int id) throws SQLException {
    return  categoryRepository.deleteCategory(id);
}

}
