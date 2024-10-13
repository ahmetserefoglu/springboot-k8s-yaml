package com.javatechie.k8s;

import com.javatechie.k8s.job.ReportGenerationScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@RestController
@EnableScheduling
public class SpringbootK8sDemoApplication implements CommandLineRunner {

    @Autowired
    private ReportGenerationScheduler scheduler;

	/*@GetMapping("/message")
	public String displayMessage(){
		return "Congratulation you successfully deployed your application to kubernetes !!";
	}*/

    public static void main(String[] args) {
        SpringApplication.run(SpringbootK8sDemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        scheduler.generateReportAndSendEmail();
    }
}
