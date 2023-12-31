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
@Table(name = "t_resource")
@NamedEntityGraph(name = "resourceIdGraph", attributeNodes = @NamedAttributeNode("id"))
@Schema(description = "资源")
public class Resource implements Serializable {
    public static final String RESOURCE_NAME = "resource";
    @Id
    @Column(name = "f_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "资源ID")
    private Long id;
    @Column(name = "f_resource_name", nullable = false)
    @Schema(description = "资源类型名称")
    private String resourceName;
    @ManyToOne
    @JoinColumn(name = "f_parent_resource_id")
    @Schema(description = "父级资源")
    private Resource parentResource;
    @Builder.Default
    @ElementCollection
    @Column(name = "f_owner_id")
    @CollectionTable(name = "t_chunk_owner", joinColumns = @JoinColumn(name = "f_chunk_id"))
    @Schema(description = "板块所有者ID集合")
    private Set<Long> ownerIdSet = new HashSet<>();
}
