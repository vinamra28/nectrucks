package com.india.nec.ubertrucks.controllers;

import com.india.nec.ubertrucks.models.Bid;
import com.india.nec.ubertrucks.models.Customer;
import com.india.nec.ubertrucks.models.Order;
import com.india.nec.ubertrucks.repositories.BidRepository;
import com.india.nec.ubertrucks.repositories.CustomerRespository;
import com.india.nec.ubertrucks.repositories.OrderRepository;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/orders")
public class OrdersController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private final CustomerRespository customerRespository;
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final BidRepository bidRepository;

    public OrdersController(CustomerRespository customerRespository, OrderRepository orderRepository, BidRepository bidRepository) {
        this.customerRespository = customerRespository;
        this.orderRepository = orderRepository;
        this.bidRepository = bidRepository;
    }

    @RequestMapping(value = "/placeOrder",method = RequestMethod.POST)
    public JSONObject placeOrder(@RequestBody JSONObject order){

        Order order1 = new Order();
        ObjectId userId = new ObjectId(order.get("userId").toString());
        order1.setSrcLoc((String)order.get("srcLoc")); ;
        order1.setDestLoc((String) order.get("destLoc"));
        order1.setLoad((Integer) order.get("load"));
        order1.setCategory((String) order.get("category"));
        order1.setStartDate((String) order.get("dt"));
        Customer customer = new Customer();
        Customer c1 = customerRespository.findByUserId(userId).orElse(null);
        JSONObject jsonObject = new JSONObject();
        if(c1==null||c1.getCurrentOrder()==null) {
            Order ord = orderRepository.save(order1);
            customer.setCurrentOrder(ord.get_id());
            customer.setUserId(userId);
            customerRespository.save(customer);
            jsonObject.put("status",200);
            jsonObject.put("message","order placed");
        }else{
            jsonObject.put("status",200);
            jsonObject.put("message","order already placed");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/getAllOrders", method = RequestMethod.GET)
    public JSONObject getOrders(){
        List<Order> allOrders = orderRepository.findAllByFinalizedOrder(false);
        JSONObject jsonObject = new JSONObject();
        if(allOrders.size()==0){
            jsonObject.put("status",404);
            jsonObject.put("message","no orders found");
        }else{
            jsonObject.put("status",200);
            jsonObject.put("message","orders present");
            jsonObject.put("orders",allOrders);
        }
        return jsonObject;
    }

    @RequestMapping(value = "/placeBid",method = RequestMethod.POST)
    public JSONObject placeBid(@RequestBody JSONObject order){
//        Result result = new Result();
//        Order placedOrder = orderRepository.findBy_id(order.get_id()).orElse(null);
//        if(placedOrder!=null){
//            orderRepository.deleteById(order.get_id());
//            placedOrder.setBidStatus(true);
//            orderRepository.save(placedOrder);
//            result.setStatus(200);
//            result.setResponse("success");
//            result.setMessage("bid placed");
//        }
//        return result;
        JSONObject jsonObject = new JSONObject();
        ObjectId orderId = new ObjectId(order.get("orderId").toString());
        ObjectId serviceId = new ObjectId(order.get("spId").toString());
        Integer bidValue = (Integer) order.get("bidValue");
        Bid bid = new Bid();
        bid.setOrderId(orderId);
        bid.setServiceProviderId(serviceId);
        bid.setBidValue(bidValue);
        Bid bid1 = bidRepository.save(bid);
        Order myOrder = orderRepository.findById(orderId).orElse(null);
        orderRepository.deleteById(orderId);
        List<ObjectId> bids=null;
        if(myOrder.getBids()!=null)
         bids = myOrder.getBids();
        if(bids==null)
            bids = new ArrayList<>();
        bids.add(bid1.get_id());
        myOrder.setBids(bids);
        orderRepository.save(myOrder);
        jsonObject.put("status",200);
        jsonObject.put("message","success");
        return jsonObject;
    }

}
