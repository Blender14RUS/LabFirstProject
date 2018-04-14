package LibApp.DAO;

import LibApp.DAO.domain.User;

import java.util.List;

public interface LibDAO {

    List<User> getAll();

    int createUser(User user);

    void deleteUserById(Long id);

}
