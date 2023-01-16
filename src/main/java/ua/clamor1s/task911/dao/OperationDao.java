package ua.clamor1s.task911.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.clamor1s.task911.dto.OperationSaveDto;
import ua.clamor1s.task911.model.Operation;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class OperationDao {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CardDao cardDao;

    public int saveOperation(OperationSaveDto dto) {
        Operation operation = saveDto2Operation(dto);
        entityManager.persist(operation);
        return operation.getOperationId();
    }

    public void updateOperation(Operation operation) {
        entityManager.merge(operation);
    }

    public void deleteOperation(int operationId) {
        int isSuccess = entityManager.createQuery("DELETE FROM Operation o WHERE o.operationId=:id")
                .setParameter("id", operationId)
                .executeUpdate();

        if (isSuccess == 0) {
            throw new RuntimeException("Troubles with deleting operation.");
        }
    }

    public Operation findOperationById(int id) {
        return entityManager.find(Operation.class, id);
    }

    public List<Operation> findAll() {
        return entityManager.createQuery("SELECT o FROM Operation o", Operation.class).getResultList();
    }

    private Operation saveDto2Operation(OperationSaveDto dto) {
        return Operation.builder()
                .card(cardDao.findCard(dto.getCardId()))
                .amount(dto.getAmount())
                .operationDatetime(dto.getOperationDatetime())
                .build();
    }

}
