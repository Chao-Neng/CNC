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
@Table(name = "t_article")
@Schema(description = "文章")
public class Article implements CNCResource {
    public static final String RESOURCE_NAME = "article";
    public static final String[] PARENT_RESOURCE_NAME_ARRAY = new String[]{"chunk"};
    @Id
    @Schema(description = "文章ID")
    private Long id;
    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "f_resource_id")
    @Schema(description = "资源")
    private Resource resource;
    @Column(name = "f_title", nullable = false)
    @Schema(description = "文章标题")
    private String title;
    @Column(name = "f_content", nullable = false, columnDefinition = "TEXT")
    @Schema(description = "文章内容")
    private String content;
}
