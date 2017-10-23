package com.cystech.shoeconverter;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import com.mcraedesign.exampleAPI.*;
import org.eclipse.swt.custom.ViewForm;

public class MainWindow {

	protected Shell shlCysShoeConverter;
	private Text shoeSize;
	private Color opaque = new Color(null, 0, 0, 0, 0);

	/**
	 * + * Launch the application. + * @param args +
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window. +
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCysShoeConverter.open();
		shlCysShoeConverter.layout();
		while (!shlCysShoeConverter.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window. +
	 */
	protected void createContents() {
		shlCysShoeConverter = new Shell();
		shlCysShoeConverter.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		shlCysShoeConverter.setSize(450, 225);
		shlCysShoeConverter.setText("CYS Shoe Converter");

		Label sizeLabel = new Label(shlCysShoeConverter, SWT.NONE);
		sizeLabel.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
		sizeLabel.setBounds(10, 10, 64, 18);
		sizeLabel.setText("Shoe Size:");
		sizeLabel.setBackground(opaque);

		shoeSize = new Text(shlCysShoeConverter, SWT.NONE);
		shoeSize.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
		shoeSize.setBounds(80, 10, 76, 21);
		shoeSize.setBackground(opaque);

		Button convert = new Button(shlCysShoeConverter, SWT.NONE);
		convert.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
		convert.setBounds(10, 80, 100, 25);
		convert.setText("Convert");
		convert.setBackground(opaque);

		Label returnSize = new Label(shlCysShoeConverter, SWT.NONE);
		returnSize.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		returnSize.setAlignment(SWT.RIGHT);
		returnSize.setFont(SWTResourceManager.getFont("Calibri", 30, SWT.NORMAL));
		returnSize.setBounds(224, 10, 200, 50);
		returnSize.setBackground(opaque);

		Label sizing = new Label(shlCysShoeConverter, SWT.NONE);
		sizing.setAlignment(SWT.RIGHT);
		sizing.setBounds(324, 66, 100, 20);
		sizing.setText("US / UK / EU");
		sizing.setBackground(opaque);

		Button infoBtn = new Button(shlCysShoeConverter, SWT.NONE);
		infoBtn.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
		infoBtn.setBounds(10, 111, 50, 25);
		infoBtn.setText("Info");
		infoBtn.setBackground(opaque);

		Button helpBtn = new Button(shlCysShoeConverter, SWT.NONE);
		helpBtn.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
		helpBtn.setBounds(60, 111, 50, 25);
		helpBtn.setText("Help");
		helpBtn.setBackground(opaque);

		Button btnMens = new Button(shlCysShoeConverter, SWT.RADIO);
		btnMens.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
		btnMens.setBounds(10, 36, 90, 16);
		btnMens.setText("Mens");
		btnMens.setBackground(opaque);

		Button btnWomens = new Button(shlCysShoeConverter, SWT.RADIO);
		btnWomens.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
		btnWomens.setBounds(10, 58, 90, 16);
		btnWomens.setText("Womens");
		btnWomens.setBackground(opaque);

		convert.setEnabled(false);

		ViewForm viewForm = new ViewForm(shlCysShoeConverter, SWT.NONE);
		viewForm.setLocation(0, 0);
		btnMens.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (!shoeSize.getText().isEmpty() && btnMens.getSelection()) {
					convert.setEnabled(true);
					convert.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event event) {
							double size = Double.parseDouble(shoeSize.getText());
							String uk = ShoeSizeConverter.mensUSToUK(size);
							String eu = ShoeSizeConverter.mensUSToEu(size);
							returnSize.setText(shoeSize.getText() + "/" + uk + "/" + eu);
						}
					});
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		btnWomens.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (!shoeSize.getText().isEmpty() && btnWomens.getSelection()) {
					convert.setEnabled(true);
					convert.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event event) {
							double size = Double.parseDouble(shoeSize.getText());
							String uk = ShoeSizeConverter.wommensUSToUK(size);
							String eu = ShoeSizeConverter.wommensUSToEu(size);
							returnSize.setText(shoeSize.getText() + "/" + uk + "/" + eu);
						}
					});
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		infoBtn.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				String infoTxt = "Clean Your Shoes\nShoe Size Converter\nVersion 1.0.0 (BETA)\nDeveloped by Jordan C. McRae\nFor CYS Tech";
				MessageBox messageBox = new MessageBox(shlCysShoeConverter, SWT.OK);
				messageBox.setText("Information");
				messageBox.setMessage(infoTxt);
				messageBox.open();
			}
		});

		helpBtn.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				String help = "Steps:\n1. Enter your shoe size.\n2. Select Men's or Women's.\n3. Press Convert.";
				MessageBox messageBox = new MessageBox(shlCysShoeConverter, SWT.OK);
				messageBox.setText("Help");
				messageBox.setMessage(help);
				messageBox.open();
			}
		});
	}
}
