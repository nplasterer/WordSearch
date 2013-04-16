package WordSearch;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;


public class SplashScreen extends JDialog {
	private JTextField playerName;
	private JComboBox categories;
	private JButton okButton;
	
	public SplashScreen() {
		// TODO Auto-generated constructor stub
	}

	public void setCategoriesText(String category) {
		categories.setSelectedItem(category);
	}
	
	public void click() {
		okButton.doClick();
	}
}
