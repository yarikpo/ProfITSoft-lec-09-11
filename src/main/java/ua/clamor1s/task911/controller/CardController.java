package ua.clamor1s.task911.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.clamor1s.task911.service.CardServiceImpl;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardServiceImpl cardService;

    

}
