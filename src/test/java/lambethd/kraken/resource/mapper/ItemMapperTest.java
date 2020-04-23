package lambethd.kraken.resource.mapper;

import lambethd.kraken.resource.ResourceConfiguration;
import lambethd.kraken.resource.interfaces.IItemMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import runescape.ItemCategory;
import runescape.Item;
import runescape.Trend;

import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ResourceConfiguration.class})
public class ItemMapperTest {
    @Autowired
    private IItemMapper mapper;

    @Test
    public void TestMapping() throws IOException {
        List<Item> itemResponse = mapper.mapToItemDto("{\"total\":97,\"items\":[{\"icon\":\"icon\",\"icon_large\":\"icon_large\",\"id\":12123,\"type\":\"Familiars\",\"typeIcon\":\"type_icon\",\"name\":\"name\",\"description\":\"desc\",\"current\":{\"trend\":\"neutral\",\"price\":\"1,192\"},\"today\":{\"trend\":\"positive\",\"price\":\"8\"},\"members\":\"true\"}]}");
        Assert.assertNotNull(itemResponse);
        Assert.assertEquals(1, itemResponse.size());
        Item single = itemResponse.get(0);
        Assert.assertEquals("icon", single.iconLink);
        Assert.assertEquals("icon_large", single.largeIconLink);
        Assert.assertEquals(Integer.valueOf(12123), single.id);
        Assert.assertEquals(ItemCategory.Familiars, single.type);
        Assert.assertEquals("type_icon", single.typeIconLink);
        Assert.assertEquals("name", single.name);
        Assert.assertEquals("desc", single.description);
        Assert.assertEquals(Trend.Neutral, single.current.trend);
        Assert.assertEquals(1192, single.current.price, 0);
        Assert.assertEquals(Trend.Positive, single.today.trend);
        Assert.assertEquals(8, single.today.price, 0);
        Assert.assertEquals(true, single.members);
    }
}