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
@Slf4j
public class Task911Application implements CommandLineRunner {

	@Autowired
	private CardServiceImpl cardService;
	@Autowired
	private OperationServiceImpl operationService;

	public static void main(String[] args) {
		SpringApplication.run(Task911Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		log.info("------ begin ------");

		Card card = Card.builder()
				.name("Abrah")
				.surname("Ava")
				.code("0007783984648374294")
				.cvv(6334)
				.creationDate(Date.valueOf(LocalDate.of(2022, 1, 28)))
				.build();

		cardService.saveCard(card);
//		System.out.println(card);
		List<Card> cards = cardService.listAll();

		log.info(cardService.listAll().toString());
		card.setName("Alexandro");
		cardService.updateCard(card);

		log.info(cardService.getCard(1).toString());

//		cardService.deleteCard(1);
//		log.info(cardService.getCard(1).toString());
//		System.out.println(cardService.listAll().toString());


		Operation operation = Operation.builder()
						.amount(20000)
						.operationDatetime(Timestamp.valueOf("2018-09-01 09:01:15"))
						.card(cardService.getCard(1))
						.build();

		operationService.saveOperation(operation);
		log.info(operationService.findOperationById(1).toString());
		operation.setAmount(12300);
		operationService.updateOperation(operation);
		log.info(operationService.findAll().toString());
/*
		operationService.deleteOperation(1);
		System.out.println(operationService.findAll().toString());
*/


		log.info("------ end ------");
	}
}
