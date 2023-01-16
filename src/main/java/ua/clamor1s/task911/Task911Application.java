package ua.clamor1s.task911;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.clamor1s.task911.model.Card;
import ua.clamor1s.task911.model.Operation;
import ua.clamor1s.task911.service.CardServiceImpl;
import ua.clamor1s.task911.service.OperationServiceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Task911Application {


	public static void main(String[] args) {
		SpringApplication.run(Task911Application.class, args);
	}

}
