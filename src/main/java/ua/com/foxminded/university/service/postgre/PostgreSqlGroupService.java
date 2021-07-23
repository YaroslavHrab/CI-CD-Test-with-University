package ua.com.foxminded.university.service.postgre;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.service.GroupService;
import ua.com.foxminded.university.util.exceptions.GroupNotFoundException;

import static ua.com.foxminded.university.util.Validator.checkIfObjectNull;

@Service
@Validated
@RequiredArgsConstructor
public class PostgreSqlGroupService implements GroupService {

    @NonNull
    private GroupDao groupDao;

    @Override
    public void addGroup(Group group) {
        checkIfObjectNull(group);
        groupDao.save(group);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupDao.findAll();
    }

    @Override
    public void deleteGroup(Group group) {
        checkIfObjectNull(group);
        groupDao.delete(group);
    }

    @Override
    public Group findOne(Long id) {
        return groupDao.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
    }

    @Override
    public void updateGroup(Group group) {
        checkIfObjectNull(group);
        groupDao.save(group);
    }
}
