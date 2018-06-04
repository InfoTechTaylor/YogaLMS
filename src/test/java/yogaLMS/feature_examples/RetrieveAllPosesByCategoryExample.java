package yogaLMS.feature_examples;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import testUtils.TestHelperMethods;
import yogaLMS.dto.yogapose.PoseCategory;
import yogaLMS.dto.yogapose.YogaPose;
import yogaLMS.service.yogapose.YogaPoseService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback(true)
public class RetrieveAllPosesByCategoryExample {

    @Inject
    YogaPoseService yogaPoseService;

    @Inject
    TestHelperMethods testHelperMethods;

    @Before
    public void setup() throws Exception{
        // arrange
        for(int i=0; i<3; i++) {
            YogaPose balancePose = testHelperMethods.createTestYogaPose();
            List<PoseCategory> categories = new ArrayList<>();
            categories.add(PoseCategory.BALANCE);
            categories.add(PoseCategory.BACKWARD);
            balancePose.setCategories(categories);
            yogaPoseService.create(balancePose);
        }
        for(int i=0; i<5; i++){
            YogaPose balancePose = new YogaPose();
            List<PoseCategory> categories = new ArrayList<>();
            categories.add(PoseCategory.TWISTING);
            categories.add(PoseCategory.BACKWARD);
            balancePose.setCategories(categories);
            yogaPoseService.create(balancePose);
        }
    }

    @Test
    public void runExample() throws Exception{
        // for user inputs balance as category
        final String CATEGORY = "Balance";
        List<YogaPose> balancePoses = yogaPoseService.retrieveAllByCategory(PoseCategory.BALANCE);

        System.out.println(CATEGORY + " Poses:");
        System.out.println("____________________________________________________________________________");

        balancePoses.stream().forEach(s -> System.out.println(s.toString()));
    }
}
