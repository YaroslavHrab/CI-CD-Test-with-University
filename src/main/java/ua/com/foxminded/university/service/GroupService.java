package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.model.Group;

public interface GroupService {
    
    void addGroup(Group group);

    List<Group> getAllGroups();
    
    void deleteGroup(Group group);
    
    Group findOne(Long id);
    
    void updateGroup(Group group);
}
