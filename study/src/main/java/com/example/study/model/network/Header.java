package com.example.study.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data//게터 세터 자동으로 만들어준다.
@NoArgsConstructor//기본 생성자
@AllArgsConstructor//모든 매개변수를 가지고 있는 생성자
@Builder//생성자 쉽게 만들어주는 기능
public class Header<T> {

    //api 통신 시간
    //@JsonProperty("transaction_time") snake타입으로 표현
    private LocalDateTime transactionTime;

    //api 응답 코드
    private String resultCode;

    //api 부가 설명
    private String description;

    private T data;

    // pagination
    private Pagination pagination;

    // OK
    public static <T> Header<T> OK(){
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    // OK data
    public static <T> Header<T> OK(T data){
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    public static <T> Header<T> OK(T data, Pagination pagination){
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .pagination(pagination)
                .build();
    }

    // Error
    public static <T> Header<T> ERROR(String description){
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description(description)
                .build();
    }

}
