package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.LibDAO;
import com.epam.lab.library.domain.User;
import com.epam.lab.library.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibServiceImpl implements LibService {

    private LibDAO libDao;

    @Autowired
    LibServiceImpl(LibDAO libDao) {
        this.libDao = libDao;
    }

    @Override
    public User getUser(Long id) {
        return libDao.getUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return libDao.getAllUsers();
    }

    @Override
    public int createUser(User user) {
        return libDao.createUser(user);
    }

    @Override
    public void deleteUserById(Long id) {
        libDao.deleteUserById(id);
    }

    @Override
    public void updateUserAccessLevel(Long id, String access_level) {
        if (access_level.equals("READER"))
            access_level = "LIBRARIAN";
        else access_level = "READER";
        libDao.updateUserAccessLevel(id, access_level);

    }
}
