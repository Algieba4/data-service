package com.example.ds.idls;

import com.example.ds.models.entities.Enclosure;
import com.example.ds.repositories.EnclosureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnclosureDataLoader {

    private final EnclosureRepository enclosureRepository;
    private final JsonMapper jsonMapper;
    private final ResourceLoader resourceLoader;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {

        log.debug("Deleting Existing Enclosure Data...");
        enclosureRepository.deleteAll();
        log.debug("Existing Enclosure Data Deleted");

        log.debug("Loading Enclosure Data...");

        try {
            Resource resource = resourceLoader.getResource("classpath:/data/enclosures.json");

            if(!resource.exists()) {
                log.error("Enclosure data file not found: {}", resource.getFilename());
                return;
            }

            // Using TypeReference here because we're reading in a list of Enclosures.
            var enclosures = jsonMapper.readValue(resource.getInputStream(), new TypeReference<List<Enclosure>>() {});
            enclosureRepository.saveAll(enclosures);

        } catch (JacksonException jacksonException) {
            log.error("Error loading Enclosure Data: {}", jacksonException.getMessage(),  jacksonException);
        } catch (Exception exception) {
            log.error("Uncaught exception: {}", exception.getMessage(), exception);
        }

        log.debug("Finished loading Enclosure Data...");
    }

}
