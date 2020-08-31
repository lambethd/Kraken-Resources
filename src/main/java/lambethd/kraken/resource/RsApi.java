package lambethd.kraken.resource;

import lambethd.kraken.resource.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runescape.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    //region IInfoApi
    public Runeday getInfo() throws IOException {
        try {
            String response = client.get(UrlConstants.URL_PREFIX + UrlConstants.INFO_SUFFIX);
            return infoMapper.mapToInfoDto(response);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    //endregion

    //region IItemApi
    @Override
    public Stream<Item> getItems() throws IOException {
        Stream<String> characters = IntStream.rangeClosed('a', 'z').mapToObj(i -> "" + ((char) i));

        return Arrays.stream(ItemCategory.values())
                .flatMap(i -> IntStream.rangeClosed('a', 'z').mapToObj(l -> "" + ((char) l)).map(c -> {
                    List<Item> responseDtos = new ArrayList<>();
                    List<Item> results = new ArrayList<>();
                    String urlSuffix = UrlConstants.ITEM_SUFFIX
                            .replace(UrlConstants.CATEGORY_ID_REPLACE, String.valueOf(i.ordinal()))
                            .replace(UrlConstants.ALPHA_REPLACE, c);
                    int pageCount = 1;
                    do {
                        try {
                            String response = client.get(UrlConstants.URL_PREFIX + urlSuffix.replace(UrlConstants.PAGE_ID_REPLACE, String.valueOf(pageCount)));
                            pageCount++;
                            responseDtos = itemMapper.mapToItemDto(response, getInfo());
                            results.addAll(responseDtos);
                        } catch (IOException e) {
                            //Do nothing, I don't really care
                            //TODO: probably deal with this at some point
                        }
                    } while (responseDtos.size() == 12);
                    return results;
                })).flatMap(Collection::stream);
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
