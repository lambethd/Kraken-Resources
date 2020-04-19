package lambethd.kraken.resource.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lambethd.kraken.resource.dto.GraphDto;
import lambethd.kraken.resource.interfaces.IGraphMapper;
import org.springframework.stereotype.Service;
import runescape.Graph;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;

@Service
public class GraphMapper implements IGraphMapper {
    @Override
    public Graph mapToGraph(String graphString) throws IOException {
        GraphDto grossGraph = new ObjectMapper().readValue(graphString, GraphDto.class);
        Graph graph = mapInternal(grossGraph);
        return graph;
    }

    private Graph mapInternal(GraphDto graphDto) {
        Graph graph = new Graph();
        graph.daily = new HashMap<>();
        graph.average = new HashMap<>();
        graphDto.daily.fieldNames().forEachRemaining(d -> {
            graph.daily.put(LocalDateTime.ofEpochSecond(Long.parseLong(d) / 1000, 0, ZoneOffset.UTC), graphDto.daily.get(d).floatValue());
        });
        graphDto.average.fieldNames().forEachRemaining(d -> {
            graph.average.put(LocalDateTime.ofEpochSecond(Long.parseLong(d) / 1000, 0, ZoneOffset.UTC), graphDto.average.get(d).floatValue());
        });
        return graph;
    }
}
