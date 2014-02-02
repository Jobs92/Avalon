package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Dictionary;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class PurchasePanel extends AvalonPanel {
	private ArrayList<JLabel> supplierLabels = new ArrayList<JLabel>();
	private ArrayList<AvalonButton> info = new ArrayList<AvalonButton>();
	private ArrayList<JTextField> amount = new ArrayList<JTextField>();
	private JLabel sumLabel = new JLabel();
	JPanel supplierPanel;
	private ArrayList<Dictionary<String, String>> supplier;

	public PurchasePanel() {
		TitledBorder tb = new TitledBorder("Purchase");
		setBorder(tb);
		setLayout(new BorderLayout());

		supplierPanel = new JPanel();
		supplierPanel.setBackground(getBackground());
		supplierPanel.setLayout(new GridLayout(3, 3));

		// init arraylists
		for (int i = 1; i <= 3; i++) {
			supplierLabels.add(new JLabel("Supplier #" + i));

			AvalonButton b = new AvalonButton("Info");
			b.setName(String.valueOf(i - 1));
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					AvalonButton b = (AvalonButton) e.getSource();
					makeInfoPopup(Integer.parseInt(b.getName()));
				}
			});
			info.add(b);

			JTextField t = new JTextField();
			t.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
					updateSum();
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}
			});
			amount.add(t);
		}

		// fill ui
		for (int i = 0; i < 3; i++) {
			supplierPanel.add(supplierLabels.get(i));
			supplierPanel.add(info.get(i));
			supplierPanel.add(amount.get(i));
		}

		add(supplierPanel, BorderLayout.NORTH);
		add(sumLabel, BorderLayout.CENTER);
	}

	protected void makeInfoPopup(int index) {
		if (index > -1) {
			String infoString = "Trust: " + supplier.get(index).get("trust")
					+ ", quality: " + supplier.get(index).get("quality")
					+ ", price: " + supplier.get(index).get("price");
			JOptionPane.showMessageDialog(null, infoString, "Supplier #"
					+ index, JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void updateSum() {
		int sum = 0;
		int cost = 0;
		int i = 0;
		for (JTextField t : amount) {
			String s = t.getText();
			if (!s.equalsIgnoreCase("")) {
				int price = new Double(supplier.get(i).get("price")).intValue();
				sum += Integer.parseInt(t.getText());
				cost += Integer.parseInt(t.getText()) * price;
			}
			i++;
		}
		sumLabel.setText("<html><p>Sum Resources: " + sum + "<br> Total Cost: "
				+ cost + "</p></html>");
	}

	@Override
	protected void fill() {
		updateSum();
		setBorder(new TitledBorder("Purchase (Fixcosts: "
				+ GuiManager.sharedInstance().getDs()
						.getDepartmentFixcosts("purchase") + ")"));
		supplier = GuiManager.sharedInstance().getDs().getSupplier();
		for (int i = 0; i < supplierLabels.size(); i++) {
			supplierLabels.get(i).setText(supplier.get(i).get("name"));
			amount.get(i).setText("0");
		}
		refresh();
		refreshBackground(getBackground());
	}

	@Override
	protected void send() {
		for (int i = 0; i < amount.size(); i++) {
			String value = amount.get(i).getText();
			if (!isNumeric(value) || Integer.parseInt(value) > 0) {
				GuiManager.sharedInstance().getApi()
						.buy(i, Integer.parseInt(value));
			}
		}
	}
	
	private static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    @SuppressWarnings("unused")
		double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

	@Override
	protected void refreshBackground(Color bg) {
		for (JLabel l : supplierLabels) {
			l.setBackground(bg);
		}
		supplierPanel.setBackground(bg);
	}

}
