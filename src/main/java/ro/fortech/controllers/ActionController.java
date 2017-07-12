package ro.fortech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.fortech.repositories.DoorRepository;

import java.util.List;
@RestController("/{username}")
public class ActionController {

    private final DoorRepository doorRepository;

    @Autowired
    public ActionController(DoorRepository doorRepository) {
        this.doorRepository = doorRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List showOptions() {
        return this.doorRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/open/{doorName}")
    public void open(@PathVariable String doorName) {

        doorRepository.findByName(doorName).get().open();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/close/{doorName}")
    public void close(@PathVariable String doorName){

        doorRepository.findByName(doorName).get().close();
}

}
