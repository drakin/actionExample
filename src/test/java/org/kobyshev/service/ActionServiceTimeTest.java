package org.kobyshev.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;

import java.util.*;

@RunWith(Parameterized.class)
@PrepareForTest(ActionServiceImpl.class)
public class ActionServiceTimeTest {

    private ActionService actionService;
    private final static Date NOW = new Date();
    private final static String GET_LIST_METHOD_NAME = "getActionList";
    private final static String GET_CURRENT_TIME_METHOD_NAME = "getCurrentTimeMillis";

    @Parameterized.Parameter()
    public String description;
    @Parameterized.Parameter(1)
    public Integer result;
    @Parameterized.Parameter(2)
    public String methodName;
    @Parameterized.Parameter(3)
    public List<Long> timeList;

    public
    @Rule
    PowerMockRule rule = new PowerMockRule();

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        List<Object[]> parameters = new ArrayList<>();

        parameters.add(new Object[]{"empty test", 0, "getLastSecondActionsCount", Collections.emptyList()});
        parameters.add(new Object[]{"empty test", 0, "getLastMinuteActionsCount", Collections.emptyList()});
        parameters.add(new Object[]{"empty test", 0, "getLastHourActionsCount", Collections.emptyList()});
        parameters.add(new Object[]{"empty test", 0, "getLastDayActionsCount", Collections.emptyList()});

        parameters.add(new Object[]{"count now test", 1, "getLastSecondActionsCount", Collections.singletonList(NOW.getTime())});
        parameters.add(new Object[]{"count now test", 1, "getLastMinuteActionsCount", Collections.singletonList(NOW.getTime())});
        parameters.add(new Object[]{"count now test", 1, "getLastHourActionsCount", Collections.singletonList(NOW.getTime())});
        parameters.add(new Object[]{"count now test", 1, "getLastDayActionsCount", Collections.singletonList(NOW.getTime())});

        parameters.add(new Object[]{"count several now test", 2, "getLastSecondActionsCount", Arrays.asList(NOW.getTime(), NOW.getTime())});
        parameters.add(new Object[]{"count several now test", 2, "getLastMinuteActionsCount", Arrays.asList(NOW.getTime(), NOW.getTime())});
        parameters.add(new Object[]{"count several now test", 2, "getLastHourActionsCount", Arrays.asList(NOW.getTime(), NOW.getTime())});
        parameters.add(new Object[]{"count several now test", 2, "getLastDayActionsCount", Arrays.asList(NOW.getTime(), NOW.getTime())});

        Calendar secondCalendar = Calendar.getInstance();
        secondCalendar.setTime(NOW);
        secondCalendar.add(Calendar.MILLISECOND, -1);

        Calendar minuteCalendar = Calendar.getInstance();
        minuteCalendar.setTime(NOW);
        minuteCalendar.add(Calendar.SECOND, -1);

        Calendar hourCalendar = Calendar.getInstance();
        hourCalendar.setTime(NOW);
        hourCalendar.add(Calendar.MINUTE, -1);

        Calendar dayCalendar = Calendar.getInstance();
        dayCalendar.setTime(NOW);
        dayCalendar.add(Calendar.HOUR, -1);

        parameters.add(new Object[]{"count close to now test", 1, "getLastSecondActionsCount", Collections.singletonList(secondCalendar.getTimeInMillis())});
        parameters.add(new Object[]{"count close to now test", 1, "getLastMinuteActionsCount", Collections.singletonList(minuteCalendar.getTimeInMillis())});
        parameters.add(new Object[]{"count close to now test", 1, "getLastHourActionsCount", Collections.singletonList(hourCalendar.getTimeInMillis())});
        parameters.add(new Object[]{"count close to now test", 1, "getLastDayActionsCount", Collections.singletonList(dayCalendar.getTimeInMillis())});

        // reinit calendars
        secondCalendar.setTime(NOW);
        minuteCalendar.setTime(NOW);
        hourCalendar.setTime(NOW);
        dayCalendar.setTime(NOW);

        secondCalendar.add(Calendar.MILLISECOND, -999);
        minuteCalendar.add(Calendar.SECOND, -59);
        hourCalendar.add(Calendar.MINUTE, -59);
        dayCalendar.add(Calendar.HOUR, -23);

        parameters.add(new Object[]{"close to boundary test", 1, "getLastSecondActionsCount", Collections.singletonList(secondCalendar.getTimeInMillis())});
        parameters.add(new Object[]{"close to boundary test", 1, "getLastMinuteActionsCount", Collections.singletonList(minuteCalendar.getTimeInMillis())});
        parameters.add(new Object[]{"close to boundary test", 1, "getLastHourActionsCount", Collections.singletonList(hourCalendar.getTimeInMillis())});
        parameters.add(new Object[]{"close to boundary test", 1, "getLastDayActionsCount", Collections.singletonList(dayCalendar.getTimeInMillis())});

        parameters.add(new Object[]{"close to boundary several test", 2, "getLastSecondActionsCount", Arrays.asList(secondCalendar.getTimeInMillis(), secondCalendar.getTimeInMillis())});
        parameters.add(new Object[]{"close to boundary several test", 2, "getLastMinuteActionsCount", Arrays.asList(minuteCalendar.getTimeInMillis(), minuteCalendar.getTimeInMillis())});
        parameters.add(new Object[]{"close to boundary several test", 2, "getLastHourActionsCount", Arrays.asList(hourCalendar.getTimeInMillis(), hourCalendar.getTimeInMillis())});
        parameters.add(new Object[]{"close to boundary several test", 2, "getLastDayActionsCount", Arrays.asList(dayCalendar.getTimeInMillis(), dayCalendar.getTimeInMillis())});

        // reinit calendars
        secondCalendar.setTime(NOW);
        minuteCalendar.setTime(NOW);
        hourCalendar.setTime(NOW);
        dayCalendar.setTime(NOW);

        secondCalendar.add(Calendar.SECOND, -1);
        minuteCalendar.add(Calendar.MINUTE, -1);
        hourCalendar.add(Calendar.HOUR, -1);
        dayCalendar.add(Calendar.DATE, -1);

        parameters.add(new Object[]{"boundary test", 0, "getLastSecondActionsCount", Collections.singletonList(secondCalendar.getTimeInMillis())});
        parameters.add(new Object[]{"boundary test", 0, "getLastMinuteActionsCount", Collections.singletonList(minuteCalendar.getTimeInMillis())});
        parameters.add(new Object[]{"boundary test", 0, "getLastHourActionsCount", Collections.singletonList(hourCalendar.getTimeInMillis())});
        parameters.add(new Object[]{"boundary test", 0, "getLastDayActionsCount", Collections.singletonList(dayCalendar.getTimeInMillis())});

        // reinit calendars
        secondCalendar.setTime(NOW);
        minuteCalendar.setTime(NOW);
        hourCalendar.setTime(NOW);
        dayCalendar.setTime(NOW);

        secondCalendar.add(Calendar.SECOND, -1);
        minuteCalendar.add(Calendar.MINUTE, -1);
        hourCalendar.add(Calendar.HOUR, -1);
        dayCalendar.add(Calendar.DATE, -1);

        parameters.add(new Object[]{"boundary several test", 0, "getLastSecondActionsCount", Arrays.asList(secondCalendar.getTimeInMillis(), secondCalendar.getTimeInMillis())});
        parameters.add(new Object[]{"boundary several test", 0, "getLastMinuteActionsCount", Arrays.asList(minuteCalendar.getTimeInMillis(), minuteCalendar.getTimeInMillis())});
        parameters.add(new Object[]{"boundary several test", 0, "getLastHourActionsCount", Arrays.asList(hourCalendar.getTimeInMillis(), hourCalendar.getTimeInMillis())});
        parameters.add(new Object[]{"boundary several test", 0, "getLastDayActionsCount", Arrays.asList(dayCalendar.getTimeInMillis(), dayCalendar.getTimeInMillis())});

        // reinit calendars
        secondCalendar.setTime(NOW);
        minuteCalendar.setTime(NOW);
        hourCalendar.setTime(NOW);
        dayCalendar.setTime(NOW);

        secondCalendar.add(Calendar.MILLISECOND, -1);
        minuteCalendar.add(Calendar.SECOND, -1);
        hourCalendar.add(Calendar.MINUTE, -1);
        dayCalendar.add(Calendar.HOUR, -1);

        long secondTmp2 = secondCalendar.getTimeInMillis();
        long minuteTmp2 = minuteCalendar.getTimeInMillis();
        long hourTmp2 = hourCalendar.getTimeInMillis();
        long dayTmp2 = dayCalendar.getTimeInMillis();

        secondCalendar.add(Calendar.MILLISECOND, -1);
        minuteCalendar.add(Calendar.SECOND, -1);
        hourCalendar.add(Calendar.MINUTE, -1);
        dayCalendar.add(Calendar.HOUR, -1);

        long secondTmp1 = secondCalendar.getTimeInMillis();
        long minuteTmp1 = minuteCalendar.getTimeInMillis();
        long hourTmp1 = hourCalendar.getTimeInMillis();
        long dayTmp1 = dayCalendar.getTimeInMillis();

        parameters.add(new Object[]{"distinct values test", 2, "getLastSecondActionsCount", Arrays.asList(secondTmp1, secondTmp2)});
        parameters.add(new Object[]{"distinct values test", 2, "getLastMinuteActionsCount", Arrays.asList(minuteTmp1, minuteTmp2)});
        parameters.add(new Object[]{"distinct values test", 2, "getLastHourActionsCount", Arrays.asList(hourTmp1, hourTmp2)});
        parameters.add(new Object[]{"distinct values test", 2, "getLastDayActionsCount", Arrays.asList(dayTmp1, dayTmp2)});

        secondCalendar.setTime(NOW);
        secondCalendar.add(Calendar.MILLISECOND, -1);
        secondTmp2 = secondCalendar.getTimeInMillis();
        secondCalendar.add(Calendar.SECOND, -1);
        secondTmp1 = secondCalendar.getTimeInMillis();

        parameters.add(new Object[]{"Extra values test", 1, "getLastSecondActionsCount", Arrays.asList(secondTmp1, secondTmp2)});


        secondCalendar.setTime(NOW);
        secondCalendar.add(Calendar.MILLISECOND, -1);
        secondTmp2 = secondCalendar.getTimeInMillis();
        secondCalendar.add(Calendar.MILLISECOND, 1);
        secondCalendar.add(Calendar.SECOND, -1);
        secondTmp1 = secondCalendar.getTimeInMillis();
        parameters.add(new Object[]{"Extra values boundary test", 1, "getLastSecondActionsCount", Arrays.asList(secondTmp1, secondTmp2)});

        return parameters;
    }

    @Before
    public void setup() throws Exception {
        actionService = PowerMockito.spy(new ActionServiceImpl());
        PowerMockito.when(actionService, PowerMockito.method(ActionServiceImpl.class, GET_CURRENT_TIME_METHOD_NAME))
                .withNoArguments().thenReturn(NOW.getTime());
    }

    @Test
    public void parametrizedTest() throws Exception {
        PowerMockito.when(actionService, PowerMockito.method(ActionServiceImpl.class, GET_LIST_METHOD_NAME))
                .withNoArguments().thenReturn(timeList);
        Assert.assertEquals(result, actionService.getClass().getMethod(methodName).invoke(actionService));

    }
}