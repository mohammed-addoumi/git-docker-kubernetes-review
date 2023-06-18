package org.allreview.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.allreview.domain.User;
import org.allreview.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public List<User> test() {
    return userService.getAllUsers();
  }

  @PostMapping
  public User addUser(@RequestBody User user) {
    return userService.addUser(user);
  }
}
