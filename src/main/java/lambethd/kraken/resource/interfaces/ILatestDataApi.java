package lambethd.kraken.resource.interfaces;

import org.springframework.stereotype.Service;
import runescape.HistoricalData;

import java.io.IOException;

@Service
public interface ILatestDataApi {
    HistoricalData getLatestData(int itemId) throws IOException;
}
