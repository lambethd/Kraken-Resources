package lambethd.kraken.resource.interfaces;

import runescape.HistoricalData;

import java.util.List;

public interface IHistoricalDataMapper {
    List<HistoricalData> mapToHistoricalData(String data);
}
