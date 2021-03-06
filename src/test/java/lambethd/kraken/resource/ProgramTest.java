package lambethd.kraken.resource;

import lambethd.kraken.resource.interfaces.IItemApi;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import runescape.Item;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ResourceConfiguration.class})
@Ignore
public class ProgramTest {
    @Autowired
    private IItemApi itemApi;

    @Test
    public void TestGetItemsAndStoreInRepo() throws IOException {
        List<Item> items = itemApi.getItems().collect(Collectors.toList());
    }
}
