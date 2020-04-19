package lambethd.kraken.resource.interfaces;

import lambethd.kraken.resource.ResourceConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ResourceConfiguration.class})
public class IPriceMapperTest {
    @Autowired
    private IPriceMapper mapper;

    @Test
    public void mapToFloat_GivenAnInt_MapsCorrectly() throws IOException {
        String price = "120";

        float result = mapper.mapToFloat(price);

        Assert.assertEquals(120f, result, 0);
    }

    @Test
    public void mapToFloat_GivenAnIntWithPlusSign_MapsCorrectly() throws IOException {
        String price = "+120";

        float result = mapper.mapToFloat(price);

        Assert.assertEquals(120f, result, 0);
    }

    @Test
    public void mapToFloat_GivenAnIntWithMinusSign_MapsCorrectly() throws IOException {
        String price = "-120";

        float result = mapper.mapToFloat(price);

        Assert.assertEquals(-120f, result, 0);
    }

    @Test
    public void mapToFloat_GivenAnIntWithK_MapsCorrectly() throws IOException {
        String price = "120k";

        float result = mapper.mapToFloat(price);

        Assert.assertEquals(120000f, result, 0);
    }

    @Test
    public void mapToFloat_GivenAnIntWithM_MapsCorrectly() throws IOException {
        String price = "120m";

        float result = mapper.mapToFloat(price);

        Assert.assertEquals(120000000f, result, 0);
    }

    @Test
    public void mapToFloat_GivenAnIntWithB_MapsCorrectly() throws IOException {
        String price = "2b";

        float result = mapper.mapToFloat(price);

        Assert.assertEquals(2000000000f, result, 0);
    }

    @Test
    public void mapToFloat_GivenANegativeIntWithK_MapsCorrectly() throws IOException {
        String price = "-120k";

        float result = mapper.mapToFloat(price);

        Assert.assertEquals(-120000f, result, 0);
    }

    @Test
    public void mapToFloat_GivenANegativeIntWithM_MapsCorrectly() throws IOException {
        String price = "-120m";

        float result = mapper.mapToFloat(price);

        Assert.assertEquals(-120000000f, result, 0);
    }

    @Test
    public void mapToFloat_GivenANegativeIntWithB_MapsCorrectly() throws IOException {
        String price = "-2b";

        float result = mapper.mapToFloat(price);

        Assert.assertEquals(-2000000000f, result, 0);
    }

    @Test
    public void mapToFloat_GivenADecimalWithK_MapsCorrectly() throws IOException {
        String price = "1.2k";

        float result = mapper.mapToFloat(price);

        Assert.assertEquals(1200f, result, 0);
    }

    @Test
    public void mapToFloat_GivenADecimalWithM_MapsCorrectly() throws IOException {
        String price = "1.2m";

        float result = mapper.mapToFloat(price);

        Assert.assertEquals(1200000f, result, 0);
    }

    @Test
    public void mapToFloat_GivenADecimalWithB_MapsCorrectly() throws IOException {
        String price = "1.2b";

        float result = mapper.mapToFloat(price);

        Assert.assertEquals(1200000000f, result, 0);
    }

    @Test
    public void mapToFloat_GivenANegativeDecimalWithK_MapsCorrectly() throws IOException {
        String price = "-1.2k";

        float result = mapper.mapToFloat(price);

        Assert.assertEquals(-1200f, result, 0);
    }

    @Test
    public void mapToFloat_GivenANegativeDecimalWithM_MapsCorrectly() throws IOException {
        String price = "-1.2m";

        float result = mapper.mapToFloat(price);

        Assert.assertEquals(-1200000f, result, 0);
    }

    @Test
    public void mapToFloat_GivenANegativeDecimalWithB_MapsCorrectly() throws IOException {
        String price = "-1.2b";

        float result = mapper.mapToFloat(price);

        Assert.assertEquals(-1200000000, result, 0);
    }
}