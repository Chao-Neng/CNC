package art.relev.springboot3.cnc.model;

import art.relev.springboot3.cnc.exclude.CNCResource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user")
@Schema(description = "用户")
public class User implements CNCResource {
    public static final String RESOURCE_NAME = "user";
    @Id
    @Schema(description = "用户ID")
    private Long id;
    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "f_resource_id")
    @Schema(description = "资源")
    private Resource resource;
    @Column(name = "f_name", unique = true, nullable = false)
    @Schema(description = "用户名称")
    private String name;
    @JsonIgnore
    @Column(name = "f_password", nullable = false)
    private String password;
    @ManyToMany
    @Builder.Default
    @JoinTable(name = "t_user_role", joinColumns = @JoinColumn(name = "f_user_id"), inverseJoinColumns = @JoinColumn(name = "f_role_id"))
    @Schema(description = "用户角色集合")
    private Set<Role> roleSet = new HashSet<>();
}
