package com.Ecom.Notification.notification;


import com.Ecom.Notification.kafka.order.OrderConfirmation;
import com.Ecom.Notification.kafka.payment.PaymentNotificationRequest;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Notification {

    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;

    private OrderConfirmation orderConfirmation;
    private PaymentNotificationRequest paymentConfirmation;

}
