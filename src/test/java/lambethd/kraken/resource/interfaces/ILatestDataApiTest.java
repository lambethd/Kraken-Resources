package lambethd.kraken.resource.interfaces;

import lambethd.kraken.resource.ResourceConfiguration;
import lambethd.kraken.resource.interfaces.ILatestDataApi;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import runescape.HistoricalData;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ResourceConfiguration.class})
public class ILatestDataApiTest {
    @Autowired
    private ILatestDataApi client;

    @Test
    public void TestApiCall() throws IOException {
        HistoricalData historicalData = client.getLatestData(28608);
        System.out.println(historicalData.getDate());
        System.out.println(historicalData.getPrice());
        Assert.assertNotNull(historicalData);
    }
}