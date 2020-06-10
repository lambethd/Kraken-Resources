package lambethd.kraken.resource.interfaces;

import org.springframework.stereotype.Service;
import runescape.HistoricalData;

import java.io.IOException;
import java.util.List;

@Service
public interface IHistoricalDataApi {
    List<HistoricalData> getHistoricalData(String itemName) throws IOException;
}
