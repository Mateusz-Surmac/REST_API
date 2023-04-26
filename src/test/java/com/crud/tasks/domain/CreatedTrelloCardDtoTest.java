package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class CreatedTrelloCardDtoTest {

    @Test
    public void noArgumentsConstructor() {
        //Given
        CreatedTrelloCardDto createdTrelloCardDtoTest = new CreatedTrelloCardDto();

        //When

        //Then
        assertNull(createdTrelloCardDtoTest.getId());
    }

    @Test
    public void AllArgumentsConstructor() {
        //Given
        TrelloDto trelloDto = new TrelloDto(1,2);
        TrelloAttachmentsByTypeDto trelloAttachmentsByTypeDto = new TrelloAttachmentsByTypeDto(trelloDto);
        TrelloBadgesDto trelloBadgesDto = new TrelloBadgesDto(3,trelloAttachmentsByTypeDto);
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("Test - id", "Test - name", "Test - url", trelloBadgesDto);

        //When

        //Then
        assertEquals(1,createdTrelloCardDto.getTrelloBadgesDto().getAttachmentsByTypeDto().getTrelloDto().getBoard());
        assertEquals(2,createdTrelloCardDto.getTrelloBadgesDto().getAttachmentsByTypeDto().getTrelloDto().getCard());
        assertEquals(3,createdTrelloCardDto.getTrelloBadgesDto().getVotes());
        assertEquals("Test - id", createdTrelloCardDto.getId());
        assertEquals("Test - name", createdTrelloCardDto.getName());
        assertEquals("Test - url", createdTrelloCardDto.getShortUrl());
    }
}
