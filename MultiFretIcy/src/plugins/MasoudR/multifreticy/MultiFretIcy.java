/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package plugins.MasoudR.multifreticy;

import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import icy.gui.frame.progress.ToolTipFrame;
import icy.plugin.abstract_.PluginActionable;
import plugins.MasoudR.multifreticy.Main.Prestart;

public class MultiFretIcy extends PluginActionable {
	// We open an instance of Prestart, which allows selecting/loading of
	// application preferences
	public static Prestart PS;

	@Override
	public void run() {
		// Check if agreement to terms of use exists
		FileInputStream in;
		Properties cp = new Properties();
		File f = new File(System.getProperty("user.home") + "\\MFIoptions.cfg");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				System.out.println("Could not write settings file!");
				e.printStackTrace();
				return;
			}
		}
		try {
			in = new FileInputStream(System.getProperty("user.home") + "\\MFIoptions.cfg");
			cp.load(in);
			in.close();

			Boolean agree = Boolean.parseBoolean(cp.getProperty("agree", "false"));
			System.out.println("Agree is " + agree);

			// If not, then open the dialog with the terms of use, finally save the user's
			// choice. Finally, we run2 to open the Prestart window.
			if (!agree) {
				if (JOptionPane.showConfirmDialog(null, getDisclaimer()) == JOptionPane.OK_OPTION) {
					cp.setProperty("agree", "true");
					cp.store(new FileOutputStream(System.getProperty("user.home") + "\\MFIoptions.cfg"), null);
					run2();
				}
			} else {
				run2();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private JEditorPane getDisclaimer() {
		// For copying style
		JLabel label = new JLabel();
		Font font = label.getFont();

		// Create some css from the label's font
		StringBuffer style = new StringBuffer("font-family:" + font.getFamily() + ";");
		style.append("font-weight:" + (font.isBold() ? "bold" : "normal") + ";");
		style.append("font-size:" + font.getSize() + "pt;");

		// HTML content
		JEditorPane ep = new JEditorPane("text/html", "<html><body style=\"" + style + "\">" //
				+ "This program is distributed in the hope that it will be useful,<br> "
				+ "but WITHOUT ANY WARRANTY; without even the implied warranty <br>"
				+ "of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. <br><br>"
				+ "See the GNU General Public License for more details.<br>"
				+ "MultiFretIcy is available under a GNU public “copyleft” license.<br>"
				+ "For questions about the GNU license refer to the <a href=\"http://www.gnu.org/licenses/gpl-faq.html\">Frequently Asked Questions about the GNU licenses page.</a><br>"
				+ "The text can be found <a href=\"http://www.gnu.org/licenses/gpl-3.0-standalone.html\">here.</a>"
				+ "</body></html>");

		// Handle link events
		ep.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED))
					try {
						Desktop.getDesktop().browse(new URI(e.getURL().toString()));
					} catch (IOException | URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		ep.setEditable(false);
		ep.setBackground(label.getBackground());
		return ep;
	}

	public void run2() {
		// Pop up a changelog within Icy for a set time, then run Prestart
		System.out.println("###### MFI Version 0.0.4.7 ######");
		new ToolTipFrame(
				"<html> <body bgcolor=\"#0036d8\"> <font color=\"#f9f1a4\"><b>Running MFI version 0.0.4.7</b></font><br>"
						+ "<font color=\"#14ff0c\"><sup>13/09/2021</sup>Changelog for this version:"
						+ "<br>•Code clean-up"
						+ "<font color=\"#14ff0c\"><sup>01/03/2021</sup>Changelog for last version:"
						+ "<br>•Fixed an issue with the custom calculations function"
						+ "<br>•Fixed an issue with the enhanced transformation function"
						+ "<br>•Added a prototype cell-detector function based on Active Contours"
						+ "<br>•Added labels for numerator / divisor in ROI settings pane"
						+ "<br>•Added the ability to choose a new .xlsx file if unable to write at the end of your experiment"
						+ "<br>•Added tab functionality to reduce GPU usage, limiting workspace tabs to a selected number of viewports before a new tab is created"
						+ "<br>•Milestones will now take only a designated section of the MultiFRET window"
						+ "<br>•Fixed an issue with graphs not appearing when starting the experiment before the microscope had the chance to capture any frames",
				20);
		PS = new Prestart();
		PS.run();
	}
}
