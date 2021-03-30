package jp.gmo.project.service;

import jp.gmo.project.dto.ProjectSearchDto;
import jp.gmo.project.dto.RankDto;
import jp.gmo.project.request.AddProjectRequest;
import jp.gmo.project.request.DetailProjectRequest;
import jp.gmo.project.request.SearchProjectRequest;
import jp.gmo.project.response.PageAndDataResponse;
import jp.gmo.project.response.ProjectDetailResponse;

import java.util.List;

public interface ProjectService {

    void executeAddProject(String email, AddProjectRequest request);
    List<RankDto> executeGetListRank();
    PageAndDataResponse<List<ProjectSearchDto>> executeGetListProject(SearchProjectRequest request);
    ProjectDetailResponse executeDetailProject(DetailProjectRequest request);
}
