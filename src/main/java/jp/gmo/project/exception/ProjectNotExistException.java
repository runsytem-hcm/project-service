package jp.gmo.project.exception;


import jp.gmo.project.constant.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ProjectNotExistException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public ProjectNotExistException() {
        super(ErrorConstants.DEFAULT_TYPE, "Không tìm thấy dự án.", Status.NOT_FOUND);
    }
}
