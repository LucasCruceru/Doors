package ro.fortech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.fortech.entities.Door;
import ro.fortech.repositories.DoorRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/doors")
public class DoorController {

    @Autowired
    private  DoorRepository doorRepository;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List readAllDoors(){
        return this.doorRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{doorId}")
    public Door readOneDoor(@PathVariable Long doorId){
        return this.doorRepository.findOne(doorId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{doorId}")
    public ResponseEntity deleteDoor(@PathVariable Long doorId) {
        doorRepository.delete(doorId);
        if (!doorRepository.exists(doorId))
            return ResponseEntity.ok().body("Door Deleted");
        else
            return ResponseEntity.unprocessableEntity().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestBody Door input) {

        Door result = doorRepository.save(new Door(input.getName(), input.isClosed()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{doorId}")
    public Door update(@PathVariable Long doorId, @RequestBody Door input) {
        this.doorRepository.findOne(doorId).updateDoor(input);
        return this.doorRepository.findOne(doorId);
    }

}
