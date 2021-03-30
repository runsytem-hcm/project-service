package jp.gmo.project.service.impl;

import jp.gmo.project.dto.ProjectDetailDto;
import jp.gmo.project.dto.ProjectDto;
import jp.gmo.project.dto.ProjectSearchDto;
import jp.gmo.project.dto.RankDto;
import jp.gmo.project.entity.ProjectDetailEntity;
import jp.gmo.project.entity.ProjectEntity;
import jp.gmo.project.exception.ProjectNotExistException;
import jp.gmo.project.mapper.ProjectDetailMapper;
import jp.gmo.project.mapper.ProjectMapper;
import jp.gmo.project.repository.ProjectDetailRepository;
import jp.gmo.project.repository.ProjectRepository;
import jp.gmo.project.repository.RankRepository;
import jp.gmo.project.request.AddProjectRequest;
import jp.gmo.project.request.DetailProjectRequest;
import jp.gmo.project.request.SearchProjectRequest;
import jp.gmo.project.response.PageAndDataResponse;
import jp.gmo.project.response.ProjectDetailResponse;
import jp.gmo.project.service.ProjectService;
import jp.gmo.project.utils.Utils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);
    private final ProjectMapper projectMapper;
    private final ProjectDetailMapper projectDetailMapper;
    private final ProjectRepository projectRepository;
    private final ProjectDetailRepository detailRepository;
    private final RankRepository rankRepository;

    @Override
    public void executeAddProject(String email, AddProjectRequest request) {

        ProjectEntity projectEntity = projectMapper.dtoToEntity(request);
        projectEntity.setCreateTime(LocalDateTime.now());
        projectEntity.setCreateBy(Utils.getUpdateBy(email));
        projectEntity.setDeleteFlag(0);

        projectRepository.save(projectEntity);
        log.debug("Created Information for Project: {}", projectRepository);

        List<ProjectDetailEntity> ProjectDetailList = new ArrayList<>();

        if (!request.getProjectManagement().isEmpty()) {
            ProjectDetailEntity projectDetail = projectDetailMapper.dtoToEntity(projectEntity.getProjectCode(), request, 1);
            projectDetail.setCreateTime(LocalDateTime.now());
            projectDetail.setCreateBy(Utils.getUpdateBy(email));
            projectDetail.setDeleteFlag(0);
            ProjectDetailList.add(projectDetail);
        }

        if (!request.getTeamLead().isEmpty()) {
            ProjectDetailEntity projectDetail = projectDetailMapper.dtoToEntity(projectEntity.getProjectCode(), request, 2);
            projectDetail.setCreateTime(LocalDateTime.now());
            projectDetail.setCreateBy(Utils.getUpdateBy(email));
            projectDetail.setDeleteFlag(0);
            ProjectDetailList.add(projectDetail);
        }

        if (!request.getBrSE().isEmpty()) {
            ProjectDetailEntity projectDetail = projectDetailMapper.dtoToEntity(projectEntity.getProjectCode(), request, 3);
            projectDetail.setCreateTime(LocalDateTime.now());
            projectDetail.setCreateBy(Utils.getUpdateBy(email));
            projectDetail.setDeleteFlag(0);
            ProjectDetailList.add(projectDetail);
        }

        ProjectDetailList.forEach(detailRepository::save);
        log.debug("Created Information for Project Detail: {}", ProjectDetailList);
        // Send Mail
    }

    @Override
    public List<RankDto> executeGetListRank() {
        return rankRepository.findByDeleteFlag(0).stream().map(rank -> new RankDto(rank.getRank(), rank.getDescription())).collect(Collectors.toList());
    }

    @Override
    public PageAndDataResponse<List<ProjectSearchDto>> executeGetListProject(SearchProjectRequest request) {

        int limit = Integer.parseInt(request.getTotalRecordOfPage());
        int offset = (Integer.parseInt(request.getCurrentPage()) - 1) * limit;

        List<ProjectSearchDto> projectDtoList = projectRepository.getListProject(request.getProjectName(), request.getStartDate(), request.getEndDate(), limit, offset);
        BigInteger totalRecord = projectRepository.countProject(request.getProjectName(), request.getStartDate(), request.getEndDate());

        return PageAndDataResponse.create(projectDtoList, request.getCurrentPage(), request.getTotalRecordOfPage(), String.valueOf(totalRecord));
    }

    @Override
    public ProjectDetailResponse executeDetailProject(DetailProjectRequest request) {

        ProjectDto project = projectRepository
                .findByProjectCode(Integer.parseInt(request.getProjectCode()))
                .map(projectMapper::entityToDto).orElseThrow(ProjectNotExistException::new);

        List<ProjectDetailDto> member = detailRepository
                .findByProjectCode(project.getProjectCode())
                .stream().map(projectDetailMapper::entityToDto).collect(Collectors.toList());

        return new ProjectDetailResponse(project, member);
    }
}