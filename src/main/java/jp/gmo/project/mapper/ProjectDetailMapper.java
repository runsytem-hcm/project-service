package jp.gmo.project.mapper;

import jp.gmo.project.dto.ProjectDetailDto;
import jp.gmo.project.entity.ProjectDetailEntity;
import jp.gmo.project.request.AddProjectDetailRequest;
import jp.gmo.project.request.AddProjectRequest;
import org.springframework.stereotype.Service;

@Service
public class ProjectDetailMapper {

    public ProjectDetailEntity dtoToEntity(AddProjectDetailRequest projectDto) {
        if (projectDto == null) {
            return null;
        } else {
            ProjectDetailEntity detailEntity = new ProjectDetailEntity();
            detailEntity.setEmployeeCode(projectDto.getEmployeeCode());
            detailEntity.setPositionCode(Integer.parseInt(projectDto.getPositionCode()));
            return detailEntity;
        }
    }

    public ProjectDetailDto entityToDto(ProjectDetailEntity entity){
        if(entity == null){
            return null;
        } else {
            ProjectDetailDto detailDto = new ProjectDetailDto();
            detailDto.setEmployeeCode(entity.getEmployeeCode());
            detailDto.setPositionCode(entity.getPositionCode());
            return  detailDto;
        }
    }
}
