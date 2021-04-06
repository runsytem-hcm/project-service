package jp.gmo.project.repository;

import jp.gmo.project.dto.ProjectSearchDto;
import jp.gmo.project.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {

    @Query(name = "getListProject", nativeQuery = true)
    List<ProjectSearchDto> getListProject(@Param("projectName") String projectName
            , @Param("startDate") String startDate
            , @Param("endDate") String endDate
            , @Param("offset") int offset
            , @Param("limit") int limit);

    
    @Query(name = "countProject", nativeQuery = true)
    BigInteger countProject(@Param("projectName") String projectName
            , @Param("startDate") String startDate
            , @Param("endDate") String endDate);

    Optional<ProjectEntity> findByProjectCodeAndDeleteFlag(int projectCode, int deleteFlag);

    Optional<ProjectEntity> findByProjectNameJPAndProjectNameVNAndDeleteFlag(String projectNameJP, String projectNameVN, int deleteFlag);

    @Query(name = "checkRoleOfProject")
    Integer checkRoleOfProject(@Param("projectCode") int projectCode,
                               @Param("employeeCode") String employeeCode,
                               @Param("positionCode") List<String> positionCode);
}
