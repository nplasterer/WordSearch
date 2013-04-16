package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import WordSearch.Game;
import WordSearch.SplashScreen;


public class WordSearchTests {
	
	private static Game testGame;
	
	//Create test word search
	@BeforeClass
	public static void setUp() {
		testGame = new Game();
	}

	@Test
	public void testLoadingWords() {
		ArrayList<String> words = new ArrayList<String>();
		words = game.getListofLinesFromFile("SecretFiles", "Cats.txt");
		assertEquals(15, words.size());
		assertTrue(words.contains("Calico"));
		assertTrue(words.contains("Siamese"));
		assertTrue(words.contains("Bengal"));
		assertTrue(words.contains("Toyger"));
		
		words = game.getListofLinesFromFile("SecretFiles", "Automobiles.txt");
		assertEquals(11, words.size());
		assertTrue(words.contains("Dodge"));
		assertTrue(words.contains("Mitsubishi"));
		assertTrue(words.contains("Hummer"));
		assertTrue(words.contains("Jeep"));
	}
	
	@Test
	public void testLoadFileNames() {
		ArrayList<String> files = new ArrayList<String>();
		files = game.getListofFiles("SecretFiles", ".txt", true);
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
	
	//check valid selection directions and words
	@Test
	public void testMouseListener() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCellCharacters() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGameEnds() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testWordBank() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testHighlightingWords() {
		fail("Not yet implemented");
	}
}
