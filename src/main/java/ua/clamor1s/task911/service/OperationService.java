package ua.clamor1s.task911.service;

import ua.clamor1s.task911.dto.OperationSaveDto;
import ua.clamor1s.task911.model.Operation;

import java.util.List;

public interface OperationService {

    void deleteOperation(int id);

    void updateOperation(Operation operation);

    int saveOperation(OperationSaveDto operationDto);

    Operation findOperationById(int id);

    List<Operation> findAll();

}
