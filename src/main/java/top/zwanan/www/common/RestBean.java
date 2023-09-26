package top.zwanan.www.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class RestBean<T> {

    @JsonIgnore
    public static final int OK_STATUS = HttpStatus.OK.value();

    private Integer code;

    private T message;

    public boolean isSuccess() {
        return code == HttpStatus.OK.value();
    }

    public static <T> RestBean<T> success() {
        return new RestBean<>(OK_STATUS, null);
    }

    public static <T> RestBean<T> success(T message) {
        return new RestBean<>(OK_STATUS, message);
    }

    public static <T> RestBean<T> failure(int code) {
        return new RestBean<>(code, null);
    }

    public static <T> RestBean<T> failure(int code, T message) {
        return new RestBean<>(code, message);
    }



}
