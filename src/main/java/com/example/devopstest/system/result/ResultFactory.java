package com.example.devopstest.system.result;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultFactory {

    // 성공 결과만 반환하는 메소드
    public static CommonResult getSuccessResult() {
        return new CommonResult(true);
    }

    // 단일 데이터를 가지는 성공을 반환하는 메소드
    public static <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        result.setSuccess(true);

        return result;
    }

    // 리스트 형태의 데이터를 가지는 성공을 반환하는 메소드
    public static <T> MultipleResult<T> getMultipleResult(List<T> data) {
        MultipleResult<T> result = new MultipleResult<>();
        result.setData(data);
        result.setSuccess(true);

        return result;
    }
}
