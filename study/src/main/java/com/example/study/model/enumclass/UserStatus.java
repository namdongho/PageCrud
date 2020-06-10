package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    REGISTERED(0, "둥록상태", "사용자 등록상태"),//index id, 외부에 노출되는 title, 조금 더 상세하게 설명하는 것
    UNREGISTERED(1, "해지", "사용자 해지상태")
    ;

    private Integer id;
    private String title;
    private String description;

}
