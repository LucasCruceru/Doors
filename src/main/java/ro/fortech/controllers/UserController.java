package ro.fortech.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fortech.entities.User;
import ro.fortech.repositories.UserRepository;

import java.util.List;

@RestController("/user")
public class UserController {

//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    public List readUsers(){
//        return this.userRepository.findAll();
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
//    public User readOneUser(@PathVariable Long userId){
//        return this.userRepository.findOne(userId);
//    }
//
////    @RequestMapping(method = RequestMethod.POST)
////    ResponseEntity<?> add( @RequestBody User input) {
////
////
////        return this.userRepository.findByUsername(input.getUsername())
////                .map(user -> {
////                    User result = userRepository.save(new User(user.getPassword(), user.getUsername()));
////
////                    URI location = ServletUriComponentsBuilder
////                            .fromCurrentRequest().path("/{id}")
////                            .buildAndExpand(result.getId()).toUri();
////                    System.out.println(location);
////
////                    return ResponseEntity.created(location)
////                            .build();
////                })
////                .orElse(ResponseEntity.noContent().build());
////
////    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
//    public List deleteUsers(@PathVariable Long userId) {
//        userRepository.delete(userId);
//        return readUsers();
//    }
//

}
