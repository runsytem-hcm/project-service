package jp.gmo.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jp.gmo.project.dto.ProjectSearchDto;
import jp.gmo.project.dto.RankDto;
import jp.gmo.project.request.AddProjectRequest;
import jp.gmo.project.request.UpdateProjectRequest;
import jp.gmo.project.response.PageAndDataResponse;
import jp.gmo.project.response.ProjectDetailResponse;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

public interface ProjectService {

    void executeAddProject(String email, AddProjectRequest request);

    List<RankDto> executeGetListRank();

    PageAndDataResponse<List<ProjectSearchDto>> executeGetListProject(String projectName, String startDate, String endDate, int page, int size);

    ProjectDetailResponse executeDetailProject(String projectCode);

    void executeUpdateProject(String email, UpdateProjectRequest request) throws ResourceAccessException, JsonProcessingException;
}
