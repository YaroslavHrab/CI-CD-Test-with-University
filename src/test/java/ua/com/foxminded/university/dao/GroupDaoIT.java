package ua.com.foxminded.university.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.junit5.DBUnitExtension;
import com.github.database.rider.junit5.api.DBRider;

import ua.com.foxminded.university.Application;
import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.model.Group;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = "classpath:application.properties")
@ExtendWith(DBUnitExtension.class)
@DBRider
public class GroupDaoIT {
	
	@Autowired
    private GroupDao groupDao;
	
	@Autowired
	private DataSource dataSource;

    @SuppressWarnings("unused")
    private ConnectionHolder connectionHolder = () -> dataSource.getConnection();

    @Test
    @DataSet(value = "groupDataset.yml", strategy = SeedStrategy.CLEAN_INSERT)
    @ExpectedDataSet(value = "groupAddExpectedDataset.yml", ignoreCols = "group_id")
    void addGroup_shouldAddGroupToDatabase() {
        Group group = new Group("AA-00");
        
        groupDao.save(group);
    }

    @Test
    @DataSet(value = "groupDataset.yml", strategy = SeedStrategy.CLEAN_INSERT)
    void getAllGroups_shouldReturnAllGroupsFromDatabase() {
        List<Group> groups = groupDao.findAll();

        assertFalse(groups.isEmpty());
        assertEquals(2, groups.size());
    }

    @Test
    @DataSet(value = "groupDataset.yml", strategy = SeedStrategy.CLEAN_INSERT)
    @ExpectedDataSet(value = "groupDeleteExpectedDataset.yml")
    void deleteGroup_shouldDeleteGroupFromDatabase() {
        Group group = groupDao.findById(2L).get();
        
        assertNotNull(group);
        groupDao.delete(group);
    }

    @Test
    @DataSet(value = "groupDataset.yml", strategy = SeedStrategy.CLEAN_INSERT)
    void findOne_shouldReturnGroupAssociatedWithId() {
        Group group = groupDao.findById(1L).get();

        assertTrue("Test1".equals(group.getName()));
    }
    
    @Test
    @DataSet(value = "groupDataset.yml", strategy = SeedStrategy.CLEAN_INSERT)
    @ExpectedDataSet(value = "groupUpdateExpectedDataset.yml")
    void updateGroup_shouldUpdateGroupInDatabase() {
        Group group = groupDao.findById(1L).get();
        assertNotNull(group);
        group.setName("AA-01");
        
        groupDao.save(group);
    }
}
