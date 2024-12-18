package org.exemple.tp_spring_batch.Processor;


import org.exemple.tp_spring_batch.Tables.Order;
import org.springframework.batch.item.ItemProcessor;

public class OrderItemProcessor implements ItemProcessor<Order,Order> {
    //private static final Logger LOG = LoggerFactory.getLogger(ProductItemProcessor.class);
    @Override
    public Order process(Order orderItem) throws Exception {
        Long id=orderItem.id();
        String customerName=orderItem.customerName().toUpperCase();
        double amount=orderItem.amount()-orderItem.amount()*0.1;
        Order transformedOrder=new Order(id,customerName,amount);
        return transformedOrder;
    }
}
