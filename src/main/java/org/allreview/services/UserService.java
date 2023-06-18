package org.allreview.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.allreview.dao.UserDao;
import org.allreview.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserDao userDao;

  public List<User> getAllUsers() {
    return userDao.findAll();
  }

  public User addUser(User user) {
    return userDao.save(user);
  }
}
