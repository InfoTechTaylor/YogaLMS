package yogaLMS;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import yogaLMS.controller.LogController;

import javax.inject.Inject;

public class app {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-persistence.xml");
        LogController controller = ctx.getBean("logController", LogController.class);
        controller.run();
    }
}
