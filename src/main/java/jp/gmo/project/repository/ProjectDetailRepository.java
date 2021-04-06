package jp.gmo.project.repository;

import jp.gmo.project.entity.ProjectDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDetailRepository extends JpaRepository<ProjectDetailEntity, Integer> {
    //List<ProjectDetailEntity> findByProjectCodeAndDeleteFlag(int projectCode, int deleteFlag);
}
