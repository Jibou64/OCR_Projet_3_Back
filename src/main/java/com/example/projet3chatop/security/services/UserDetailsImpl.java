package com.example.projet3chatop.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Builder
@AllArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 1L;

  // Identifiant de l'utilisateur
  private Long id;

  // Nom d'utilisateur
  private String username;

  // Nom de l'utilisateur
  private String name;

  // Mot de passe de l'utilisateur (ignoré lors de la sérialisation JSON)
  @JsonIgnore
  private String password;

  // Récupère les autorisations de l'utilisateur (dans ce cas, une collection vide)
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return new HashSet<GrantedAuthority>();
  }

  // Indique si le compte de l'utilisateur est expiré (toujours actif)
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  // Indique si le compte de l'utilisateur est verrouillé (jamais verrouillé)
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  // Indique si les informations d'identification de l'utilisateur sont expirées (jamais expirées)
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  // Indique si le compte de l'utilisateur est activé (toujours activé)
  @Override
  public boolean isEnabled() {
    return true;
  }

  // Compare cet utilisateur avec un autre objet
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }
}
