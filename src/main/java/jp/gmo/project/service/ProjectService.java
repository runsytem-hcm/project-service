package jp.gmo.project.service;

import jp.gmo.project.dto.ProjectDto;
import jp.gmo.project.dto.RankDto;
import jp.gmo.project.request.AddProjectRequest;
import jp.gmo.project.request.SearchProjectRequest;
import jp.gmo.project.response.PageAndDataResponse;

import java.util.List;

public interface ProjectService {

    void executeAddProject(String email, AddProjectRequest request);

    List<RankDto> executeGetListRank();
    PageAndDataResponse<List<ProjectDto>> executeGetLisProject(SearchProjectRequest request);
}
