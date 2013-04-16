package Test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import WordSearch.Board;
import WordSearch.Cell;
import WordSearch.Game;
import WordSearch.SplashScreen;
import WordSearch.WordBank;


public class WordSearchTests {
	
	private static Game testGame;
	
	//Create test word search
	@BeforeClass
	public static void setUp() {
		testGame = new Game();
		testGame.loadConfigFile("Cats.txt");
	}

	@Test
	public void testLoadingWords() {
		ArrayList<String> words = new ArrayList<String>();
		words = testGame.getListofLinesFromFile("SecretFiles", "Cats.txt");
		assertEquals(15, words.size());
		assertTrue(words.contains("Calico"));
		assertTrue(words.contains("Siamese"));
		assertTrue(words.contains("Bengal"));
		assertTrue(words.contains("Toyger"));
		
		words = testGame.getListofLinesFromFile("SecretFiles", "Automobiles.txt");
		assertEquals(11, words.size());
		assertTrue(words.contains("Dodge"));
		assertTrue(words.contains("Mitsubishi"));
		assertTrue(words.contains("Hummer"));
		assertTrue(words.contains("Jeep"));
	}
	
	@Test
	public void testLoadFileNames() {
		ArrayList<String> files = new ArrayList<String>();
		files = testGame.getListofFiles("SecretFiles", ".txt", true);
		assertEquals(2, files.size());
		assertTrue(files.contains("Cats"));
		assertTrue(files.contains("Automobiles"));
	}

	@Test
	public void testSplashScreen() {
		SplashScreen splashy = new SplashScreen();
		splashy.setCategoriesText("Cats");
		splashy.click();
		Assert.assertEquals("Cats", testGame.getCategory());
		
		splashy.setCategoriesText("Automobiles");
		splashy.click();
		Assert.assertEquals("Automobiles", testGame.getCategory());
	}
	
	//check selection is valid
	@Test
	public void testValidSelection() {
		//test same column
		assertTrue(testGame.checkValidSelection(new Point(1,1), new Point(1,15)));
		
		//test same row
		assertTrue(testGame.checkValidSelection(new Point(1,1), new Point(15,1)));
		
		//test diagonal
		assertTrue(testGame.checkValidSelection(new Point(1,1), new Point(5,5)));
		assertTrue(testGame.checkValidSelection(new Point(5,1), new Point(1,5)));
		
		//test invalid selections
		assertFalse(testGame.checkValidSelection(new Point(5,5), new Point(1,12)));
		assertFalse(testGame.checkValidSelection(new Point(7,8), new Point(3,2)));
		assertFalse(testGame.checkValidSelection(new Point(5,5), new Point(5,9)));
	}
	
	//check word is correct
	@Test
	public void testValidWord() {
		assertTrue(testGame.checkValidWord("Calico"));
		assertTrue(testGame.checkValidWord("Bengal"));
		assertTrue(testGame.checkValidWord("Highlander"));
		
		assertFalse(testGame.checkValidWord("Dodge"));
		assertFalse(testGame.checkValidWord("Husky"));
		assertFalse(testGame.checkValidWord("Mitsubishi"));
	}
		
	@Test
	public void testCellCharacters() {
		Board testBoard = new Board();
		String inserted = "Kohlrabi";
		Map<Character, Boolean> found = new HashMap<Character, Boolean>();
		testBoard.insertWord(inserted);
		
		for(int i = 0; i < inserted.length(); i++) {
			found.put(inserted.charAt(i), false);
		}
		
		for(int i = 0; i < testBoard.getRows(); i++) {
			for(int j = 0; j < testBoard.getColumns(); j++) {
				Cell c = testBoard.getCellAt(i, j);
				if(c.getLetter() != ' ') {
					found.put(c.getLetter(), true);
				}
			}
		}
		
		for(Boolean b : found.values()) {
			assertTrue(b);
		}
	}
	
	@Test
	public void testGameEnds() {
		testGame.setWordsLeft(0);
		Assert.assertTrue(testGame.checkIfDone());
	}
	
	@Test
	public void testWordBank() {
		WordBank bank = testGame.getWordBank();
		bank.checkBox("Calico");
		bank.checkBox("Siamese");
		bank.checkBox("Bengal");
		Assert.assertEquals(testGame.getWords().size(), bank.getWordBank().size());
		
		
		assertTrue(bank.getWordBank().get("Calico").isSelected());
		assertTrue(bank.getWordBank().get("Siamese").isSelected());
		assertTrue(bank.getWordBank().get("Bengal").isSelected());
		assertFalse(bank.getWordBank().get("Ocelot").isSelected());
		assertFalse(bank.getWordBank().get("Persian").isSelected());
	}
	
	@Test
	public void testHighlightingWords() {
		fail("Not yet implemented");
	}
}
