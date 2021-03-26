package lambethd.kraken.resource;

import lambethd.kraken.resource.interfaces.IHistoricalDataApi;
import lambethd.kraken.resource.interfaces.IHistoricalDataMapper;
import lambethd.kraken.resource.interfaces.ILatestDataApi;
import lambethd.kraken.resource.interfaces.ILatestDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runescape.HistoricalData;
import runescape.Item;
import runescape.UrlConstants;

import java.io.IOException;
import java.util.List;

@Service
public class WeirdGloopApi implements IHistoricalDataApi, ILatestDataApi {
    @Autowired
    private SimpleRestClient client;
    @Autowired
    private IHistoricalDataMapper historicalDataMapper;
    @Autowired
    private ILatestDataMapper latestDataMapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<HistoricalData> getHistoricalData(int itemId) throws IOException {
        try {
            String response = client.get(UrlConstants.WEIRD_GLOOP_PREFIX.concat(UrlConstants.WEIRD_GLOOP_HISTORICAL_SUFFIX).replace(UrlConstants.ITEM_ID_REPLACE, "" + itemId));
            List<HistoricalData> historicalData = historicalDataMapper.mapToHistoricalData(response);
            return historicalData;
        } catch (IOException e) {
            logger.error("Failed to get historical data", e);
            throw e;
        }
    }

    @Override
    public HistoricalData getLatestData(int itemId) throws IOException {
        try {
            String response = client.get(UrlConstants.WEIRD_GLOOP_PREFIX.concat(UrlConstants.WEIRD_GLOOP_LATEST_SUFFIX).replace(UrlConstants.ITEM_ID_REPLACE, "" + itemId));
            HistoricalData latestData = latestDataMapper.mapToLatestData(response);
            return latestData;
        } catch (IOException e) {
            logger.error("Failed to get latest data", e);
            throw e;
        }
    }
}
