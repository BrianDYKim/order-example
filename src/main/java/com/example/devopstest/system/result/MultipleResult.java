package com.example.devopstest.system.result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MultipleResult<T> extends CommonResult {

    private List<T> data;
}
