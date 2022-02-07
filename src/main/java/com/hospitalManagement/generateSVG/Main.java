package com.hospitalManagement.generateSVG;
//import java.awt.Rectangle;
//import java.awt.Graphics2D;
//import java.awt.Color;
//import java.io.Writer;
//import java.io.OutputStreamWriter;
//import java.io.IOException;
//
//import org.apache.batik.svggen.SVGGraphics2D;
//import org.apache.batik.dom.GenericDOMImplementation;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.DOMImplementation;
//
//public class Main {
//
//    public void paint(Graphics2D g2d) {
//        g2d.setPaint(Color.red);
//        g2d.fill(new Rectangle(10, 10, 100, 100));
//    }
//
//    public static void main(String[] args) throws IOException {
//
//        // Get a DOMImplementation.
//        DOMImplementation domImpl =
//                GenericDOMImplementation.getDOMImplementation();
//
//        // Create an instance of org.w3c.dom.Document.
//        String svgNS = "http://www.w3.org/2000/svg";
//        Document document = domImpl.createDocument(svgNS, "svg", null);
//
//        // Create an instance of the SVG Generator.
//        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
//
//        // Ask the test to render into the SVG Graphics2D implementation.
//        Main test = new Main();
//        test.paint(svgGenerator);
//
//        // Finally, stream out SVG to the standard output using
//        // UTF-8 encoding.
//        boolean useCSS = true; // we want to use CSS style attributes
//        Writer out = new OutputStreamWriter(System.out, "UTF-8");
//        svgGenerator.stream(out, useCSS);
//    }
//}
import java.awt.*;
import java.awt.geom.*;
import java.io.File;

import javax.swing.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.hospitalManagement.entity.Room;
import org.apache.batik.swing.*;
import org.apache.batik.svggen.*;
import org.apache.batik.anim.dom.SVGDOMImplementation;

import org.w3c.dom.*;
import org.w3c.dom.svg.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws TransformerException {
        //My List Rooms

        List<Room> roomList = new ArrayList<>();

        roomList.add(new Room(1L, null, 101, 1, null, true));
        roomList.add(new Room(12L, null, 102, 1, null, false));
        roomList.add(new Room(3L, null, 103, 1, null, false));
        roomList.add(new Room(4L, null, 104, 1, null, true));
        roomList.add(new Room(5L, null, 105, 1, null, false));
        roomList.add(new Room(6L, null, 106, 1, null, false));
        roomList.add(new Room(7L, null, 107, 1, null, false));
        // Create an SVG document.
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        SVGDocument doc = (SVGDocument) impl.createDocument(svgNS, "svg", null);

        // Create a converter for this document.
        SVGGraphics2D g = new SVGGraphics2D(doc);
        g.setSVGCanvasSize(new Dimension(1000, 1000));
        // Do some drawing.
        for (Room roomFigure: roomList) {

            Shape rectangle = new Rectangle2D.Double(0, 0, 100, 100);

            if (roomFigure.isBookingStatus()) {
                g.setPaint(Color.red);
            } else {
                g.setPaint(Color.green);

            }
            g.draw(rectangle);
            g.drawString(String.valueOf(roomFigure.getNumberRoom()) , 0, 115);
            g.fill(rectangle);
            g.translate(110, 0);
            //new Ellipse2D.Double(0, 0, 100, 100);
        }

//        g.setPaint(Color.red);
//        g.fill(circle);
//        g.translate(100, 0);
//        g.setPaint(Color.green);
//        g.fill(circle);
//        g.translate(100, 0);
//        g.setPaint(Color.blue);
//        g.fill(circle);
//        g.setSVGCanvasSize(new Dimension(600, 600));

        // Populate the document root with the generated SVG content.
        Element root = doc.getDocumentElement();
        g.getRoot(root);

        // Display the document.
        JSVGCanvas canvas = new JSVGCanvas();
        JFrame f = new JFrame();
        f.getContentPane().add(canvas);
        canvas.setSVGDocument(doc);
        System.out.println(doc);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(
                new File("/Users/iliu/IdeaProjects/HospitalManagement/src/main/java" +
                        "/com/hospitalManagement/generateSVG/output.svg"));
        Source input = new DOMSource(doc);
        transformer.transform(input, output);
        f.pack();
        f.setVisible(true);
    }
}
