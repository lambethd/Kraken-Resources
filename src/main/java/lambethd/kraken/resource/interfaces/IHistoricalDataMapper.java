package lambethd.kraken.resource.interfaces;

import org.springframework.stereotype.Service;
import runescape.Graph;
import runescape.HistoricalData;
import runescape.Runeday;

import java.io.IOException;
import java.util.List;

@Service
public interface IHistoricalDataMapper {
    List<HistoricalData> mapToHistoricalData(String data);
}
