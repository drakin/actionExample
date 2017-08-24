package org.kobyshev.service;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kobyshev.model.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/action-service-impl-test-context.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ActionServiceCommonTest {

    private ActionService actionService;

    @Test
    public void addAction() throws Exception {
        Action action = new Action();
        action.setValue(UUID.randomUUID().toString());
        Action resultAction = actionService.addAction(action);
        Assert.assertNotNull(resultAction);
        Assert.assertNotSame(0, resultAction.getId());
        Assert.assertEquals(resultAction.getValue(), action.getValue());
        Assert.assertNotNull(resultAction.getActionDate());
    }
    @Test
    public void addNulAction() throws Exception {
        Action resultAction = actionService.addAction(null);
        Assert.assertNull(resultAction);
    }

    @Test
    public void getNonExistingAction() throws Exception {
        Assert.assertNull(actionService.getAction(1));

    }

    @Test
    public void getExistingAction() throws Exception {
        Action action = new Action();
        action.setValue(UUID.randomUUID().toString());
        Action dbAction = actionService.addAction(action);
        Assume.assumeNotNull(dbAction);
        Action resultAction = actionService.getAction(dbAction.getId());
        Assert.assertNotNull(resultAction);
        Assert.assertEquals(dbAction.getId(), resultAction.getId());
        Assert.assertEquals(dbAction.getValue(), resultAction.getValue());
        Assert.assertEquals(dbAction.getActionDate(), resultAction.getActionDate());

    }

    @Autowired
    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }
}