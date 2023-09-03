package art.relev.springboot3.cnc.model;

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
@Table(name = "t_authority")
@Schema(description = "权限")
public class Authority {
    @Id
    @Column(name = "f_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "权限ID")
    private Long id;
    // TODO: 唯一约束
    @Column(name = "f_name", nullable = false)
    @Schema(description = "权限名称")
    private String name;
    @Column(name = "f_resource_name", nullable = false)
    @Schema(description = "资源名称")
    private String resourceName;
    @Column(name = "f_allow_owner")
    @Schema(description = "允许资源所属者")
    private Boolean allowOwner;
    // TODO: 黑名单可能存在问题 user role list 可能存在冲突
    @Column(name = "f_black_list_mode")
    @Schema(description = "黑名单模式")
    private Boolean blackListMode;
    @ManyToMany
    @Builder.Default
    @JoinTable(name = "t_authority_role", joinColumns = @JoinColumn(name = "f_authority_id"), inverseJoinColumns = @JoinColumn(name = "f_role_id"))
    @Schema(description = "角色集合")
    private Set<Role> roleSet = new HashSet<>();
}
