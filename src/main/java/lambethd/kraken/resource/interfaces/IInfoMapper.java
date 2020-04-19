package lambethd.kraken.resource.interfaces;

import org.springframework.stereotype.Service;
import runescape.Runeday;

import java.io.IOException;

@Service
public interface IInfoMapper {
    Runeday mapToInfoDto(String infoString) throws IOException;
}
