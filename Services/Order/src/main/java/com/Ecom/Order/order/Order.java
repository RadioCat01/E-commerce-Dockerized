package com.Ecom.Order.order;


import com.Ecom.Order.orderline.OrderLine;
import jakarta.persistence.*;
import jakarta.ws.rs.GET;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="customer_order")
public class Order {
   @Id
   @GeneratedValue
   private Integer id;
   private String reference;
   private BigDecimal totalAmount;

   @Enumerated(EnumType.STRING)
   private PaymentMethod paymentMethod;
   private String customerId;

   @OneToMany(mappedBy = "order")
   private List<OrderLine> orderLines;

   @CreatedDate
   @Column(updatable = false, nullable = false)
   private LocalDateTime createdAt;

   @CreatedDate
   @Column(insertable = false)
   private LocalDateTime lastModifiedDate;

}
