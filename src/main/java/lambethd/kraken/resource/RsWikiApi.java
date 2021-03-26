package lambethd.kraken.resource;

import lambethd.kraken.resource.interfaces.IBuyingLimitApi;
import lambethd.kraken.resource.mapper.BuyingLimitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runescape.BuyingLimit;
import runescape.UrlConstants;

import java.io.IOException;
import java.util.List;

@Service
public class RsWikiApi implements IBuyingLimitApi {
    @Autowired
    private SimpleRestClient client;
    @Autowired
    private BuyingLimitMapper mapper;

    @Override
    public List<BuyingLimit> getBuyingLimits() throws IOException {
        try {
            String response = client.get(UrlConstants.WIKI_BUYING_LIMIT_URL);
            List<BuyingLimit> buyingLimits = mapper.map(response);
            return buyingLimits;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
