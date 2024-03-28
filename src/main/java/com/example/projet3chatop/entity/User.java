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

    // Identifiant de l'utilisateur
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Email de l'utilisateur, unique et limité à 50 caractères
    @NonNull
    @Size(max = 50)
    @Email
    private String email;

    // Indique si l'utilisateur est un administrateur ou non
    @NonNull
    private boolean admin;

    // Nom de l'utilisateur, limité à 20 caractères
    @NonNull
    @Size(max = 20)
    @Column(name = "name")
    private String name;

    // Mot de passe de l'utilisateur, limité à 120 caractères
    @NonNull
    @Size(max = 120)
    private String password;

    // Date de création de l'utilisateur
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Date de la dernière mise à jour de l'utilisateur
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}