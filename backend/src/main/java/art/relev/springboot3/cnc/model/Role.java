package art.relev.springboot3.cnc.model;

import art.relev.springboot3.cnc.exclude.CNCResource;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_role")
@Schema(description = "角色")
public class Role implements CNCResource {
    public static final String RESOURCE_NAME = "role";
    public static final String[] PARENT_RESOURCE_NAME_ARRAY = new String[]{null};
    @Id
    @Schema(description = "角色ID")
    private Long id;
    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "f_resource_id")
    @Schema(description = "资源")
    private Resource resource;
    @Column(name = "f_name", unique = true, nullable = false)
    @Schema(description = "角色名称")
    private String name;
}
