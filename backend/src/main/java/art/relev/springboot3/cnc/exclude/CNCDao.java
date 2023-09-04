package art.relev.springboot3.cnc.exclude;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CNCDao<T> extends JpaRepository<T, Long> {
}