package lambethd.kraken.resource.interfaces;

import org.springframework.stereotype.Service;
import runescape.HistoricalData;

import java.io.IOException;
import java.util.List;

@Service
public interface IHistoricalDataApi {
    List<HistoricalData> getHistoricalData(int itemId) throws IOException;
}
