package com.Ecom.Order.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {

        var order = mapper.toOrderLine(orderLineRequest);

        return repository.save(order).getId();

    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return repository.findAllByOrderId(orderId).stream().map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
