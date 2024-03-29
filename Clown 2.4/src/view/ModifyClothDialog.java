package view;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.Cloth;
import model.Provider;
import control.ActionManager;
import enums.ClothColor;
import enums.Seasson;
import enums.Sex;

@SuppressWarnings("serial")
public class ModifyClothDialog extends JDialog{

	private JTextField code;
	private JTextField description;
	private JComboBox color;
	private JTextField size;
	private JTextField cost;
	private JTextField wholesalePrice;
	private JTextField retailPrice;
	private JTextField amount;
	private JComboBox seasson;
	private JComboBox provider;
	private JComboBox year;
	private JComboBox sex;
	private JButton addProvider;
	
	private JDialog photoDialog;
	private JLabel photoLabel;
	private ImageIcon photo;
	
	private JButton modifyItemButton;
	private JButton cancelButton;
	private JButton addImageButton;
	
	private ActionManager am;
	
	private String path;
	
	private Cloth cloth;
	private JLabel pathLabel;
	
	public String getPath() {
		return path;
	}

	public ModifyClothDialog(ActionManager am, JFrame frame){
		super(frame);
		this.am = am;
		photo = new ImageIcon(getClass().getResource("/Images/noDisponible.jpg"));
		photoLabel = new JLabel(photo);
		photoDialog = new JDialog(this);
		setImageNormalSize();
		photoDialog.add(photoLabel);
		photoDialog.setSize(180, 180);
		photoDialog.setLocation(30, 30);
		addContent();
	}

	private void addContent() {
		JPanel panel1 = new JPanel(new GridLayout(15,0));
		panel1.setBorder(new TitledBorder("Modificar Producto"));
		getContentPane().add(panel1);
		
		panel1.add(new JLabel("  C�digo:"));
		code = new JTextField(20);
		panel1.add(code);

		
		panel1.add(new JLabel("  Descripci�n:"));
		description = new JTextField(20);
		panel1.add(description);

		
		panel1.add(new JLabel("  Color:"));
		color = new JComboBox(ClothColor.values());
		panel1.add(color);
		
		panel1.add(new JLabel("  Talle:"));
		size = new JTextField(20);
		panel1.add(size);

		
		panel1.add(new JLabel("  Costo: (en pesos $)"));
		cost = new JTextField(20);
		panel1.add(cost);
		
		panel1.add(new JLabel("  Precio por Mayor: (en pesos $)"));
		wholesalePrice = new JTextField(20);
		panel1.add(wholesalePrice);

		panel1.add(new JLabel("  Precio por Menor: (en pesos $)"));
		retailPrice = new JTextField(20);
		panel1.add(retailPrice);
		
		panel1.add(new JLabel("  Cantidad:"));
		amount = new JTextField(20);
		panel1.add(amount);
		
		panel1.add(new JLabel("  Temporada:"));
		seasson = new JComboBox(Seasson.values());
		panel1.add(seasson);
		
		panel1.add(new JLabel("  Proveedor:"));
		provider = new JComboBox();
		panel1.add(provider);
		
		panel1.add(new JLabel("  A�o:"));
		year = new JComboBox();
		for(int i = 2008; i < 2050; i++){
			year.addItem(i);
		}
		panel1.add(year);
		
		panel1.add(new JLabel("  Sexo:"));
		sex = new JComboBox(Sex.values());
		panel1.add(sex);
		
		panel1.add(new JLabel("  Imagen:"));
		pathLabel = new JLabel("vacio");
		panel1.add(pathLabel);
		
		addProvider = new JButton();
		addProvider.setAction(am.getAddProvider());
		panel1.add(addProvider);
		
		addImageButton = new JButton("Agregar Imagen...");
		addImageButton.setAction(addImageAction);
		panel1.add(addImageButton);
		
		modifyItemButton = new JButton();
		panel1.add(modifyItemButton);
		modifyItemButton.setAction(am.getModifyItem(this));
		
		cancelButton = new JButton("Cancelar");
		cancelButton.setAction(exit);
		panel1.add(cancelButton);
		
		setSize(300, 500);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public String getCode(){
		return code.getText();
	}
	
	public String getDescription(){
		return description.getText();
	}
	
	public ClothColor getColor(){
		return (ClothColor)color.getSelectedItem();
	}
	
	public String getItemSize(){
		return size.getText();
	}
	
	public String getCost(){
		return cost.getText();
	}
	
	public String getWholesalePrice(){
		return wholesalePrice.getText();
	}
	
	public String getRetailPrice(){
		return retailPrice.getText();
	}
	
	public String getYear(){
		return year.getSelectedItem().toString();
	}
	
	public Sex getSex(){
		return (Sex) sex.getSelectedItem();
	}
	
	public String getAmount(){
		return amount.getText();
	}
	
	public Seasson getSeasson(){
		return (Seasson) seasson.getSelectedItem();
	}
	
	public Provider getProvider(){
		return new Provider(provider.getSelectedItem().toString());
	}
	
	public JButton getAddItemButton(){
		return modifyItemButton;
	}

	public JButton getCancelButton(){
		return cancelButton;
	}
	
	public void openWindow(Cloth cloth){
		setCloth(cloth);
		setVisible(true);
		photoDialog.setVisible(true);
	}
	
	private AbstractAction exit = new AbstractAction("Cancelar"){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	};
	
	private AbstractAction addImageAction = new AbstractAction("Modificar Imagen", new ImageIcon(getClass().getResource("/Images/Picture.png"))){
		public void actionPerformed(ActionEvent e){
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            path = file.getAbsolutePath();
	            pathLabel.setText(path);
	            setImage(path);
			}
		}
	};

	public void addProvider(Provider provider) {
		this.provider.addItem(provider.getName());
	}
	
	public void removeProvider(Provider provider) {
		this.provider.removeItem(provider.getName());
	}

	public Cloth getCloth() {
		return cloth;
	}

	public void setCloth(Cloth cloth) {
		this.cloth = cloth;
		code.setText(cloth.getCode());
		description.setText(cloth.getDescription());
		size.setText(cloth.getSize());
		amount.setText(new Integer(new Double(cloth.getAmount()).intValue()).toString());
		cost.setText(new Double(cloth.getCost()).toString());
		retailPrice.setText(new Double(cloth.getRetailPrice()).toString());
		wholesalePrice.setText(new Double(cloth.getWholesalePrice()).toString());
		seasson.setSelectedItem(cloth.getSeasson());
		provider.setSelectedItem(cloth.getProvider().getName());
		year.setSelectedItem(cloth.getYear());
		sex.setSelectedItem(cloth.getSex());
		path = cloth.getImagePath();
		pathLabel.setText(cloth.getImagePath());
		setImage(cloth.getImagePath());
	}
	
	private void setImage(String path){
		if(path != null){
			File f1 = new File(path);
			if(f1.exists()){
				remove(photoLabel);
				photo = new ImageIcon(f1.getAbsolutePath());
				photoLabel.setIcon(photo);
			}else{
				photo = new ImageIcon(getClass().getResource("/Images/noDisponible.jpg"));
				photoLabel.setIcon(photo);
			}
		}else{
			photo = new ImageIcon(getClass().getResource("/Images/noDisponible.jpg"));
			photoLabel.setIcon(photo);
		}
		setImageNormalSize();
	}
	
	private void setImageNormalSize(){
		Image newImage = photo.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		photo.setImage(newImage);
	}
}