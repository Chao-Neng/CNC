package art.relev.springboot3.cnc.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_authority", uniqueConstraints = @UniqueConstraint(columnNames = {"f_name", "f_endpoint_name"}))
@Schema(description = "权限")
public class Authority implements Serializable {
    @Id
    @Column(name = "f_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "权限ID")
    private Long id;
    @Column(name = "f_name", nullable = false)
    @Schema(description = "权限名称")
    private String name;
    @Column(name = "f_endpoint_name", nullable = false)
    @Schema(description = "端点名称")
    private String endpointName;
    @Column(name = "f_allow_owner")
    @Schema(description = "允许资源所属者")
    private Boolean allowOwner;
    @Column(name = "f_black_list_mode")
    @Schema(description = "黑名单模式")
    private Boolean blackListMode;
    @ManyToMany
    @Builder.Default
    @JoinTable(name = "t_authority_role", joinColumns = @JoinColumn(name = "f_authority_id"), inverseJoinColumns = @JoinColumn(name = "f_role_id"))
    @Schema(description = "角色集合")
    private Set<Role> roleSet = new HashSet<>();
}
