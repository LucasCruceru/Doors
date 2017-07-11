package ro.fortech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.fortech.entities.Door;
import ro.fortech.repositories.DoorRepository;

import java.util.List;

@RestController("/door")
public class DoorController {

    private final DoorRepository doorRepository;

    @Autowired
    public DoorController(DoorRepository doorRepository){
        this.doorRepository = doorRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List readAllDoors(){
        return this.doorRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{doorId}")
    public Door readOneDoor(@PathVariable Long doorId){
        return this.doorRepository.findOne(doorId);
    }



    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public List deleteDoor(@PathVariable Long userId) {
        doorRepository.delete(userId);
        return readAllDoors();
    }
}
