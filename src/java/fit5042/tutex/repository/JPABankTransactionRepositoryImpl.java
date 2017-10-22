package fit5042.tutex.repository;

import fit5042.tutex.repository.entities.BankTransaction;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author tomz123
 */
@Stateless
public class JPABankTransactionRepositoryImpl implements TransactionRepository {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void addBankTransaction(BankTransaction bankTransaction) throws Exception { 
        entityManager.persist(bankTransaction);
    }

    @Override
    public BankTransaction searchBankTransactionById(int id) throws Exception {    
        BankTransaction bankTransaction = entityManager.find(BankTransaction.class, id);
        return bankTransaction;
    }

    @Override
    public List<BankTransaction> getAllBankTransactions() throws Exception {        
        return entityManager.createNamedQuery(BankTransaction.GET_ALL_QUERY_NAME).getResultList();
    }
        
    @Override
    public void removeBankTransaction(int transacitonId) throws Exception {          
        BankTransaction bankTransaction = this.searchBankTransactionById(transacitonId);
      
        if (bankTransaction != null) {
            entityManager.remove(bankTransaction);
        }
    }

    @Override
    public void editBankTransaction(BankTransaction bankTransaction) throws Exception {      
        entityManager.merge(bankTransaction);
    }  
        
    @Override
    public List<BankTransaction> searchBankTransactionByName(String bankTransactionName) throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(BankTransaction.class);
        Root<BankTransaction> p = query.from(BankTransaction.class);

        return entityManager.createQuery(query).getResultList();
    }
    
    @Override
    public List<BankTransaction> searchBankingTransactionByNo(int bankTransactionNo) throws Exception {      
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(BankTransaction.class);
        Root<BankTransaction> p = query.from(BankTransaction.class);
        //query.select(p).where(builder.lessThanOrEqualTo(p.get("price").as(Double.class), budget));
        
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<BankTransaction> searchbankingTransactionByType(String type) throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(BankTransaction.class);
        Root<BankTransaction> p = query.from(BankTransaction.class);
        //query.select(p).where(builder.lessThanOrEqualTo(p.get("price").as(Double.class), budget));
        
        return entityManager.createQuery(query).getResultList();    }
}
