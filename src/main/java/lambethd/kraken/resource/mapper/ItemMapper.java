package lambethd.kraken.resource.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lambethd.kraken.resource.dto.ItemResponseDto;
import lambethd.kraken.resource.interfaces.IItemMapper;
import lambethd.kraken.resource.interfaces.IPriceMapper;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runescape.ItemCategory;
import runescape.Item;
import runescape.ItemMovement;
import runescape.Trend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemMapper implements IItemMapper {

    @Autowired
    private IPriceMapper priceMapper;

    public List<Item> mapToItemDto(String itemResponseString) throws IOException {
        if(itemResponseString.equals("")){
            return new ArrayList<>();
        }
        ItemResponseDto response = internalMapToItemResponse(itemResponseString);
        List<Item> items = response.items.stream().map((a) -> {
            Item item = new Item();
            item.id = Integer.parseInt(a.id);
            item.iconLink = a.icon;
            item.largeIconLink = a.icon_large;
            item.type = ItemCategory.valueOf(WordUtils.capitalizeFully(a.type.replace("-","").replace(",","")).replace(" ",""));
            item.typeIconLink = a.typeIcon;
            item.name = a.name;
            item.description = a.description;
            item.current = internalMapToItemMovement(a.current);
            item.today = internalMapToItemMovement(a.today);
            item.members = a.members;
            return item;
        }).collect(Collectors.toList());
        return items;
    }

    private ItemMovement internalMapToItemMovement(lambethd.kraken.resource.dto.ItemMovement itemMovement) {
        ItemMovement mapped = new ItemMovement();
        switch (itemMovement.trend) {
            case "negative":
                mapped.trend = Trend.Negative;
                break;
            case "neutral":
                mapped.trend = Trend.Neutral;
                break;
            case "positive":
                mapped.trend = Trend.Positive;
                break;
        }
        mapped.price = priceMapper.mapToFloat(itemMovement.price);
        return mapped;
    }

    private ItemResponseDto internalMapToItemResponse(String itemResponseString) throws IOException {
        return new ObjectMapper().readValue(itemResponseString, ItemResponseDto.class);
    }
}
