package jp.gmo.project.repository;

import jp.gmo.project.dto.ProjectSearchDto;
import jp.gmo.project.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {

    @Query(name = "getListProject", nativeQuery = true)
    List<ProjectSearchDto> getListProject(@Param("projectName") String projectName
            , @Param("startDate") String startDate
            , @Param("startDate") String endDate
            , @Param("offset") int offset
            , @Param("limit") int limit);

    @Query(name = "countProject", nativeQuery = true)
    BigInteger countProject(@Param("projectName") String projectName
            , @Param("startDate") String startDate
            , @Param("startDate") String endDate);

    Optional<ProjectEntity> findByProjectCode(int projectCode);
}
