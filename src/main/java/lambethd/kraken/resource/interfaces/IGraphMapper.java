package lambethd.kraken.resource.interfaces;

import org.springframework.stereotype.Service;
import runescape.Graph;

import java.io.IOException;

@Service
public interface IGraphMapper {
    Graph mapToGraph(String graphString) throws IOException;
}
