package lambethd.kraken.resource.interfaces;

import runescape.Item;

import java.io.IOException;
import java.util.List;

public interface IItemMapper {
    public List<Item> mapToItemDto(String infoString) throws IOException;
}
