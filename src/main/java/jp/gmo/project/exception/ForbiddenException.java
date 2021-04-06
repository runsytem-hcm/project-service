package jp.gmo.project.exception;


import jp.gmo.project.constant.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ForbiddenException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public ForbiddenException() {
        super(ErrorConstants.DEFAULT_TYPE, "Bạn không có quyền cập nhật dự án", Status.FORBIDDEN);
    }
}
