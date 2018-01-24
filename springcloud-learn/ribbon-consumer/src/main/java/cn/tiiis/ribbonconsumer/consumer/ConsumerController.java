package cn.tiiis.ribbonconsumer.consumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConsumerController {
    @Resource
    ConsumerService consumerService;

    @GetMapping(value = "/ribbon")
    public String helloConsumer() {
        return consumerService.helloService();
    }

}