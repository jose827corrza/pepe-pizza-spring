package io.jose827corrza.github.pepepizza.persistence.audit;

import io.jose827corrza.github.pepepizza.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {
    private PizzaEntity currentValue;

    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity entity) {
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE" + currentValue.toString());
        System.out.println("NEW VALUE" + entity.toString());
    }

    @PreRemove
    public void onPreDelete(PizzaEntity entity) {
        System.out.println(entity.toString());
    }

    @PostLoad
    public void onPostLoad(PizzaEntity entity) {
        System.out.println("POST LOAD");
        this.currentValue = SerializationUtils.clone(entity);
    }
}
