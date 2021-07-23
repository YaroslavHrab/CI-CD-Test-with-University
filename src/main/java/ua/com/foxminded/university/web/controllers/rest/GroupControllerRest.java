package ua.com.foxminded.university.web.controllers.rest;

import java.util.List;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.service.postgre.PostgreSqlGroupService;

@RestController
@RequestMapping("/m.cruds")
@RequiredArgsConstructor
@Api
public class GroupControllerRest {

    @NonNull
    private PostgreSqlGroupService groupService;

    @GetMapping("/groups")
    public List<Group> getGroups() {
        return groupService.getAllGroups();
    }

    @PostMapping("/groups")
    public void newGroup(@RequestBody @Valid Group group) {
        groupService.addGroup(group);
    }
    
    @GetMapping("/groups/{id}")
    public Group findOne(@PathVariable Long id) {
      return groupService.findOne(id);
    }
    
    @PutMapping("/groups/{id}")
    public void updateGroup(@RequestBody @Valid Group newGroup, @PathVariable Long id) {
        Group group = groupService.findOne(id);
        group.setName(newGroup.getName());
        group.setStudents(newGroup.getStudents());
        groupService.updateGroup(group);
    }
    
    @DeleteMapping("/groups/{id}")
    void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(groupService.findOne(id));
    }
}
