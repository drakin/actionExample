package org.kobyshev.controller;

import org.kobyshev.model.Action;
import org.kobyshev.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
public class ActionController {
    private ActionService actionService;

    @Autowired
    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @Async
    @RequestMapping(value = "/action/add", method = RequestMethod.POST)
    @ResponseBody
    public CompletableFuture<Action> addAction(@RequestBody final Action action) {
        return CompletableFuture.supplyAsync(() -> actionService.addAction(action));
    }

    @Async
    @RequestMapping(value = "/action/lastSecond")
    @ResponseBody
    public CompletableFuture<List<Action>> getLastSecondActions() {
        return CompletableFuture.supplyAsync(() -> this.actionService.getLastSecondActions());
    }

    @Async
    @RequestMapping(value = "/action/lastMinute")
    @ResponseBody
    public CompletableFuture<List<Action>> getLastMinuteActions() {
        return CompletableFuture.supplyAsync(() -> this.actionService.getLastMinuteActions());
    }

    @Async
    @RequestMapping(value = "/action/lastHour")
    @ResponseBody
    public CompletableFuture<List<Action>> getLastHourActions() {
        return CompletableFuture.supplyAsync(() -> this.actionService.getLastHourActions());
    }

    @Async
    @RequestMapping(value = "/action/lastDay")
    @ResponseBody
    public CompletableFuture<List<Action>> getLastDayActions() {
        return CompletableFuture.supplyAsync(() -> this.actionService.getLastDayActions());
    }
}
