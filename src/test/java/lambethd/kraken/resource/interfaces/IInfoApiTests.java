package lambethd.kraken.resource.interfaces;

import lambethd.kraken.resource.ResourceConfiguration;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import runescape.Runeday;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ResourceConfiguration.class})
@Ignore
public class IInfoApiTests {

    @Autowired
    private IInfoApi client;

    @Test
    public void TestApiCall() throws IOException {
        Runeday info = client.getInfo();
        Assert.assertNotNull(info);
        Assert.assertNotEquals(0, info.lastConfigUpdateRuneday, 0);
    }
}