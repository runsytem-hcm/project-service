package jp.gmo.project.repository;

import jp.gmo.project.entity.ProjectDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectDetailRepository extends JpaRepository<ProjectDetailEntity, Integer> {
    List<ProjectDetailEntity> findByProjectCode(int projectCode);
}
