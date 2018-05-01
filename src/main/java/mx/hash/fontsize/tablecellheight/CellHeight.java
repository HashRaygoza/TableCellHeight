/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.hash.fontsize.tablecellheight;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class CellHeight {

    private static Logger LOGGER = Logger.getLogger("mx.hash.fontsize.tablecellheight.CellHeight");

    public Table crearTablaConAtura(float altura) {
        // Creamos la tabla
        float[] anchos = {150f, 150f, 150f};
        Table tabla = new Table(anchos);
        
        // Creamos las celdas
        Cell celda1 = new Cell();
        Cell celda2 = new Cell();
        Cell celda3 = new Cell();

        celda1.add(new Paragraph("Celda 1"));
        // Indicamos la altura para la celda 1
        celda1.setHeight(altura);

        celda2.add(new Paragraph("Celda 2"));
        // Indicamos la altura para la celda 2
        celda2.setHeight(altura);

        celda3.add(new Paragraph("Celda 3"));
        // Indicamos la altura para la celda 3
        celda3.setHeight(altura);
        
        // Agregamos las celdas a la tabla
        tabla.addCell(celda1);
        tabla.addCell(celda2);
        tabla.addCell(celda3);

        return tabla;
    }

    static public void main(String[] args) {
        PdfWriter pdfWriter;
        try {
            // Creamos un documento pdf con iText
            pdfWriter = new PdfWriter("./fontSize.pdf");
            PdfDocument pdfDoc = new PdfDocument(pdfWriter);
            Document doc = new Document(pdfDoc, PageSize.LETTER);
            
            CellHeight generadorTablas = new CellHeight();
            
            doc.add( new Paragraph("Altura 12f, demasiado chico para el tamaño de texto, por lo que no se vera, tenga cuidado con esto y redusca el tamaño de fuente acordemente") );
            doc.add( generadorTablas.crearTablaConAtura(12f) );
            
            Table tablaChica = generadorTablas.crearTablaConAtura(12f);
            // Cambiamos el tamaño de texto
            tablaChica.setFontSize(8f);
            
            doc.add( new Paragraph("Con un tamaño de texto acorde ya se ve mejor") );
            doc.add(tablaChica);
            
            doc.add( new Paragraph("Altura 22f") );
            doc.add( generadorTablas.crearTablaConAtura(22f) );
            
            doc.add( new Paragraph("Altura 32f") );
            doc.add( generadorTablas.crearTablaConAtura(32f) );
            
            doc.add( new Paragraph("Altura 42f") );
            doc.add( generadorTablas.crearTablaConAtura(42f) );
            
            doc.add( new Paragraph("Altura 52f") );
            doc.add( generadorTablas.crearTablaConAtura(52f) );
            
            doc.add( new Paragraph("El tamaño se respeta por *fila*, al cambiar de fila usa la altura de la celda mas alta de esa fila") );
            
            // Una tabla a la que agregaremos otra fila
            Table tablaExtendida =  generadorTablas.crearTablaConAtura(52f);
            tablaExtendida.addCell( new Paragraph("Esta celda deberia ser de tamaño normal!") );
            
            // Creamos una nueva celda
            Cell celdaGrande = new Cell();
                        
            celdaGrande.add( new Paragraph("Esta celda cambia el tamaño de toda la fila"));
            // indicamos el tamaño de esta celda
            celdaGrande.setHeight(100f);
            tablaExtendida.addCell(celdaGrande);
            
            doc.add(tablaExtendida);

            doc.close();

        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

}
