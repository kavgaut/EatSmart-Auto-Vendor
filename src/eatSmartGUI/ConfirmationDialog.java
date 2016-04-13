package eatSmartGUI;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class ConfirmationDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JLabel lblMessageLabel;

	/**
	 * Create the dialog.
	 */
	public ConfirmationDialog() {
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		setBounds(100, 100, 350, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		lblMessageLabel = new JLabel("Confirmation message");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMessageLabel, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMessageLabel, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				okButton.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
