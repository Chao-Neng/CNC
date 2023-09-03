package art.relev.springboot3.cnc.param;

import art.relev.springboot3.cnc.exclude.CNCParam;
import art.relev.springboot3.cnc.model.Resource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ResourceParam {
    @Data
    @Schema(name = "QueryChildResourceParam", description = "查询子级资源参数")
    public static class QueryChild implements CNCParam, Specification<Resource> {
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @Schema(description = "资源类型名称")
        private String typeName;
        @Schema(description = "页码")
        @Min(value = 1, message = "页码不能小于1")
        private Integer pageNumber = 1;
        @Schema(description = "页容量")
        private Integer pageSize = 10;
        // TODO 排序

        @Override
        public Predicate toPredicate(Root<Resource> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("parentResource"), Resource.builder().id(resourceId).build()));
            if (typeName != null) {
                predicateList.add(criteriaBuilder.equal(root.get("typeName"), typeName));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }

        @Override
        @JsonIgnore
        public Long getParentResourceId() {
            return null;
        }
    }
}
