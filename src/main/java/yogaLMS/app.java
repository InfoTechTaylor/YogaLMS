package yogaLMS;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import yogaLMS.controller.YogaLMSController;

public class app {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-persistence.xml");
        YogaLMSController controller = ctx.getBean("controller", YogaLMSController.class);
        controller.run();
    }
}
