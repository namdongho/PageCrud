package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data//getter, setter를 만들어준다.
@AllArgsConstructor//모든 변수들을 가지는 생성자를 만들어 준다.
public class SearchParam {

    private String account;
    private String email;
    private int page;

}
