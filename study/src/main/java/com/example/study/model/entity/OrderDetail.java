package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.net.InterfaceAddress;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity// order_detail로 자동으로 연결된다.
@ToString(exclude = {"orderGroup", "item"})
@EntityListeners(AuditingEntityListener.class)//createdby modifiedby 어노테이션을 지정했던 것들을 자동으로 LoginAuditorAware의 AdminServer에 설정값들이 적용된다.
@Builder// .연산자로 생성자를 편하게 만들 수 있다.
@Accessors(chain = true)// .연산자로 set을 편하게 할 수 있다.
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

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

    //OrderDetail N : 1 Item
    @ManyToOne
    private Item item;

    // OrderDetail N : 1 OrderGroup
    @ManyToOne
    private OrderGroup orderGroup;

//    // N : 1
//    @ManyToOne//orderdetail입장에서 userid가 1이고 자신이 n
//    private User user;
//
//    // N : 1
//    @ManyToOne
//    private Item item;

}
