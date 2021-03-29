package jp.gmo.project.mapper;

import jp.gmo.project.constant.RegexConstants;
import jp.gmo.project.entity.ProjectEntity;
import jp.gmo.project.request.AddProjectRequest;
import jp.gmo.project.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ProjectMapper {

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
            projectEntity.setEmailCC(StringUtils.join(",", projectDto.getEmailCC()));

            return projectEntity;
        }
    }
}
