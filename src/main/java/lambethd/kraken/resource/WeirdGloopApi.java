package lambethd.kraken.resource;

import lambethd.kraken.resource.interfaces.IHistoricalDataApi;
import lambethd.kraken.resource.interfaces.IHistoricalDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runescape.HistoricalData;
import runescape.UrlConstants;

import java.io.IOException;
import java.util.List;

@Service
public class WeirdGloopApi implements IHistoricalDataApi {
    @Autowired
    private SimpleRestClient client;
    @Autowired
    private IHistoricalDataMapper historicalDataMapper;

    @Override
    public List<HistoricalData> getHistoricalData(int itemId) throws IOException {
        try {
            String response = client.get(UrlConstants.WEIRD_GLOOP_PREFIX.replace(UrlConstants.ITEM_ID_REPLACE, "" + itemId));
            List<HistoricalData> historicalData = historicalDataMapper.mapToHistoricalData(response);
            return historicalData;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
