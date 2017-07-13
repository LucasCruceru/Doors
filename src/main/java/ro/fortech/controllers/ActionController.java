package ro.fortech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.fortech.entities.Door;
import ro.fortech.entities.Log;
import ro.fortech.repositories.DoorRepository;
import ro.fortech.repositories.LogRepository;

import java.util.Date;
import java.util.List;
@RestController(value = "/action/{username}")
public class ActionController {

    private final DoorRepository doorRepository;

    private final LogRepository logRepository;

    @Autowired
    public ActionController(DoorRepository doorRepository, LogRepository logRepository) {
        this.doorRepository = doorRepository;
        this.logRepository = logRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List showOptions() {
        return this.doorRepository.findAll();
    }



    @RequestMapping(method = RequestMethod.POST, value = "/open/{doorName}")
    public Door open(@PathVariable String doorName, @PathVariable String username) {

        logRepository.save(new Log(new Date(System.currentTimeMillis()), username, doorName));

        doorRepository.findByName(doorName).get().open();

        return doorRepository.findByName(doorName).get();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/close/{doorName}")
    public Door close(@PathVariable String doorName, @PathVariable String username){

        logRepository.save(new Log(new Date(System.currentTimeMillis()), username, doorName));

        doorRepository.findByName(doorName).get().close();

        return doorRepository.findByName(doorName).get();
}

}
