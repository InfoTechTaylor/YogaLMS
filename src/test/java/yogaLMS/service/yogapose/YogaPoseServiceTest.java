package yogaLMS.service.yogapose;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import testUtils.TestHelperMethods;
import yogaLMS.dto.yogapose.YogaPose;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback(true)
public class YogaPoseServiceTest {

    @Inject
    TestHelperMethods testHelperMethods;

    @Inject
    YogaPoseService yogaPoseService;

    @Before
    public void setUp() throws Exception {

        List<YogaPose> allPoses = yogaPoseService.retrieveAll();
        for(YogaPose pose : allPoses){
            yogaPoseService.delete(pose);
        }
    }

    @Test
    public void testCreate() throws Exception{
        // arrange
        YogaPose yogaPose = testHelperMethods.createTestYogaPose();

        // act
        YogaPose createdYogaPose = yogaPoseService.create(yogaPose);

        // assert
        assertNotNull(createdYogaPose.getId());
    }

    @Test
    public void testRead() throws Exception{
        // arrange
        YogaPose createdYogaPose = yogaPoseService.create(testHelperMethods.createTestYogaPose());

        // act
        YogaPose readYogaPose = yogaPoseService.read(createdYogaPose.getId());

        // assert
        assertNotNull(readYogaPose);
        assertEquals(createdYogaPose.getId(), readYogaPose.getId());
        assertEquals(createdYogaPose.getName(), readYogaPose.getName());
    }

    @Test
    public void testUpdate() throws Exception{
        // arrange
        YogaPose createdYogaPose = yogaPoseService.create(testHelperMethods.createTestYogaPose());
        createdYogaPose.setName("Downward Facing Dog");

        // act
        yogaPoseService.update(createdYogaPose);

        // assert
        YogaPose readYogaPose = yogaPoseService.read(createdYogaPose.getId());
        assertEquals("Downward Facing Dog", readYogaPose.getName());
    }

    @Test
    public void testDelete() throws Exception{
        // arrange
        YogaPose createdYogaPose = yogaPoseService.create(testHelperMethods.createTestYogaPose());

        // act
        yogaPoseService.delete(createdYogaPose);

        // assert
        assertNull(yogaPoseService.read(createdYogaPose.getId()));
    }

    @Test
    public void testRetrieveAll() throws Exception{
        // arrange
        for(int i=0; i<15; i++){
            yogaPoseService.create(testHelperMethods.createTestYogaPose());
        }

        // act
        List<YogaPose> allPoses = yogaPoseService.retrieveAll();

        // assert
        assertEquals(15, allPoses.size());
    }

}