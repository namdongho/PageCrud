package com.example.study.model.entity;

import com.example.study.model.enumclass.OrderType;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"user", "orderDetailList"})
@EntityListeners(AuditingEntityListener.class)//createdby modifiedby 어노테이션을 지정했던 것들을 자동으로 LoginAuditorAware의 AdminServer에 설정값들이 적용된다.
@Builder// .연산자로 생성자를 편하게 만들 수 있다.
@Accessors(chain = true)// .연산자로 set을 편하게 할 수 있다.
public class OrderGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @Enumerated(EnumType.STRING)
    private OrderType orderType; // 주문의 형태 - 일괄/개별

    private String revAddress;

    private String revName;

    private String paymentType; // 카드/현금

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

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

    // OrderGroup N : 1 User
    @ManyToOne
    private User user;

    // OrderGroup 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;
}
