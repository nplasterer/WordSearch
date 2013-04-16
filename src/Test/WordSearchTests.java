package Test;

import static org.junit.Assert.*;
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
		fail("Not yet implemented");
	}
	
	@Test
	public void testLoadFileNames() {
		fail("Not yet implemented");
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
