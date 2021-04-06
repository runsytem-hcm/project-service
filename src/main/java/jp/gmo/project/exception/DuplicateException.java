package jp.gmo.project.exception;

import jp.gmo.project.constant.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class DuplicateException extends AbstractThrowableProblem {

    public DuplicateException(String message) {
        super(ErrorConstants.DEFAULT_TYPE, "Nhân viên và vai trò trong dự án không được trùng nhau.", Status.BAD_REQUEST);
    }
}
