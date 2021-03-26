package lambethd.kraken.resource.interfaces;

import lambethd.kraken.resource.ResourceConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import runescape.Runeday;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ResourceConfiguration.class})
public class IInfoApiTests {

    @Autowired
    private IInfoApi client;

    @Test
    public void TestApiCall() throws IOException {
        Runeday info = client.getInfo();
        Assert.assertNotNull(info);
        Assert.assertNotEquals(0, info.lastConfigUpdateRuneday, 0);
        System.out.println(info.lastConfigUpdateRuneday);

        LocalDateTime actual = LocalDateTime.of(2002, 2, 27, 0, 0);

        LocalDateTime now = LocalDateTime.now();//.withHour(0).withMinute(0).withSecond(0).withNano(0);

        System.out.println(actual.until(now, ChronoUnit.DAYS));
    }
}