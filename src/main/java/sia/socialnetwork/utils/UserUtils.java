package sia.socialnetwork.utils;

import jakarta.persistence.EntityManager;
import sia.socialnetwork.data.UserTable;

import java.util.List;

public class UserUtils {

    private EntityManager entityManager;

    public UserUtils(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<UserTable> buildUsers(){
        return entityManager.createQuery("select u from UserTable u", UserTable.class).getResultList();
    }

}
