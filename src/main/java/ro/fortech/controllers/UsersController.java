package ro.fortech.controllers;//Created by internship on 12.07.2017.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.fortech.entities.User;
import ro.fortech.repositories.UserRepository;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(value = "/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List readUsers(){
        return this.userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public User readOneUser(@PathVariable Long userId){
        return this.userRepository.findOne(userId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public ResponseEntity deleteUsers(@PathVariable Long userId) {
        userRepository.delete(userId);
        if (!userRepository.exists(userId))
            return ResponseEntity.ok().body("User Deleted");
        else
            return ResponseEntity.unprocessableEntity().build();
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestBody User input) {

        User result = userRepository.save(new User(input.getPassword(), input.getUsername()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{userId}")
    public User update(@PathVariable Long userId, @RequestBody User input) {


        this.userRepository.findOne(userId).updateUser(input);


        return this.userRepository.findOne(userId);
    }




}


