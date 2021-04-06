package jp.gmo.project.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize
@AllArgsConstructor
public class PageAndDataResponse<T> {

    @JsonProperty(value = "data", index = 1)
    private T data;
    private int totalRecord;
    private int currentPage;
    private int totalRecordOfPage;

    public static <T> PageAndDataResponse<T> create(T data, int totalRecord, int currentPage, int totalRecordOfPage) {
        return new PageAndDataResponse<T>(data, totalRecord, currentPage, totalRecordOfPage);
    }
}
