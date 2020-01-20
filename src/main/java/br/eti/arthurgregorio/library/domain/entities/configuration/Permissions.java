package br.eti.arthurgregorio.library.domain.entities.configuration;

import lombok.Getter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * The possible permissions to use in the authorization process
 *
 * @author Arthur Gregorio
 *
 * @version 1.1.0
 * @since 1.0.0, 12/01/2018
 */
@Named
@ApplicationScoped
public class Permissions implements Serializable {

    @Getter
    @PermissionGrouper("author")
    private final String AUTHOR_ADD = "author:add";
    @Getter
    @PermissionGrouper("author")
    private final String AUTHOR_UPDATE = "author:update";
    @Getter
    @PermissionGrouper("author")
    private final String AUTHOR_DELETE = "author:delete";
    @Getter
    @PermissionGrouper("author")
    private final String AUTHOR_DETAIL = "author:detail";
    @Getter
    @PermissionGrouper("author")
    private final String AUTHOR_ACCESS = "author:access";

    @Getter
    @PermissionGrouper("book")
    private final String BOOK_ADD = "book:add";
    @Getter
    @PermissionGrouper("book")
    private final String BOOK_UPDATE = "book:update";
    @Getter
    @PermissionGrouper("book")
    private final String BOOK_DELETE = "book:delete";
    @Getter
    @PermissionGrouper("book")
    private final String BOOK_DETAIL = "book:detail";
    @Getter
    @PermissionGrouper("book")
    private final String BOOK_ACCESS = "book:access";

    @Getter
    @PermissionGrouper("user")
    private final String USER_ADD = "user:add";
    @Getter
    @PermissionGrouper("user")
    private final String USER_UPDATE = "user:update";
    @Getter
    @PermissionGrouper("user")
    private final String USER_DELETE = "user:delete";
    @Getter
    @PermissionGrouper("user")
    private final String USER_DETAIL = "user:detail";
    @Getter
    @PermissionGrouper("user")
    private final String USER_ACCESS = "user:access";

    @Getter
    @PermissionGrouper("group")
    private final String GROUP_ADD = "group:add";
    @Getter
    @PermissionGrouper("group")
    private final String GROUP_UPDATE = "group:update";
    @Getter
    @PermissionGrouper("group")
    private final String GROUP_DELETE = "group:delete";
    @Getter
    @PermissionGrouper("group")
    private final String GROUP_DETAIL = "group:detail";
    @Getter
    @PermissionGrouper("group")
    private final String GROUP_ACCESS = "group:access";

    @Getter
    @PermissionGrouper("import-user")
    private final String IMPORT_USER_ACCESS = "import-user:access";

    /**
     * @return a permission list in the {@link Authorization} format
     */
    public List<Authorization> toAuthorizationList() {

        final List<Authorization> authorizations = new ArrayList<>();

        for (Field field : this.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            try {
                final PermissionGrouper grouper = field
                        .getAnnotation(PermissionGrouper.class);

                final String permission
                        = String.valueOf(field.get(Permissions.this));

                final String functionality = grouper.value();

                authorizations.add(
                        new Authorization(functionality,
                                permission.replace(functionality + ":", "")));
            } catch (IllegalAccessException ex) { }
        }
        return authorizations;
    }
    
    /**
     * Annotation to help grouping ther authorizations
     */
    @Documented
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    private @interface PermissionGrouper {

        /**
         * @return the value of this grouper
         */
        String value() default "";
    }
}
