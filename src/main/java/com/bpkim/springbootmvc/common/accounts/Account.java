package com.bpkim.springbootmvc.common.accounts;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account implements UserDetails {

    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String username;
    private String password;

    private String uid;

    private boolean accountNotExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<AccountRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles;
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_"+r.name()))
                .collect(Collectors.toSet());
    }


    @Override
    public String getUsername() {
        return this.username;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        // true : 만료되지 않음
        return accountNotExpired;
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        // true : 계정 잠금 안됨
        return accountNonLocked;
    }

    // 패스워드 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        // true : 패스워드 만료 안됨
        return credentialsNonExpired;
    }

    // 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        // true : 사용 가능
        return this.enabled;
    }
}
