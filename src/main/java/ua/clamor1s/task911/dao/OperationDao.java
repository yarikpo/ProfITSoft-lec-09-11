package ua.clamor1s.task911.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.clamor1s.task911.model.Operation;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class OperationDao {

    @Autowired
    private EntityManager entityManager;

    public void saveOperation(Operation operation) {
        entityManager.persist(operation);
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

}
