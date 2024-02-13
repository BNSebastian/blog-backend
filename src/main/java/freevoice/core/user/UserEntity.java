package freevoice.core.user;

import freevoice.features.forum.comments.models.ForumComment;
import freevoice.features.forum.posts.models.ForumPost;
import freevoice.features.videos.comments.models.VideoComment;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    private Boolean locked = false;

    @Builder.Default
    private Boolean enabled = true;

    private String firstname;

    private String lastname;

    private String username;

    private String email;

    private String password;

    private Long profileImageId;

    @OneToMany(cascade = ALL, mappedBy = "user")
    public List<VideoComment> videoComments;

    @OneToMany(cascade = ALL, mappedBy = "userEntity")
    public List<ForumPost> forumPosts;

    @OneToMany(cascade = ALL, mappedBy = "userEntity")
    public List<ForumComment> forumComments;

    public UserEntity(Role role,
            String firstname,
            String lastname,
            String username,
            String email,
            String password,
            Boolean locked,
            Boolean enabled) {
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.locked = locked;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    // @OneToMany(cascade=ALL, mappedBy = "userEntity")
    // public List<Activity> activities;

}