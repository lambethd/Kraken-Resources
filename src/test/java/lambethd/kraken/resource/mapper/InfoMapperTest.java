package lambethd.kraken.resource.mapper;

import lambethd.kraken.resource.ResourceConfiguration;
import lambethd.kraken.resource.interfaces.IInfoMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import runescape.Runeday;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ResourceConfiguration.class})
public class InfoMapperTest {

    @Autowired
    private IInfoMapper mapper;

    @Test
    public void TestMapping() throws IOException {
        Runeday info = mapper.mapToInfoDto("{\"lastConfigUpdateRuneday\":6613}");
        Assert.assertNotNull(info);
        Assert.assertEquals(6613, info.lastConfigUpdateRuneday, 0);
    }

}