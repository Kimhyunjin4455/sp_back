package starbucks3355.starbucksServer.domainOrders.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import starbucks3355.starbucksServer.domainOrders.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
}
