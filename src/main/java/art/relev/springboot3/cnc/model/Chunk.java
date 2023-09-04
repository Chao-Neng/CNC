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
@Table(name = "t_chunk")
@Schema(description = "板块")
public class Chunk implements CNCResource {
    public static final String RESOURCE_NAME = "chunk";
    public static final String[] PARENT_RESOURCE_NAME_ARRAY = new String[]{null, "chunk"};
    @Id
    @Schema(description = "板块ID")
    private Long id;
    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "f_resource_id")
    @Schema(description = "资源")
    private Resource resource;
    @Column(name = "f_name", nullable = false)
    @Schema(description = "板块名称")
    private String name;
    @Column(name = "f_description", nullable = false, columnDefinition = "TEXT")
    @Schema(description = "板块描述")
    private String description;
}
