package com.proekt.recepti.model;

import com.proekt.recepti.model.emuns.RolesEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="users")
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, length = 20, nullable = false)
    private String username;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date birth_date;

    @Enumerated(EnumType.STRING)
    private RolesEnum role;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeModel> recipes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeModel> liked = new ArrayList<>();

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    public UserModel(String username, String email, String password, Date birth_date) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.birth_date = birth_date;
        this.role = RolesEnum.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
