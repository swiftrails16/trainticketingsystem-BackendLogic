package com.swiftrails.SWIFTRAILS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.UUID;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.swiftrails.SWIFTRAILS.DBConnection.DBconnection;

@Service
public class PDFGeneratorService {

	@Autowired
	public DBconnection dbconnection;
	
    @Autowired
    public EmailSenderService senderService;

    public byte[] export(String mailId, String cardNumber, String cardExpiry, String cardCVV, double amount,
                         String name, String trainName, String date,String seat,String from_station,
                         String to_station) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        String responseCode = null;
		String query = "INSERT INTO history VALUES(?,?,?,?,?,?,?)";
		String query2= "INSERT INTO cardDetails VALUES(?,?,?,?,?)";
		String transID = UUID.randomUUID().toString();
		String daySplit[]=date.split("/");
		int year=Integer.parseInt(daySplit[2]);
		System.out.println(year);
		int month=Integer.parseInt(daySplit[1]);
		int day=Integer.parseInt(daySplit[0]);https://pace.zoom.us/j/93323900452 (Passcode: 564750)
		try {
			Connection con = dbconnection.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			PreparedStatement ps2=con.prepareStatement(query2);
			ps.setString(1, transID);
			ps.setString(2, mailId);
			ps.setDate(3, new java.sql.Date(year, month, day));
			ps.setString(4, from_station);
			ps.setString(5, to_station);
			ps.setString(6, seat);
			ps.setDouble(7, amount);
			int rs = ps.executeUpdate();
			ps2.setString(1, cardNumber);
			ps2.setString(2, cardExpiry);
			ps2.setString(3, cardCVV);
			ps2.setString(4, "Visa");
			ps2.setString(5, mailId);
			
			int rs2=ps2.executeUpdate();
			System.out.println(rs);
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().toUpperCase().contains("ORA-00001")) {
				responseCode += " : " + "User With Id: " + mailId + " is already registered ";
			} else {
				responseCode += " : " + e.getMessage();
				System.out.println(responseCode);
			}
		}

        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, baos);
            document.open();

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontTitle.setSize(18);
            Paragraph title = new Paragraph("Ticket Information", fontTitle);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA);
            fontNormal.setSize(12);
            Paragraph info = new Paragraph("Below is some information about the ticket", fontNormal);
            info.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(info);

            PdfPTable table = new PdfPTable(2);
            table.addCell("Mail ID");
            table.addCell(mailId);

            table.addCell("Card Number");
            table.addCell(cardNumber);

            table.addCell("Card Expiry");
            table.addCell(cardExpiry);

            table.addCell("Card CVV");
            table.addCell(cardCVV);

            table.addCell("Amount");
            table.addCell(String.valueOf(amount));

            table.addCell("Name");
            table.addCell(name);

            table.addCell("Train Name");
            table.addCell(trainName);

            table.addCell("Date");
            table.addCell(date);
            
            table.addCell("From Station");
            table.addCell(from_station);
            
            table.addCell("To Station");
            table.addCell(to_station);

            document.add(table);
            document.close();

            // Sending email with the attachment
            String emailBody = "This is your Ticket information";
            senderService.sendEmailWithAttachment(mailId, "Welcome to SWIFTRAILS", emailBody, baos.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }
}
