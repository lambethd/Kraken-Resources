package lambethd.kraken.resource.interfaces;

import lambethd.kraken.resource.ResourceConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import runescape.Graph;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ResourceConfiguration.class})
public class IGraphApiTest {
    @Autowired
    private IGraphApi graphApi;

    @Test
    public void getGraph_GivenAValidItemId_ReturnsAValidGraph() throws IOException {
        Graph graph = graphApi.getGraph(21787);
    }
}