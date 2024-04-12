package com.example.projet3chatop.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Data
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = {"id"})
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    // User ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User email, unique and limited to 50 characters
    @NonNull
    @Size(max = 50)
    @Email
    private String email;

    // Indicates whether the user is an administrator or not
    @NonNull
    private boolean admin;

    // User name, limited to 20 characters
    @NonNull
    @Size(max = 20)
    @Column(name = "name")
    private String name;

    // User password, limited to 120 characters
    @NonNull
    @Size(max = 120)
    private String password;

    // User creation date
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Date of the last update of the user
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}