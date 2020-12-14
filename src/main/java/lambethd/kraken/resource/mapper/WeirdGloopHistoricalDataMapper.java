package lambethd.kraken.resource.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lambethd.kraken.resource.interfaces.IHistoricalDataMapper;
import org.springframework.stereotype.Service;
import runescape.HistoricalData;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

@Service
public class WeirdGloopHistoricalDataMapper implements IHistoricalDataMapper {

    @Override
    public List<HistoricalData> mapToHistoricalData(String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<HistoricalData> historicalData = new LinkedList<>();
        JsonNode dto;
        try {
            dto = objectMapper.readValue(data, JsonNode.class);
            dto.fields().next().getValue().elements().forEachRemaining(n -> {
                HistoricalData datum = new HistoricalData();
                datum.setDate(Instant.ofEpochMilli(n.get(0).asLong()).atZone(ZoneOffset.UTC).toLocalDateTime());
                datum.setPrice(n.get(1).asLong());
                historicalData.add(datum);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return historicalData;
    }
}
