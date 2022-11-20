package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.npc.ghost.Blinky;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.AssertJUnit.assertNotNull;

/**
 * This is a test class for MapParser.
 */
@ExtendWith(MockitoExtension.class)
public class MapParserTest {
    @Mock
    private BoardFactory boardFactory;
    @Mock
    private LevelFactory levelFactory;
    @Mock
    private Blinky blinky;


    /**
     * Test for the parseMap method (good map).
     */
    @Test
    public void testParseMapGood() {
        MockitoAnnotations.initMocks(this);
        assertNotNull(boardFactory);
        assertNotNull(levelFactory);
        Mockito.when(levelFactory.createGhost()).thenReturn(blinky);
        MapParser mapParser = new MapParser(levelFactory, boardFactory);
        ArrayList<String> map = new ArrayList<>();
        map.add("############");
        map.add("#P        G#");
        map.add("############");
        mapParser.parseMap(map);
    }

    @Test
    public void testParseMapWrong1() {
        PacmanConfigurationException thrown =
            Assertions.assertThrows(PacmanConfigurationException.class,
                () -> {
                    MockitoAnnotations.initMocks(this);
                    assertNotNull(boardFactory);
                    assertNotNull(levelFactory);
                    MapParser mapParser = new MapParser(levelFactory,
                        boardFactory);
                    ArrayList<String> map = new ArrayList<>();
 /*
 Create a map with inconsistent size between
 each row or contain invalid characters
 */
                    map.add("############");
                    map.add("#P U G#");
                    map.add("############");
                    mapParser.parseMap(map);
                });
        Assertions.assertEquals("Invalid character at 3,1: U",
            thrown.getMessage());
    }


    Mockito.verify(levelFactory, Mockito.times(1)).createGhost();
}


