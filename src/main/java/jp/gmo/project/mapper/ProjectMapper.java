package jp.gmo.project.mapper;

import jp.gmo.project.constant.RegexConstants;
import jp.gmo.project.dto.ProjectDetailDto;
import jp.gmo.project.dto.ProjectDto;
import jp.gmo.project.entity.ProjectEntity;
import jp.gmo.project.request.AddProjectRequest;
import jp.gmo.project.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectMapper {

    private final ProjectDetailMapper projectDetailMapper;

    public ProjectEntity dtoToEntity(AddProjectRequest projectDto) {

        if (projectDto == null) {
            return null;
        } else {
            ProjectEntity projectEntity = new ProjectEntity();

            if (projectDto.getProjectNameJP() != null) {
                projectEntity.setProjectNameJP(projectDto.getProjectNameJP());
            }

            if (projectDto.getProjectNameVN() != null) {
                projectEntity.setProjectNameVN(projectDto.getProjectNameVN());
            }

            projectEntity.setBillableEffort(projectDto.getBillableEffort());
            projectEntity.setStartDate(DateUtils.stringToDate(projectDto.getStartDate(), RegexConstants.CONST_STR_PATTERN_YYYY_MM_DD));

            if (projectDto.getEndDate() != null) {
                projectEntity.setEndDate(DateUtils.stringToDate(projectDto.getEndDate(), RegexConstants.CONST_STR_PATTERN_YYYY_MM_DD));
            }

            projectEntity.setCustomerName(projectDto.getCustomerName());
            projectEntity.setSale(projectDto.getSale());
            projectEntity.setRank(Integer.parseInt(projectDto.getRank()));
            projectEntity.setScope(projectDto.getScope());
            projectEntity.setObjectives(projectDto.getObjectives());
            projectEntity.setEmailCC(String.join(",", projectDto.getEmailCC()));

            return projectEntity;
        }
    }

    public ProjectDto entityToDto(ProjectEntity entity) {
        if (entity == null) {
            return null;
        } else {
            ProjectDto projectDto = new ProjectDto();
            projectDto.setProjectCode(entity.getProjectCode());
            if (entity.getProjectNameJP() != null) {
                projectDto.setProjectNameJP(entity.getProjectNameJP());
            }
            if (entity.getProjectNameVN() != null) {
                projectDto.setProjectNameVN(entity.getProjectNameVN());
            }
            if (entity.getBillableEffort() != null) {
                projectDto.setBillableEffort(entity.getBillableEffort());
            }
            projectDto.setStartDate(entity.getStartDate());
            if (entity.getEndDate() != null) {
                projectDto.setEndDate(entity.getEndDate());
            }

            projectDto.setCustomerName(entity.getCustomerName());
            projectDto.setSale(entity.getSale());
            projectDto.setRank(entity.getRank());
            projectDto.setScope(entity.getScope());
            projectDto.setObjectives(entity.getObjectives());
            projectDto.setEmailCC(entity.getEmailCC());
            projectDto.setMemberList(entity.getProjectDetailList().stream().map(projectDetailMapper::entityToDto).collect(Collectors.toList()));

            return projectDto;
        }
    }
}
