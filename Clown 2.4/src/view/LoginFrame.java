package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Cloth;
import model.ClothSold;
import model.ModelListener;
import model.Provider;
import model.User;
import control.ActionManager;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame implements ModelListener{
	
	private JTextField username;
	private JPasswordField password;
	private ActionManager am;
	
	public LoginFrame(ActionManager am){
		super("Clown Baby");
		this.am = am;
		username = new JTextField("Administrador", 20);
		password = new JPasswordField(20);
//		password.requestFocusInWindow();
		addComponents();
	}
	
	private void addComponents(){
		JPanel panel1 = new JPanel();
		add(panel1);
		
		JLabel welcome = new JLabel(new ImageIcon(getClass().getResource("/Images/Clown.png")));
		Font font = new Font("Curlz MT", Font.PLAIN, 22);
		panel1.add(welcome);
		welcome.setFont(font);
		welcome.setForeground(Color.RED);
		
		JLabel usernameLabel = new JLabel("Usuario");
		panel1.add(usernameLabel);
		panel1.add(username);
		
		JLabel passwordLabel = new JLabel("Contraseņa");
		panel1.add(passwordLabel);
		panel1.add(password);
		
		username.setAction(am.getCheckLogin(this));
		password.setAction(am.getCheckLogin(this));
		
		JButton loginButton = new JButton();
		loginButton.setAction(am.getCheckLogin(this));
		panel1.add(loginButton);
		
		setSize(210, 270);
        setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setAlwaysOnTop(true);
	}

	public void addUser(User User) {
		setVisible(false);
	}

	public void removeUser() {
		setVisible(true);
	}

	public JTextField getUsername() {
		return username;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public void loadClothes(ArrayList<Cloth> clothes) {}

	public void loadCloth(Cloth cloth) {}

	public void removeCloth(Cloth cloth) {}

	public void addProvider(Provider provider) {}

	public void removeProvider(Provider provider) {}

	public void ModifyCloth(Cloth cloth) {}

	public void sell(Cloth cloth, int amount) {}

	public void loadSellClothes(ArrayList<ClothSold> clothSold) {}
}