package edu.alenkin.springboot_hateoas_app.utils;

import edu.alenkin.springboot_hateoas_app.model.BaseEntity;
import edu.alenkin.springboot_hateoas_app.model.User;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class ValidationUtil {
    public static void checkNew(BaseEntity entity) {
        if (! entity.isNew()) {
            throw new IllegalArgumentException("Entity is not new because id != null");
        }
    }

    public static void checkIdConsistance(BaseEntity entity, Integer id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (!entity.getId().equals(id)) {
            throw new IllegalArgumentException("ID not consistent!");
        }
    }
}
