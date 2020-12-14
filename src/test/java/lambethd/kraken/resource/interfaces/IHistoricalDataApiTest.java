package lambethd.kraken.resource.interfaces;

import lambethd.kraken.resource.ResourceConfiguration;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import runescape.HistoricalData;
import runescape.Item;

import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ResourceConfiguration.class})
public class IHistoricalDataApiTest {
    @Autowired
    private IHistoricalDataApi client;

    @Test
    public void TestApiCall() throws IOException {
        List<HistoricalData> historicalData = client.getHistoricalData(28608);
        System.out.println(historicalData.size());
        Assert.assertNotNull(historicalData);
    }
}