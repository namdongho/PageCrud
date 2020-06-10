package com.example.study.model.entity;

import com.example.study.model.enumclass.UserStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//@Table(name="user") // 클래스 이름이랑 데이터베이스 테이블 이름과 같다면 생략 가능. 지금 상황에서 생략 가능
@Data// 기본 생성자와 getter setter를 만들어준다.
@AllArgsConstructor// 모든 매개변수를 가지는 생성자
@NoArgsConstructor// 파라미터 없는 기본 생성자 추가
@Entity// == table
@ToString(exclude = {"orderGroupList"})
@EntityListeners(AuditingEntityListener.class)//createdby modifiedby 어노테이션을 지정했던 것들을 자동으로 LoginAuditorAware의 AdminServer에 설정값들이 적용된다.
@Builder// .연산자로 생성자를 편하게 만들 수 있다.
@Accessors(chain = true)// .연산자로 set을 편하게 할 수 있다.
public class User {

    //@Column(name = "id") 변수명과 데이터의 컬럼이 이름이 같다면 생략 가능
    @Id//식별자 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 어떤 식으로 키 전략을 가져갈 것인지

    private Long id;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status; // REGISTERED / UNREGISTERED / WAITING

    private String account;

    private String email;

    private String phoneNumber;//snake로 하지 않더라도 jpa에서 자동으로 카멜로 맞춰서 매칭 시켜준다.

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    //생성되면 자동으로 아래 값들이 자동으로 채워진다.
    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    //수정되면 자동으로 아래 값들이 자동으로 채워진다.
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    // User 1 : N OrderGroup
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;

//    //1:n
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")// OrderDetail에 있는 user와 이름을 맞춰야한다.
//    private List<OrderDetail> orderDetailList;

}
