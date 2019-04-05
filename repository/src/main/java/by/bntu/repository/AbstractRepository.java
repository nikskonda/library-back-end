//package by.bntu.repository;
//
//import by.bntu.firt.model.BaseEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.lang.reflect.ParameterizedType;
//import java.util.Optional;
//import java.util.Set;
//
//public abstract class AbstractRepository<T extends BaseEntity, S>  implements JpaRepository {
//
//    private final Class<T> entityClass;
//
//    @PersistenceContext
//    protected EntityManager entityManager;
//
//    protected Query<T, S> query;
//
//
//    @SuppressWarnings("unchecked")
//    protected AbstractRepository() {
//        this.entityClass =
//                (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//    }
//
//    /**
//     * Constructor.
//     *
//     * @param query - query.
//     */
//    protected AbstractRepository(Query<T, S> query) {
//        this.query = query;
//        this.entityClass =
//                (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//    }
//
//    /**
//     * Return information about object by id.
//     *
//     * @param id - Id of the object.
//     * @return information about object from database.
//     * @see Optional
//     */
//    public Optional<T> read(Integer id) {
//        return Optional.ofNullable(entityManager.find(entityClass, id));
//    }
//
//    /**
//     * Add new entity in system.
//     *
//     * @param obj - BaseEntity.
//     * @return entity if added was successful.
//     */
//    public Optional<T> create(T obj) {
//        entityManager.persist(obj);
//        return Optional.ofNullable(obj);
//    }
//
//    /**
//     * Update entity in system.
//     *
//     * @param obj - BaseEntity.
//     * @return entity if updated was successful.
//     */
//    public Optional<T> update(T obj) {
//        return Optional.ofNullable(entityManager.merge(obj));
//    }
//
//
//    /**
//     * Delete entity in system.
//     *
//     * @param obj - BaseEntity.
//     */
//    public void delete(T obj) {
//        entityManager.remove(obj);
//    }
//
//
//    /**
//     * Return list of entities.
//     *
//     * @return list of entities.
//     */
//    public Set<T> getAll() {
//        return entityManager.createQuery(query.getAll(entityManager.getCriteriaBuilder())).getResultList();
//    }
//
//    public Page<T> getAllByPage(Pageable pageable) {
//        Page<T> list = new Page<>();
//        list.setPageable(pageable);
//        list.setList(entityManager.createQuery(query.getAll(entityManager.getCriteriaBuilder()))
//                .setFirstResult(list.getPageable().getFirstItemNumber())
//                .setMaxResults(list.getPageable().getLastItemNumber())
//                .getResultList());
//        list.setTotalCountOfItems(getCount());
//        return list;
//    }
//
//    /**
//     * Return entity by search criteria.
//     *
//     * @param searchCriteria - criteria of search
//     * @return entity.
//     */
//    public Optional<T> searchForObject(S searchCriteria) {
//        TypedQuery<T> typedQuery = entityManager
//                .createQuery(query.getSearchQuery(searchCriteria, entityManager.getCriteriaBuilder()));
////        List<User> users = (List<User>) typedQuery.getResultList();
//        return typedQuery.getResultList().isEmpty() ?
//                Optional.empty() :
//                Optional.ofNullable(typedQuery.getSingleResult());
//    }
//
//    /**
//     * Return list of entities by search criteria.
//     *
//     * @param searchCriteria - criteria of search
//     * @return list of entities.
//     */
//    public List<T> searchForList(S searchCriteria) {
//        return entityManager
//                .createQuery(query.getSearchQuery(searchCriteria, entityManager.getCriteriaBuilder()))
//                .getResultList();
//    }
//
//    public Page<T> searchForList(S searchCriteria, Pageable pageable) {
//        Page<T> list = new Page<>();
//        list.setPageable(pageable);
//        list.setList(entityManager
//                .createQuery(query.getSearchQuery(searchCriteria, entityManager.getCriteriaBuilder()))
//                .setFirstResult(list.getPageable().getFirstItemNumber())
//                .setMaxResults(list.getPageable().getSize())
//                .getResultList());
//        list.setTotalCountOfItems(getCount(searchCriteria));
//        return list;
//    }
//
//    public List<Object[]> searchWithStoredProcedure(String procedureName) {
//        return entityManager
//                .createStoredProcedureQuery(procedureName)
//                .getResultList();
//    }
//
//    private Long getCount() {
//        return entityManager
//                .createQuery(query.getCount(entityManager.getCriteriaBuilder()))
//                .getSingleResult();
//    }
//
//    private Long getCount(S searchCriteria) {
////        return entityManager
////                .createQuery(query.getCount(searchCriteria, entityManager.getCriteriaBuilder()))
////                .getSingleResult();
//        return (long) entityManager
//                .createQuery(query.getSearchQuery(searchCriteria, entityManager.getCriteriaBuilder()))
//                .getResultList().size();
//    }
//
//}
//
