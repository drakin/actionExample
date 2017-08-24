package org.kobyshev.controller;

import org.kobyshev.model.Action;
import org.kobyshev.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ActionController {
    private ActionService actionService;

    @Autowired
    public ActionController(ActionService actionService){
        this.actionService = actionService;
    }

    @RequestMapping(value = "/action/add", method = RequestMethod.POST)
    @ResponseBody
    public Action addAction(@RequestBody Action action) {
        return this.actionService.addAction(action);
    }

    @RequestMapping(value = "/action/lastSecond")
    @ResponseBody
    public List<Action> getLastSecondActions() {
        return this.actionService.getLastSecondActions();
    }

    @RequestMapping(value = "/action/lastMinute")
    @ResponseBody
    public List<Action> getLastMinuteActions() {
        return this.actionService.getLastMinuteActions();
    }

    @RequestMapping(value = "/action/lastHour")
    @ResponseBody
    public List<Action> getLastHourActions() {
        return this.actionService.getLastHourActions();
    }

    @RequestMapping(value = "/action/lastDay")
    @ResponseBody
    public List<Action> getLastDayActions() {
        return this.actionService.getLastDayActions();
    }
}
