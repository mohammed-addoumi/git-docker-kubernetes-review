package org.allreview.controllers;

import lombok.RequiredArgsConstructor;
import org.allreview.domain.User;
import org.allreview.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
  public ResponseEntity<User> addUser(@RequestBody User user) {
    if("fake".equalsIgnoreCase(user.getName())) return ResponseEntity.badRequest().build();
    User createdUser = userService.addUser(user);
    return ResponseEntity.ok(createdUser);
  }
}
