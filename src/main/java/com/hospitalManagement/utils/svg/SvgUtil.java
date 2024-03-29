package com.hospitalManagement.utils.svg;

import com.hospitalManagement.entity.Room;
import com.hospitalManagement.utils.convertRooms.ConvertListToMapUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Class to generate SVG map
 */
@UtilityClass
@Slf4j
public class SvgUtil {
    private static final int WIDTH_RECTANGLE = 70;
    private static final int HEIGHT_RECTANGLE = 50;
    private static final int PADDING = 65;
    private static final int NEW_LINE = 110;
    private static final String PATH_TO_MAP = "src/main/resources/images/map.svg";
    private static final int SPACE = 10;
    private static final int SIZE_DIMENSION = 1500;

    /**
     * This method generates a svg map from the hospital rooms
     *
     * @param rooms rooms from which the map is generated
     * @return Array bytes
     * @throws TransformerException An exceptional condition that occurred during the transformation process
     * @throws IOException          Signals that an I/O exception of some sort has occurred
     */
    public byte[] svg(List<Room> rooms) {
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        SVGDocument doc = (SVGDocument) impl.createDocument(svgNS, "svg", null);
        SVGGraphics2D mapHospital = new SVGGraphics2D(doc);
        mapHospital.setSVGCanvasSize(new Dimension(SIZE_DIMENSION, SIZE_DIMENSION / 2));
        Map<Integer, List<Room>> floorToRooms = ConvertListToMapUtil.convert(rooms);
        draw(floorToRooms, mapHospital, rooms.get(0).getHospital().getName());
        Element root = doc.getDocumentElement();
        mapHospital.getRoot(root);
        byte[] map = new byte[0];
        try {
            map = mapToBytes(mapHospital, doc);
        } catch (TransformerException | IOException e) {
            log.error("Ops!", e);
        }
        return map;
    }

    /**
     * The method transforms the map into an Array bytes
     *
     * @param mapHospital SVGGraphics2D canvas
     * @param doc         SVGDocument
     * @return Array bytes
     * @throws TransformerException An exceptional condition that occurred during the transformation process
     * @throws IOException          Signals that an I/O exception of some sort has occurred
     */
    public byte[] mapToBytes(SVGGraphics2D mapHospital, SVGDocument doc) throws IOException, TransformerException {
        Writer out = new OutputStreamWriter(System.out, UTF_8);
        mapHospital.stream(out, true);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(new File(PATH_TO_MAP));
        Source input = new DOMSource(doc);
        transformer.transform(input, output);
        RandomAccessFile file = new RandomAccessFile(PATH_TO_MAP, "r");
        byte[] b = new byte[(int) file.length()];
        file.readFully(b);
        out.close();
        return b;
    }

    /**
     * The method that performs the rendering
     *
     * @param mapRooms     Converted list of rooms to map
     * @param map          SVGGraphics2D map where the image will be applied
     * @param hospitalName Hospital name
     */
    public void draw(Map<Integer, List<Room>> mapRooms, SVGGraphics2D map, String hospitalName) {
        for (Map.Entry<Integer, List<Room>> entry : mapRooms.entrySet()) {
            int newBlock = 0;
            int floor = entry.getKey();
            List<Room> rooms = entry.getValue();
            map.setPaint(Color.BLACK);
            map.drawString(String.format("%s floor: ", floor), SPACE, NEW_LINE * (floor - 1) + PADDING / 2 + 10);
            map.drawString("Room number: ", SPACE, NEW_LINE * (floor - 1) + PADDING / 2 + 42);
            map.drawString("Room type: ", SPACE, NEW_LINE * (floor - 1) + PADDING / 2 + 57);
            printHospitalRooms(map, newBlock, floor, rooms);
        }
        infoCol(map, Color.red, 100, SIZE_DIMENSION / 2 - 170, " - The room is occupied.",
                SIZE_DIMENSION / 2 - 155);
        infoCol(map, Color.green, 100, SIZE_DIMENSION / 2 - 210, " - The room is free.",
                SIZE_DIMENSION / 2 - 195);
        map.setPaint(Color.BLACK);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        map.drawString(String.format("Date of generation: %s", LocalDateTime.now().format(dateTimeFormatter)), 100,
                SIZE_DIMENSION / 2 - 100);
        map.drawString(String.format("Hospital name: %s", hospitalName), SIZE_DIMENSION - 300,
                SIZE_DIMENSION / 2 - 100);
    }

    /**
     * Method to print rooms
     *
     * @param map      SVGGraphics2D map where the image will be applied
     * @param newBlock Indent for new block
     * @param floor    Number floor
     * @param rooms    List of rooms that will be created
     */
    private void printHospitalRooms(SVGGraphics2D map, int newBlock, int floor, List<Room> rooms) {
        for (Room room : rooms) {
            if (room.isBookingStatus()) {
                map.setPaint(Color.red);
            }
            map.setPaint(Color.green);
            newBlock += 90;
            Shape rectangle = new Rectangle2D.Double(SPACE + newBlock, NEW_LINE * (floor - 1) + 10,
                    WIDTH_RECTANGLE, HEIGHT_RECTANGLE);
            map.drawString(String.valueOf(room.getNumberRoom()), SPACE + newBlock,
                    NEW_LINE * (floor - 1) + PADDING + 10);
            map.drawString(String.valueOf(room.getType()), SPACE + newBlock,
                    NEW_LINE * (floor - 1) + PADDING + 25);
            map.draw(rectangle);
            map.fill(rectangle);
        }
    }

    /**
     * Information rendering method
     *
     * @param map   SVGGraphics2D map where the image will be applied
     * @param color Block color
     * @param x     Coordinate
     * @param y     Coordinate
     * @param info  Information text
     * @param strY  Coordinate
     */
    public void infoCol(SVGGraphics2D map, Color color, int x, int y, String info, int strY) {
        map.setPaint(color);
        map.drawRect(x, y, 20, 20);
        map.drawString(info, 125, strY);
    }
}