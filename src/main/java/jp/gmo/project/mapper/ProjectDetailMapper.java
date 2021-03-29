package jp.gmo.project.mapper;

import jp.gmo.project.entity.ProjectDetailEntity;
import jp.gmo.project.request.AddProjectRequest;

public class ProjectDetailMapper {

    public ProjectDetailEntity dtoToEntity(Integer projectCode, AddProjectRequest projectDto, int position) {
        if (projectCode == null || projectDto == null) {
            return null;
        } else {
            ProjectDetailEntity detailEntity = new ProjectDetailEntity();
            detailEntity.setProjectCode(projectCode);
            switch (position) {
                case 1:
                    detailEntity.setEmployeeCode(projectDto.getProjectManagement());
                    detailEntity.setPositionCode(1);
                case 2:
                    detailEntity.setEmployeeCode(projectDto.getTeamLead());
                    detailEntity.setPositionCode(2);
                case 3:
                    detailEntity.setEmployeeCode(projectDto.getBrSE());
                    detailEntity.setPositionCode(3);
            }
            return detailEntity;
        }
    }
}
