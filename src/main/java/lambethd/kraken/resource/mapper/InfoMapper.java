package lambethd.kraken.resource.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lambethd.kraken.resource.interfaces.IInfoMapper;
import org.springframework.stereotype.Service;
import runescape.Runeday;

import java.io.IOException;

@Service
public class InfoMapper implements IInfoMapper {
    public Runeday mapToInfoDto(String infoString) throws IOException {
        return new ObjectMapper().readValue(infoString, Runeday.class);
    }
}
