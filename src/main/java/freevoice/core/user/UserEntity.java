package freevoice.core.user;

import freevoice.features.chat.models.ChatComment;
import freevoice.features.forum.models.ForumComment;
import freevoice.features.forum.models.ForumPost;
import freevoice.features.videos.models.VideoComment;
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

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;

    @Lob
    private byte[] profileImage;

    @OneToMany(cascade=ALL, mappedBy = "user")
    public List<VideoComment> videoComments;

    @OneToMany(cascade=ALL, mappedBy = "user")
    public List<ChatComment> chatComments;

    @OneToMany(cascade=ALL, mappedBy = "userEntity")
    public List<ForumPost> forumPosts;

    @OneToMany(cascade=ALL, mappedBy = "userEntity")
    public List<ForumComment> forumComments;

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
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    @OneToMany(cascade=ALL, mappedBy = "userEntity")
//    public List<Activity> activities;

}