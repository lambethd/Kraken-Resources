package lambethd.kraken.resource.interfaces;

import runescape.HistoricalData;

public interface ILatestDataMapper {
    HistoricalData mapToLatestData(String data);
}
