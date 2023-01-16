package ua.clamor1s.task911.service;

import ua.clamor1s.task911.model.Operation;

import java.util.List;

public interface OperationService {

    void deleteOperation(int id);

    void updateOperation(Operation operation);

    void saveOperation(Operation operation);

    Operation findOperationById(int id);

    List<Operation> findAll();

}
