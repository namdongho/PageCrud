package com.example.study.model.entity;

import com.example.study.model.enumclass.ItemStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data//getter setter를 자동으로 만들어주는 것
@AllArgsConstructor
@NoArgsConstructor
@Entity// table 엔티티임을 명시
@ToString(exclude = {"orderDetailList", "partner"})
@EntityListeners(AuditingEntityListener.class)//createdby modifiedby 어노테이션을 지정했던 것들을 자동으로 LoginAuditorAware의 AdminServer에 설정값들이 적용된다.
@Builder// .연산자로 생성자를 편하게 만들 수 있다.
@Accessors(chain = true)// .연산자로 set을 편하게 할 수 있다.
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//기본키 생성을 데이터베이스에 위임 auto increment
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemStatus status; // 등록 / 해지 / 검수중 (등록대기중)

    private String name;

    private String title;

    private String content;

    private BigDecimal price;

    private String brandName;

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


    //Item N : 1 Partner
    @ManyToOne
    private Partner partner;

    //Item 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;

//    //1:n
//    //lazy = 지연로딩, eager = 즉시 로딩
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
//    private List<OrderDetail> orderDetailList;
}
