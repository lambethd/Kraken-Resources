package lambethd.kraken.resource.interfaces;

import runescape.Item;
import runescape.Runeday;

import java.io.IOException;
import java.util.List;

public interface IItemMapper {
    List<Item> mapToItemDto(String infoString, Runeday runeday) throws IOException;
}
