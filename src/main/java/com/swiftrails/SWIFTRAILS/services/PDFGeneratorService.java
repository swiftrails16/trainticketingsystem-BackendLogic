package com.swiftrails.SWIFTRAILS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PDFGeneratorService {

    @Autowired
    public EmailSenderService senderService;

    public byte[] export(String mailId, String cardNumber, String cardExpiry, String cardCVV, double amount,
                         String name, String trainName, String date) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

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
