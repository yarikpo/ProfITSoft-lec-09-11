package ua.clamor1s.task911.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.clamor1s.task911.dao.OperationDao;
import ua.clamor1s.task911.dto.OperationSaveDto;
import ua.clamor1s.task911.model.Operation;

import java.util.List;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationDao dao;


    @Override
    public void deleteOperation(int id) {
        dao.deleteOperation(id);
    }

    @Override
    public void updateOperation(Operation operation) {
        dao.updateOperation(operation);
    }

    @Override
    public int saveOperation(OperationSaveDto dto) {
        return dao.saveOperation(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public Operation findOperationById(int id) {
        return dao.findOperationById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Operation> findAll() {
        return dao.findAll();
    }
}
