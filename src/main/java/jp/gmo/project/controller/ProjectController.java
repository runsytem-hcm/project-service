package jp.gmo.project.controller;

import jp.gmo.project.dto.ProjectSearchDto;
import jp.gmo.project.dto.RankDto;
import jp.gmo.project.request.AddProjectRequest;
import jp.gmo.project.response.PageAndDataResponse;
import jp.gmo.project.response.ProjectDetailResponse;
import jp.gmo.project.service.CallAPIsService;
import jp.gmo.project.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;
    private final CallAPIsService callAPIsService;

    @PostMapping("/add-project")
    @ResponseStatus(HttpStatus.CREATED)
    public void executeAddProject(@RequestHeader("email") String email, @Valid @RequestBody AddProjectRequest request) {
        projectService.executeAddProject(email, request);
    }

    @GetMapping(path = "/list-rank")
    public ResponseEntity<List<RankDto>> executeGetProjectRank() {
        return new ResponseEntity<>(projectService.executeGetListRank(), HttpStatus.OK);
    }

    @GetMapping("/list-project")
    public ResponseEntity<PageAndDataResponse<List<ProjectSearchDto>>> executeGetListEmployees(
            @RequestParam(name = "projectName") String projectName,
            @RequestParam(name = "startDate") String startDate,
            @RequestParam(name = "endDate") String endDate,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size) {

        return new ResponseEntity<>(projectService.executeGetListProject(projectName, startDate, endDate, page, size), HttpStatus.OK);
    }

    @GetMapping(path = "/detail-project")
    public ResponseEntity<ProjectDetailResponse> executeGetDetailProject(@RequestParam(name = "projectCode", required = false) String projectCode) {
        return new ResponseEntity<>(projectService.executeDetailProject(projectCode), HttpStatus.OK);
    }
}