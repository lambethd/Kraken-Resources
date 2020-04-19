package lambethd.kraken.resource.interfaces;

import lambethd.kraken.resource.ResourceConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import runescape.Catalogue;
import runescape.ItemCategory;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ResourceConfiguration.class})
public class ICatalogueApiTest {
    @Autowired
    private ICatalogueApi client;

    @Test
    public void TestApiCall() throws IOException {
        Catalogue cat = client.getCatalogueForCategory(ItemCategory.Ammo);
        Assert.assertNotNull(cat);
        Assert.assertEquals(ItemCategory.Ammo, cat.types.get(0));
    }
}