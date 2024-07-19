/*
 * package com.learning.userservice.controller;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.cache.annotation.CacheEvict; import
 * org.springframework.cache.annotation.Cacheable; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.CrossOrigin; import
 * org.springframework.web.bind.annotation.DeleteMapping; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.PutMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.learning.userservice.entities.User;
 * 
 * @RestController
 * 
 * @RequestMapping("/users")
 * 
 * @CrossOrigin(origins = "http://localhost:4200") public class UserController {
 * 
 * @Autowired private UserServiceImpl userService;
 * 
 * @PostMapping("/add")
 * 
 * @Cacheable(value = "user") public ResponseEntity<User>
 * createUser(@RequestBody User user) { User createdUser =
 * userService.saveUser(user); return new ResponseEntity<>(createdUser,
 * HttpStatus.CREATED); }
 * 
 * @GetMapping("/{userId}")
 * 
 * @Cacheable(value = "user") public ResponseEntity<User>
 * getSingleUSer(@PathVariable Integer userId) { User user =
 * userService.getUser(userId); return ResponseEntity.ok(user); }
 * 
 * @GetMapping("/all") public ResponseEntity<List<User>> getAllUser() {
 * List<User> allUser = userService.getAllUser(); return
 * ResponseEntity.ok(allUser); }
 * 
 * @GetMapping("/byName") public ResponseEntity<User>
 * findByUserName(@RequestParam String username) { User user =
 * userService.findUserByUsername(username); return ResponseEntity.ok(user); }
 * 
 * @GetMapping("/byEmail") public ResponseEntity<User> findByEmail(@RequestParam
 * String email) { User user = userService.findUserByEmail(email); return
 * ResponseEntity.ok(user); }
 * 
 * @GetMapping("/loginByEmailPwd") public ResponseEntity<User>
 * loginByEmailAndPassword(@RequestParam String email, String password) { User
 * user = userService.findByEmailAndPassword(email, password); return
 * ResponseEntity.ok(user); }
 * 
 * @GetMapping("/loginByUsernamePwd") public ResponseEntity<User>
 * loginByUsernameAndPassword(@RequestParam String userName, String password) {
 * User user = userService.findByUsernameAndPassword(userName, password); return
 * ResponseEntity.ok(user); }
 * 
 * @DeleteMapping("/{id}")
 * 
 * @CacheEvict(value = "employee", key = "#id") public ResponseEntity<Void>
 * deleteUser(@PathVariable Integer id) { userService.deleteById(id); return new
 * ResponseEntity<>(HttpStatus.NO_CONTENT); }
 * 
 * @PutMapping("/{id}") public ResponseEntity<User>
 * updateUser(@PathVariable(value = "id") Integer userId, @RequestBody User
 * userDetails) { User updatedUser = userService.updateUser(userId,
 * userDetails); return ResponseEntity.ok(updatedUser); } }
 */