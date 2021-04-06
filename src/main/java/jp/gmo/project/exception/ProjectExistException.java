package jp.gmo.project.exception;


import jp.gmo.project.constant.ErrorConstants;
import jp.gmo.project.entity.ProjectEntity;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ProjectExistException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public ProjectExistException() {
        super(ErrorConstants.DEFAULT_TYPE, "Tên dự án đã tồn tại.", Status.BAD_REQUEST);
    }
}
