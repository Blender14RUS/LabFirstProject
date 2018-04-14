package LibApp.service;

import LibApp.DAO.domain.User;

import java.util.List;

public interface LibService {

    List<User> getAll();

    int createUser(User user);

    void deleteUserById(Long id);

}
