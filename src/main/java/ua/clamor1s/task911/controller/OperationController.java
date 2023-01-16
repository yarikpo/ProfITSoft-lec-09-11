package ua.clamor1s.task911.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.clamor1s.task911.dto.OperationSaveDto;
import ua.clamor1s.task911.dto.RestResponse;
import ua.clamor1s.task911.service.OperationService;


@RestController
@RequestMapping("/api/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createOperation(@RequestBody OperationSaveDto dto) {
        int id = operationService.saveOperation(dto);
        return new RestResponse(String.valueOf(id));
    }

}
