package gui.ex24;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

public class Property extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ClockPanel clock;
	ClockFrame frame;
	ClockData backup;

	public Property(ClockFrame frame, ClockPanel clock, ClockData backup) {
		this.clock = clock;
		this.frame = frame;
		this.backup = backup;

		setBounds(100, 100, 450, 324);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setComponents();
	}

	private void setComponents() {
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 173, 100 };
		gbl_contentPanel.rowHeights = new int[] { 60, 60, 60, 60, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblFont = new JLabel("Font");
			GridBagConstraints gbc_lblFont = new GridBagConstraints();
			gbc_lblFont.insets = new Insets(0, 0, 5, 5);
			gbc_lblFont.gridx = 0;
			gbc_lblFont.gridy = 0;
			contentPanel.add(lblFont, gbc_lblFont);
		}
		{
			String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(fonts);
			JComboBox<String> comboBox = new JComboBox<String>(model);
			comboBox.setBackground(Color.WHITE);
			comboBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					clock.font = new Font((String) e.getItem(), Font.PLAIN, clock.font.getSize());
				}
			});

			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 0);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 0;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			JLabel lblFontSize = new JLabel("Font Size");
			GridBagConstraints gbc_lblFontSize = new GridBagConstraints();
			gbc_lblFontSize.insets = new Insets(0, 0, 5, 5);
			gbc_lblFontSize.gridx = 0;
			gbc_lblFontSize.gridy = 1;
			contentPanel.add(lblFontSize, gbc_lblFontSize);
		}
		{
			String sizes[] = { "Small", "Normal", "Large", "XLarge" };
			double ratios[] = { 0.5, 1.0, 2.0, 3.0 };

			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(sizes);
			JComboBox<String> comboBox = new JComboBox<String>(model);
			comboBox.setBackground(Color.WHITE);
			comboBox.setSelectedIndex(1);
			comboBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					int index = comboBox.getSelectedIndex();
					double ratio = ratios[index];
					clock.ratio = ratio;
					frame.setSize((int) (frame.WITDH * ratio), (int) (frame.HEIGHT * ratio));
					clock.font = new Font(clock.font.getFontName(), Font.PLAIN, (int) (clock.FONT_SIZE * ratio));
				}
			});
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 0);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 1;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			JLabel lblNewLabel = new JLabel("Font Color");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 2;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			// DefaultComboBoxModel<String> colors = new
			// DefaultComboBoxModel<String>(RichColors.richColors);
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(RichColors.richColors);
			// for(String color : RichColors.richColors) {
			// model.addElement(new ComboLabel("Lion", new ImageIcon("./img/reo1s.png"));
			// }
			JComboBox<String> comboBox = new JComboBox<String>(model);
			MyCellRenderer renderer = new MyCellRenderer();
			comboBox.setRenderer(renderer);
			comboBox.setBackground(Color.WHITE);
			comboBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					clock.fontColor = RichColors.toRealColor((String) e.getItem());
				}
			});

			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 0);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 2;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Back Color");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 3;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(RichColors.richColors);
			JComboBox<String> comboBox = new JComboBox<String>(model);
			MyCellRenderer renderer = new MyCellRenderer();
			comboBox.setRenderer(renderer);
			comboBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					clock.backColor = RichColors.toRealColor((String) e.getItem());
				}
			});
			comboBox.setBackground(Color.WHITE);

			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 3;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Property.this.setVisible(false);
					}
				});

				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton okButton = new JButton("Cansel");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						clock.ratio = backup.ratio;
						clock.font = new Font(backup.fontName, Font.PLAIN, (int) (clock.FONT_SIZE * clock.ratio));
						frame.setSize((int) (frame.WITDH * backup.ratio), (int) (frame.HEIGHT * backup.ratio));
						clock.backColor = backup.backColor;
						clock.fontColor = backup.fontColor;
						Property.this.setVisible(false);
					}
				});

				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	// Graphics g = getGraphics();
	class MyCellRenderer extends JLabel implements ListCellRenderer<String> {
		BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_3BYTE_BGR);
		MyCellRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
				boolean isSelected, boolean cellHasFocus) {

			setText(value);
			ImageIcon icon = new ImageIcon();
			Graphics g = image.createGraphics();
			g.setColor(RichColors.toRealColor(value));
			g.fillRect(0, 0, image.getWidth(), image.getHeight());
			icon.setImage(image);
			setIcon(icon);

			setBackground( isSelected ? RichColors.toRealColor(value) : Color.WHITE );

			return this;
		}
	}

}
