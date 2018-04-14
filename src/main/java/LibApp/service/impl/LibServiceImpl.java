package LibApp.service.impl;

import LibApp.DAO.LibDAO;
import LibApp.DAO.domain.User;
import LibApp.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibServiceImpl implements LibService{

    private LibDAO libDao;

    @Autowired
    LibServiceImpl(LibDAO libDao) {
        this.libDao = libDao;
    }

    @Override
    public List<User> getAll() {
        return libDao.getAll();
    }

    @Override
    public int createUser(User user) {
        return libDao.createUser(user);
    }

    @Override
    public void deleteUserById(Long id) {
        libDao.deleteUserById(id);
    }
}
