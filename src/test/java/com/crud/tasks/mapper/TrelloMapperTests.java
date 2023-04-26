package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrelloMapperTests {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "Test - name", true);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1","Test - name", trelloListDtoList);
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertTrue(trelloBoards.get(0).getLists().get(0).isClosed());
        assertEquals("1",trelloBoards.get(0).getId());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "Test - name", true);
        List<TrelloList> trelloListList = new ArrayList<>();
        trelloListList.add(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("1","Test - name",trelloListList);
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertTrue(trelloBoardDtoList.get(0).getLists().get(0).isClosed());
        assertEquals("1",trelloBoardDtoList.get(0).getId());
    }

    @Test
    public void testMapToTrelloList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "Test - name", false);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(trelloListDto);

        //When
        List<TrelloList> trelloListList = trelloMapper.mapToList(trelloListDtoList);

        //Then
        assertFalse(trelloListList.get(0).isClosed());
    }

    @Test
    public void testMapToTrelloListDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "Test - name",false);
        List<TrelloList> trelloListList = new ArrayList<>();
        trelloListList.add(trelloList);

        //When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloListList);

        //Then
        assertFalse(trelloListDtoList.get(0).isClosed());
    }

    @Test
    public void testMapToTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test - name", "Test - description", "Test - pos", "Test - id");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("Test - name", trelloCard.getName());
    }

    @Test
    public void testMapToTrelloCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Test - name", "Test - description", "Test - pos", "Test - id");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("Test - id", trelloCardDto.getListId());
    }
}
