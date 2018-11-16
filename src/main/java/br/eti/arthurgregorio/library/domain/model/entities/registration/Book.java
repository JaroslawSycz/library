package br.eti.arthurgregorio.library.domain.model.entities.registration;

import br.eti.arthurgregorio.library.domain.model.entities.PersistentEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static br.eti.arthurgregorio.library.infrastructure.utilities.DefaultSchemes.REGISTRATION;
import static br.eti.arthurgregorio.library.infrastructure.utilities.DefaultSchemes.REGISTRATION_AUDIT;

/**
 * The Book entity
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 2.0.0, 14/11/2018
*/
@Entity
@Audited
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "books", schema = REGISTRATION)
@AuditTable(value = "books", schema = REGISTRATION_AUDIT)
public class Book extends PersistentEntity {

    @Getter
    @Setter
    @NotBlank(message = "{book.isbn}")
    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;
    @Getter
    @Setter
    @NotBlank(message = "{book.title}")
    @Column(name = "title", nullable = false, length = 90)
    private String title;
    @Getter
    @Setter
    @NotBlank(message = "{book.summary}")
    @Column(name = "summary", nullable = false, columnDefinition = "TEXT")
    private String summary;

    @Getter
    @Setter
    @ManyToOne(optional = false)
    @NotNull(message = "{book.author}")
    @JoinColumn(name = "id_author", nullable = false)
    private Author author;
}
