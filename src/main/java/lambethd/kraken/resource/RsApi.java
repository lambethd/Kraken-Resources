package lambethd.kraken.resource;

import lambethd.kraken.resource.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runescape.*;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RsApi implements IInfoApi, IItemApi, ICatalogueApi, IGraphApi, IHistoricalDataApi {
    @Autowired
    private SimpleRestClient client;
    @Autowired
    private IInfoMapper infoMapper;
    @Autowired
    private IItemMapper itemMapper;
    @Autowired
    private ICatalogueMapper catalogueMapper;
    @Autowired
    private IGraphMapper graphMapper;
    @Autowired
    private IHistoricalDataMapper historicalDataMapper;

    private Runeday runedayCache;

    //region IInfoApi
    public Runeday getInfo() throws IOException {
        int hour = LocalDateTime.now(Clock.systemUTC()).getHour();
        if (runedayCache == null || hour >= 23) {
            try {
                String response = client.get(UrlConstants.URL_PREFIX + UrlConstants.INFO_SUFFIX);
                runedayCache = infoMapper.mapToInfoDto(response);
                return runedayCache;
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        }

        return runedayCache;
    }
    //endregion

    //region IItemApi
    @Override
    public List<Item> getItems() throws IOException {
        try {
            List<Item> results = new ArrayList<>();
            for (ItemCategory value : ItemCategory.values()) {
                for (int l = 0; l < 26; l++) {
                    String urlSuffix = UrlConstants.ITEM_SUFFIX
                            .replace(UrlConstants.CATEGORY_ID_REPLACE, String.valueOf(value.ordinal()))
                            .replace(UrlConstants.ALPHA_REPLACE, String.valueOf((char) (l + 97)));
                    List<Item> responseDtos;
                    int pageCount = 1;
                    do {
                        String response = client.get(UrlConstants.URL_PREFIX + urlSuffix.replace(UrlConstants.PAGE_ID_REPLACE, String.valueOf(pageCount)));
                        pageCount++;
                        responseDtos = itemMapper.mapToItemDto(response, getInfo());
                        results.addAll(responseDtos);
                    } while (responseDtos.size() == 12);
                }
            }
            return results;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    //endregion

    //region ICatalogueApi
    @Override
    public Catalogue getCatalogueForCategory(ItemCategory category) throws IOException {
        try {
            String response = client.get(UrlConstants.URL_PREFIX + UrlConstants.CATALOGUE_SUFFIX.replace(UrlConstants.CATEGORY_ID_REPLACE, String.valueOf(category.ordinal())));
            Catalogue catalogue = catalogueMapper.mapToCatalogue(response);
            catalogue.types = new ArrayList<>(Collections.singleton(category));
            return catalogue;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Graph getGraph(int itemId) throws IOException {
        try {
            String response = client.get(UrlConstants.URL_PREFIX + UrlConstants.GRAPH_SUFFIX.replace(UrlConstants.ITEM_ID_REPLACE, String.valueOf(itemId)));
            Graph graph = graphMapper.mapToGraph(response);
            graph.id = itemId;
            return graph;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    //endregion

    //region IHistoricalDataApi
    @Override
    public List<HistoricalData> getHistoricalData(String itemName) throws IOException {
        try {
            String response = client.get(UrlConstants.WIKI_PREFIX.replace(UrlConstants.ITEM_NAME_REPLACE, itemName.replace(" ", "_")));
            return historicalDataMapper.mapToHistoricalData(response);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    //endregion
}
