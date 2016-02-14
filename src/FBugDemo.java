import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;


public class FBugDemo {
	public static void main(String[] args) throws IOException {
		final PDDocument document = new PDDocument();
		final PDType0Font font = PDType0Font.load(document, FBugDemo.class.getResourceAsStream("Lato-Regular.ttf"));
		final PDPage page = new PDPage(PDRectangle.A4);
		document.addPage(page);
		final PDPageContentStream stream = new PDPageContentStream(document, page, true, false);
		stream.beginText();
		stream.setFont(font, 12);
		stream.newLineAtOffset(50, 500);
		stream.showText("ABCDEFG");
		stream.endText();
		stream.close();
		final OutputStream result = new FileOutputStream(FBugDemo.class.getSimpleName() + ".pdf");
		document.save(result);
		document.close();
	}
}
