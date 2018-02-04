package com.xnnotifiter.controller;

import com.xnnotifiter.model.JsonResponse;
import com.xnnotifiter.service.dao.AlertDBService;
import com.xnnotifiter.service.dao.SMSDBService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("")
public class IndexController {

    @Resource
    private SMSDBService smsDBService;

    @Resource
    private AlertDBService alertDBService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public JsonResponse<?> index() {
        return JsonResponse.success(new HashMap<String, List<?>>() {{
            put("sms", smsDBService.listAll());
            put("alerts", alertDBService.listAll());
        }});
    }
}
