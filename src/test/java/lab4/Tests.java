package lab4;

import lab4.controller.DataController;
import lab4.controller.LogTransformationBeanPostProcessor;
import lab4.controller.ValidateLog;
import lab4.model.Model;
import lab4.repository.LoginsRepo;
import lab4.repository.UsersRepo;
import lab4.service.impl.LoadServiceImpl;
import lab4.service.impl.ValidateDate;
import lab4.service.impl.ValidateUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class Tests {
	List<Model> models = new ArrayList<>();
	@Autowired
	private DataController dataController;
	@Autowired
	private LogTransformationBeanPostProcessor logTransformationBeanPostProcessor;
	@Autowired
	private ValidateLog validateLog;
	@Autowired
	private LoadServiceImpl loadService;
	@Autowired
	private ValidateDate validateDate;
	@Autowired
	private ValidateUser validateUser;

	@BeforeEach
	public void setModel() {
    	models.clear();
		Model model = new Model();
		model.username   = "Login1";
		model.fio        = "F I O";
		model.accessdate = LocalDateTime.now();
		model.app        = "web";
   		models.add(model);

		model.username   = "Login2";
		model.fio        = "F I O";
		model.accessdate = LocalDateTime.now();
		model.app        = "app";
		models.add(model);
	}

	@Test
	@DisplayName("Check Autowired")
	public void testAutowired() {
		assertThat(dataController).isNotNull();
		assertThat(logTransformationBeanPostProcessor).isNotNull();
		assertThat(validateLog).isNotNull();
		assertThat(loadService).isNotNull();
		assertThat(validateDate).isNotNull();
		assertThat(validateUser).isNotNull();
	}
	@Test
	@DisplayName("Check Date")
	public void testDate() {
    	int cnt = models.size();
		Model model = models.get(0);
		model.accessdate = null;
		validateDate.validate(models);
		assertThat(models.size()).isEqualTo(cnt-1);
    }

	@Test
	@DisplayName("Check User")
	public void testUser() {
		int cnt = models.size();
		Model model = models.get(0);
		model.username = "f i o";
		validateUser.validate(models);
		assertThat(models.get(0).fio).isEqualTo("F I O");
	}

	@Test
	public void runService(){
		ApplicationContext ctx = SpringApplication.run(Starter.class);
		DataController dc = ctx.getBean("dataController", DataController.class);
		LoginsRepo loginsRepo = ctx.getBean("loginsRepo",LoginsRepo.class);
		UsersRepo usersRepo = ctx.getBean("usersRepo", UsersRepo.class);
		loginsRepo.deleteAll();
		usersRepo.deleteAll();
		assertThat(loginsRepo.count()).isZero();
		assertThat(usersRepo.count()).isZero();
		dc.make("c:\\Users\\79099\\IdeaProjects\\Homework_4\\src\\main\\resources\\Authorization");
		assertThat(usersRepo.count()).isNotZero();
		assertThat(loginsRepo.count()).isNotZero();
	}
}